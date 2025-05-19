/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.DoctorModel;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author AngkitKharel
 */
public class Doctordao {
     MysqlConnection mysql = new MysqlConnection(); // adjust to your actual connection handler

    public boolean addDoctor(DoctorModel model) {
    String query = "INSERT INTO doctor (name, email, specialization, phone, available_time_from, available_time_to) VALUES (?, ?, ?, ?, ?, ?)";
    Connection conn = mysql.openConnection();

    try {
        PreparedStatement stmnt = conn.prepareStatement(query);
        stmnt.setString(1, model.getName());
        stmnt.setString(2, model.getEmail());
        stmnt.setString(3, model.getSpeciality());
        stmnt.setString(4, model.getPhone());
        stmnt.setString(5, model.getTimeFrom());
        stmnt.setString(6, model.getTimeTo());

        int result = stmnt.executeUpdate();
        return result > 0;

    } catch (SQLException e) {
        e.printStackTrace(); // This will print the full error to console
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error to user
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
}
    public List<DoctorModel> getAllDoctors() {
    List<DoctorModel> doctors = new ArrayList<>();
    // Include doctor_id and use correct column name (specialization)
    String query = "SELECT doctor_id, name, email, specialization, phone, " +
                   "available_time_from, available_time_to FROM doctor";
    Connection conn = mysql.openConnection();

    try {
        System.out.println("Executing query: " + query); // Debug output
        PreparedStatement stmnt = conn.prepareStatement(query);
        ResultSet rs = stmnt.executeQuery();

        int count = 0;
        while (rs.next()) {
            count++;
            DoctorModel doctor = new DoctorModel(
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("specialization"), // Changed to match table
                rs.getString("phone"),
                rs.getString("available_time_from"),
                rs.getString("available_time_to"));
            
            // Set the doctor_id if your model has this field
            doctor.setId(rs.getString("doctor_id"));
            
            doctors.add(doctor);
            System.out.println("Loaded doctor: " + doctor.getName()); // Debug
        }
        System.out.println("Total doctors loaded: " + count); // Debug
    } catch (SQLException e) {
        System.err.println("Error loading doctors:");
        // Now we'll see errors
        JOptionPane.showMessageDialog(null, 
            "Error loading doctor data: " + e.getMessage());
    } finally {
        mysql.closeConnection(conn);
    }
    return doctors;
}


    // Method to delete a doctor by ID
    public boolean deleteDoctorById(String id) {
        String query ="DELETE FROM doctor WHERE doctor_id = ?";
        Connection conn=mysql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1, id);
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            return false;
        }finally {
            mysql.closeConnection(conn);
        }
    }


}


