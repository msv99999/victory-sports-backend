package org.victoryfoundation.sportsapp.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivityDetailDomain {

    private Long id;
    private String description;
    private String image1;
    private String image2;
    private String note1;
    private String note2;
    private String note3;
    private String image6;
    private double score;
    private Long createdOn;
    private Long updatedOn;
    private String actionType;
    private double defaultScore;

}
