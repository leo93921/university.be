package it.unisalento.se.models;

import it.unisalento.se.common.validation.IValidatableModel;
import it.unisalento.se.common.validation.IValidationStrategy;

import java.util.Date;
import java.util.List;

public class ReportingModel implements CourseOfStudyNode, IValidatableModel {


    private Integer ID;
    private SupportDeviceModel supportDevice;
    private String note;
    private Date lastModified;
    private UserModel doneBy;
    private ReportingStatusModel reportingStatus;
    private ClassroomModel classroom;
    private String problemDescription;

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

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

    @Override
    public Boolean validate(IValidationStrategy strategy) {
        return strategy.isValid(this);
    }

    @Override
    public List<String> getValidationErrors(IValidationStrategy strategy) {
        return strategy.brokenRules(this);
    }
}
