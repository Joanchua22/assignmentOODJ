
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.table.DefaultTableModel;

public class TechAddFeedback extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TechAddFeedback.class.getName());

    private String currentUserId;
    public TechAddFeedback(String currentUserId) {
        this.currentUserId = currentUserId;
        initComponents();
        loadAppointments();
        addTableDoubleClickEvent();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        appointmentTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        appointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Appointment ID", "Vehicle ID", "CustomerID", "Service Type ID", "Appointment Date", "Status", "Feedback Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(appointmentTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Leave Feedback");

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(btnBack)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void loadAppointments() {
        DefaultTableModel model = (DefaultTableModel) appointmentTable.getModel();
        model.setRowCount(0);
        try (BufferedReader br = new BufferedReader(
                new FileReader(FileManager.APPOINTMENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length < 11) continue;
                if (!d[4].trim().equalsIgnoreCase(currentUserId.trim()))
                    continue;
                String appointmentId = d[0];
                String feedbackStatus = hasFeedback(appointmentId)
                        ? "Provided"
                        : "Not Provided";
                model.addRow(new Object[]{
                    d[0], d[1], d[2], d[5], d[6],d[9],feedbackStatus
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   private boolean hasFeedback(String appointmentId) {
    try (BufferedReader br = new BufferedReader(
        new FileReader(FileManager.FEEDBACK_FILE))) {
            String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length < 2) continue;
            if (data[1].trim().equalsIgnoreCase(appointmentId.trim())) {
                 return true;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
      return false;
    }
   
    private void addTableDoubleClickEvent() {
        appointmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2 && appointmentTable.getSelectedRow() != -1) {

                    int row = appointmentTable.convertRowIndexToModel(appointmentTable.getSelectedRow());

                    String appointmentId = appointmentTable.getModel().getValueAt(row, 0).toString();

                    showFeedbackPopup(appointmentId);
                }
            }
        });
    }
    
    private void showFeedbackPopup(String appointmentId) {
    String[] existingFeedback = getExistingFeedback(appointmentId);
    String feedbackId;
    String feedbackDate;
    String feedbackText;
    if (existingFeedback != null) {
        feedbackId = existingFeedback[0];
        feedbackText = existingFeedback[2];
        feedbackDate = existingFeedback[3];
    } else {
        feedbackId = generateFeedbackId();
        feedbackText = "";
        feedbackDate = java.time.LocalDate.now().toString();
    }
    javax.swing.JTextField txtFeedbackId = new javax.swing.JTextField(feedbackId);
    javax.swing.JTextField txtAppointmentId = new javax.swing.JTextField(appointmentId);
    javax.swing.JTextField txtFeedbackDate = new javax.swing.JTextField(feedbackDate);
    javax.swing.JTextArea txtFeedback = new javax.swing.JTextArea(feedbackText, 6, 35);
    txtFeedbackId.setEditable(false);
    txtAppointmentId.setEditable(false);
    txtFeedbackDate.setEditable(false);
    txtFeedback.setLineWrap(true);
    txtFeedback.setWrapStyleWord(true);
    javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
    gbc.insets = new java.awt.Insets(8, 8, 8, 8);
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new javax.swing.JLabel("Feedback ID:"), gbc);
    gbc.gridx = 1;
    gbc.weightx = 1.0;
    panel.add(txtFeedbackId, gbc);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    panel.add(new javax.swing.JLabel("Appointment ID:"), gbc);
    gbc.gridx = 1;
    gbc.weightx = 1.0;
    panel.add(txtAppointmentId, gbc);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;
    panel.add(new javax.swing.JLabel("Feedback Date:"), gbc);
    gbc.gridx = 1;
    gbc.weightx = 1.0;
    panel.add(txtFeedbackDate, gbc);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 0;
    gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
    panel.add(new javax.swing.JLabel("Feedback:"), gbc);
    gbc.gridx = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = java.awt.GridBagConstraints.BOTH;
    panel.add(new javax.swing.JScrollPane(txtFeedback), gbc);
    panel.setPreferredSize(new java.awt.Dimension(550, 330));
    int result = javax.swing.JOptionPane.showConfirmDialog(
            this,
            panel,
            "Technician Feedback",
            javax.swing.JOptionPane.OK_CANCEL_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE
    );

    if (result == javax.swing.JOptionPane.OK_OPTION) {
        String newFeedbackText = txtFeedback.getText().trim();
        if (newFeedbackText.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Feedback cannot be empty.");
            return;
        }
        if (existingFeedback != null) {
            updateTechnicianFeedback(feedbackId, appointmentId, newFeedbackText, feedbackDate);
        } else {
        saveTechnicianFeedback(feedbackId, appointmentId, newFeedbackText, feedbackDate);
        }
        loadAppointments();
        }
    }
    
    private void updateTechnicianFeedback(String feedbackId, String appointmentId, String feedbackText, String feedbackDate) {
        java.io.File input = new java.io.File(FileManager.FEEDBACK_FILE);
        java.io.File temp = new java.io.File("temp_feedback.txt");
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(input));
            java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(temp))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 4);
                if (data.length >= 2 && data[1].trim().equalsIgnoreCase(appointmentId.trim())) {
                    line = feedbackId + "," + appointmentId + "," + feedbackText + "," + feedbackDate;
                }
                pw.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error updating feedback: " + e.getMessage());
            return;
        }
        if (input.delete()) {
            temp.renameTo(input);
            javax.swing.JOptionPane.showMessageDialog(this, "Feedback updated successfully.");
        }
    }
    
    private String[] getExistingFeedback(String appointmentId) {
    try (java.io.BufferedReader br = new java.io.BufferedReader(
            new java.io.FileReader(FileManager.FEEDBACK_FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",", 4);
            if (data.length < 4) continue;
            if (data[1].trim().equalsIgnoreCase(appointmentId.trim())) {
                return data;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
    }
    
    private void saveTechnicianFeedback(String feedbackId, String appointmentId,String feedbackText, String feedbackDate) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(
                new java.io.FileWriter(FileManager.FEEDBACK_FILE, true))) {
            pw.println(feedbackId + "," + appointmentId + "," + feedbackText + "," + feedbackDate);
            javax.swing.JOptionPane.showMessageDialog(this, "Feedback submitted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error saving feedback: " + e.getMessage());
        }
    }
    
    private String generateFeedbackId() {
        int max = 0;
        try (java.io.BufferedReader br = new java.io.BufferedReader(
                new java.io.FileReader(FileManager.FEEDBACK_FILE))) {
            String line;        
        while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                    if (data.length > 0 && data[0].startsWith("TFB")) {
                    int num = Integer.parseInt(data[0].substring(3));
                    if (num > max) {
                        max = num;
                    }
                }
            }
        } catch (Exception e) {  
        }
        return String.format("TFB%04d", max + 1);
    }
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
    new TechnicianPage(currentUserId).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed
public static void main(String args[]) {

    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        logger.log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(() ->
        new TechAddFeedback("USR0002").setVisible(true)
    );
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable appointmentTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
