package org.victoryfoundation.sportsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.victoryfoundation.sportsapp.dao.ActivityDetailRepository;
import org.victoryfoundation.sportsapp.dao.ActivityRepository;
import org.victoryfoundation.sportsapp.dao.UserRepository;
import org.victoryfoundation.sportsapp.domains.ActivityDetailDomain;
import org.victoryfoundation.sportsapp.domains.ActivityDomain;
import org.victoryfoundation.sportsapp.domains.ActivityStatus;
import org.victoryfoundation.sportsapp.domains.ReviewActivityResponse;
import org.victoryfoundation.sportsapp.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ActivityResource {

    @Autowired
    private ActivityRepository activityDao;

    @Autowired
    private ActivityDetailRepository activityDetailDao;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private HubResource hubDao;

    @GetMapping("/activities/{coachId}/active")
    public ActivityDomain findOpenActivityByCoach(@PathVariable long coachId) {
        List<Activity> activity = activityDao.findActiveActivityByCoach(coachId);
        System.out.println(activity);
        if (activity != null && activity.size() > 0) {
            Activity act = activity.get(0);
            ActivityDomain domain = getActivityDomain(act);
            return domain;
        }
        return null;
    }

    private ActivityDomain getActivityDomain(Activity act) {
        List<ActivityDetail> details = activityDetailDao.findByActivity(act);
        Hub hub = hubDao.findById(act.getHubId()).get();
        User user = userDao.findByCoach_Id(act.getCoach().getId());
        Coach coach = user.getCoach();
        ActivityDomain domain = new ActivityDomain();
        domain.setId(act.getId());
        domain.setCoachId(act.getCoach().getId());
        domain.setCreatedOn(act.getCreatedOn());
        domain.setUpdatedOn(act.getUpdatedOn());
        domain.setHubId(act.getHubId());
        domain.setStatus(act.getStatus());
        domain.setHubName(hub.getHubName());
        domain.setCoachName(coach.getName());
        domain.setCoachImage(coach.getImage());

        List<ActivityDetailDomain> collect = details.stream().map(ad -> {
            ActivityDetailDomain detail = new ActivityDetailDomain();
            detail.setActionType(ad.getType().getName());
            detail.setCreatedOn(ad.getCreatedOn());
            detail.setDescription(ad.getDescription());
            detail.setUpdatedOn(ad.getUpdatedOn());
            detail.setImage1(ad.getImage1());
            detail.setImage2(ad.getImage2());
            detail.setImage6(ad.getImage6());
            detail.setNote1(ad.getNote1());
            detail.setNote2(ad.getNote2());
            detail.setNote3(ad.getNote3());
            detail.setId(ad.getId());
            detail.setScore(ad.getScore());
            detail.setDefaultScore(ad.getType().getDefaultScore());
            return detail;
        }).collect(Collectors.toList());
        domain.setDetail(collect);
        return domain;
    }

    @GetMapping("/activities/{startTime}/{endTime}/review")
    public ReviewActivityResponse fetchActivityForReview(@PathVariable(value = "startTime") long startTime, @PathVariable(value = "endTime") long endTime) {
        ReviewActivityResponse response = new ReviewActivityResponse();
        List<Activity> reviewActivities = activityDao.findActivitiesForReview(startTime, endTime, ActivityStatus.REVIEW.getStatus());
        if (reviewActivities != null && reviewActivities.size() > 0) {
            List<ActivityDomain> activities = reviewActivities.stream().map(activity -> getActivityDomain(activity)).collect(Collectors.toList());
            response.setActivity(activities);
        }
        return response;
    }

    @GetMapping("/activities/{startTime}/review")
    public ReviewActivityResponse fetchActivityForReview(@PathVariable(value = "startTime") long startTime) {
        ReviewActivityResponse response = new ReviewActivityResponse();
        List<Activity> reviewActivities = activityDao.findActivitiesForReview(startTime, startTime + 86400, ActivityStatus.REVIEW.getStatus());
        System.out.println(reviewActivities);
        if (reviewActivities != null && reviewActivities.size() > 0) {
            List<ActivityDomain> activities = reviewActivities.stream().map(activity -> getActivityDomain(activity)).collect(Collectors.toList());
            response.setActivity(activities);
        }
        return response;
    }

}
