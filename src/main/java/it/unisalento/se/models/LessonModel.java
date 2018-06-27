package it.unisalento.se.models;

public class LessonModel implements CourseOfStudyNode {

    private Integer ID;
    private ClassroomModel classroom;
    private TimeSlotModel timeSlot;
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

    public TimeSlotModel getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlotModel timeSlot) {
        this.timeSlot = timeSlot;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }
}
