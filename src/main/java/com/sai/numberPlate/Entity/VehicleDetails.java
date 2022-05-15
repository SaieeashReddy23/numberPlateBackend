package com.sai.numberPlate.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
public class VehicleDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;


    private String vehicleNumber;

    private Double latitude;

    private Double longitude;

    private Timestamp timestamp;

    public VehicleDetails() {
    }


    public VehicleDetails(String vehicleNumber, Double latitude, Double longitude,Timestamp timestamp) {
        this.vehicleNumber = vehicleNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp=timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "id=" + id +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }
}
