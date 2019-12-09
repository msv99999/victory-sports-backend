package org.victoryfoundation.sportsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import javax.persistence.*;

@Entity(name = "sport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sport {

    @Id
    @Column(name =  "sports_id")
    private Long id;

    @Column(name = "active")
    private String active = "Y";

    @Column(name="sports_name")
    private String sports_name;

}
