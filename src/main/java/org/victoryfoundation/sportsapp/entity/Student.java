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

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Hub getHubId() {
        return hubId;
    }

    public void setHubId(Hub hubId) {
        this.hubId = hubId;
    }

    public Sport getSportId() {
        return sportId;
    }

    public void setSportId(Sport sportId) {
        this.sportId = sportId;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getJersySize() {
        return jersySize;
    }

    public void setJersySize(String jersySize) {
        this.jersySize = jersySize;
    }

    public Long getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(Long shoeSize) {
        this.shoeSize = shoeSize;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public Long getFatherIncome() {
        return fatherIncome;
    }

    public void setFatherIncome(Long fatherIncome) {
        this.fatherIncome = fatherIncome;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMotherIncome() {
        return motherIncome;
    }

    public void setMotherIncome(String motherIncome) {
        this.motherIncome = motherIncome;
    }

    public Long getSibblings() {
        return sibblings;
    }

    public void setSibblings(Long sibblings) {
        this.sibblings = sibblings;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfessionalImage() {
        return professionalImage;
    }

    public void setProfessionalImage(String professionalImage) {
        this.professionalImage = professionalImage;
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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}