/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.MedicineModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AngkitKharel
 */
public class Medicinedao {
 MysqlConnection mysql=new MysqlConnection();
  
    public boolean addMedicine(MedicineModel med) {
    String sql = "INSERT INTO medicines (name, price, amount, expiry_date) VALUES (?, ?, ?, ?)";
    try (Connection conn = mysql.openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, med.getName());
        ps.setDouble(2, med.getPrice());
        ps.setInt(3, med.getAmount());
        ps.setDate(4, new java.sql.Date(med.getExpiryDate().getTime()));
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public List<MedicineModel> getAllMedicines() {
    List<MedicineModel> list = new ArrayList<>();
    String sql = "SELECT * FROM medicines";
    try (Connection conn = mysql.openConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            MedicineModel med = new MedicineModel();
            med.setId(rs.getInt("id"));
            med.setName(rs.getString("name"));
            med.setPrice(rs.getDouble("price"));
            med.setAmount(rs.getInt("amount"));
            med.setExpiryDate(rs.getDate("expiry_date"));
            list.add(med);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

public boolean updateAmount(int id, int newAmount) {
    String sql = "UPDATE medicines SET amount =? WHERE id = ?";
    try (Connection conn = mysql.openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, newAmount);
        ps.setInt(2, id);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean deleteMedicine(int id) {
    String sql = "DELETE FROM medicines WHERE id = ?";
    try (Connection conn = mysql.openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
    

