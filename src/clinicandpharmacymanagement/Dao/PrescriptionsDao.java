/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

/**
 *
 * @author lenovo
 */
public class PrescriptionsDao {
    Connection conn;

    public PrescriptionDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> list = new ArrayList<>();
        String query = "SELECT * FROM prescriptions";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prescription p = new Prescription(
                    rs.getString("time"),
                    rs.getString("description"),
                    rs.getString("medicine"),
                    rs.getString("dosage")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
