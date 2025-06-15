/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.BillingModel;



import java.sql.*;
/**
 *
 * @author AngkitKharel
 */
public class Billingdao {
    MysqlConnection mysql=new MysqlConnection();

    /**
     *
     * @param model
     * @return
     */
    public boolean addbilling(BillingModel model){
    String query ="insert into Doctor(id,date,customername,phone,amount) values(?,?,?,?,?,?)";
        Connection conn=mysql.openConnection();
        try{
            
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,model.getId());
            stmnt.setString(2,model.getDate());
            stmnt.setString(3,model.getCustomername());
            stmnt.setString(4,model.getPhone());
            stmnt.setString(5,model.getAmount());
           
            
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            return false;
        }finally {
            mysql.closeConnection(conn);
        }
 
    }
    
    

