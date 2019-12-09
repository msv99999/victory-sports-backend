package org.victoryfoundation.sportsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.victoryfoundation.sportsapp.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from student s where s.hubId.hubId = :hubId")
    List<Student> findByHubId(@Param("hubId") Long hubId);
}
