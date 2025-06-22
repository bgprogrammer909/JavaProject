/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.ResetView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class ResetController {
     ResetView view;
    
    public ResetController(ResetView view){
        this.view=view;
        this.view.showOldPassword(new ShowOldPasswordControl());
        this.view.showNewPassword(new ShowNewPasswordControl());
        this.view.showRetypePassword(new ShowRetypePasswordControl());
        this.view.reset(new ResetUser());
    }
     public void open(){
        view.setVisible(true);
    }
    
    public void close(){
        view.dispose();
    }
    class ShowOldPasswordControl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO add your handling code here:
        if (view.getOldVisible().isSelected()){
            view.getOldPassword().setEchoChar((char)0);   
        }
        else{
             view.getOldPassword().setEchoChar('*');
        }
        }
    }
      class ShowNewPasswordControl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO add your handling code here:
        if (view.getNewVisible().isSelected()){
            view.getNewPassword().setEchoChar((char)0);   
        }
        else{
             view.getNewPassword().setEchoChar('*');
        }
        }
    }
      class ShowRetypePasswordControl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO add your handling code here:
        if (view.getRetypeVisible().isSelected()){
            view.getRetypePassword().setEchoChar((char)0);   
        }
        else{
             view.getRetypePassword().setEchoChar('*');
        }
        }
    }
      class ResetUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getUserName().getText().trim();
            String oldPassword = new String(view.getOldPassword().getPassword()).trim();
            String newPassword = new String(view.getNewPassword().getPassword()).trim();
            String retypePassword = new String(view.getRetypePassword().getPassword()).trim();
            
            UserDao userDao=new UserDao();
            if (email.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || retypePassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
                return;
            }

            if (!userDao.validateUser(email, oldPassword)) {
                JOptionPane.showMessageDialog(null, "Invalid email or old password.");
                return;
            }

            if (!newPassword.equals(retypePassword)) {
                JOptionPane.showMessageDialog(null, "New passwords do not match.");
                return;
            }

            boolean success = userDao.resetPassword(email, newPassword);
            if (success) {
                JOptionPane.showMessageDialog(null, "Password reset successful.");
            } else {
                JOptionPane.showMessageDialog(null, "Password reset failed.");
            }
        }              
            }
          
      
    
}
