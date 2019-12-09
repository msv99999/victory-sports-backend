package org.victoryfoundation.sportsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.victoryfoundation.sportsapp.entity.Coach;
import org.victoryfoundation.sportsapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long > {

    User findById(long id);
    User findByUsername(String username);
    User findByCoach_Id(Long id);
}
