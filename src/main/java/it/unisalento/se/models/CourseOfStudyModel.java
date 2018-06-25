package it.unisalento.se.models;

public class CourseOfStudyModel implements CourseOfStudyNode {

    private Integer ID;
    private AcademicYearModel academicYear;
    private String name;

    @Override
    public Integer getID() {
        return this.ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public AcademicYearModel getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYearModel academicYear) {
        this.academicYear = academicYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
