/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;


import clinicandpharmacymanagement.Dao.Appointmentdao;
import clinicandpharmacymanagement.Dao.Doctordao;
import clinicandpharmacymanagement.Dao.PrescriptionsDao;
import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.SampleCard;
import clinicandpharmacymanagement.view.InfoDetail;
import clinicandpharmacymanagement.view.model.DoctorModel;
import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.PrescriptionModel;
import clinicandpharmacymanagement.view.model.UserData;
import clinicandpharmacymanagement.view.model.ViewAppointment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;




/**
 *
 * @author ACER
 */
public class PatientDashboardController {
    SampleCard view;
    UserData user;
    LoginRequest req;
    public PatientDashboardController(SampleCard view,UserData user,LoginRequest req){
        this.view=view;
        this.user=user;
        this.req=req;
        this.view.editIt(new Editor());
        this.view.prof(new Names());
        this.view.refreshDoctor(new LoadDoctorDataListener());
        this.view.Book(new Appoint());
        this.view.setPrescription(new LoadLatestPrescription());
        this.view.getAllPrescription(new LoadAllPrescription() );
        this.view.getLatestPrescription(new LoadLatestPrescription());
        this.view.getAppointings(new LoadPatientAppointmentsListener());
    }
    class LoadAllPrescription implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PrescriptionsDao prescriptionDao=new PrescriptionsDao();
        String userId = user.getId();  // or however you have the patient id
        List<PrescriptionModel> prescriptions = prescriptionDao.getAllPrescriptionsByUserId(userId);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Prescription ID");
        model.addColumn("Morning");
        model.addColumn("Day");
        model.addColumn("Night");
        model.addColumn("Date");

        if (prescriptions != null) {
            for(PrescriptionModel p:prescriptions){
            model.addRow(new Object[]{
                p.getPrescriptionId(),
                p.getMorningDescription(),
                p.getDayDescription(),
                p.getNightDescription(),
                p.getCreatedAt()
            });
            }
        } else {
            JOptionPane.showMessageDialog(view, "No prescription found.");
        }

        view.getPrescriptionTable().setModel(model);
    }
}
class LoadPatientAppointmentsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String userId = user.getId();  // or however you store the patient ID
        Appointmentdao appointmentDao=new Appointmentdao();
        List<ViewAppointment> appointments = appointmentDao.getAppointmentsByUserId(userId);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Appointment ID");
        model.addColumn("Doctor");
        model.addColumn("Time Slot");
        model.addColumn("Status");

        for (ViewAppointment appt : appointments) {
            model.addRow(new Object[]{
                appt.getAppointmentId(),
                appt.getDoctorName(),
                appt.getTimeSlot(),
                appt.getStatus()
            });
        }

        view.getAppointmentTable().setModel(model);
    }
}

    class LoadLatestPrescription implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PrescriptionsDao prescriptionDao=new PrescriptionsDao();
        String userId = user.getId();  // or however you have the patient id
        PrescriptionModel p = prescriptionDao.getLatestPrescriptionByUserId(userId);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Prescription ID");
        model.addColumn("Morning");
        model.addColumn("Day");
        model.addColumn("Night");
        model.addColumn("Date");

        if (p != null) {
            model.addRow(new Object[]{
                p.getPrescriptionId(),
                p.getMorningDescription(),
                p.getDayDescription(),
                p.getNightDescription(),
                p.getCreatedAt()
            });
        } else {
            JOptionPane.showMessageDialog(view, "No prescription found.");
        }

        view.getPrescriptionTable().setModel(model);
    }
}
    
    

class Appoint implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Doctordao doctorDao=new Doctordao();
        Appointmentdao appointmentDao=new Appointmentdao();
        int selectedRow = view.getDocTable().getSelectedRow();
        if (selectedRow != -1) {
            int doctorId = Integer.parseInt(view.getDocTable().getValueAt(selectedRow, 0).toString());

            String inputTime = JOptionPane.showInputDialog(null, "Enter appointment time (24-hour format, e.g., 14:00):");

            if (inputTime != null && !inputTime.trim().isEmpty()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime appointmentTime = LocalTime.parse(inputTime.trim(), formatter);

                    LocalTime[] available = doctorDao.getDoctorAvailableTime(doctorId);
                    if (available == null) {
                        JOptionPane.showMessageDialog(null, "Failed to retrieve doctor's availability.");
                        return;
                    }

                    if (appointmentTime.isBefore(available[0]) || appointmentTime.isAfter(available[1])) {
                        JOptionPane.showMessageDialog(null, "Selected time is outside doctor's available time (" +
                                available[0] + " - " + available[1] + ").");
                        return;
                    }
                    String patientId=(user.getId());
                    boolean success = appointmentDao.bookAppointment(patientId, doctorId, inputTime.trim());
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Appointment booked successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to book appointment.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid time format. Please enter as HH:mm (e.g., 14:00).");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Time slot is required.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a doctor to book an appointment.");
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
        private class Names implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadUserData();
        }
        private void loadUserData() {
        try {
            UserDao userdao=new UserDao();
            // Use the DAO to fetch patient data
            UserData fetchedUser = userdao.getPatientByEmail(req.getEmail(),user);
            
            if (fetchedUser != null) {
                // Update the view with the retrieved data
                updateViewWithUserData(fetchedUser);
                
                // Update the local user object
                user = fetchedUser;
            } else {
                showErrorMessage("User not found in database!");
            }
        } catch (Exception ex) {
            showErrorMessage("Error loading user data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
        private void updateViewWithUserData(UserData userData) {
         
         view.getId().setText(userData.getId());
         view.getWelcome().setText("Hello "+userData.getName());
         view.getHello().setText("Hello "+userData.getName());
        view.getTheName().setText(userData.getName());
        view.getAge().setText(String.valueOf(userData.getAge()));
        view.getBloodtype().setText(userData.getBloodGroup());
        view.getGender().setText(userData.getGender());
    }
    }
    
    
    class Editor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            InfoDetail viewer=new InfoDetail();
            InfoDetailController con=new InfoDetailController(viewer,user,req);
            con.open();
            
        }
        
    }
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    
    
}
