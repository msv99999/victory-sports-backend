package org.victoryfoundation.sportsapp.rest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.victoryfoundation.sportsapp.dao.ActionTypeRepo;
import org.victoryfoundation.sportsapp.dao.ActivityDetailRepository;
import org.victoryfoundation.sportsapp.dao.ActivityRepository;
import org.victoryfoundation.sportsapp.domains.ActionType;
import org.victoryfoundation.sportsapp.domains.ActivityDetailModel;
import org.victoryfoundation.sportsapp.domains.ActivityRequest;
import org.victoryfoundation.sportsapp.domains.ActivityStatus;
import org.victoryfoundation.sportsapp.entity.Activity;
import org.victoryfoundation.sportsapp.entity.ActivityDetail;
import org.victoryfoundation.sportsapp.entity.Coach;
import org.victoryfoundation.sportsapp.service.AmazonClient;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ActivityDetailResource {

    @Autowired
    private ActivityRepository activityDao;

    @Autowired
    private ActivityDetailRepository activityDetailDao;

    @Autowired
    private ActionTypeRepo actionTypeRepo;

    @Autowired
    private AmazonClient s3Client;

    Logger logger = LoggerFactory.getLogger(ActivityDetailResource.class);

    @PostMapping("/actions")
    @Transactional
    public ResponseEntity<Object> create(@RequestBody ActivityRequest request) {
        System.out.println(request);
        ActivityDetail action = request.getAction();
        if (ActionType.CHECK_IN.getType().equalsIgnoreCase(action.getType().getName())) {
            List<Activity> activityByCoach = activityDao.findActiveActivityByCoach(request.getCoachId());
            if (activityByCoach.size() > 0) {
                return new ResponseEntity<>("There is already an active activity for Coach", HttpStatus.CONFLICT);
            }
            Activity activity = activityDao.save(createActivity(request));
            logger.info("Activity id - " + activity.getId());
            action.setActivity(activity);
        } else {
            Activity activity = activityDao.getOne(action.getActivity().getId());
            org.victoryfoundation.sportsapp.entity.ActionType actionType = actionTypeRepo
                    .findByName(action.getType().getName());
            ActivityDetail detail = activityDetailDao.findByActivityAndType(activity, actionType);
            if (detail != null) {
                if (ActionType.CHECK_IN.getType().equalsIgnoreCase(action.getType().getName())) {
                    return new ResponseEntity<>("Activity type already available for activity id", HttpStatus.CONFLICT);
                } else {
                    //Edit activity
                    Optional.ofNullable(action.getDescription()).ifPresent(desc -> detail.setDescription(desc));
                    Optional.ofNullable(action.getNote1()).ifPresent(note1 -> detail.setNote1(note1));
                    Optional.ofNullable(action.getNote2()).ifPresent(note2 -> detail.setNote1(note2));
                    Optional.ofNullable(action.getNote3()).ifPresent(note3 -> detail.setNote1(note3));
                    Optional.ofNullable(action.getImage1()).ifPresent(image1 -> {
                        deleteOldFile(detail.getImage1());
                        detail.setImage1(image1);
                    });
                    Optional.ofNullable(action.getImage2()).ifPresent(image2 -> {
                        deleteOldFile(detail.getImage2());
                        detail.setImage2(image2);
                    });
                    Optional.ofNullable(action.getImage6()).ifPresent(image6 -> {
                        deleteOldFile(detail.getImage6());
                        detail.setImage6(image6);
                    });
                }

            }
        }

        System.out.println("Activity detail - " + action);

        long now = Instant.now().getEpochSecond();
        action.setCreatedOn(now);
        action.setUpdatedOn(now);

        ActivityDetail savedEntity = activityDetailDao.save(action);
        if (ActionType.CHECK_OUT.getType().equalsIgnoreCase(action.getType().getName())) {
            activityDao.updateStatus(org.victoryfoundation.sportsapp.domains.ActivityStatus.REVIEW.getStatus(),
                    action.getActivity().getId());
        }

        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    private void deleteOldFile(String fileName) {
        if (StringUtils.isNotBlank(fileName)) {
            String oldImage = fileName.split("/")[2];
            logger.info("Deleting file - " + oldImage);
            s3Client.deleteFile(oldImage);
        }
    }

    private Activity createActivity(ActivityRequest request) {
        Activity activity = new Activity();
        Coach coach = new Coach();
        coach.setId(request.getCoachId());
        activity.setCoach(coach);
        // Hub hub = new Hub();
        // hub.setHubId(request.getHubId());
        // activity.setHub(hub);
        activity.setHubId(request.getHubId());
        long now = Instant.now().getEpochSecond();
        activity.setCreatedOn(now);
        activity.setUpdatedOn(now);
        activity.setStatus(org.victoryfoundation.sportsapp.domains.ActivityStatus.INPROGRESS.getStatus());
        return activity;
    }

    @RequestMapping(method = RequestMethod.POST, value = "activities/{activityId}/updatescore")
    public ResponseEntity<Object> updateScore(@RequestBody List<ActivityDetailModel> request,
                                              @PathVariable("activityId") Long activityId) {
        logger.info("updateScore JSON");
        logger.info(request.toString());
        try {
            processUpdateScore(request, activityId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("{\"error\":\"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @Transactional
    private void processUpdateScore(List<ActivityDetailModel> request, Long activityId) throws Exception {
        Optional<Activity> activityEntity = activityDao.findById(activityId);
        if (activityEntity.isPresent()) {
            Activity activityTobePersisited = activityEntity.get();
            List<ActivityDetail> activityDetails = new ArrayList<>();
            for (ActivityDetailModel model : request) {
                ActivityDetail activityDetailModel = null;
                Optional<ActivityDetail> optionalHub = activityDetailDao.findById(model.getActivityDetailId());
                if (optionalHub.isPresent()) {
                    activityDetailModel = optionalHub.get();
                    activityDetailModel.setScore(model.getScore());
                    long now = Instant.now().getEpochSecond();
                    activityDetailModel.setCreatedOn(now);
                    activityDetailModel.setUpdatedOn(now);
                    activityDetails.add(activityDetailModel);
                } else {
                    throw new Exception("ActivityDetail is not available");
                }
            }

            activityDetailDao.saveAll(activityDetails);
            activityTobePersisited.setStatus(ActivityStatus.CLOSED.getStatus());
            activityDao.save(activityTobePersisited);
        } else {
            throw new Exception("Activity is not available");
        }

    }

}
