
/**
 *
 * @author AngkitKharel
 */

package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.view.model.MedicineModel;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MedicinedaoTest {

    Medicinedao dao = new Medicinedao();

    @Test
    public void testAddMedicine() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date expiry = sdf.parse("2026-12-31");

            MedicineModel med = new MedicineModel();
            med.setName("Paracetamol");
            med.setPrice(10.5);
            med.setAmount(100);
            med.setExpiryDate(expiry);

            boolean result = dao.addMedicine(med);
            Assert.assertTrue("Medicine should be added successfully", result);
        } catch (Exception e) {
            Assert.fail("Exception occurred during testAddMedicine: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllMedicines() {
        List<MedicineModel> list = dao.getAllMedicines();
        Assert.assertNotNull("Medicine list should not be null", list);
        Assert.assertTrue("Medicine list size should be >= 0", list.size() >= 0);
    }

    @Test
    public void testUpdateAmount() {
        // Change this ID to a valid one in your database for the test to pass
        int testId = 1;
        int newAmount = 200;

        boolean result = dao.updateAmount(testId, newAmount);
        Assert.assertTrue("Medicine amount should be updated", result);
    }

    @Test
    public void testDeleteMedicine() {
        // Add a medicine first to get a valid ID for deletion
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date expiry = sdf.parse("2027-01-01");

            MedicineModel med = new MedicineModel();
            med.setName("ToDelete");
            med.setPrice(5.0);
            med.setAmount(50);
            med.setExpiryDate(expiry);

            boolean added = dao.addMedicine(med);
            Assert.assertTrue("Medicine should be added for deletion test", added);

            // Fetch list to get ID of the last added medicine
            List<MedicineModel> list = dao.getAllMedicines();
            int lastId = list.get(list.size() - 1).getId();

            boolean deleted = dao.deleteMedicine(lastId);
            Assert.assertTrue("Medicine should be deleted successfully", deleted);
        } catch (Exception e) {
            Assert.fail("Exception occurred during testDeleteMedicine: " + e.getMessage());
        }
    }
}
