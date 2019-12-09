package org.victoryfoundation.sportsapp.rest;

import java.time.Instant;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.victoryfoundation.sportsapp.dao.ItemRepository;
import org.victoryfoundation.sportsapp.entity.Item;

@RestController
public class ItemController {

	@Autowired
	private ItemRepository itemResource;

	Logger logger = LoggerFactory.getLogger(ItemController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/item")
	public ResponseEntity<?> getHub() {
		return new ResponseEntity<>(itemResource.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/item/{itemId}")
	public ResponseEntity<?> getHub(@PathVariable("itemId") Long itemId) {
		Item item = null;
		Optional<Item> optionaluItem = itemResource.findById(itemId);
		if (optionaluItem.isPresent()) {
			item = optionaluItem.get();
		} else {
			return new ResponseEntity<>(item, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/item")
	public ResponseEntity<?> createItem(@RequestBody Item item) {
		Item responseItem = null;
		try {
			responseItem = processItem(item);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(responseItem, HttpStatus.CREATED);
	}

	@Transactional
	private Item processItem(Item item) throws Exception {
		Item response = null;
		try {

			long now = Instant.now().getEpochSecond();
			item.setCreatedOn(now);
			item.setUpdatedOn(now);
			response = itemResource.save(item);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return response;
	}

}
