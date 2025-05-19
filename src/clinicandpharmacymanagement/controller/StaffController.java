/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.Medicinedao;
import clinicandpharmacymanagement.view.StaffDash;
import clinicandpharmacymanagement.view.model.MedicineModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class StaffController {
StaffDash view;
Medicinedao dao;
    public StaffController(StaffDash view) {
        this.view= view;
        this.view.addmedicine(new Adder());
        
    }
class Adder implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = view.getId().getText();
        String name = view.getName().getText();
        String amount = view.getAmount().getText();
        String price = view.getPrice().getText();
        String expiryDate = view.getExpire().getText();

        if (id.isEmpty() || name.isEmpty() || amount.isEmpty() || price.isEmpty() || expiryDate.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Fill all the fields");
        } else {
            MedicineModel medicine=new MedicineModel(name,amount);
            boolean result = dao.addMedicine(medicine);
            

            if (result) {
                JOptionPane.showMessageDialog(view, "MEDICINE ADDED SUCCESSFULLY!");
            } else {
                JOptionPane.showMessageDialog(view, "SOMETHING WENT WRONG!");
            }
        }
    }
}
 public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    
    
}    
    

