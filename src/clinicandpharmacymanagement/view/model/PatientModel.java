/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;


import java.util.Date;



/**
 *
 * @author AngkitKharel
 */
public class PatientModel {

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the emergency
     */
    public String getEmergency() {
        return emergency;
    }

    /**
     * @param emergency the emergency to set
     */
    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }
    private String id;
    private String name;
    private String address;
    private String phone;
    private String condition;
    private String emergency;

    
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

