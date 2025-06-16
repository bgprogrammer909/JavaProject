/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.BillingsModel;
import java.sql.*;

/**
 *
 * @author user
 */
public class Billingsdao {
    MysqlConnection mysql = new MysqlConnection();

     public boolean insertBilling(BillingsModel billingsModel) {
        String query = "INSERT INTO medicinemodel (id, medincename, unit, price, total) VALUES (?, ?, ?, ?, ?)";
        java.sql.Connection conn = mysql.openConnection();

        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, billingsModel.getId());
            stmnt.setString(2, billingsModel.getMedicinename());
            stmnt.setInt(3, billingsModel.getUnit());
            stmnt.setDouble(4, Double.parseDouble(billingsModel.getPrice()));
            stmnt.setDouble(5, billingsModel.getUnit() * Double.parseDouble(billingsModel.getPrice()));

            int result = stmnt.executeUpdate();
            return result > 0;
        } catch (SQLException | NumberFormatException e) {
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
}
