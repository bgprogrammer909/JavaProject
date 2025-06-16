/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.AppointmentModel;
import java.sql.*;

/**
 *
 * @author user
 */
public class Appointmentdao {
    MysqlConnection mysql = new MysqlConnection();

    public boolean insertAppointment(AppointmentModel appointmentModel) {
        String query = "INSERT INTO appointments (id, appointment, patient, doctor) VALUES (?, ?, ?, ?)";
        Connection conn = mysql.openConnection();

        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, appointmentModel.getId());
            stmnt.setString(2, appointmentModel.getAppointment());
            stmnt.setString(3, appointmentModel.getPatient());
            stmnt.setString(4, appointmentModel.getDoctor());

            int result = stmnt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    
}
