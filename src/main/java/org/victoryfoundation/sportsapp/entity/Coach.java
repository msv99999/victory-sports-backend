package org.victoryfoundation.sportsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "coach")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach implements Serializable
{
    @Id
    @Column(name = "coach_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "education_detail_doc")
    private String education;

    @Column(name = "profile_image")
    private String image;

    @Column(name = "about")
    private String about;

    @Column(name = "created_on")
    private long createdon;

    @Column(name = "updated_on")
    private long updated;
    
    @Column(name = "experience_detail_doc")
    private String experience;

    @Column(name = "coach_name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="zipcode")
    private String zipcode;

    @Column(name = "active")
    private String active = "Y";

    @ManyToOne
    @JoinColumn(name = "sports_id", referencedColumnName = "sports_id")
    private Sport sport;

    @Column(name="document")
    private String id_proof;

    @Column(name="dob")
    private long dob;
}
