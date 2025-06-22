/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;


/**
 *
 * @author AngkitKharel
 */
public class DoctorModel {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String speciality;
    private String timeFrom;
    private String timeTo;

    public DoctorModel(String name, String email, String speciality, String phone, String timeFrom, String timeTo) {
        this.name = name;
        this.email = email;
        this.speciality=speciality;
        this.phone = phone;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

   
}

   
   

         
     