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
public interface Dbconnection {
    Connection openConnection();
    void closeConnection(Connection conn);
    
}
