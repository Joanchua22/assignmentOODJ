import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ViewServiceNPayment extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewServiceNPayment.class.getName());

    private String currentUserId;
    
    private List<String[]> historyDetails = new ArrayList<>();
    
    public ViewServiceNPayment(String currentUserId) {
        this.currentUserId = currentUserId;
        initComponents();
        loadHistory();
        addTableDoubleClickEvent();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toDateField = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HistoryTable = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fromDateField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnFilter.setText("Filter");
        btnFilter.addActionListener(this::btnFilterActionPerformed);

        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("*Date Format: yyyy-MM-dd");

        jLabel1.setText("Service & Payment History");

        HistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Vehicle", "Service Type", "Appointment Date", "Amount", "Payment Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HistoryTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(HistoryTable);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel2.setText("From Date:");

        jLabel3.setText("To Date:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fromDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(37, 37, 37))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBack)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fromDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilter))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btnReset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addContainerGap(21, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void loadHistory() {
        try {
            historyDetails = FileManager.getCustomerHistory(currentUserId);

            DefaultTableModel model = (DefaultTableModel) HistoryTable.getModel();
            model.setRowCount(0);

            for (String[] record : historyDetails) {
                model.addRow(new Object[] {
                    record[1], 
                    record[2], 
                    record[3], 
                    record[4],
                    record[5]
                });
            }

            if (historyDetails.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No service and payment history found.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading history.");
        }
    }

        private void addTableDoubleClickEvent() {
            HistoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int row = HistoryTable.getSelectedRow();
                        if (row >= 0) {
                            showHistoryDetails(row);
                        }
                    }
                }
            });
        }

    private void showHistoryDetails(int row) {
        if (row < 0 || row >= historyDetails.size()) {
            return;
        }

        String[] details = historyDetails.get(row);

        String message =
                "Vehicle: " + details[1] + "\n" +
                "Service Type: " + details[2] + "\n" +
                "Appointment Date: " + details[3] + "\n" +
                "Start Time: " + details[9] + "\n" +
                "End Time: " + details[10] + "\n" +
                "Amount: " + details[4] + "\n" +
                "Payment Status: " + details[5] + "\n" +
                "Payment Method: " + details[6] + "\n" +
                "Payment Date: " + details[7] + "\n" +
                "Appointment Status: " + details[11] + "\n" +
                "Remarks: " + details[8];

        JOptionPane.showMessageDialog(
                this,
                message,
                "Service & Payment Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void filterHistory() {
        try {
            List<String[]> allHistory = FileManager.getCustomerHistory(currentUserId);

            String fromText = fromDateField.getText().trim();
            String toText = toDateField.getText().trim();

            LocalDate fromDate = null;
            LocalDate toDate = null;

            if (!fromText.isEmpty()) {

                if (!FileManager.isValidDate(fromText)) {
                    JOptionPane.showMessageDialog(this,
                            "From Date must be in yyyy-MM-dd format.");
                    return;
                }

                if (!FileManager.isNotFutureDate(fromText)) {
                    JOptionPane.showMessageDialog(this,
                            "From Date cannot be in the future.");
                    return;
                }

                fromDate = LocalDate.parse(fromText);
            }

            if (!toText.isEmpty()) {

                if (!FileManager.isValidDate(toText)) {
                    JOptionPane.showMessageDialog(this,
                            "To Date must be in yyyy-MM-dd format.");
                    return;
                }

                if (!FileManager.isNotFutureDate(toText)) {
                    JOptionPane.showMessageDialog(this,
                            "To Date cannot be in the future.");
                    return;
                }

                toDate = LocalDate.parse(toText);
            }

            if (fromDate != null
                    && toDate != null
                    && fromDate.isAfter(toDate)) {

                JOptionPane.showMessageDialog(this,
                        "From Date cannot be later than To Date.");
                return;
            }

            historyDetails.clear();

            DefaultTableModel model =
                    (DefaultTableModel) HistoryTable.getModel();

            model.setRowCount(0);

            for (String[] record : allHistory) {

                String appointmentDateText = record[3];
                LocalDate appointmentDate;

                try {
                    appointmentDate =
                            LocalDate.parse(appointmentDateText);

                } catch (DateTimeParseException e) {
                    continue;
                }

                boolean match = true;

                if (fromDate != null
                        && appointmentDate.isBefore(fromDate)) {

                    match = false;
                }

                if (toDate != null
                        && appointmentDate.isAfter(toDate)) {

                    match = false;
                }

                if (match) {

                    historyDetails.add(record);

                    model.addRow(new Object[]{
                        record[1],
                        record[2],
                        record[3],
                        record[4],
                        record[5]
                    });
                }
            }

            if (historyDetails.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No records found for the selected date range.");
            }

        } catch (IOException ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    "Error filtering history.");
        }
    }
    
    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        filterHistory();
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        fromDateField.setText("");
        toDateField.setText("");
        loadHistory();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new Customer(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable HistoryTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnReset;
    private javax.swing.JTextField fromDateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField toDateField;
    // End of variables declaration//GEN-END:variables
}
