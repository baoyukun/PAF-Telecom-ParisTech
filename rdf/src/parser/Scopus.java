package parser;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Scopus {
	private String aggregationType;
	private String paperScopusId;
	private String  publicationName;
	private ArrayList<Author> author;
	
	public final String getAggregationType() {
		return aggregationType;
	}
	public final void setAggregationType(String aggregationType) {
		this.aggregationType = aggregationType;
	}
	public final String getPaperScopusId() {
		return paperScopusId;
	}
	public final void setPaperScopusId(String paperScopusId) {
		this.paperScopusId = paperScopusId;
	}
	public final String getPublicationName() {
		return publicationName;
	}
	public final void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}
	

	public final ArrayList<Author> getAuthor() {
		return author;
	}
	
	@JsonDeserialize(using = ListDeserializer.class)
	public final void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}
	
}
