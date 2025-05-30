/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clinicandpharmacymanagement;

import clinicandpharmacymanagement.controller.HomepageController;
import clinicandpharmacymanagement.view.HomePage;

/**
 *
 * @author ACER
 */
public class ClinicAndPharmacyManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HomePage home=new HomePage();
        HomepageController hcon=new HomepageController(home);
        hcon.open();
    }
    
}
