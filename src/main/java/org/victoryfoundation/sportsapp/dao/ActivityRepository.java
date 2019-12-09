package org.victoryfoundation.sportsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.victoryfoundation.sportsapp.entity.Activity;

import java.util.List;



public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("select a from Activity a where a.coach.id = :coachId and a.status = 'IN-PROGRESS'")
    List<Activity> findActiveActivityByCoach(Long coachId);

    @Modifying(clearAutomatically = true)
    @Query("update Activity set status = :status where id = :id")
    void updateStatus(String status, Long id);

    @Query("select a from Activity a where a.createdOn >= :startTime and a.createdOn <= :endTime and status = :status")
    List<Activity> findActivitiesForReview(long startTime, long endTime, String status);

}
