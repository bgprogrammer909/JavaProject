/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.view.HomePage;
import clinicandpharmacymanagement.view.Registration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import clinicandpharmacymanagement.view.LoginView;
import clinicandpharmacymanagement.view.ResetView;


/**
 *
 * @author ACER
 * 
 */
public class HomepageController {
    HomePage view;

    public HomepageController(HomePage view) {
        this.view=view;
        this.view.register(new GoRegister());
        this.view.loggingIn(new GoLogin());
        this.view.reseter(new GoReset());
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
        RegisterController con=new RegisterController(regView);
        con.open();
        close();
    }
    
}   
class GoLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView logView=new LoginView();
            LoginController logcon=new LoginController(logView);
            logcon.open();
            close();
        }
    
}
class GoReset implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ResetView view=new ResetView();
            ResetController con=new ResetController(view);
            con.open();
            close();
        }
    
}
}
