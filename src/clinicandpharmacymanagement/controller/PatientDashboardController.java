/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.view.PatientDashboard;
import clinicandpharmacymanagement.view.SampleCard;
import clinicandpharmacymanagement.view.model.UserData;



/**
 *
 * @author ACER
 */
public class PatientDashboardController {
    SampleCard view;
    UserData user;
    public PatientDashboardController(SampleCard view,UserData user){
        this.view=view;
        this.user=user;
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    
    
}
