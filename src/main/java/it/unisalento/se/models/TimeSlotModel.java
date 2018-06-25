package it.unisalento.se.models;

import java.util.Date;

public class TimeSlotModel implements CourseOfStudyNode {

    private Integer ID;
    private Date startTime;
    private Date endTime;

    @Override
    public Integer getId() {
        return ID;
    }

    @Override
    public void setId(Integer ID) {
        this.ID = ID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
