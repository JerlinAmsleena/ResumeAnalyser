package models;

public class Resume {
	String resumeId;
	String candidateName;
	String contactNumber;
	String education;
	String description;
	String linkedInDetails;
	public Resume(String candidateName, String contactNumber, String education, String description) {
		
		this.candidateName = candidateName;
		this.contactNumber = contactNumber;
		this.education = education;
		this.description = description;
	}
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLinkedInDetails() {
		return linkedInDetails;
	}
	public void setLinkedInDetails(String linkedInDetails) {
		this.linkedInDetails = linkedInDetails;
	}
	@Override
	public String toString() {
		return "Resume [resumeId=" + resumeId + ", candidateName=" + candidateName + ", contactNumber=" + contactNumber
				+ ", education=" + education + ", description=" + description + ", linkedInDetails=" + linkedInDetails
				+ "]";
	}
	
	
}
