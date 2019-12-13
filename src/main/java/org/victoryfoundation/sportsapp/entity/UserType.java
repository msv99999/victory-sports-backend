package org.victoryfoundation.sportsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserType {
  @Id
  @Column(name = "user_type_id")
    public Long id;

  @Column(name="type")
    public String type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
