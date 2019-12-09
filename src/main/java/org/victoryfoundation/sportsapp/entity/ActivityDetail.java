package org.victoryfoundation.sportsapp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Activity_Detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_detail_id")
    private Long id;
    @Column(name = "detail1")
    private String description;
    @Column(name = "detail2")
    private String image1;
    @Column(name = "detail3")
    private String image2;
    @Column(name = "detail4")
    private String note1;
    @Column(name = "detail5")
    private String note2;
    @Column(name = "detail6")
    private String note3;
    @Column(name = "detail7")
    private String image6;
    @Column(name = "score")
    private double score;
    @Column(name = "created_on")
    private Long createdOn;
    @Column(name = "updated_on")
    private Long updatedOn;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    //@JsonBackReference("activity")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "action_name", referencedColumnName = "action_name")
    private ActionType type;
}
