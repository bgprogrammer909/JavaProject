/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Database;

import java.sql.*;


/**
 *
 * @author ACER
 */
public class MysqlConnection implements DbConnection {

    @Override
    public Connection openConnection() {
        String username="root";
       String password="g0d_0f_bl@ckf13ld";
       String database="ClinicAndPharmacy";
       try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn;
           conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password);
           return conn;
       }catch (Exception e){
           return null;
       }
    }

    @Override
    public void closeConnection(Connection conn) {
        try{
         if(conn!=null && !conn.isClosed()){
             conn.close();
         } 
     }catch(Exception e){
         
     }
    } 
    }


   

