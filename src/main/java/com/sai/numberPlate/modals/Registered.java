package com.sai.numberPlate.modals;

public class Registered {

    private String deviceID;
    private Boolean isRegistered;


    public Registered() {
    }

    public Registered(String deviceID, Boolean isRegistered) {
        this.deviceID = deviceID;
        this.isRegistered = isRegistered;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    @Override
    public String toString() {
        return "Registered{" +
                "deviceID='" + deviceID + '\'' +
                ", isRegistered=" + isRegistered +
                '}';
    }
}
