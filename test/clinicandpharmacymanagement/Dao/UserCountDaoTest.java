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

public class UserCountDaoTest {

    private final UserCountDao dao = new UserCountDao();

    @Test
    public void testCountPatients() {
        int patientCount = dao.countPatients();
        Assert.assertTrue("Patient count should not be negative", patientCount >= 0);
    }

    @Test
    public void testCountStaff() {
        int staffCount = dao.countStaff();
        Assert.assertTrue("Staff count should not be negative", staffCount >= 0);
    }

    @Test
    public void testCountAdmins() {
        int adminCount = dao.countAdmins();
        Assert.assertTrue("Admin count should not be negative", adminCount >= 0);
    }

    @Test
    public void testCountDoctors() {
        int doctorCount = dao.countDoctors();
        Assert.assertTrue("Doctor count should not be negative", doctorCount >= 0);
    }

    @Test
    public void testCountAllUsers() {
        int userCount = dao.countAllUsers();
        Assert.assertTrue("User count should not be negative", userCount >= 0);
    }

    @Test
    public void testDashboardCounts() {
        int[] counts = dao.getDashboardCounts();
        Assert.assertNotNull("Dashboard counts should not be null", counts);
        Assert.assertEquals("Dashboard counts array should have 4 elements", 4, counts.length);

        for (int i = 0; i < counts.length; i++) {
            Assert.assertTrue("Each count should be non-negative", counts[i] >= 0);
        }
    }
}
