package org.victoryfoundation.sportsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.victoryfoundation.sportsapp.entity.ActionType;
import org.victoryfoundation.sportsapp.entity.User;

public interface ActionTypeRepo extends JpaRepository<ActionType, String> {
	ActionType findByName(String name);
}
