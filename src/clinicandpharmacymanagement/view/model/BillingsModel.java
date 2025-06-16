/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

/**
 *
 * @author user
 */
public class BillingsModel {
    private String id;
    private String medicinename;
    private int unit;
    private String price;

    // Constructor
    public BillingsModel(String id, String medicinename, int unit, String price) {
        this.id = id;
        this.medicinename = medicinename;
        this.unit = unit;
        this.price = price;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public int getUnit() {
        return unit;
    }

    public String getPrice() {
        return price;
    }

    // Optional: Auto-calculated total
    public double getTotal() {
        try {
            return unit * Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Setters (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}
