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
    private String time;
    private String description;
    private String medicine;
    private String dosage;

    public PrescriptionModel(String time, String description, String medicine, String dosage) {
        this.time = time;
        this.description = description;
        this.medicine = medicine;
        this.dosage = dosage;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}

