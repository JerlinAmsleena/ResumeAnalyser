package models;

import java.time.LocalDateTime;

public class Recruit {
	int recruitId;
	String recruitName;
	Requirement requirement;
	int maximumNumber;
//	String createdAt;
	LocalDateTime createdAt;
	
	public Recruit(String recruitName, int maximumNumber) {
//		super();
		this.recruitName = recruitName;
		this.maximumNumber = maximumNumber;
	}
	
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public int getRecruitId() {
		return recruitId;
	}
	public void setRecruitId(int recruitId) {
		this.recruitId = recruitId;
	}
	public String getRecruitName() {
		return recruitName;
	}
	public void setRecruitName(String recruitName) {
		this.recruitName = recruitName;
	}
	public Requirement getRequirement() {
		return requirement;
	}
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	public int getMaximumNumber() {
		return maximumNumber;
	}
	public void setMaximumNumber(int maximumNumber) {
		this.maximumNumber = maximumNumber;
	}



	@Override
	public String toString() {
		return "Recruit [recruitId=" + recruitId + ", recruitName=" + recruitName + ", requirement=" + requirement
				+ ", maximumNumber=" + maximumNumber + ", createdAt=" + createdAt + "]";
	}

	
	
}
