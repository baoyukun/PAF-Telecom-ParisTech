package parser;


import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Author {
	private Affiliation affiliation = new Affiliation();
	private String affiliation_id;
	private String email = null;
	private Name name;
	private String scopus_id;
	private String uri = null;

	public Author() {
		this.name = new Name();
	}

	
	public final Set<String> getAddress() {
		return affiliation.getAddress();
	}

	public final Affiliation getAffiliation() {
		return affiliation;
	}

	public final String getAffiliation_id() {
		return affiliation_id;
	}

	public final Set<String> getDepartement() {
		return affiliation.getDepartment();
	}

	public final String getEmail() {
		return email;
	}
	
	
	@JsonProperty("familyName")
	public final String getFamilyName() {
		return name.getFamilyName();
	}

	public final String getFullName() {
		return this.name.getFullName();
	}

	@JsonProperty("givenName")
	public final String getGivenName() {
		return name.getGivenName();
	}

	public final Set<String> getGroupe() {
		return affiliation.getGroup();
	}

	public final String getMiddleName() {
		return this.name.getMiddleName();
	}

	public final Name getName() {
		return name;
	}


	public final String getScopus_id() {
		return scopus_id;
	}


	public final String getUri() {
		return uri;
	}


	public final void setAddress(String address) {
		this.affiliation.getAddress().add(address);
	}


	public final void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	public final void setAffiliation_id(String affiliation_id) {
		this.affiliation_id = affiliation_id;
	}

	public final void setDepartement(String departement) {
		this.affiliation.getDepartment().add(departement);
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("familyName")
	public final void setFamilyName(String familyName) {
		this.name.setFamilyName(StringEscapeUtils.unescapeXml(familyName).trim());
	}
	
	@JsonProperty("fullName")
	public final void setFullName(String fullName) {
		this.name.setFullName(fullName);
	}

	@JsonProperty("givenName")
	public final void setGivenName(String givenName) {
		this.name.setGivenName(StringEscapeUtils.unescapeXml(givenName).trim());
	}
	
	public final void setGroupe(String groupe) {
		this.affiliation.getGroup().add(groupe);
	}

	public final void setInstitution(String string){
		this.affiliation.getInstitution().add(string);
	}
	
	@JsonProperty("middleName")
	public final void setMiddleName(String middleName) {
		this.name.setMiddleName(middleName);
	}

	public final void setName(Name name) {
		this.name = name;
	}

	public final void setScopus_id(String scopus_id) {
		this.scopus_id = scopus_id;
	}

	public final void setUri(String uri) {
		this.uri = uri;
	}
	
	
	
}
