/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.OurDoctorModel;
import java.sql.*;
/**
 *
 * @author lenovo
 */
public class DoctorDao {
    MysqlConnection mysql = new MysqlConnection();

    public boolean insertDoctor(OurDoctorModel doctor) {
        String query = "INSERT INTO doctors (name, speciality, available_at) VALUES (?, ?, ?)";
        Connection conn = mysql.openConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpeciality());
            stmt.setString(3, doctor.getAvailableAt());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}
