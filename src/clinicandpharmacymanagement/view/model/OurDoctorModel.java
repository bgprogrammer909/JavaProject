/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

/**
 *
 * @author lenovo
 */
public class OurDoctorModel {
    private String name;
    private String speciality;
    private String availableAt;

    public OurDoctorModel(String name, String speciality, String availableAt) {
        this.name = name;
        this.speciality = speciality;
        this.availableAt = availableAt;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getAvailableAt() {
        return availableAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setAvailableAt(String availableAt) {
        this.availableAt = availableAt;
    }
}
