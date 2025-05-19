package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.Database.MysqlConnection;
import java.sql.*;

public class UserCountDao {
    private final MysqlConnection mysql = new MysqlConnection();

    // Count all patients (users with type 'patient')
    public int countPatients() {
        String query = "SELECT COUNT(*) FROM users WHERE type = 'patient'";
        return executeCountQuery(query);
    }

    // Count all staff members (users with type 'staff')
    public int countStaff() {
        String query = "SELECT COUNT(*) FROM users WHERE type = 'staff'";
        return executeCountQuery(query);
    }

    // Count all admin users
    public int countAdmins() {
        String query = "SELECT COUNT(*) FROM users WHERE type = 'admin'";
        return executeCountQuery(query);
    }

    // Count all doctors
    public int countDoctors() {
        String query = "SELECT COUNT(*) FROM doctor";
        return executeCountQuery(query);
    }

    // Count all users regardless of type
    public int countAllUsers() {
        String query = "SELECT COUNT(*) FROM users";
        return executeCountQuery(query);
    }

    // Generic method to execute count queries
    private int executeCountQuery(String query) {
        Connection conn = mysql.openConnection();
        int count = 0;
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        
        return count;
    }

    // Get counts for dashboard statistics
    public int[] getDashboardCounts() {
        int[] counts = new int[4];
        counts[0] = countPatients();
        counts[1] = countStaff();
        counts[2] = countDoctors();
        counts[3] = countAllUsers();
        return counts;
    }
}