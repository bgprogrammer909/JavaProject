package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Billingdao {
    MysqlConnection mysql = new MysqlConnection();

    public boolean saveBill(String patientId, String billText, double totalAmount) {
        String query = "insert into bills(patient_id, bill_text, total_amount) values(?,?,?)";
        Connection conn = mysql.openConnection();
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, patientId);
            stmnt.setString(2, billText);
            stmnt.setDouble(3, totalAmount);
            int result = stmnt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}
