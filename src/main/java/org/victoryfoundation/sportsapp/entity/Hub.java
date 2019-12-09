package org.victoryfoundation.sportsapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "HUB")
public class Hub {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hub_id", unique = true, nullable = false)
	private long hubId;

	@Column(name = "created_on", nullable = false)
	private long createdOn;

	@Column(name = "hub_address", nullable = false, length = 500)
	private String hubAddress;

	@Column(name = "hub_name", nullable = false, length = 100)
	private String hubName;

	@Column(name = "pin", nullable = false)
	private int pin;

	@Column(name = "supported_by", length = 100)
	private String supportedBy;

	@Column(name = "updated_on", nullable = false)
	private long updatedOn;

	// bi-directional many-to-one association to HubItem
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hub")
	@JsonManagedReference("hub")
	private Set<HubItem> hubItems;
	/*
	 * ======= // @OneToMany(mappedBy = "hub") // private Set<HubItem> hubItems;
	 * >>>>>>> 8682469d6554bc0043ef15fa2e80ffb4c65ceada
	 */

	public Hub() {
	}

	public long getHubId() {
		return hubId;
	}

	public void setHubId(long hubId) {
		this.hubId = hubId;
	}

	public String getHubAddress() {
		return this.hubAddress;
	}

	public void setHubAddress(String hubAddress) {
		this.hubAddress = hubAddress;
	}

	public String getHubName() {
		return this.hubName;
	}

	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	public int getPin() {
		return this.pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getSupportedBy() {
		return this.supportedBy;
	}

	public void setSupportedBy(String supportedBy) {
		this.supportedBy = supportedBy;
	}

	// public Set<HubItem> getHubItems() {
	// return this.hubItems;
	// }
	//
	// public void setHubItems(Set<HubItem> hubItems) {
	// this.hubItems = hubItems;
	// }
	//
	// public HubItem addHubItem(HubItem hubItem) {
	// getHubItems().add(hubItem);
	// hubItem.setHubId(this.hubId);
	//
	// return hubItem;
	// }

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
