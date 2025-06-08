package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.AdminDashboardPharma;
import clinicandpharmacymanagement.view.model.UserData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class AdminController {
    AdminDashboardPharma view;
    UserData user;
    UserDao userDao = new UserDao();  // Added instance

    public AdminController(AdminDashboardPharma view, UserData user) {
        this.view = view;
        this.user = user;
        // Removed the unnecessary AdminDashboardPharma1 ad2 instantiation
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    class LoadDataListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<UserData> users = userDao.getAllUsers();  // Use instance, not static call
            DefaultTableModel model = new DefaultTableModel();
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
