package org.victoryfoundation.sportsapp.entity;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.hibernate.annotations.CascadeType.*;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name =  "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String username;

    @Column(name = "user_name")
    private String name;

    @Column(name = "createdOn")
    private long createdOn;

    @Column(name = "updatedOn")
    private long updatedOn;

    @ManyToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
    private UserType role;

    @Column(name = "status")
    private String status = "A";

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
        Optional<Coach> optionalCoach= Optional.of(coach);
        if(optionalCoach.isPresent()){
            optionalCoach.get().setCreatedon(Instant.now().toEpochMilli());
            optionalCoach.get().setUpdated(Instant.now().toEpochMilli());
        }

    }

    @OneToOne(cascade = {CascadeType.ALL},optional = true)
    @JoinColumn(name="coach_id",referencedColumnName = "coach_id", unique= true, nullable=true, insertable=true, updatable=true)
    private Coach coach;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
