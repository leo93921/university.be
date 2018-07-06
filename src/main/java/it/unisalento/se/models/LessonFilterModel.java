package it.unisalento.se.models;

public class LessonFilterModel {

    private TimeSlotModel startTime;
    private TimeSlotModel endTime;
    private SubjectModel subject;

    public TimeSlotModel getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeSlotModel startTime) {
        this.startTime = startTime;
    }

    public TimeSlotModel getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeSlotModel endTime) {
        this.endTime = endTime;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }
}
