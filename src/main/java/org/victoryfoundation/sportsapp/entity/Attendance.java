package org.victoryfoundation.sportsapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "Attendance")
@Data
@NoArgsConstructor
public class Attendance {

    @EmbeddedId
    private AttendanceKey id;
    @Column(name = "created_on")
    private Long createdOn;
    @Column(name = "updated_on")
    private Long updatedOn;

    public AttendanceKey getId() {
        return id;
    }

    public void setId(AttendanceKey id) {
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
}
