/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author AngkitKharel
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.view.model.AppointmentModel;
import clinicandpharmacymanagement.view.model.ViewAppointment;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AppointmentdaoTest {

    private final Appointmentdao dao = new Appointmentdao();

    @Test
    public void testBookAppointment() {
        // Replace with valid IDs from your DB
        String patientId = "1";
        int doctorId = 1;
        String timeSlot = "10:00";

        boolean booked = dao.bookAppointment(patientId, doctorId, timeSlot);
        Assert.assertTrue("Appointment should be booked successfully", booked);
    }

    @Test
    public void testBookDuplicateAppointment() {
        String patientId = "1";
        int doctorId = 1;
        String timeSlot = "10:00"; // Same as above

        boolean booked = dao.bookAppointment(patientId, doctorId, timeSlot);
        Assert.assertFalse("Booking should fail if time slot is already taken", booked);
    }

    @Test
    public void testIsTimeSlotTaken() {
        int doctorId = 1;
        String timeSlot = "10:00";

        boolean taken = dao.isTimeSlotTaken(doctorId, timeSlot);
        Assert.assertTrue("Time slot should be taken", taken);
    }

    @Test
    public void testGetAppointmentsByDoctor() {
        int doctorId = 1;
        List<AppointmentModel> appointments = dao.getAppointmentsByDoctor(doctorId);
        Assert.assertNotNull("Appointments list should not be null", appointments);
        Assert.assertTrue("Appointments size should be >= 0", appointments.size() >= 0);
    }

    @Test
    public void testUpdateAppointmentStatus() {
        int appointmentId = 1; // Replace with actual ID
        String newStatus = "Completed";

        boolean updated = dao.updateAppointmentStatus(appointmentId, newStatus);
        Assert.assertTrue("Appointment status should be updated", updated);
    }

    @Test
    public void testGetAllAppointmentsWithNames() {
        List<ViewAppointment> list = dao.getAllAppointmentsWithNames();
        Assert.assertNotNull("View appointment list should not be null", list);
        Assert.assertTrue("View appointments list should be >= 0", list.size() >= 0);
    }

    @Test
    public void testGetAppointmentsByUserId() {
        String userId = "1"; // Replace with valid user ID
        List<ViewAppointment> list = dao.getAppointmentsByUserId(userId);
        Assert.assertNotNull("Appointments by user ID should not be null", list);
        Assert.assertTrue("Appointments by user ID list should be >= 0", list.size() >= 0);
    }
}
