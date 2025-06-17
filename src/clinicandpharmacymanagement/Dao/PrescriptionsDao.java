/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.PrescriptionModel;
import java.sql.*;

/**
 *
 * @author lenovo
 */
public class PrescriptionsDao {
    MysqlConnection mysql = new MysqlConnection();

    public boolean insertPrescription(PrescriptionModel prescription) {
        String query = "INSERT INTO prescriptions (time, description, medicine, dosage) VALUES (?, ?, ?, ?)";
        Connection conn = mysql.openConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, prescription.getTime());
            stmt.setString(2, prescription.getDescription());
            stmt.setString(3, prescription.getMedicine());
            stmt.setString(4, prescription.getDosage());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}
