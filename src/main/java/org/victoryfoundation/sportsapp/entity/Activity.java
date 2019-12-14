package org.victoryfoundation.sportsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;
    @Column(name = "created_on")
    private Long createdOn;
    @Column(name = "updated_on")
    private Long updatedOn;
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "coach_id")
    private Coach coach;
    /*@ManyToOne
    @JoinColumn(name = "hub_id", referencedColumnName = "hub_id")
    private Hub hub;*/
    @Column(name = "hub_id")
    private long hubId;
    @Column(name = "status")
    private String status;

    /*@OneToMany(mappedBy = "activity")
    @JsonManagedReference("activity")*/
    @Transient
    private List<ActivityDetail> detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public long getHubId() {
        return hubId;
    }

    public void setHubId(long hubId) {
        this.hubId = hubId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ActivityDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<ActivityDetail> detail) {
        this.detail = detail;
    }
}
