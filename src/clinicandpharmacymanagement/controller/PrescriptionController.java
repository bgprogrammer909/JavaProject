/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Controller;

/**
 *
 * @author lenovo
 */
public class PrescriptionController {
    private PrescriptionView view;
    private PrescriptionDAO dao;

    public PrescriptionController(PrescriptionView view, PrescriptionDAO dao) {
        this.view = view;
        this.dao = dao;

        this.view.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPrescriptions();
            }
        });
    }

    private void loadPrescriptions() {
        List<Prescription> list = dao.getAllPrescriptions();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0); // clear old data
        for (Prescription p : list) {
            model.addRow(new Object[]{p.getTime(), p.getDescription(), p.getMedicine(), p.getDosage()});
        }
    }
}
