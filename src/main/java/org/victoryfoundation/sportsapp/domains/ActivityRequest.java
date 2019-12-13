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

    public ActivityDetail getAction() {
        return action;
    }

    public void setAction(ActivityDetail action) {
        this.action = action;
    }

    public long getHubId() {
        return hubId;
    }

    public void setHubId(long hubId) {
        this.hubId = hubId;
    }

    public long getCoachId() {
        return coachId;
    }

    public void setCoachId(long coachId) {
        this.coachId = coachId;
    }
}
