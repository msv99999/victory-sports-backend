package org.victoryfoundation.sportsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.victoryfoundation.sportsapp.entity.ActionType;
import org.victoryfoundation.sportsapp.entity.Activity;
import org.victoryfoundation.sportsapp.entity.ActivityDetail;

import java.util.List;

public interface ActivityDetailRepository extends JpaRepository<ActivityDetail, Long> {

//    @Query("from ActivityDetailDomain  where type.name = :actionType and activity.id = :activityId")
//    ActivityDetailDomain findByActivityAndType(@Param("actionType") String actionType, @Param("activityId") Long activityId);
	
    //@Query("from ActivityDetailDomain  where type.name = :actionType and activity.id = :activityId")
	//ActivityDetailDomain findByActionTypeAndActivity(@Param("actionType") String actionType, @Param("activityId") Long activityId);
    ActivityDetail findByActivityAndType(Activity activity, ActionType actiontype);

    List<ActivityDetail> findByActivity(Activity activity);
}
