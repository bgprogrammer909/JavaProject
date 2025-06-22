/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.PatientModel;
import java.sql.*;
/**
 *
 * @author AngkitKharel
 */
public class Patientdao {
    MysqlConnection mysql=new MysqlConnection();
    public boolean addPatient(PatientModel Model){
    String query ="insert into Patient(id,name,address,phone,Condition,emergency) values(?,?,?,?,?,?)";
        Connection conn=mysql.openConnection();
        try{
            
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,Model.getId());
            stmnt.setString(2,Model.getName());
            stmnt.setString(3, Model.getAddress());
            stmnt.setString(4,Model.getPhone());
            stmnt.setString(5,Model.getCondition());
            stmnt.setString(6,Model.getEmergency());
           
            
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            return false;
        }finally {
            mysql.closeConnection(conn);
        }
    }
    }
}

