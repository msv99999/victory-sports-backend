package org.victoryfoundation.sportsapp.domains;

public class ActivityDetailModel {

	private Long activityDetailId;

	private Double score;

	public Long getActivityDetailId() {
		return activityDetailId;
	}

	public void setActivityDetailId(Long activityDetailId) {
		this.activityDetailId = activityDetailId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ActivityDetailModel [activityDetailId=" + activityDetailId + ", score=" + score + "]";
	}

}
