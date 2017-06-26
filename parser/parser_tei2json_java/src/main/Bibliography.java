package main;

import java.util.List;

public class Bibliography {

    private String title;
    List<PaperAuthor> authors;
    private String imprints;
    private String meeting;
    private String monTitle;
    private List<String> editor;

    public List<PaperAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<PaperAuthor> authors) {
        this.authors = authors;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getMonTitle() {
        return monTitle;
    }

    public void setMonTitle(String monTitle) {
        this.monTitle = monTitle;
    }

    public List<String> getEditor() {
        return editor;
    }

    public void setEditor(List<String> editor) {
        this.editor = editor;
    }

    public Bibliography(List<PaperAuthor> author, String title, String monTitle, String meeting, String imprint, List<String> editor) {
        this.authors = author;
        this.title = title;
        this.monTitle = monTitle;
        this.meeting = meeting;
        this.imprints = imprint;
        this.editor = editor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImprints() {
        return imprints;
    }

    public void setImprints(String imprints) {
        this.imprints = imprints;
    }

    @Override
    public String toString(){
        return "Title: "+title + ". Author:"+ authors + " Imprints :"+ imprints +" Meetings:"+ meeting
                + " Mon title:"+monTitle + " Editor:"+editor;
    }
}
