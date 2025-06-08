/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.ResetPasswordRequest;
import clinicandpharmacymanagement.view.model.UserData;
import java.sql.*;

/**
 *
 * @author ACER
 */
public class UserDao {
    MysqlConnection mysql=new MysqlConnection();
    public boolean register(UserData user){
        String query ="insert into users(fname,email,fpassword,type) values(?,?,?,?)";
        Connection conn=mysql.openConnection();
        try{
            String defaultType="patient";
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1, user.getName());
            stmnt.setString(2, user.getEmail());
            stmnt.setString(3, user.getPassword());
            stmnt.setString(4, defaultType);
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            return false;
        }finally {
            mysql.closeConnection(conn);
        }
    
}
    public UserData login(LoginRequest loginReq){
        String query="select * from users where email=? and fpassword=?";
        Connection conn=mysql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,loginReq.getEmail());
            stmnt.setString(2, loginReq.getPassword());
            ResultSet result=stmnt.executeQuery();
            if (result.next()){
                String email=result.getString("email");
                String name=result.getString("fname");
                String password=result.getString("fpassword");
                String id=result.getString("id");
                String utype=result.getString("type");

                UserData user=new UserData(id,name,email,password,utype);
                return user;
            }else {
                return null;
            }
        } catch(SQLException e){
            return null;
        }       
    }
    public boolean checkEmail(String email){
        String query="Select * from users where email=?";
        Connection conn=mysql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,email);
            ResultSet result=stmnt.executeQuery();
            if (result.next()){
                return true;
            }else {
                return false;
            }
        }catch(SQLException e){
            return false;
        }finally{
            mysql.closeConnection(conn);
        }
    }
    public boolean resetPassword(ResetPasswordRequest reset){
        String query="Update user set fpassword=? where email=?";
        Connection conn=mysql.openConnection();
        try{
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setString(1,reset.getPassword());
            stmnt.setString(2,reset.getEmail());
            int result=stmnt.executeUpdate();
            return result>0;
        }catch(SQLException e){
            return false;
        }finally{
            mysql.closeConnection(conn);
        }
    }
}
