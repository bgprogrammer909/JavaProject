/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.PrescriptionModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class PrescriptionsDao {
    MysqlConnection mysql = new MysqlConnection();
    
    public boolean addPrescription(PrescriptionModel prescription) {
        String sql = "INSERT INTO prescription (user_id, morning_description, day_description, night_description) VALUES (?, ?, ?, ?)";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prescription.getUserId());
            stmt.setString(2, prescription.getMorningDescription());
            stmt.setString(3, prescription.getDayDescription());
            stmt.setString(4, prescription.getNightDescription());

            int inserted = stmt.executeUpdate();
            return inserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();  // for debugging, you can replace with proper logging
            return false;
        }
    }
    public PrescriptionModel getLatestPrescriptionByUserId(String userId) {
    String sql = "SELECT prescription_id, morning_description, day_description, night_description, created_at " +
                 "FROM prescription WHERE user_id = ? ORDER BY created_at DESC LIMIT 1";

    try (Connection conn = mysql.openConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            PrescriptionModel p = new PrescriptionModel(
                userId,
                rs.getString("morning_description"),
                rs.getString("day_description"),
                rs.getString("night_description")
            );
            p.setPrescriptionId(rs.getInt("prescription_id"));
            p.setCreatedAt(rs.getString("created_at"));
            return p;
        }

    } catch (SQLException e) {
        e.printStackTrace();  // Replace with logging
    }

    return null;  // No prescription found
}
    
    public List<PrescriptionModel> getAllPrescriptionsByUserId(String userId) {
        List<PrescriptionModel> prescriptions = new ArrayList<>();

        String sql = "SELECT prescription_id, morning_description, day_description, night_description, created_at " +
                     "FROM prescription WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PrescriptionModel p = new PrescriptionModel(
                    userId,
                    rs.getString("morning_description"),
                    rs.getString("day_description"),
                    rs.getString("night_description")
                );
                p.setPrescriptionId(rs.getInt("prescription_id"));
                p.setCreatedAt(rs.getString("created_at"));
                prescriptions.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Replace with proper logging
        }

        return prescriptions;
    }

}
