package org.victoryfoundation.sportsapp.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ActivityDomain {
    private Long id;
    private Long createdOn;
    private Long updatedOn;
    private Long coachId;
    private long hubId;
    private String status;
    private String coachName;
    private String hubName;
    private String coachImage;

    private List<ActivityDetailDomain> detail;
}
