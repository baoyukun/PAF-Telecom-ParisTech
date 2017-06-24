package main;

import java.net.URI;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/** This class is used to parse the json file*/
public class Article {
	private String entrytype;
	private String uri;
	private String ref;
	private String year;
	private String month;
	private String address;
	private String belongTo;
	private String category;
	private String language;
	private String audience;

    @JsonProperty("author")
	private List<Author> authorsList;
	private String title;
	private String booktitle;
	private String state;
	private String project;
	
	@JsonProperty("dept")
	private String departement;
	private String group;
	private String id;
	private String pages;
	private String documentURL;
	private String doi;
	private String publisher;
	@JsonProperty("invited state")
	private String invitedState;
	
	public final String getInvitedState() {
		return invitedState;
	}
	public final void setInvitedState(String invitedState) {
		this.invitedState = invitedState;
	}
	public final String getPublisher() {
		return publisher;
	}
	public final void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public final String getDoi() {
		return doi;
	}
	public final void setDoi(String doi) {
		this.doi = doi;
	}
	public final String getDocumentURL() {
		return documentURL;
	}
	public final void setDocumentURL(String documentURL) {
		this.documentURL = documentURL;
	}

	
	public final String getPages() {
		return pages;
	}
	public final void setPages(String pages) {
		this.pages = pages;
	}
	public final List<Article> getReferenceList() {
		return referenceList;
	}
	public final void setReferenceList(List<Article> referenceList) {
		this.referenceList = referenceList;
	}
	public final List<String> getKeywords() {
		return keywords;
	}
	public final void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	private List<Article> referenceList;
	private List<String> keywords;

	
	public final String getUri() {
		return uri;
	}
	public final void setUri(String uri) {
		this.uri = uri;
	}
	public final String getRef() {
		return ref;
	}
	public final void setRef(String ref) {
		this.ref = ref;
	}
	public final String getYear() {
		return year;
	}
	public final void setYear(String year) {
		this.year = year;
	}
	public final String getMonth() {
		return month;
	}
	public final void setMonth(String month) {
		this.month = month;
	}
	public final String getAddress() {
		return address;
	}
	public final void setAddress(String address) {
		this.address = address;
	}
	public final String getBelongTo() {
		return belongTo;
	}
	public final void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public final String getCategory() {
		return category;
	}
	public final void setCategory(String category) {
		this.category = category;
	}
	public final String getLanguage() {
		return language;
	}
	public final void setLanguage(String language) {
		this.language = language;
	}
	public final String getAudience() {
		return audience;
	}
	public final void setAudience(String audience) {
		this.audience = audience;
	}
	public final String getEntrytype() {
		return entrytype;
	}
	public final void setEntrytype(String entrytype) {
		this.entrytype = entrytype;
	}
	public final List<Author> getAuthorsList() {
		return authorsList;
	}
	public final void setAuthorsList(List<Author> authorsList) {
		this.authorsList = authorsList;
	}
	public final String getTitle() {
		return title;
	}
	public final void setTitle(String title) {
		this.title = title;
	}
	public final String getBooktitle() {
		return booktitle;
	}
	public final void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public final String getState() {
		return state;
	}
	public final void setState(String state) {
		this.state = state;
	}
	public final String getProject() {
		return project;
	}
	public final void setProject(String project) {
		this.project = project;
	}
	public final String getDepartement() {
		return departement;
	}
	public final void setDepartement(String departement) {
		this.departement = departement;
	}
	public final String getGroup() {
		return group;
	}
	public final void setGroup(String group) {
		this.group = group;
	}
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	
	
	
		
}
