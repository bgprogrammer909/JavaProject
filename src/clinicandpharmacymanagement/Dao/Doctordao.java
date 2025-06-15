/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.DoctorModel;


import java.sql.*;
/**
 *
 * @author AngkitKharel
 */
public class Doctordao {
    MysqlConnection mysql=new MysqlConnection();
    public boolean addDoctor(DoctorModel model){
    String query ="insert into Doctor(id,name,phone,time) values(?,?,?,?)";
        Connection conn=mysql.openConnection();
        try{
            
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,model.getId());
            stmnt.setString(2, model.getName());
            stmnt.setString(3,model.getPhone());
            stmnt.setString(4,model.getTime());
           
            
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            return false;
        }finally {
            mysql.closeConnection(conn);
        }
 
}
    
    

