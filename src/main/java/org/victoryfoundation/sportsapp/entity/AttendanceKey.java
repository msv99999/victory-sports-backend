package org.victoryfoundation.sportsapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class AttendanceKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    private Activity activity;
}
