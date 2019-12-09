package org.victoryfoundation.sportsapp.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.victoryfoundation.sportsapp.entity.ActivityDetail;

@Data
@NoArgsConstructor
public class ActivityRequest {

    private ActivityDetail action;
    private long hubId;
    private long coachId;

}
