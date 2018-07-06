package it.unisalento.se.models;

import it.unisalento.se.dao.User;

import java.util.Date;

public class ReportingModel implements CourseOfStudyNode {

    private Integer ID;
    private SupportDeviceModel supportDevice;
    private String description;
    private Date lastModified;
    private User doneBy;
    private ReportingStatusModel reportingStatus;
    private ClassroomModel classroom;

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public SupportDeviceModel getSupportDevice() {
        return supportDevice;
    }

    public void setSupportDevice(SupportDeviceModel supportDevice) {
        this.supportDevice = supportDevice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public User getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(User doneBy) {
        this.doneBy = doneBy;
    }

    public ReportingStatusModel getReportingStatus() {
        return reportingStatus;
    }

    public void setReportingStatus(ReportingStatusModel reportingStatus) {
        this.reportingStatus = reportingStatus;
    }

    public ClassroomModel getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomModel classroom) {
        this.classroom = classroom;
    }
}
