/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.AdminDashboardPharma;
import clinicandpharmacymanagement.view.PatientDashboard;
import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import clinicandpharmacymanagement.view.LoginView;

/**
 *
 * @author ACER
 */
public class LoginController {
    LoginView view;
    
    public LoginController(LoginView view){
        this.view=view;
        LoginUser loginUser= new LoginUser();
        this.view.loginIt(loginUser);
        ShowPasswordControl pcon=new ShowPasswordControl();
        this.view.showPassword(pcon);
    }
    
    public void open(){
        view.setVisible(true);
    }
    
    public void close(){
        view.dispose();
    }
    class ShowPasswordControl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO add your handling code here:
        if (view.getCheckBox().isSelected()){
            view.getPasswordField().setEchoChar((char)0);   
        }
        else{
             view.getPasswordField().setEchoChar('*');
        }
        }}
    class LoginUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String email=view.getEmailField().getText();
            String password=String.valueOf(view.getPasswordField().getPassword());
            if (email.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(view, "Field all the fields");
                
            }else {
                LoginRequest loginData=new LoginRequest(email,password);
                UserDao ud=new UserDao();
                UserData user=ud.login(loginData);
                if (user==null){
                    JOptionPane.showMessageDialog(view, "logged failed");
                }else if(user.getUtype().equals("patient")){
                    JOptionPane.showMessageDialog(view, "logged in sucessful");
                    PatientDashboard dashboardView=new PatientDashboard();
                    PatientDashboardController dashboardControl=new PatientDashboardController(dashboardView,user);
                    dashboardControl.open();
                    close();
                }
                else if(user.getUtype().equals("staff")){
                    JOptionPane.showMessageDialog(view, "logged in sucessful");
                    PatientDashboard dashboardView=new PatientDashboard();
                    PatientDashboardController dashboardControl=new PatientDashboardController(dashboardView,user);
                    dashboardControl.open();
                    close();
                }else if(user.getUtype().equals("admin")){
                    JOptionPane.showMessageDialog(view, "logged in sucessful");
                    AdminDashboardPharma dashboardView=new AdminDashboardPharma();
                    AdminController dashboardControl=new AdminController(dashboardView,user);
                    dashboardControl.open();
                    close();
                }
                
            
        }}}}
        
        
    

