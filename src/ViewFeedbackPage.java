import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ViewFeedbackPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewFeedbackPage.class.getName());
    
    private String currentUsername;
    private List<String[]> feedbackDetails = new ArrayList<>();
    
    public ViewFeedbackPage(String username) {
        this.currentUsername = username;
        initComponents();
        loadFeedbackHistory();
        addTableDoubleClickEvent();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        feedbackTable = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fromDateField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        toDateField = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Appointment Feedback History");

        feedbackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Appointment Date", "Vehicle", "Service Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(feedbackTable);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("*Double Click to View Full Details");

        jLabel3.setText("From Date:");

        jLabel4.setText("To Date:");

        btnFilter.setText("Filter");
        btnFilter.addActionListener(this::btnFilterActionPerformed);

        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("*Date Format: yyyy-MM-dd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fromDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(toDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(52, 52, 52)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fromDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(btnReset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(33, 33, 33))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new CustomerPage(currentUsername).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        try {
            List<String[]> allFeedback = FileManager.getCustomerFeedbackHistory(currentUsername);

            String fromText = fromDateField.getText().trim();
            String toText = toDateField.getText().trim();

            LocalDate fromDate = null;
            LocalDate toDate = null;

            if (!fromText.isEmpty()) {
                if (!FileManager.isValidDate(fromText)) {
                    JOptionPane.showMessageDialog(this, "From Date must be in yyyy-MM-dd format.");
                    return;
                }

                if (!FileManager.isNotFutureDate(fromText)) {
                    JOptionPane.showMessageDialog(this, "From Date cannot be in the future.");
                    return;
                }

                fromDate = LocalDate.parse(fromText);
            }

            if (!toText.isEmpty()) {
                if (!FileManager.isValidDate(toText)) {
                    JOptionPane.showMessageDialog(this, "To Date must be in yyyy-MM-dd format.");
                    return;
                }

                if (!FileManager.isNotFutureDate(toText)) {
                    JOptionPane.showMessageDialog(this, "To Date cannot be in the future.");
                    return;
                }

                toDate = LocalDate.parse(toText);
            }

            if (fromDate != null && toDate != null && fromDate.isAfter(toDate)) {
                JOptionPane.showMessageDialog(this, "From Date cannot be later than To Date.");
                return;
            }

            feedbackDetails.clear();

            DefaultTableModel model = (DefaultTableModel) feedbackTable.getModel();
            model.setRowCount(0);

            for (String[] record : allFeedback) {
                String appointmentDateText = record[0];
                LocalDate appointmentDate;

                try {
                    appointmentDate = LocalDate.parse(appointmentDateText);
                } catch (DateTimeParseException e) {
                    continue;
                }

                boolean match = true;

                if (fromDate != null && appointmentDate.isBefore(fromDate)) {
                    match = false;
                }

                if (toDate != null && appointmentDate.isAfter(toDate)) {
                    match = false;
                }

                if (match) {
                    feedbackDetails.add(record);

                    model.addRow(new Object[] {
                        record[0],  // appointment date
                        record[1], // vehicle
                        record[3], // service name
                        record[4] // status
                    });
                }
            }

            if (feedbackDetails.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No records found for the selected date range.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error filtering history.");
        }
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        fromDateField.setText("");
        toDateField.setText("");
        loadFeedbackHistory();
    }//GEN-LAST:event_btnResetActionPerformed
 
    private void loadFeedbackHistory() {
        try {
            feedbackDetails = FileManager.getCustomerFeedbackHistory(currentUsername);

            DefaultTableModel model = (DefaultTableModel) feedbackTable.getModel();
            model.setRowCount(0);

            for (String[] record : feedbackDetails) {
                model.addRow(new Object[] {
                    record[0], // appointment date
                    record[1], // vehicle
                    record[3], // service name
                    record[4]  // status
                });
            }

            if (feedbackDetails.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No feedback history found.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading feedback history.");
        }
    }
    
    private void addTableDoubleClickEvent() {
        feedbackTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = feedbackTable.getSelectedRow();
                    if (row >= 0) {
                        showFeedbackDetails(row);
                    }
                }
            }
        });
    }

    private void showFeedbackDetails(int row) {
        if (row < 0 || row >= feedbackDetails.size()) {
            return;
        }

        String[] details = feedbackDetails.get(row);

        String message =
                "Appointment Date: " + details[0] + "\n" +
                "Vehicle: " + details[1] + "\n" +
                "Service Type: " + details[2] + "\n" +
                "Service Name: " + details[3] + "\n" +
                "Status: " + details[4] + "\n" +
                "Feedback Text: " + details[5] + "\n" +
                "Feedback Date: " + details[6];

        JOptionPane.showMessageDialog(
                this,
                message,
                "Feedback Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnReset;
    private javax.swing.JTable feedbackTable;
    private javax.swing.JTextField fromDateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField toDateField;
    // End of variables declaration//GEN-END:variables
}
