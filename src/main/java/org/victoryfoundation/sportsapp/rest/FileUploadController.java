package org.victoryfoundation.sportsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.victoryfoundation.sportsapp.domains.ActivityDetailModel;
import org.victoryfoundation.sportsapp.domains.ActivityRequest;
import org.victoryfoundation.sportsapp.domains.FileUploadResponse;
import org.victoryfoundation.sportsapp.entity.ActionType;
import org.victoryfoundation.sportsapp.entity.Activity;
import org.victoryfoundation.sportsapp.entity.ActivityDetail;
import org.victoryfoundation.sportsapp.service.AmazonClient;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.UUID;

@RestController
@RequestMapping("/file/")
public class FileUploadController {
    @Autowired
    private AmazonClient amazonClient;
    @Autowired
    private ActivityDetailResource activityDetailResource;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam("type") String type, @RequestParam("id") String id) {
        try {
            String url = this.amazonClient.uploadFile(file, type, id);
            FileUploadResponse response = new FileUploadResponse(url);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("There was an unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/activitydetail")
    @Transactional
    public ResponseEntity<?> createActivityDetail(@RequestPart(value = "image1", required = false) MultipartFile image1, @RequestPart(value = "image2", required = false) MultipartFile image2,
            /*@ModelAttribute ActivityDetailModel model,*/ @RequestHeader HttpHeaders headers, HttpServletRequest req
            , WebRequest webReq,
                                                  @RequestParam("coach_id") long coachId, @RequestParam("hub_id") long hubId, @RequestParam("action_type") String actionType,
                                                  @RequestParam(value = "description", required = false) String description, @RequestParam(value = "activity_id", required = false) Long activityId,
                                                  @RequestParam(value = "note1", required = false) String note1, @RequestParam(value = "note2", required = false) String note2,
                                                  @RequestParam(value = "note3", required = false) String note3) {
        System.out.println(req.getParameter("coachId"));
        System.out.println(webReq.getParameter("coachId"));
        System.out.println(headers);

        ActivityRequest request = new ActivityRequest();
        request.setCoachId(coachId);
        request.setHubId(hubId);
        ActivityDetail detail = new ActivityDetail();
        ActionType type = new ActionType();
        type.setName(actionType);
        detail.setType(type);
        detail.setDescription(description);
        detail.setNote1(note1);
        detail.setNote2(note2);
        detail.setNote3(note3);
        if (activityId != null) {
            try {
                if (image1 != null) {
                    detail.setImage1(this.amazonClient.uploadFile(image1, "activity", activityId + "1"));
                    System.out.println("Uploaded image1");
                }
                if (image2 != null) {
                    detail.setImage1(this.amazonClient.uploadFile(image2, "activity", activityId + "2"));
                    System.out.println("Uploaded image2");
                }
                Activity activity = new Activity();
                activity.setId(activityId);
                detail.setActivity(activity);
            } catch (Exception e) {
                return new ResponseEntity<>("There was an unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);

            }
            request.setAction(detail);
            return activityDetailResource.create(request);
        }
        request.setAction(detail);
        return activityDetailResource.create(request);
    }


}
