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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public long getCreatedon() {
        return createdon;
    }

    public void setCreatedon(long createdon) {
        this.createdon = createdon;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getId_proof() {
        return id_proof;
    }

    public void setId_proof(String id_proof) {
        this.id_proof = id_proof;
    }

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }
}
