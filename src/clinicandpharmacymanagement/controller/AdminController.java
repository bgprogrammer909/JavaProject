package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.AdminDashboardPharma;

import clinicandpharmacymanagement.view.model.UserData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminController {
    AdminDashboardPharma view;
    UserData user;
    UserDao userDao = new UserDao();  

    public AdminController(AdminDashboardPharma view, UserData user) {
        this.view = view;
        this.user = user;
        this.view.refresh(new LoadDataListener());
        this.view.refresh2(new LoadStaffDataListener());
        this.view.registerStaff(new RegisterStaff());
        this.view.deleteStaff(new DeleteStaff());
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
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
