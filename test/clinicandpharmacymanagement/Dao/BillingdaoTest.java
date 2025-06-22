/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

/**
 *
 * @author AngkitKharel
 */


import org.junit.Assert;
import org.junit.Test;

public class BillingdaoTest {

    private final Billingdao dao = new Billingdao();

    @Test
    public void testSaveBill() {
        // Use a valid patient ID from your test database
        String patientId = "1";  // ⚠️ Replace with actual ID
        String billText = "Consultation - 100\nMedication - 150";
        double totalAmount = 250.0;

        boolean result = dao.saveBill(patientId, billText, totalAmount);
        Assert.assertTrue("Bill should be saved successfully", result);
    }

    @Test
    public void testSaveBillWithInvalidPatientId() {
        String patientId = "invalid"; // ID that doesn't exist in DB or is wrong
        String billText = "Consultation - 100";
        double totalAmount = 100.0;

        boolean result = dao.saveBill(patientId, billText, totalAmount);
        Assert.assertFalse("Bill save should fail for invalid patient ID", result);
    }

    @Test
    public void testSaveBillWithEmptyBillText() {
        String patientId = "1"; // Use a valid ID
        String billText = "";
        double totalAmount = 0.0;

        boolean result = dao.saveBill(patientId, billText, totalAmount);
        Assert.assertTrue("Bill should be saved even with empty bill text", result); // Depends on DB constraints
    }
}
