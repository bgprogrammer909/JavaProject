/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.Medicinedao;
import clinicandpharmacymanagement.Dao.Appointmentdao;
import clinicandpharmacymanagement.Dao.PrescriptionsDao;
import clinicandpharmacymanagement.Dao.UserCountDao;
import clinicandpharmacymanagement.view.Paymentdash;
import clinicandpharmacymanagement.view.StaffDash;
import clinicandpharmacymanagement.view.model.MedicineModel;
import clinicandpharmacymanagement.view.model.PrescriptionModel;
import clinicandpharmacymanagement.view.model.ViewAppointment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StaffController {
    StaffDash view;
    Medicinedao dao = new Medicinedao();
    Appointmentdao appointmentDao = new Appointmentdao();
    Medicinedao medicinedao=new Medicinedao();
    public StaffController(StaffDash view) {
        this.view = view;
        this.view.addmedicine(new Adder());
        this.view.appointment(new ViewAppoint());
        this.view.cancelt(new CancelAppoint());
        this.view.doneIt(new DoneAppoint());
        this.view.prescribeIt(new AddPrescriptionListener());
        this.view.prescriptionTable(new PrescriptionView());
        this.view.updateMed(new Sold());
        this.view.throwMed(new DisposeMed());
        this.view.doBilling(new GoBilling());
        getNames();
    }
    private void getNames(){
        UserCountDao countDao = new UserCountDao();
        
        // Get all counts
        
        int patients = countDao.countPatients();
        
        int doctors = countDao.countDoctors();
        
        // Update labels with formatted text
        
        view.getTotalpatients().setText(""+patients);
        
        view.getTotaldoctors().setText(""+doctors);
    }
    class GoBilling implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Paymentdash viewer=new Paymentdash();
            PaymentController conto=new PaymentController(viewer);
            conto.open();
        }
        
    }
    class DisposeMed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           int row = view.getMedcineTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a medicine to delete.");
            return;
        }

        int id = (int) view.getMedcineTable().getValueAt(row, 0);
        String name = (String) view.getMedcineTable().getValueAt(row, 1);

        int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete: " + name + "?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return; // user cancelled
        }

        boolean success = medicinedao.deleteMedicine(id);
        if (success) {
            JOptionPane.showMessageDialog(null, "Medicine disposed successfully.");
            reloadTable();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to dispose medicine.");
        }
    }

    private void reloadTable() {
        List<MedicineModel> medicines = medicinedao.getAllMedicines();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Amount", "Expiry Date", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Date today = new Date();
        for (MedicineModel med : medicines) {
            String status = med.getExpiryDate().before(today) ? "Expired" : "Available";
            model.addRow(new Object[]{
                med.getId(),
                med.getName(),
                med.getPrice(),
                med.getAmount(),
                med.getExpiryDate().toString(),
                status
            });
        }
        view.getMedcineTable().setModel(model);
    
        }
        
    }
    class Sold implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        int row = view.getMedcineTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a medicine to update.");
            return;
        }

        int id = (int) view.getMedcineTable().getValueAt(row, 0);
        int currentAmount = (int) view.getMedcineTable().getValueAt(row, 3);

        String input = JOptionPane.showInputDialog("Enter quantity to sell:");
        if (input == null) return;  // user cancelled

        int sellAmount;
        try {
            sellAmount = Integer.parseInt(input.trim());
            if (sellAmount < 0) {
                JOptionPane.showMessageDialog(null, "Enter a positive number.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid number.");
            return;
        }

        if (sellAmount > currentAmount) {
            JOptionPane.showMessageDialog(null, "Not enough stock.");
            return;
        }

        int newAmount = currentAmount - sellAmount;

        boolean success = medicinedao.updateAmount(id, newAmount);
        if (success) {
            JOptionPane.showMessageDialog(null, "Amount updated successfully.");
            // Refresh table
            List<MedicineModel> medicines = medicinedao.getAllMedicines();
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Amount", "Expiry Date", "Status"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            Date today = new Date();
            for (MedicineModel med : medicines) {
                String status = med.getExpiryDate().before(today) ? "Expired" : "Available";
                model.addRow(new Object[]{
                    med.getId(),
                    med.getName(),
                    med.getPrice(),
                    med.getAmount(),
                    med.getExpiryDate().toString(),
                    status
                });
            }
            view.getMedcineTable().setModel(model);

        } else {
            JOptionPane.showMessageDialog(null, "Failed to update amount.");
        }
  
        }
        
    }
      class PrescriptionView implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        List<MedicineModel> medicines = medicinedao.getAllMedicines();

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Amount", "Expiry Date", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // make table read-only
            }
        };

        Date today = new Date();

        for (MedicineModel med : medicines) {
            String status = med.getExpiryDate().before(today) ? "Expired" : "Available";
            model.addRow(new Object[]{
                med.getId(),
                med.getName(),
                med.getPrice(),
                med.getAmount(),
                med.getExpiryDate().toString(),
                status
            });
        }

        view.getMedcineTable().setModel(model);
    
        }
    }
    class Adder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        
        String name = view.getName1().getText();
        String priceText = view.getPrice().getText();
        String amountText = view.getAmount().getText();
        String expiryText = view.getExpire().getText();

        // Basic validation
        if (name.isEmpty() || priceText.isEmpty() || amountText.isEmpty() || expiryText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields.");
            return;
        }

        double price;
        int amount;
        Date expiryDate;

        try {
            price = Double.parseDouble(priceText);
            amount = Integer.parseInt(amountText);

            // Validate date format
            if (!expiryText.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(null, "Invalid date format! Use YYYY-MM-DD");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            expiryDate = sdf.parse(expiryText);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Price must be a number and amount must be an integer.");
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid date value.");
            return;
        }

        MedicineModel med = new MedicineModel();
        med.setName(name);
        med.setPrice(price);
        med.setAmount(amount);
        med.setExpiryDate(expiryDate);

        boolean success = medicinedao.addMedicine(med);
        if (success) {
            JOptionPane.showMessageDialog(null, "Medicine added successfully!");
            view.getName1().setText("");
            view.getAmount().setText("");
            view.getPrice().setText("");
            view.getExpire().setText("");
            List<MedicineModel> medicines = medicinedao.getAllMedicines();
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Amount", "Expiry Date", "Status"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            Date today = new Date();
            for (MedicineModel medi : medicines) {
                String status = med.getExpiryDate().before(today) ? "Expired" : "Available";
                model.addRow(new Object[]{
                    medi.getId(),
                    medi.getName(),
                    medi.getPrice(),
                    medi.getAmount(),
                    medi.getExpiryDate().toString(),
                    status
                });
            }
            view.getMedcineTable().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add medicine.");
        }
         
    }
}
    

    // View Appointments
    class ViewAppoint implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<ViewAppointment> appointments = appointmentDao.getAllAppointmentsWithNames();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Appointment ID");
            model.addColumn("Doctor Name");
            model.addColumn("Patient Name");
            model.addColumn("Time Slot");
            model.addColumn("Status");
            
            for (ViewAppointment appt : appointments) {
                model.addRow(new Object[]{
                    appt.getAppointmentId(),
                    appt.getDoctorName(),
                    appt.getPatientName(),
                    appt.getTimeSlot(),
                    appt.getStatus()
                });
            }

            view.getAppointmentTable().setModel(model);
        }
    }
    class DoneAppoint implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = view.getAppointmentTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select an appointment to mark as done.");
            return;
        }

        int appointmentId = (int) view.getAppointmentTable().getValueAt(selectedRow, 0);
        boolean result = appointmentDao.updateAppointmentStatus(appointmentId, "Done");

        if (result) {
            JOptionPane.showMessageDialog(view, "Appointment marked as done.");
            refreshAppointmentTable();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update appointment status.");
        }
    }
}

class CancelAppoint implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = view.getAppointmentTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select an appointment to cancel.");
            return;
        }

        int appointmentId = (int) view.getAppointmentTable().getValueAt(selectedRow, 0);
        boolean result = appointmentDao.updateAppointmentStatus(appointmentId, "Cancelled");

        if (result) {
            JOptionPane.showMessageDialog(view, "Appointment cancelled successfully.");
            refreshAppointmentTable();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to cancel appointment.");
        }
    }
}
private void refreshAppointmentTable() {
    List<ViewAppointment> appointments = appointmentDao.getAllAppointmentsWithNames();

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Appointment ID");
    model.addColumn("Doctor Name");
    model.addColumn("Patient Name");
    model.addColumn("Time Slot");
    model.addColumn("Status");

    for (ViewAppointment appt : appointments) {
        model.addRow(new Object[]{
            appt.getAppointmentId(),
            appt.getDoctorName(),
            appt.getPatientName(),
            appt.getTimeSlot(),
            appt.getStatus()
        });
    }

    view.getAppointmentTable().setModel(model);
}
class AddPrescriptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String userId = view.getPatientId().getText();  // Ensure you have these text fields
            String morning = view.getMorning().getText();
            String day = view.getDay().getText();
            String night = view.getNight().getText();

            if (morning.isEmpty() && day.isEmpty() && night.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please provide at least one description.");
                return;
            }

            PrescriptionModel prescription = new PrescriptionModel(userId, morning, day, night);
            PrescriptionsDao dao = new PrescriptionsDao();
            boolean success = dao.addPrescription(prescription);

            if (success) {
                JOptionPane.showMessageDialog(view, "Prescription added successfully.");
                view.getPatientId().setText("");
                view.getMorning().setText("");
                view.getNight().setText("");
                view.getDay().setText("");
                
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add prescription.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid User ID.");
        }
    }
}

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
}
