package org.victoryfoundation.sportsapp.domains;

public enum ActivityStatus {

	INPROGRESS("IN-PROGRESS"), REVIEW("REVIEW"), CLOSED("CLOSED");

	private final String status;

	private ActivityStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
