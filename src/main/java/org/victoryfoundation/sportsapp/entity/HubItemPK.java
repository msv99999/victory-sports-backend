package org.victoryfoundation.sportsapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HubItemPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "item_id", nullable = false) //, insertable = false, updatable = false, unique = true, nullable = false)
	private long itemId;

	@Column(name = "hub_id", nullable = false) //, insertable = false, updatable = false, unique = true, nullable = false)
	private long hubId;

	public HubItemPK() {
	}
	public HubItemPK(long hubId, long itemId) {
		this.hubId = hubId;
		this.itemId = itemId;
	}
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getHubId() {
		return hubId;
	}

	public void setHubId(long hubId) {
		this.hubId = hubId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (hubId ^ (hubId >>> 32));
		result = prime * result + (int) (itemId ^ (itemId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HubItemPK other = (HubItemPK) obj;
		if (hubId != other.hubId)
			return false;
		if (itemId != other.itemId)
			return false;
		return true;
	}
	
	

}