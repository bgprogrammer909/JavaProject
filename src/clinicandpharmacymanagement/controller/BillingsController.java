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
    private Billingsdao billingsdao;

    public BillingsController() {
        billingsdao = new Billingsdao();
    }

    public boolean addBilling(String id, String medicinename, int unit, String price) {
        BillingsModel model = new BillingsModel(id, medicinename, unit, price);
        return billingsdao.insertMedicine(model);
    }
}
