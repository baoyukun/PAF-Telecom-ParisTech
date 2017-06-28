package parser;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
	
	@JsonProperty("abstract")
	private String summary;

	private String title;
	private String booktitle;
	private String state;
	private String project;
	private String monoTitle;
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
	
    @JsonProperty("author")
	private List<Author> authorsList;
    @JsonProperty("bibliography")
	private List<Article> citationList;
    
    private Scopus scopus;
    
    public final Scopus getScopus() {
		return scopus;
	}

	public final void setScopus(Scopus scopus) {
		this.scopus = scopus;
	}

	private String citedCount;
	
	public final String getCitedCount() {
		return citedCount;
	}

	public final void setCitedCount(String citedCount) {
		this.citedCount = citedCount;
	}

	private List<String> keywords;
	
	
	public final String getSummary() {
		return summary;
	}

	public final void setSummary(String summary) {
		this.summary = summary;
	}

	public final String getMonoTitle() {
		return monoTitle;
	}

	public final void setMonoTitle(String monoTitle) {
		this.monoTitle = monoTitle;
	}

	
	public final List<Article> getCitationList() {
		return citationList;
	}

	public final void setCitationList(List<Article> citationList) {
		this.citationList = citationList;
	}

	
	public Article() {

	}
	
	@JsonProperty("lang")
	public final void setLang(String lang){
		this.language = lang;
	}
	
	@JsonProperty("identity")
	public final void setIdentity(String identity){
		setUri(uri);
	}

	@JsonProperty("date")
	@JsonDeserialize(using = HashMapDeserializer.class)  
	public final void setDate(HashMap<String, String> date){
		if(date == null) return;
		if(date.containsKey("year")) this.year = date.get("year");
		if(date.containsKey("month")) this.month = date.get("month");
	}
	
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

	public final List<String> getKeywords() {
		return keywords;
	}
	public final void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	
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
		this.title = StringEscapeUtils.unescapeXml(title);
		this.title = this.title.trim().replaceAll("[`\\{\\}\\(\\)]+","").replaceAll("\\s+", " ");
		this.title.replaceAll("^\\*+", "");

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
	
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuilder output = new StringBuilder();
		output.append("{");
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object obj = field.get(this); 
				
				if(obj == null) output.append(field.getName() + ":null");
				else output.append(field.getName() + ":" + obj.toString());
				output.append("\n,");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		output.deleteCharAt(output.lastIndexOf("\n,"));
		output.append("}");
		return output.toString();
		
	}
}
