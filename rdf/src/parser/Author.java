package parser;



public class Author {
	private String familyName = null;
	private String givenName = null;
	private String uri = null;
	private String affiliation = null;
	private String departement = null;
	private String groupe = null;
	
	
	public final String getAffiliation() {
		return affiliation;
	}

	public final void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public final String getDepartement() {
		return departement;
	}

	public final void setDepartement(String departement) {
		this.departement = departement;
	}

	public final String getGroupe() {
		return groupe;
	}

	public final void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public final String getFamilyName() {
		return familyName.trim();
	}

	public final void setFamilyName(String familyName) {
		this.familyName = familyName.trim();
	}

	public final String getGivenName() {
		return givenName.trim();
	}

	public final void setGivenName(String givenName) {
		this.givenName = givenName.trim();
	}

	public final String getUri() {
		return uri.trim();
	}

	public final void setUri(String uri) {
		this.uri = uri;
	}

	public Author() {
		
	}
	
	public Author(String familyName, String givenName){
		this.familyName = familyName;
		this.givenName = givenName;
	}
	
	@Override
	public String toString() {
		
		return ("{Family Name:" + this.familyName 
				+ ",Given Name:" + this.givenName
				+ ",uri:" + this.uri
				+ ",Affiiliation:" + this.affiliation
				+ ",Departement:" + this.departement
				+ ",Group:" + this.groupe + "}");
	} 
	
	
}
