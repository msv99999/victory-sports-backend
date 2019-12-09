package org.victoryfoundation.sportsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Action_Type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionType {

    @Id
    @Column(name = "action_name")
    private String name;
    @Column(name = "default_score")
    private double defaultScore;

}
