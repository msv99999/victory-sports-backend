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
}
