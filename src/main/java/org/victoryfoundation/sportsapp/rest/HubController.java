package org.victoryfoundation.sportsapp.rest;

import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.victoryfoundation.sportsapp.dao.ItemRepository;
import org.victoryfoundation.sportsapp.entity.Hub;
import org.victoryfoundation.sportsapp.entity.HubItem;
import org.victoryfoundation.sportsapp.entity.HubItemPK;
import org.victoryfoundation.sportsapp.entity.Item;

@RestController
public class HubController {

	@Autowired
	private HubResource hubResource;

	@Autowired
	private ItemRepository itemResource;

	Logger logger = LoggerFactory.getLogger(HubController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/hub")
	public ResponseEntity<?> getHub() {
		return new ResponseEntity<>(hubResource.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hub/{hubId}")
	public ResponseEntity<?> getHubById(@PathVariable("hubId") Long hubId) {
		Hub hub = null;
		Optional<Hub> optionaluHub = hubResource.findById(hubId);
		if (optionaluHub.isPresent()) {
			hub = optionaluHub.get();
		} else {
			return new ResponseEntity<>("{\"error\":\"No Hub found\"}", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(hub, HttpStatus.OK);
	}

	@PostMapping("/hub/create")
	public ResponseEntity<?> createHub(@RequestBody Hub hub) {
		try {
			processHub(hub);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("{\"error\":\"Hub cannot be created\"}", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(hub, HttpStatus.CREATED);
	}

	@Transactional
	protected Hub processHub(Hub hub) throws Exception {
		Hub response = null;
		try {
			Set<HubItem> items = hub.getHubItems();
			hub.setHubItems(null);
			response = hubResource.save(hub);
			Long createdHubId = response.getHubId();
			logger.info("Generated Id" + createdHubId);
			if (null != items) {
				Set<HubItem> hubItemsToPersist = new HashSet<>();
				Iterator<HubItem> itr = items.iterator();
				while (itr.hasNext()) {
					HubItem hubItem = itr.next();
					Item item = hubItem.getItem();
					long itemId = item.getItemId();
					logger.info("itemId-->" + itemId);
					Optional<Item> dbItemOptional = itemResource.findById(itemId);
					logger.info("dbItem --> " + dbItemOptional.isPresent());
					if (dbItemOptional.isPresent()) {
						Item dbItem = dbItemOptional.get();

						long now = Instant.now().getEpochSecond();
						HubItemPK pk = new HubItemPK();
						pk.setHubId(createdHubId);
						pk.setItemId(dbItem.getItemId());

						HubItem hubItemToPersist = new HubItem();
						hubItemToPersist.setId(pk);
						hubItemToPersist.setHub(response);
						hubItemToPersist.setItem(dbItem);
						hubItemToPersist.setCount(hubItem.getCount());
						hubItemToPersist.setCreatedOn(now);
						hubItemToPersist.setUpdatedOn(now);
						hubItemsToPersist.add(hubItemToPersist);
					} else {
						logger.error("Item is not available");
						throw new Exception("Item is not available");
					}

				}
				response.setHubItems(hubItemsToPersist);
			}
/*=======
//			Set<HubItem> items = hub.getHubItems();
//			if (null != items) {
//				Set<HubItem> hubItemsToPersist = new HashSet<>();
//				Iterator<HubItem> itr = items.iterator();
//				while (itr.hasNext()) {
//					HubItem hubItem = itr.next();
//					long itemId = hubItem.getItemId();
//					logger.info("itemId-->" + itemId);
////					Optional<Item> dbItem = itemResource.findById(itemId);
////					logger.info("dbItem --> " + dbItem.isPresent());
////					if (dbItem.isPresent()) {
//						HubItem hubItemToPersist = new HubItem();
//						long now = Instant.now().getEpochSecond();
//						hubItemToPersist.setHubId(hub.getHubId());
//						hubItemToPersist.setItemId(itemId);
//						hubItemToPersist.setCount(hubItem.getCount());
//						hubItemToPersist.setCreatedOn(now);
//						hubItemToPersist.setUpdatedOn(now);
//						hubItemsToPersist.add(hubItemToPersist);
////					} else {
////						throw new Exception("Item is not available");
////					}
//
//				}
//				hub.setHubItems(hubItemsToPersist);
//			}*/
			long now = Instant.now().getEpochSecond();
			hub.setCreatedOn(now);
			hub.setUpdatedOn(now);
			response = hubResource.save(response);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return response;
	}

}
