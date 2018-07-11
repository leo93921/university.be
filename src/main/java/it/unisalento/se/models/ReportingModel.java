package it.unisalento.se.models;

import java.util.Date;

public class ReportingModel implements CourseOfStudyNode {


    private Integer ID;
    private SupportDeviceModel supportDevice;
    private String note;
    private Date lastModified;
    private UserModel doneBy;
    private ReportingStatusModel reportingStatus;
    private ClassroomModel classroom;

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    private String problemDescription;

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

    public String getNote() {
        return note;
    }

    public void setNote(String description) {
        this.note = description;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public UserModel getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(UserModel doneBy) {
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
