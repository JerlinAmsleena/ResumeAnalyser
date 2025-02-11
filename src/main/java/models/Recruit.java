package models;

public class Recruit {
	int recruitId;
	String recruitName;
	Requirement requirement;
//	Str
	int maximumNumber;
	public Recruit(String recruitName, int maximumNumber) {
//		super();
		this.recruitName = recruitName;
		this.maximumNumber = maximumNumber;
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
				+ ", maximumNumber=" + maximumNumber + "]";
	}
	
	
}
