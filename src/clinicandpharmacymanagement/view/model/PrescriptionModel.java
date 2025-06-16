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
    private String time, description, medicine, dosage;

    public Prescription(String time, String description, String medicine, String dosage) {
        this.time = time;
        this.description = description;
        this.medicine = medicine;
        this.dosage = dosage;
    }

    public String getTime() { return time; }
    public String getDescription() { return description; }
    public String getMedicine() { return medicine; }
    public String getDosage() { return dosage; }
}
