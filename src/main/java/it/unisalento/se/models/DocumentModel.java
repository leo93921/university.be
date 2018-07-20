package it.unisalento.se.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

public class DocumentModel implements CourseOfStudyNode {

    private Integer ID;
    private String name;
    private String note;
    private Date publishDate;
    @JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "@class")
    @JsonSubTypes(
            value = @JsonSubTypes.Type(value = LessonModel.class, name = "lesson")
    )
    private CourseOfStudyNode node;
    private String link;

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public CourseOfStudyNode getLesson() {
        return node;
    }

    public void setLesson(CourseOfStudyNode node) {
        this.node = node;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
