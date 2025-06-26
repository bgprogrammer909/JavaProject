/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Controller.SMTPSController;
import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.AdminDashboardPharma;

import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import clinicandpharmacymanagement.view.LoginView;
import clinicandpharmacymanagement.view.SampleCard;
import clinicandpharmacymanagement.view.StaffDash;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        this.view.forgotPassword( new RPass());
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
                    SampleCard dashboardView=new SampleCard();
                    PatientDashboardController dashboardControl=new PatientDashboardController(dashboardView,user,loginData);
                    dashboardControl.open();
                    close();
                }
                else if(user.getUtype().equals("staff")){
                    JOptionPane.showMessageDialog(view, "logged in sucessful");
                    StaffDash dashboardView=new StaffDash();
                    StaffController dashboardControl=new StaffController(dashboardView);
                    dashboardControl.open();
                    close();
                }else if(user.getUtype().equals("admin")){
                    JOptionPane.showMessageDialog(view, "logged in sucessful");
                    AdminDashboardPharma dashboardView=new AdminDashboardPharma();
                    AdminController dashboardControl=new AdminController(dashboardView,user);
                    dashboardControl.open();
                    close();
                }
                
    
        }}}
class RPass implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        String email = JOptionPane.showInputDialog(view, "Enter your email:");
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email field was empty");
            return;
        }

        UserDao useDao = new UserDao();
        boolean emailExists = useDao.checkEmail(email);
        if (!emailExists) {
            JOptionPane.showMessageDialog(view, "No user found");
            return;
        }

        // Generate a random 6-digit OTP
        String otp = String.format("%06d", new java.util.Random().nextInt(999999));
        String title = "Reset Password Verification";
        String body = otp + " is the OTP to reset your password.";

        boolean emailSent = SMTPSController.sendMail(email, title, body);
        if (!emailSent) {
            JOptionPane.showMessageDialog(view, "Failed to send OTP. Please try again.");
            return;
        }

        String receivedOtp = JOptionPane.showInputDialog(view, "Enter the OTP:");
        if (receivedOtp == null || receivedOtp.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "OTP field was empty");
            return;
        }

        if (!otp.equals(receivedOtp.trim())) {
            JOptionPane.showMessageDialog(view, "Invalid OTP");
            return;
        }

        String newPassword = JOptionPane.showInputDialog(view, "Enter the new password:");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password field was empty");
            return;
        }

        boolean passwordChanged = useDao.resetPassword(email, newPassword);
        if (!passwordChanged) {
            JOptionPane.showMessageDialog(view, "Failed to reset password");
        } else {
            JOptionPane.showMessageDialog(view, "Password has been changed!");
        }
    }

    // Other MouseListener methods (empty implementations)
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
}
        
        
    

