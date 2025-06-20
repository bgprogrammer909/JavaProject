/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

/**
 *
 * @author ACER
 */
import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.AppointmentModel;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Appointmentdao {
    
    private final MysqlConnection mysql = new MysqlConnection();

    //  Book an appointment
    public boolean bookAppointment(String patientId, int doctorId, String timeSlot) {
        if (isTimeSlotTaken(doctorId, timeSlot)) {
            return false; // already booked
        }

        String query = "INSERT INTO appointments (patient_id, doctor_id, time_slot) VALUES (?, ?, ?)";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setString(3, timeSlot); // "14:00"

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Check if doctor already has appointment at that time
    public boolean isTimeSlotTaken(int doctorId, String timeSlot) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND time_slot = ? AND status = 'Pending'";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, doctorId);
            stmt.setString(2, timeSlot);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all appointments for a doctor
    public List<AppointmentModel> getAppointmentsByDoctor(int doctorId) {
        List<AppointmentModel> list = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE doctor_id = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AppointmentModel appt = new AppointmentModel();
                appt.setId(rs.getInt("id"));
                appt.setPatientId(rs.getInt("patient_id"));
                appt.setDoctorId(rs.getInt("doctor_id"));
                appt.setTimeSlot(rs.getString("time_slot"));
                appt.setStatus(rs.getString("status"));
                list.add(appt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //  Optional: update appointment status
    public boolean updateAppointmentStatus(int id, String status) {
        String query = "UPDATE appointments SET status = ? WHERE id = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
  
}
