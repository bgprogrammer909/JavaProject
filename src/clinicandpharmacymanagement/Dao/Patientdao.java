///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package clinicandpharmacymanagement.Dao;
//
//import clinicandpharmacymanagement.Database.MysqlConnection;
//import clinicandpharmacymanagement.view.model.PatientModel;
//import java.sql.*;
//
//
///**
// *
// * @author user
// */
//public class PatientDao {
//    MysqlConnection mysql = new MysqlConnection();
//
//    public boolean insertPatient(PatientModel patientModel) {
//        String query = "INSERT INTO patients (patient_id, name, doctor, time) VALUES (?, ?, ?, ?)";
//        Connection conn = mysql.openConnection();
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, patientModel.getPatientId());
//            stmt.setString(2, patientModel.getName());
//            stmt.setString(3, patientModel.getDoctor());
//            stmt.setString(4, patientModel.getTime());
//
//            int result = stmt.executeUpdate();
//            return result > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            mysql.closeConnection(conn);
//        }
//    }
//    
//}
