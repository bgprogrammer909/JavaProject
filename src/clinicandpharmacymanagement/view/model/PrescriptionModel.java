/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

/**
 *
 * @author lenovo
 */

public class PrescriptionModel {
    private int prescriptionId;
    private String userId;
    private String morningDescription;
    private String dayDescription;
    private String nightDescription;
    private String createdAt;

    // constructor for inserting new prescription
    public PrescriptionModel(String userId, String morningDescription, String dayDescription, String nightDescription) {
        this.userId = userId;
        this.morningDescription = morningDescription;
        this.dayDescription = dayDescription;
        this.nightDescription = nightDescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // getters + setters
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMorningDescription() {
        return morningDescription;
    }

    public void setMorningDescription(String morningDescription) {
        this.morningDescription = morningDescription;
    }

    public String getDayDescription() {
        return dayDescription;
    }

    public void setDayDescription(String dayDescription) {
        this.dayDescription = dayDescription;
    }

    public String getNightDescription() {
        return nightDescription;
    }

    public void setNightDescription(String nightDescription) {
        this.nightDescription = nightDescription;
    }
  
}

