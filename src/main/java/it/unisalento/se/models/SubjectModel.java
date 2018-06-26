package it.unisalento.se.models;

public class SubjectModel implements CourseOfStudyNode {

    private Integer ID;
    private String name;
    private UserModel professor;
    private Integer CFU;
    private Integer teachingYear;
    private CourseOfStudyModel courseOfStudy;

    @Override
    public Integer getID() {
        return this.ID;
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

    public UserModel getProfessor() {
        return professor;
    }

    public void setProfessor(UserModel professor) {
        this.professor = professor;
    }

    public Integer getCFU() {
        return CFU;
    }

    public void setCFU(Integer CFU) {
        this.CFU = CFU;
    }

    public Integer getTeachingYear() {
        return teachingYear;
    }

    public void setTeachingYear(Integer teachingYear) {
        this.teachingYear = teachingYear;
    }

    public CourseOfStudyModel getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(CourseOfStudyModel courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }
}
