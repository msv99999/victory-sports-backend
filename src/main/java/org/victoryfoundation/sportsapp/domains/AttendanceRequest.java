package org.victoryfoundation.sportsapp.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.victoryfoundation.sportsapp.entity.Attendance;

import java.util.List;

@Data
@NoArgsConstructor
public class AttendanceRequest {
    private Long activityId;
    private List<Long> studentIds;
}
