/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.Billingsdao;
import clinicandpharmacymanagement.view.model.BillingsModel;

/**
 *
 * @author user
 */
public class BillingsController {
    private Billingsdao billingsDAO;

    public BillingsController() {
        billingsDAO = new Billingsdao();
    }

    public boolean addBilling(String id, String medicinename, int unit, String price) {
        BillingsModel model = new BillingsModel(id, medicinename, unit, price);
        return billingsDAO.insertBilling(model); 
    }
}
