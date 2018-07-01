package it.unisalento.se.models;

public class SupportDeviceModel implements CourseOfStudyNode {

    private Integer ID;
    private String name;

    @Override
    public Integer getID() {
        return ID;
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
}
