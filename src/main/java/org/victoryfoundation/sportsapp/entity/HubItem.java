package org.victoryfoundation.sportsapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the hub_item database table.
 * 
 */
@Entity(name = "HUB_ITEM")
public class HubItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HubItemPK id;

	@Column(nullable = false, length = 1)
	private String active="Y";

	@Column(nullable = false)
	private int count;

	@Column(name = "created_on")
	private long createdOn;

	@Column(name = "updated_on")
	private long updatedOn;

	// bi-directional many-to-one association to Hub
	@ManyToOne
	@JoinColumn(name = "hub_id", nullable = false, insertable = false, updatable = false)
	@JsonBackReference("hub")
	private Hub hub;

	// bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false, insertable = false, updatable = false)
	@JsonBackReference("item")
	private Item item;

	/*private Long hubId;
	private Long itemId;*/

	public HubItem() {
	}

	public HubItemPK getId() {
		return this.id;
	}

	public void setId(HubItemPK id) {
		this.id = id;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}



	/*public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}*/

	public long getCreatedOn() {
		return createdOn;
	}

	public long getUpdatedOn() {
		return updatedOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public void setUpdatedOn(long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Hub getHub() {
		return hub;
	}

	public void setHub(Hub hub) {
		this.hub = hub;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}