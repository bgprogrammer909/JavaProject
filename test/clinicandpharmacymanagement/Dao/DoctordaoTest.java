///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
// * @author AngkitKharel
// */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.view.model.DoctorModel;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

public class DoctordaoTest {

    private final Doctordao dao = new Doctordao();

    @Test
    public void testAddDoctor() {
        DoctorModel doctor = new DoctorModel(
            "Dr. Suman Ghimire",
            "suman.ghimire@example.com",
            "Dermatologist",
            "9800000000",
            "09:00",
            "17:00"
        );

        boolean added = dao.addDoctor(doctor);
        Assert.assertTrue("Doctor should be added successfully", added);
    }

    @Test
    public void testGetAllDoctors() {
        List<DoctorModel> doctors = dao.getAllDoctors();
        Assert.assertNotNull("Doctor list should not be null", doctors);
        Assert.assertTrue("Doctor list size should be >= 0", doctors.size() >= 0);
    }

    @Test
    public void testGetDoctorAvailableTime() {
        // Change this doctorId based on your actual DB values
        int testDoctorId = 1;
        LocalTime[] times = dao.getDoctorAvailableTime(testDoctorId);

        if (times != null) {
            Assert.assertNotNull("Start time should not be null", times[0]);
            Assert.assertNotNull("End time should not be null", times[1]);
            Assert.assertTrue("Start time should be before end time", times[0].isBefore(times[1]));
        } else {
            System.out.println("No doctor found with ID: " + testDoctorId);
        }
    }

    @Test
    public void testDeleteDoctorById() {
        // Add a dummy doctor to get ID for deletion
        DoctorModel doctor = new DoctorModel(
            "ToDelete Doctor",
            "delete@hospital.com",
            "Test Speciality",
            "9811000000",
            "08:00",
            "12:00"
        );

        boolean added = dao.addDoctor(doctor);
        Assert.assertTrue("Doctor should be added for deletion test", added);

        // Fetch doctors and get the last added one
        List<DoctorModel> doctors = dao.getAllDoctors();
        String lastDoctorId = doctors.get(doctors.size() - 1).getId();

        boolean deleted = dao.deleteDoctorById(lastDoctorId);
        Assert.assertTrue("Doctor should be deleted successfully", deleted);
    }
}

