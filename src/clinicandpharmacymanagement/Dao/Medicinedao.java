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
public class Medicinedao {
 MysqlConnection mysql=new MysqlConnection();
    public boolean medicine(MedicineModel medicinemodel){
        String query ="insert into medicinemodel(medincename,id,unit,price,expirydate) values(?,?,?,?,?)";
        Connection conn=mysql.openConnection();
        try{
            
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1, medicinemodel.getmedicinename());
            stmnt.setUnit(2, medicinemodel.getunit());
            stmnt.setDate(3, (Date) medicinemodel.getexpirydate());
            stmnt.setString(4, medicinemodel.getId());
            stmnt.setString(5, medicinemodel.getprice());
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            return false;
        }finally {
            mysql.closeConnection(conn);
        }
 
    }
    

