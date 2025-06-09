/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

import java.util.Date;



/**
 *
 * @author AngkitKharel
 */
public class MedicineModel {
    private String  medicinename;

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    private int unit;
    private Date expirydate;
    private String id;
    private String price;

    public MedicineModel(String medicinename, int unit, Date expirydate, String id, String price) {
        this.medicinename = medicinename;
        this.unit = unit;
        this.expirydate = expirydate;
        this.id = id;
        this.price = price;
    }
     
     
}
         
     