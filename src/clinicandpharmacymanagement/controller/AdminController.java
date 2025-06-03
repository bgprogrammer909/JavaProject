/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.view.AdminDashboardPharma;
import clinicandpharmacymanagement.view.AdminDashboardPharma1;
import clinicandpharmacymanagement.view.model.UserData;

/**
 *
 * @author ACER
 */
public class AdminController {
    AdminDashboardPharma view;
    UserData user;
    public AdminController(AdminDashboardPharma view,UserData user){
        this.view=view;
        AdminDashboardPharma1 ad2=new AdminDashboardPharma1();
        new ButtonController(view.getUsers(),this.view,ad2);
        this.user=user;
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
}
}