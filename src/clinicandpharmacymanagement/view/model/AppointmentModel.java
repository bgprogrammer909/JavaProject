/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

/**
 *
 * @author user
 */
public class AppointmentModel {
    private String id;
    private String appointment;
    private String patient;
    private String doctor;

    public AppointmentModel(String id, String appointment, String patient, String doctor) {
        this.id = id;
        this.appointment = appointment;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getAppointment() {
        return appointment;
    }

    public String getPatient() {
        return patient;
    }

    public String getDoctor() {
        return doctor;
    }

    // Setters (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
    

