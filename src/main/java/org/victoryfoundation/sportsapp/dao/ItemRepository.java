package org.victoryfoundation.sportsapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.victoryfoundation.sportsapp.entity.Item;

@Repository //RestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends CrudRepository<Item, Long> {


}
