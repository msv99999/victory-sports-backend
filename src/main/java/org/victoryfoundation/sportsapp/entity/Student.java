package org.victoryfoundation.sportsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long studentId;

    @Column(name = "student_name")
    private String studentName;

    @ManyToOne
    @JoinColumn(name = "hub_id", referencedColumnName = "hub_id")
    private Hub hubId;

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "sports_id")
    private Sport sportId;

    @Column(name = "active")
    private char active;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "status")
    private StudentStatus status;

    @Column(name = "dob")
    @Type(type="date")
    private Date dob;

    @Column(name = "aadhar")
    private String aadhar;

    @Column(name = "educational_institution")
    private String educationalInstitution;

    @Column(name = "cource")
    private String course;

    @Column(name = "year")
    private Long year;

    @Column(name = "jercy_size")
    private String jersySize;

    @Column(name = "shoe_size")
    private Long shoeSize;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "address")
    private String address;

    @Column(name = "land_mark")
    private String landMark;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_occupation")
    private String fatherOccupation;

    @Column(name = "father_income")
    private Long fatherIncome;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_occupation")
    private String motherOccupation;

    @Column(name = "mother_income")
    private String motherIncome;

    @Column(name = "siblings")
    private Long sibblings;

    @Column(name = "post_office")
    private String postOffice;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "professional_image")
    private String professionalImage;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "updated_on")
    private Long updatedOn;

    @Column(name = "height")
    private Double height;

    @Column(name = "medical_record")
    private String medicalRecord;

    @PrePersist
    protected void onCreate() {
        long now = Instant.now().getEpochSecond();
        if (createdOn == null || createdOn == 0) { createdOn = now; }
        updatedOn = now;
    }

}