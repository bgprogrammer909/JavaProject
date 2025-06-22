package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.Doctordao;
import clinicandpharmacymanagement.Dao.UserCountDao;
import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.AdminDashboardPharma;
import clinicandpharmacymanagement.view.model.DoctorModel;

import clinicandpharmacymanagement.view.model.UserData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminController {
    AdminDashboardPharma view;
    UserData user;
    UserDao userDao = new UserDao();  
    Doctordao doctorDao = new Doctordao();
    public AdminController(AdminDashboardPharma view, UserData user) {
        this.view = view;
        this.user = user;
        this.view.refresh(new LoadDataListener());
        this.view.refresh2(new LoadStaffDataListener());
        this.view.refreshDoctor(new LoadDoctorDataListener());
        this.view.registerDoc(new RegisterDoctor());
        this.view.deleteDoctor(new DeleteDoctor());
        this.view.registerStaff(new RegisterStaff());
        this.view.deleteStaff(new DeleteStaff());
        this.view.getCounts(new UpdateCountsListener());
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
    
    class UpdateCountsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        UserCountDao countDao = new UserCountDao();
        
        // Get all counts
        int total = countDao.countAllUsers();
        int patients = countDao.countPatients();
        int staff = countDao.countStaff();
        int doctors = countDao.countDoctors();
        
        // Update labels with formatted text
        view.getTotalUser().setText("Total Uer " + total);
        view.getTotalpatient().setText("Total Patients: " + patients);
        view.getTotalStaff().setText("Total Staff: " + staff);
        view.getTotalDoctor().setText("Total Doctors: " + doctors);
    }
}
    
    class DeleteStaff implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getStaffTable().getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Assuming first column contains ID
                String id = (String) view.getStaffTable().getValueAt(selectedRow, 0);
                
                // Call DAO to delete
                boolean success = userDao.deleteUserById(id);
                
                if (success) {
                    // Refresh table or remove row
                    ((DefaultTableModel) view.getStaffTable().getModel()).removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Record deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete record.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }
        
        
    }
    class DeleteDoctor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getDocTable().getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Assuming first column contains ID
                String id = (String) view.getDocTable().getValueAt(selectedRow, 0);
                
                // Call DAO to delete
                boolean success = doctorDao.deleteDoctorById(id);
                
                if (success) {
                    // Refresh table or remove row
                    ((DefaultTableModel) view.getDocTable().getModel()).removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Record deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete record.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }}
    class RegisterStaff implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name=view.getNameTextField().getText();
            String email=view.getEmailTextField().getText();
            String password=view.getPasswordField().getText();
            String utype="staff";
            if (name.isEmpty() || email.isEmpty()|| password.isEmpty()){
                JOptionPane.showMessageDialog(view, "Fill all the fields");
            } else{
            UserDao userDao=new UserDao();
            UserData user=new UserData(name,email,password,utype);
            boolean result=userDao.registerStaff(user);
            if (result){
                JOptionPane.showMessageDialog(view,"STAFF ADDED SUCESSFULLY!!!!!");
                
            }else{
             JOptionPane.showMessageDialog(view,"SOMETHINGS' GONE WRONG!!!!!");   
            }
        }
        }
        
    }
    class LoadStaffDataListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<UserData> users = userDao.getAllUsers();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Password");
        model.addColumn("Type");
        
        for (UserData user : users) {
            if ("staff".equalsIgnoreCase(user.getUtype())) {  // ✅ Only staff
                model.addRow(new Object[]{
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUtype()
                });
            }
        }
        view.getStaffTable().setModel(model);
    }

    }
    class RegisterDoctor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = view.getDocName().getText();
        String email = view.getDocEmail().getText();
        String speciality = view.getSpeciality().getText();
        String phone = view.getPhone().getText();
        
        // Fix time formatting
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String availableFrom = timeFormat.format(view.getFrom().getValue());
        String availableTo = timeFormat.format(view.getTo().getValue());

        if (name.isEmpty() || email.isEmpty() || speciality.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Fill all the fields");
        } else {
            Doctordao doctorDao = new Doctordao();
            DoctorModel doctor = new DoctorModel(name, email, speciality, phone, availableFrom, availableTo);
            boolean result = doctorDao.addDoctor(doctor);
            if (result) {
                JOptionPane.showMessageDialog(view, "DOCTOR ADDED SUCCESSFULLY!!!!!");
            } else {
                JOptionPane.showMessageDialog(view, "SOMETHING'S GONE WRONG!!!!!");
            }
        }
    }
}

    class LoadDoctorDataListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Doctordao doctorDao = new Doctordao();
        List<DoctorModel> doctors = doctorDao.getAllDoctors();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Match these column names with your view
        model.addColumn("Doctor ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Specialization");
        model.addColumn("Phone");
        model.addColumn("Available From");
        model.addColumn("Available To");

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        for (DoctorModel doctor : doctors) {
            try {
                // Format time for better display
                String fromTime = doctor.getTimeFrom() != null ? 
                    timeFormat.format(new SimpleDateFormat("HH:mm:ss").parse(doctor.getTimeFrom())) : "";
                String toTime = doctor.getTimeTo() != null ? 
                    timeFormat.format(new SimpleDateFormat("HH:mm:ss").parse(doctor.getTimeTo())) : "";

                model.addRow(new Object[]{
                    doctor.getId(),  // Changed from getId()
                    doctor.getName(),
                    doctor.getEmail(),
                    doctor.getSpeciality(),  // Added this column
                    doctor.getPhone(),
                    fromTime,  // Formatted time
                    toTime     // Formatted time
                });
            } catch (ParseException ex) {
                // Fallback to raw values if parsing fails
                model.addRow(new Object[]{
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getEmail(),
                    doctor.getSpeciality(),
                    doctor.getPhone(),
                    doctor.getTimeFrom(),
                    doctor.getTimeTo()
                });
            }
        }

        view.getDocTable().setModel(model);
        view.getDocTable().repaint();  // Force UI refresh
    }
}


    class LoadDataListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<UserData> users = userDao.getAllUsers();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Password");
        model.addColumn("Type");

        for (UserData user : users) {
            model.addRow(new Object[]{
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUtype()
            });
        }

        view.getTable().setModel(model);
    }
    }
}
