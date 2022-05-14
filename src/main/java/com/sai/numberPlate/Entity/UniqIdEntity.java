package com.sai.numberPlate.Entity;

import javax.persistence.*;

@Entity
@Table
public class UniqIdEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String deviceId;

    public UniqIdEntity() {
    }

    public UniqIdEntity(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "UniqIdEntity{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
