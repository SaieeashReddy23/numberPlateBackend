package com.sai.numberPlate.modals;

public class DetectedTextClass {
    private String detected;
    private Double confidence;
    private Integer id;


    public DetectedTextClass() {
    }

    public DetectedTextClass(String detected, Double confidence, Integer id) {
        this.detected = detected;
        this.confidence = confidence;
        this.id = id;
    }

    public String getDetected() {
        return detected;
    }

    public void setDetected(String detected) {
        this.detected = detected;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
