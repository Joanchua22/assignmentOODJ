
import java.io.IOException;
import javax.swing.JOptionPane;

public class ViewTechnicianFeedbackDetails extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewTechnicianFeedbackDetails.class.getName());

    private final String feedbackId;
    private final String currentUserId;

    public ViewTechnicianFeedbackDetails(String feedbackId, String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        this.feedbackId = feedbackId;
        loadFeedbackDetails();
    }
    
    private void loadFeedbackDetails() {
    try {
        String[] details = FileManager.getTechnicianFeedbackDetailsById(feedbackId);

        if (details == null) {
            JOptionPane.showMessageDialog(this, "Feedback details not found.");
            return;
        }

            feedbackIdField.setText(details[0]);
            appointmentIdField.setText(details[1]);
            technicianField.setText(details[2]);
            feedbackDateField.setText(details[3]);
            technicianFeedbackField.setText(details[4]);
            vehicleIdField.setText(details[5]);
            serviceTypeField.setText(details[6]);
            serviceItemField.setText(details[7]);

            feedbackIdField.setEditable(false);
            appointmentIdField.setEditable(false);
            technicianField.setEditable(false);
            feedbackDateField.setEditable(false);
            vehicleIdField.setEditable(false);
            serviceTypeField.setEditable(false);
            serviceItemField.setEditable(false);
            technicianFeedbackField.setEditable(false);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading feedback details.");
        }
    }
    
    private void viewVehicleDetails() {
        String vehicleId = vehicleIdField.getText().trim();
        if (vehicleId.isEmpty() || vehicleId.equals("-")) {
            JOptionPane.showMessageDialog(this,"No vehicle selected."
            );
            return;
        }

        try {
            String[] vehicle = FileManager.getVehicleDetailsById(vehicleId);
            if (vehicle == null) {
                JOptionPane.showMessageDialog(this,"Vehicle details not found."
                );
                return;
            }
            showVehicleDetailsPopup(vehicle);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading vehicle details."
            );
        }
    }
    
    private void showVehicleDetailsPopup(String[] vehicle) {

        JOptionPane.showMessageDialog(
                this,
                "Customer ID: " + vehicle[1] +
                "\nCustomer Username: " + vehicle[2] +
                "\nVehicle Plate No: " + vehicle[3] +
                "\nVehicle Type: " + vehicle[4] +
                "\nVehicle Model: " + vehicle[5] +
                "\nYear of Manufacture: " + vehicle[6],
                "Vehicle Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void viewAppointmentDetails() {

        String appointmentId = appointmentIdField.getText().trim();

        if (appointmentId.isEmpty()|| appointmentId.equals("-")) {
            JOptionPane.showMessageDialog(this, "No appointment selected."
            );
            return;
        }

        try {
            String[] appt = FileManager.getAppointmentDetailsById(appointmentId);
            if (appt == null) {
                JOptionPane.showMessageDialog(this,"Appointment details not found.");

                return;
            }
            showAppointmentDetailsPopup(appt);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this,"Error loading appointment details.");
        }
    }
    
    private void showAppointmentDetailsPopup(String[] appt) {

        JOptionPane.showMessageDialog(
                this,
                "\nCustomer ID: " + appt[5] +
                "\nCustomer Username: " + appt[6] +
                "\nDate: " + appt[0] +
                "\nTime: " + appt[1] + " - " + appt[2] +
                "\nStatus: " + appt[3] +
                "\nCounter Staff: " + appt[4],
                "Appointment Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        feedbackIdField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        appointmentIdField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        technicianField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        vehicleIdField = new javax.swing.JTextField();
        viewDetailsBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        serviceTypeField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        serviceItemField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        feedbackDateField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        technicianFeedbackField = new javax.swing.JTextArea();
        backBtn = new javax.swing.JButton();
        appoinmentBtn = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Technician Feedback");

        jLabel2.setText("Feedback ID:");

        jLabel3.setText("Appointmen ID:");

        jLabel4.setText("Technician:");

        jLabel7.setText("Vehicle ID:");

        viewDetailsBtn.setText("View Details");
        viewDetailsBtn.addActionListener(this::viewDetailsBtnActionPerformed);

        jLabel8.setText("Service Type:");

        jLabel9.setText("Service Item:");

        jLabel11.setText("Feedback Date:");

        jLabel10.setText("Techinician Feedback:");

        technicianFeedbackField.setColumns(20);
        technicianFeedbackField.setRows(5);
        jScrollPane1.setViewportView(technicianFeedbackField);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        appoinmentBtn.setText("View Details");
        appoinmentBtn.addActionListener(this::appoinmentBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(feedbackDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(technicianField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(appoinmentBtn)
                                    .addComponent(appointmentIdField)
                                    .addComponent(feedbackIdField))
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(viewDetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(36, 36, 36))
                                            .addComponent(vehicleIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(serviceTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(serviceItemField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(100, 100, 100))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(309, 309, 309))
            .addGroup(layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(feedbackIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(appointmentIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(appoinmentBtn)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(technicianField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(vehicleIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewDetailsBtn)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(serviceTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(serviceItemField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(feedbackDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(backBtn)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ViewTechnicianFeedback(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void viewDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsBtnActionPerformed
        viewVehicleDetails();
    }//GEN-LAST:event_viewDetailsBtnActionPerformed

    private void appoinmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appoinmentBtnActionPerformed
        viewAppointmentDetails();
    }//GEN-LAST:event_appoinmentBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appoinmentBtn;
    private javax.swing.JTextField appointmentIdField;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField feedbackDateField;
    private javax.swing.JTextField feedbackIdField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField serviceItemField;
    private javax.swing.JTextField serviceTypeField;
    private javax.swing.JTextArea technicianFeedbackField;
    private javax.swing.JTextField technicianField;
    private javax.swing.JTextField vehicleIdField;
    private javax.swing.JButton viewDetailsBtn;
    // End of variables declaration//GEN-END:variables
}
