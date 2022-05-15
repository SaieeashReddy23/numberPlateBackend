package com.sai.numberPlate.modals;

import java.sql.Timestamp;

public class VehicleAllDetails {

    private String text;
    private Double latitude;
    private Double longitude;

    private Timestamp timestamp;

    public VehicleAllDetails() {
    }

    public VehicleAllDetails(String text, Double latitude, Double longitude, Timestamp timestamp) {
        this.text = text;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "VehicleAllDetails{" +
                "text='" + text + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
