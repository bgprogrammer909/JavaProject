/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;
import javax.swing.JFrame;
import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.InfoDetail;
import clinicandpharmacymanagement.view.model.LoginRequest;

import clinicandpharmacymanagement.view.model.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class InfoDetailController {
    InfoDetail view;
    LoginRequest req;
    UserData user;
    public InfoDetailController(InfoDetail view,UserData user,LoginRequest req) {
        this.view=view;
        this.user=user;
        this.req=req;
        this.view.updateProfile(new Profile());
    }
    
public class Profile implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Get and validate inputs
            String name = view.getPName().getText().trim();
            if (name.isEmpty()) {
                showError("Name cannot be empty");
                return;
            }

            int age;
            try {
                age = Integer.parseInt(view.getAge().getText().trim());
                if (age <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showError("Age must be a positive number");
                return;
            }

            String gender = (String) view.getGender().getSelectedItem();
            if (gender == null || gender.trim().isEmpty()) {
                showError("Please select a gender");
                return;
            }
            String bloodgroup=view.getBlood().getText();
            
            
            String email=req.getEmail();
            // Create UserData object with updated info
            UserData updatedUser = new UserData(name,age,gender,bloodgroup);
            updatedUser.setEmail(email);  // Crucial - the identifier
            updatedUser.setName(name);
            updatedUser.setAge(age);
            updatedUser.setGender(gender);
            updatedUser.setBloodGroup(bloodgroup);
            
             UserDao userDao=new UserDao();
             
            
            // Update via DAO
            boolean success = userDao.updatePatientProfile(updatedUser);
            
            if (success) {
                showSuccess("Profile updated successfully!");
               
            } else {
                showError("Failed to update profile");
            }

        } catch (Exception ex) {
            showError("Database error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(view, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

    public void open(){
    // Make sure your view is properly initialized
    if (view == null) {
        view = new InfoDetail();  // Or your actual constructor
    }
    
    // Set the close operation
    view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    view.setVisible(true);
}
    
    public void close(){
        view.dispose();
    }
}
