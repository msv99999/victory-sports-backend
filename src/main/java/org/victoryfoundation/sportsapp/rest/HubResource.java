package org.victoryfoundation.sportsapp.rest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.victoryfoundation.sportsapp.entity.Hub;

@RepositoryRestResource(collectionResourceRel = "hub", path = "hub")
public interface HubResource extends CrudRepository<Hub, Long> {

	List<Hub> findByHubId(@Param("id") String hubId);
	
	List<Hub> findByHubName(@Param("hubName") String hubName);

}
