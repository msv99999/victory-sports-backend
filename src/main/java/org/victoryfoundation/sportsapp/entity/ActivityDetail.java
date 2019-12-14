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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getImage6() {
        return image6;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }
}
