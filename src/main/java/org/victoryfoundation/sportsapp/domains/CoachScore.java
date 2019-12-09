package org.victoryfoundation.sportsapp.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoachScore {
    private String coachName;
    private String coachImage;
    private double score;
}
