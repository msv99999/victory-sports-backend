package org.victoryfoundation.sportsapp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.victoryfoundation.sportsapp.dao.GradeRepository;
import org.victoryfoundation.sportsapp.domains.CoachScore;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class GradeResource {

    @Autowired
    private GradeRepository gradeDao;

    Logger logger = LoggerFactory.getLogger(ActivityDetailResource.class);

    @GetMapping("/scores")
    public ResponseEntity<Object> getScore(@RequestParam(name = "startTime", required = false) Long startTime, @RequestParam(name = "endTime", required = false) Long endTime) {
        LocalDateTime now = LocalDateTime.now();
        long nowInUTC = now.toEpochSecond(ZoneOffset.UTC);
        long start, end;
        if (startTime == null && endTime == null) {
            start = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).toEpochSecond(ZoneOffset.UTC);
            end = nowInUTC;
        } else if (startTime == null) {
            if (endTime > nowInUTC) {
                logger.error("Future date provided for endTime");
                return new ResponseEntity<>("endTime cannot be in the past", HttpStatus.BAD_REQUEST);
            }
            start = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).toEpochSecond(ZoneOffset.UTC);
            end = endTime;
        } else if (endTime == null) {
            logger.error("Future date provided for startTime");
            if (startTime > nowInUTC) {
                return new ResponseEntity<>("startTime cannot be in the past", HttpStatus.BAD_REQUEST);
            }
            start = startTime;
            end = nowInUTC;
        } else {
            //Both are not null
            start = startTime;
            end = endTime;
        }

        logger.info(String.format("Querying grade for duration from %s to %s", start, end));

        List<CoachScore> scores = gradeDao.getScore(start, end);
        logger.info("retrieved scores count - " + scores.size());
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }
}
