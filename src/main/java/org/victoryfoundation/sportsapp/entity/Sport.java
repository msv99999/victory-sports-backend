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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSports_name() {
        return sports_name;
    }

    public void setSports_name(String sports_name) {
        this.sports_name = sports_name;
    }
}
