package org.victoryfoundation.sportsapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.victoryfoundation.sportsapp.entity.Hub;
import org.victoryfoundation.sportsapp.entity.HubItem;
import org.victoryfoundation.sportsapp.entity.HubItemPK;

@Repository //RestResource(collectionResourceRel = "hubitem", path = "hubitem")
public interface HubItemRepository extends CrudRepository<HubItem, HubItemPK> {

	//List<HubItem> findByHubId(Long hubId); //itemPKHubId
	
	List<HubItem> findByIdHubId(Long hubId); //itemPKHubId
}
