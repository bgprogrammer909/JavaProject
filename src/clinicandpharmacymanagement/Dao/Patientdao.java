/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.MedicineModel;
import java.sql.*;
/**
 *
 * @author AngkitKharel
 */
public class Patientdao {
 MysqlConnection mysql=new MysqlConnection();
    public boolean patient(PatientModel model){
        String query ="insert into model(id,name,address,phone,condition,emergency) values(?,?,?,?,?,?)";
        Connection conn=mysql.openConnection();
    }
    

