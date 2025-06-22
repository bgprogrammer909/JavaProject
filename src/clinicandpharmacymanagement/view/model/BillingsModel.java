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
    private String medicineName;
    private int unit;
    private double price;
    private String expire;

    // Constructor for billing details
    public BillingsModel(String id, String medicineName, int unit, double price, String expire) {
        this.id = id;
        this.medicineName = medicineName;
        this.unit = unit;
        this.price = price;
        this.expire = expire;
    }

    // Constructor for expiry-only use case
    public BillingsModel(String expire) {
        this.expire = expire;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public String getExpire() {
        return expire;
    }

    // Auto-calculated total price
    public double getTotal() {
        return unit * price;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }
}
