/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

/**
 *
 * @author user
 */
public class PatientModel {
    private String patientId;
    private String name;
    private String doctor;
    private String time;

    // Constructor
    public PatientModel(String patientId, String name, String doctor, String time) {
        this.patientId = patientId;
        this.name = name;
        this.doctor = doctor;
        this.time = time;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getTime() {
        return time;
    }

    // Setters (optional)
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
