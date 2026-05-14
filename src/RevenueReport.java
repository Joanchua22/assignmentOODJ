import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RevenueReport extends javax.swing.JFrame {
    
    private String currentUserId;

    public RevenueReport(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        loadRevenueReport();
        
        revenueTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = revenueTable.getSelectedRow();

                    if (selectedRow == -1) {
                        return;
                    }

                    String paymentId = revenueTable.getValueAt(selectedRow, 0).toString();
                    showPaymentDetailsPopup(paymentId);
                }
            }
        });
    }
    
    private void showPaymentDetailsPopup(String paymentId) {
        try {
            String[] details = FileManager.getPaymentDetailsById(paymentId);

            if (details == null) {
                JOptionPane.showMessageDialog(this, "Payment details not found.");
                return;
            }

            JDialog dialog = new JDialog(this, "Payment Details", true);
            dialog.setSize(480, 430);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout(10, 10));

            JPanel panel = new JPanel(new GridLayout(0, 2, 15, 12));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 15, 25));

            panel.add(new JLabel("Payment ID:"));
            panel.add(new JLabel(details[0]));

            panel.add(new JLabel("Appointment ID:"));
            panel.add(new JLabel(details[1]));

            panel.add(new JLabel("Service Item:"));
            panel.add(new JLabel(details[2]));

            panel.add(new JLabel("Amount:"));
            panel.add(new JLabel("RM " + details[3]));

            panel.add(new JLabel("Payment Method:"));
            panel.add(new JLabel(details[4]));

            panel.add(new JLabel("Payment Date:"));
            panel.add(new JLabel(details[5]));

            panel.add(new JLabel("Collected By:"));
            panel.add(new JLabel(details[6]));

            panel.add(new JLabel("Status:"));
            panel.add(new JLabel(details[7]));

            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(e -> dialog.dispose());

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
            buttonPanel.add(closeBtn);

            dialog.add(panel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading payment details.");
        }
    }
    
    private void loadRevenueReport() {
        try {
            List<String[]> list = FileManager.getRevenueReportList("", "");
            loadRevenueTable(list);
            updateRevenueSummary(list);
            loadServiceBreakdownTable(list);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading revenue report.");
        }
    }
    
    private void loadRevenueTable(List<String[]> list) {
        DefaultTableModel model = (DefaultTableModel) revenueTable.getModel();
        model.setRowCount(0);

        for (String[] row : list) {
            model.addRow(new Object[]{
                row[0], // Payment ID
                row[1], // Appointment ID
                row[2], // Service Item
                row[3], // Amount
                row[4], // Method
                row[5], // Date
                row[6]  // Status
            });
        }
    }
    
    private String generateRevenueReportText() {
        StringBuilder sb = new StringBuilder();

        sb.append("APU Automotive Service Centre\n");
        sb.append("Revenue Report\n");
        sb.append("Generated At: ").append(FileManager.getCurrentDateTime()).append("\n");
        sb.append("==============================================================\n\n");

        sb.append(String.format("%-15s %-18s %-22s %-12s %-18s %-12s %-10s\n",
                "Payment ID", "Appointment ID", "Service Item", "Amount", "Method", "Date", "Status"));

        sb.append("------------------------------------------------------------------------------------------------\n");

        DefaultTableModel model = (DefaultTableModel) revenueTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            sb.append(String.format("%-15s %-18s %-22s %-12s %-18s %-12s %-10s\n",
                    model.getValueAt(i, 0),
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3),
                    model.getValueAt(i, 4),
                    model.getValueAt(i, 5),
                    model.getValueAt(i, 6)
            ));
        }

        sb.append("\n==============================================================\n");
        sb.append("Revenue Summary\n\n");
        sb.append("Total Revenue             : RM ").append(totalRevenueField.getText()).append("\n");
        sb.append("Total Paid Transactions   : ").append(totalPaidField.getText()).append("\n");
        sb.append("Total Unpaid Transactions : ").append(totalUnpaidField.getText()).append("\n");

        sb.append("\n==============================================================\n");
        sb.append("Service Item Revenue Breakdown\n\n");

        sb.append(String.format("%-25s %-18s %-10s\n",
                "Service Item", "Total Revenue", "Count"));

        sb.append("------------------------------------------------------------\n");

        DefaultTableModel breakdownModel = (DefaultTableModel) serviceItemTable.getModel();

        for (int i = 0; i < breakdownModel.getRowCount(); i++) {
            sb.append(String.format("%-25s %-18s %-10s\n",
                    breakdownModel.getValueAt(i, 0),
                    breakdownModel.getValueAt(i, 1),
                    breakdownModel.getValueAt(i, 2)
            ));
        }

        return sb.toString();
    }
    
    private void showReportPreviewPopup() {
        String reportText = generateRevenueReportText();

        JDialog dialog = new JDialog(this, "Revenue Report Preview", true);
        dialog.setSize(900, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JTextArea previewArea = new JTextArea(reportText);
        previewArea.setEditable(false);
        previewArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(previewArea);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeBtn);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
    
    private void exportRevenueReport() {
        String reportText = generateRevenueReportText();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export Revenue Report");
        fileChooser.setSelectedFile(new java.io.File("Revenue_Report.txt"));

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File file = fileChooser.getSelectedFile();

                java.nio.file.Files.write(
                        file.toPath(),
                        reportText.getBytes(),
                        java.nio.file.StandardOpenOption.CREATE,
                        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
                );

                JOptionPane.showMessageDialog(this, "Revenue report exported successfully.");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error exporting revenue report.");
            }
        }
    }
    
    private void updateRevenueSummary(List<String[]> list) {
        double totalRevenue = 0;
        int paidCount = 0;
        int unpaidCount = 0;

        for (String[] row : list) {
            double amount = 0;

            try {
                amount = Double.parseDouble(row[3]);
            } catch (NumberFormatException e) {
                amount = 0;
            }

            String status = row[6];

            if (status.equalsIgnoreCase("Paid")) {
                totalRevenue += amount;
                paidCount++;
            } else if (status.equalsIgnoreCase("Unpaid")) {
                unpaidCount++;
            }
        }

        totalRevenueField.setText(String.format("%.2f", totalRevenue));
        totalPaidField.setText(String.valueOf(paidCount));
        totalUnpaidField.setText(String.valueOf(unpaidCount));
        
        totalRevenueField.setEditable(false);
        totalPaidField.setEditable(false);
        totalUnpaidField.setEditable(false);
    }
    
    private void loadServiceBreakdownTable(List<String[]> list) {
        DefaultTableModel model = (DefaultTableModel) serviceItemTable.getModel();
        model.setRowCount(0);

        Map<String, double[]> breakdownMap = new HashMap<>();

        for (String[] row : list) {
            String serviceItem = row[2];
            String status = row[6];

            if (!status.equalsIgnoreCase("Paid")) {
                continue;
            }

            double amount = 0;

            try {
                amount = Double.parseDouble(row[3]);
            } catch (NumberFormatException e) {
                amount = 0;
            }

            breakdownMap.putIfAbsent(serviceItem, new double[]{0, 0});
            breakdownMap.get(serviceItem)[0] += amount; // total revenue
            breakdownMap.get(serviceItem)[1] += 1;      // count
        }

        for (String serviceItem : breakdownMap.keySet()) {
            double total = breakdownMap.get(serviceItem)[0];
            int count = (int) breakdownMap.get(serviceItem)[1];

            model.addRow(new Object[]{
                serviceItem,
                String.format("%.2f", total),
                count
            });
        }
    }
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        startDateField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        endDateField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        revenueTable = new javax.swing.JTable();
        searchBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalRevenueField = new javax.swing.JTextField();
        totalPaidField = new javax.swing.JTextField();
        totalUnpaidField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        serviceItemTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        paymentMethodField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        serviceItemField = new javax.swing.JTextField();
        previewBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Date: ");

        startDateField.setText("YYYY-MM-DD");

        jLabel3.setText("-");

        endDateField.setText("YYYY-MM-DD");

        jLabel1.setText("Revenue Report");

        revenueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Payment ID", "Appointment ID", "Service Item", "Amount (RM)", "Method", "Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(revenueTable);

        searchBtn.setText("Search");
        searchBtn.addActionListener(this::searchBtnActionPerformed);

        clearBtn.setText("Clear");
        clearBtn.addActionListener(this::clearBtnActionPerformed);

        jLabel4.setText("Summary:");

        jLabel5.setText("Total Revenue:");

        jLabel6.setText("Total Paid Transactions:");

        jLabel7.setText("Total Unpaid Transactions:");

        serviceItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Service Item", "Total Revenue", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(serviceItemTable);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        jLabel8.setText("Payment Method: ");

        jLabel9.setText("Service Item: ");

        previewBtn.setText("Preview");
        previewBtn.addActionListener(this::previewBtnActionPerformed);

        exportBtn.setText("Export");
        exportBtn.addActionListener(this::exportBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(backBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(60, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(totalRevenueField, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                            .addComponent(totalPaidField)
                                            .addComponent(totalUnpaidField))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(24, 24, 24)
                                        .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(serviceItemField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(paymentMethodField)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(103, 103, 103)
                                        .addComponent(previewBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(exportBtn))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(clearBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(searchBtn)))))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(previewBtn)
                            .addComponent(exportBtn))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(paymentMethodField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(serviceItemField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchBtn)
                        .addComponent(clearBtn)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(totalRevenueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(totalPaidField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(totalUnpaidField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBtn)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ViewReport(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        String paymentMethod = paymentMethodField.getText().trim().toLowerCase();
        String serviceItem = serviceItemField.getText().trim().toLowerCase();

        if (!startDate.isEmpty() && !FileManager.isValidDate(startDate)) {
            JOptionPane.showMessageDialog(this, "Start date must be valid in YYYY-MM-DD format.");
            return;
        }

        if (!endDate.isEmpty() && !FileManager.isValidDate(endDate)) {
            JOptionPane.showMessageDialog(this, "End date must be valid in YYYY-MM-DD format.");
            return;
        }

        if (!FileManager.isValidDateRange(startDate, endDate)) {
            JOptionPane.showMessageDialog(this, "Start date cannot be after end date.");
            return;
        }

        try {
            List<String[]> fullList = FileManager.getRevenueReportList(startDate, endDate);
            List<String[]> filteredList = new ArrayList<>();

            for (String[] row : fullList) {
                String rowServiceItem = row[2].toLowerCase();
                String rowMethod = row[4].toLowerCase();

                boolean matches = true;

                if (!paymentMethod.isEmpty() && !rowMethod.contains(paymentMethod)) {
                    matches = false;
                }

                if (!serviceItem.isEmpty() && !rowServiceItem.contains(serviceItem)) {
                    matches = false;
                }

                if (matches) {
                    filteredList.add(row);
                }
            }

            loadRevenueTable(filteredList);
            updateRevenueSummary(filteredList);
            loadServiceBreakdownTable(filteredList);

            if (filteredList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No matching revenue records found.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error searching revenue report.");
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        startDateField.setText("");
        endDateField.setText("");
        paymentMethodField.setText("");
        serviceItemField.setText("");

        loadRevenueReport();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed
        showReportPreviewPopup();
    }//GEN-LAST:event_previewBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        exportRevenueReport();
    }//GEN-LAST:event_exportBtnActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField endDateField;
    private javax.swing.JButton exportBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField paymentMethodField;
    private javax.swing.JButton previewBtn;
    private javax.swing.JTable revenueTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField serviceItemField;
    private javax.swing.JTable serviceItemTable;
    private javax.swing.JTextField startDateField;
    private javax.swing.JTextField totalPaidField;
    private javax.swing.JTextField totalRevenueField;
    private javax.swing.JTextField totalUnpaidField;
    // End of variables declaration//GEN-END:variables
}
