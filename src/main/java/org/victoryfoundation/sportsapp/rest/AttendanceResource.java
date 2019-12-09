package org.victoryfoundation.sportsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.victoryfoundation.sportsapp.dao.AttendanceRepostiory;
import org.victoryfoundation.sportsapp.domains.AttendanceRequest;
import org.victoryfoundation.sportsapp.entity.Activity;
import org.victoryfoundation.sportsapp.entity.Attendance;
import org.victoryfoundation.sportsapp.entity.AttendanceKey;
import org.victoryfoundation.sportsapp.entity.Student;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AttendanceResource {

    @Autowired
    private AttendanceRepostiory attendanceDao;

    @PostMapping("attendances")
    public List<Attendance> createAttendance(@RequestBody AttendanceRequest request) {
        List<Attendance> updatedAttendance = request.getStudentIds().stream().map(att -> {
            Attendance attendance = new Attendance();
            AttendanceKey key = new AttendanceKey();
            Activity activity = new Activity();
            activity.setId(request.getActivityId());
            Student student = new Student();
            student.setStudentId(att);
            key.setActivity(activity);
            key.setStudent(student);
            attendance.setId(key);
            long now = Instant.now().getEpochSecond();
            attendance.setCreatedOn(now);
            attendance.setUpdatedOn(now);
            return attendance;
        }).collect(Collectors.toList());
        return attendanceDao.saveAll(updatedAttendance);
    }
}
