package it.unisalento.se.models;

public class ExamFilterModel {


    private TimeSlotModel startTime;
    private TimeSlotModel endTime;
    private SubjectModel subject;
    private CourseOfStudyModel courseOfStudy;
    private UserModel professor;

    public UserModel getProfessor() {
        return professor;
    }

    public void setProfessor(UserModel professor) {
        this.professor = professor;
    }

    public CourseOfStudyModel getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(CourseOfStudyModel courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

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
