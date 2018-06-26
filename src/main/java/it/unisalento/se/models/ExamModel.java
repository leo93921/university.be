package it.unisalento.se.models;

public class ExamModel {


    private Integer ID;
    private  String description;
    private SubjectModel subject;
    private ClassroomModel classroom;
    private TimeSlotModel timeslot;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public ClassroomModel getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomModel classroom) {
        this.classroom = classroom;
    }

    public TimeSlotModel getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlotModel timeslot) {
        this.timeslot = timeslot;
    }
}
