package clinicandpharmacymanagement.controller;


import clinicandpharmacymanagement.Dao.Billingdao;
import clinicandpharmacymanagement.view.Paymentdash;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class PaymentController {
    private Paymentdash view;
    private Billingdao billDao = new Billingdao();

    public PaymentController(Paymentdash view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getCalculateButton().addActionListener(e -> calculateTotal());
        view.getGenerateButton().addActionListener(e -> generateBill());
        view.getBackButton().addActionListener(e -> closeView());
    }

    private void calculateTotal() {
        double total = 0.0;
        TableModel model = view.getTable().getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            Object qtyObj = model.getValueAt(i, 1);
            Object costObj = model.getValueAt(i, 2);

            if (qtyObj != null && costObj != null) {
                try {
                    int qty = Integer.parseInt(qtyObj.toString());
                    double cost = Double.parseDouble(costObj.toString());
                    total += qty * cost;
                } catch (NumberFormatException ex) {
                    // Skip invalid data
                }
            }
        }

        view.getTotalField().setText(String.format("%.2f", total));
    }

    private void generateBill() {
        String patientId = view.getPatientIdField().getText().trim();
        if (patientId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Patient ID is required!");
            return;
        }

        TableModel model = view.getTable().getModel();
        StringBuilder bill = new StringBuilder();

        bill.append("=========== HOSPITAL BILL ===========\n");
        bill.append("Patient ID: ").append(patientId).append("\n\n");
        bill.append(String.format("%-20s %-10s %-10s\n", "Service", "Qty", "Cost"));
        bill.append("-------------------------------------------\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            Object service = model.getValueAt(i, 0);
            Object qty = model.getValueAt(i, 1);
            Object cost = model.getValueAt(i, 2);

            if (service != null && qty != null && cost != null) {
                bill.append(String.format("%-20s %-10s %-10s\n", service, qty, cost));
            }
        }

        double totalAmount = Double.parseDouble(view.getTotalField().getText());
        bill.append("\nTotal Amount: NPR ").append(view.getTotalField().getText());
        bill.append("\n=====================================");

        // Show bill
        JOptionPane.showMessageDialog(view, bill.toString(), "Generated Bill", JOptionPane.INFORMATION_MESSAGE);

        // Save to DB
        boolean saved = billDao.saveBill(patientId, bill.toString(), totalAmount);
        if (saved) {
            JOptionPane.showMessageDialog(view, "Bill saved to database successfully.");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to save bill to database.");
        }
    }

    private void closeView() {
        view.dispose();
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
}
