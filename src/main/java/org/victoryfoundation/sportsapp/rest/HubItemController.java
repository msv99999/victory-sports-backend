package org.victoryfoundation.sportsapp.rest;

import java.time.Instant;
import java.util.List;

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
import org.victoryfoundation.sportsapp.dao.HubItemRepository;
import org.victoryfoundation.sportsapp.entity.HubItem;
import org.victoryfoundation.sportsapp.entity.HubItemPK;
import org.victoryfoundation.sportsapp.model.HubItemModel;

@RestController
public class HubItemController {

	@Autowired
	private HubItemRepository hubItemResource;

	@Autowired
	private HubResource hubResource;

	Logger logger = LoggerFactory.getLogger(HubItemController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/hubitem/{hubId}")
	public ResponseEntity<?> getHubItem(@PathVariable("hubId") Long hubId) {

		ResponseEntity response = null;
		List<HubItem> optionaluHubItem = hubItemResource.findByIdHubId(hubId);
		if (null == optionaluHubItem || optionaluHubItem.size() > 0) {
			response = new ResponseEntity<>(optionaluHubItem, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>("{\"error\":\"No Item for the hub id: " + hubId+"\"}", HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/hubitem")
	public ResponseEntity<?> createItem(@RequestBody HubItemModel hubItem) {
		HubItem responseItem = null;
		try {
			responseItem = processHubItem(hubItem);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(responseItem, HttpStatus.CREATED);
	}

	@Transactional
	protected HubItem processHubItem(HubItemModel hubItemModel) throws Exception {
		HubItem response=null;
		try {
			HubItem hubItem = new HubItem();
			long now = Instant.now().getEpochSecond();
			hubItem.setId(new HubItemPK(hubItemModel.getHubId(), hubItemModel.getItemId()));
			hubItem.setCount(hubItemModel.getCount());
			hubItem.setCreatedOn(now);
			hubItem.setUpdatedOn(now);
			hubItem.setActive("Y");
			response = hubItemResource.save(hubItem);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return response;
	}

}
