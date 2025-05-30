/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.view.HomePage;
import clinicandpharmacymanagement.view.Registration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author ACER
 */
public class HomepageController {
    HomePage view;

    public HomepageController(HomePage view) {
        this.view=view;
        this.view.register(new GoRegister());
        
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    
 class GoRegister implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e){
        
        Registration regView=new Registration();
        regView.setVisible(true);
        close();
    }
    
}   
}
