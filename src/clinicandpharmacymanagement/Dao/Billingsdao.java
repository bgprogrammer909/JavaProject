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

    public boolean insertMedicine(BillingsModel BillingsModel) {
        String query = "INSERT INTO medicinemodel (id, medincename, unit, price, total) VALUES (?, ?, ?, ?, ?)";
        java.sql.Connection conn=mysql.openConnection();

        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, BillingsModel.getId());
            stmnt.setString(2, BillingsModel.getMedicinename());
            stmnt.setInt(3, BillingsModel.getUnit());
            stmnt.setDouble(4, Double.parseDouble(BillingsModel.getPrice()));
            stmnt.setDouble(5, BillingsModel.getUnit() * Double.parseDouble(BillingsModel.getPrice()));

            int result = stmnt.executeUpdate();
            return result > 0;
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
}
