package it.unisalento.se.models;

import java.util.ArrayList;
import java.util.List;

public class ClassroomModel implements CourseOfStudyNode {
    private Integer id;
    private String name;
    private double latitude;
    private double longitude;
    private List<SupportDeviceModel> supportDevices = new ArrayList<>(0);

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<SupportDeviceModel> getSupportDevices() {
        return supportDevices;
    }

    public void setSupportDevices(List<SupportDeviceModel> supportDevices) {
        this.supportDevices = supportDevices;
    }
}



