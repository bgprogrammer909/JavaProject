/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.PatientDashboard;
import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import marksapp.java.view.LoginView;

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
    }
    
    public void open(){
        view.setVisible(true);
    }
    
    public void close(){
        view.dispose();
    }
    
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
                }else{
                    JOptionPane.showMessageDialog(view, "logged in sucessful");
                    PatientDashboard dashboardView=new PatientDashboard();
                    PatientDashboardController dashboardControl=new PatientDashboardController(dashboardView,user);
                    dashboardControl.open();
                    close();
                }
                
            }
        }
        
        
    }
}
