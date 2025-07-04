/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import clinicandpharmacymanagement.controller.ResetController;
import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.ResetPasswordRequest;
import clinicandpharmacymanagement.view.model.UserData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




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
        public boolean deleteUserById(String id){
        String query ="DELETE FROM users WHERE id = ?";
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
    public boolean registerStaff(UserData user){
        String query ="insert into users(fname,email,fpassword,type) values(?,?,?,?)";
        Connection conn=mysql.openConnection();
        try{
            String defaultType="staff";
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
        String query="Update users set fpassword=? where email=?";
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
     public List<UserData> getAllUsers() {
        List<UserData> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Connection conn = mysql.openConnection();

        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();

            while (rs.next()) {
                UserData user = new UserData(
                    rs.getString("id"),
                    rs.getString("fname"),
                    rs.getString("email"),
                    rs.getString("fpassword"),
                    rs.getString("type")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            
        } finally {
            mysql.closeConnection(conn);
        }
        return users;
    }

       
    public boolean validateUser(String email, String oldPassword) {
            String query = "SELECT * FROM users WHERE email = ? AND fpassword = ?";
            Connection conn=mysql.openConnection();
            try {
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, email);
                stmt.setString(2, oldPassword);

                ResultSet rs = stmt.executeQuery();
                return rs.next(); // valid if user found
            } catch (SQLException e) {
                
                return false;
            }
        }

        public boolean resetPassword(String email, String newPassword) {
            String query = "UPDATE users SET fpassword = ? WHERE email = ?";
            Connection conn=mysql.openConnection();
            try {
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, newPassword);
                stmt.setString(2, email);

                int updated = stmt.executeUpdate();
                return updated > 0;
            } catch (SQLException e) {
               
                return false;
            }
        }
    public boolean updatePatientProfile(UserData user) {
    // Changed to use email instead of ID
    String query = "UPDATE users SET fname=?, gender=?, blood_group=?, age=? WHERE email=?";
    
    try (Connection conn = mysql.openConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getGender());
        stmt.setString(3, user.getBloodGroup());
        stmt.setInt(4, user.getAge());
        stmt.setString(5, user.getEmail());  // Using email as identifier
        
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
        
    } catch (SQLException e) {
        System.err.println("Update error: " + e.getMessage());
        return false;
    }
}
public UserData getPatientByEmail(String email,UserData user) {
    String query = "SELECT fname,id, age, blood_group, gender FROM users WHERE email=?";
    
    try (Connection conn = mysql.openConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, email);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                user.setName(rs.getString("fname"));
                user.setId(rs.getString("id"));
                user.setAge(rs.getInt("age"));
                user.setBloodGroup(rs.getString("blood_group"));
                user.setGender(rs.getString("gender"));
                user.setEmail(email);
                return user;
            }
            return null;  // No patient found with this email
        }
        
    } catch (SQLException e) {
        System.err.println("Query error: " + e.getMessage());
        return null;
    }
}
}
