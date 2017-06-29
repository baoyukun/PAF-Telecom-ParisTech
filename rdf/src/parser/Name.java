package parser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {
	@JsonProperty("lastName")
	private String familyName = null;
	@JsonProperty("firstName")
	private String givenName = null;
	
	private String middleName = null;
	private String fullName = null;
	
	public final String getFullName() {
		return fullName;
	}
	public final void setFullName(String fullName) {
		this.fullName = fullName.trim().replace("\\s+", " ");
	}
	public final String getFamilyName() {
		return familyName;
	}
	public final void setFamilyName(String familyName) {
		this.familyName = familyName.trim().replace("\\s+", "");
	}
	
	
	public final String getGivenName() {
		return givenName;
	}
	public final void setGivenName(String givenName) {
		this.givenName = givenName.trim().replace("\\s+", "");
	}
	
	public final String getMiddleName() {
		return middleName;
	}
	public final void setMiddleName(String middleName) {
		this.middleName = middleName.trim().replace("\\s+", "");
	}
}
