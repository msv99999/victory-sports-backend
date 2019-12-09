package org.victoryfoundation.sportsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "student_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentStatus {

    @Id
    @Column(name = "status")
    private String status;
}
