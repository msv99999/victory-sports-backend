package org.victoryfoundation.sportsapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "ITEM")
public class Item {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private long itemId;

	@Column(nullable = false, length = 1)
	private String active;

	@Column(name = "created_on", nullable = false)
	private long createdOn;

	@Column(name = "item_name", nullable = false, length = 200)
	private String itemName;

	@Column(name = "updated_on", nullable = false)
	private long updatedOn;

	// bi-directional many-to-one association to HubItem
	@OneToMany(mappedBy = "item")
	@JsonManagedReference("item")
	private Set<HubItem> hubItems;

	public Item() {
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Set<HubItem> getHubItems() {
		return hubItems;
	}

	public void setHubItems(Set<HubItem> hubItems) {
		this.hubItems = hubItems;
	}

}