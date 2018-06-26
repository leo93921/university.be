package it.unisalento.se.models;

public class LessonModel implements CourseOfStudyNode {

    private Integer ID;
    private ClassroomModel classroom;
    private TimeSlotModel timeslot;
    private SubjectModel subject;


    @Override
    public Integer getID() {
        return this.ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;

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

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }
}
