import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ActivityLogs extends BaseReport {
    
    private final String currentUserId;

    public ActivityLogs(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;

        try {
            loadActivityLogTable(FileManager.getAllActivityLogs());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading activity logs.");
        }
        
        activityLogTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    showSelectedLogDetails();
                }
            }
        });
    }
    
    @Override
    public String generateReportText() {

        StringBuilder sb = new StringBuilder();

        sb.append("APU Automotive Service Centre\n");
        sb.append("Activity Logs Report\n");
        sb.append("Generated At: ").append(FileManager.getCurrentDateTime()).append("\n");
        sb.append("==============================================================\n\n");

        sb.append(String.format("%-12s %-12s %-20s %-35s %-20s\n",
                "Log ID", "User ID", "Action", "Details", "Date"));

        sb.append("----------------------------------------------------------------------------------------------------\n");

        DefaultTableModel model = (DefaultTableModel) activityLogTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            sb.append(String.format("%-12s %-12s %-20s %-35s %-20s\n",
                    model.getValueAt(i, 0),
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3),
                    model.getValueAt(i, 4)
            ));
        }

        sb.append("\n==============================================================\n");
        sb.append("Total Logs: ").append(model.getRowCount()).append("\n");

        return sb.toString();
    }
    

    private void showSelectedLogDetails() {
        int selectedRow = activityLogTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an activity log first.");
            return;
        }

        String logId = activityLogTable.getValueAt(selectedRow, 0).toString();
        String userId = activityLogTable.getValueAt(selectedRow, 1).toString();
        String action = activityLogTable.getValueAt(selectedRow, 2).toString();
        String details = activityLogTable.getValueAt(selectedRow, 3).toString();
        String dateTime = activityLogTable.getValueAt(selectedRow, 4).toString();

        JOptionPane.showMessageDialog(
                this,
                "Log ID: " + logId +
                "\nUser ID: " + userId +
                "\nAction: " + action +
                "\nDetails: " + details +
                "\nDate/Time: " + dateTime,
                "Activity Log Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void loadActivityLogTable(List<String[]> logs) {
        DefaultTableModel model = (DefaultTableModel) activityLogTable.getModel();
        model.setRowCount(0);

        for (String[] log : logs) {
            model.addRow(new Object[] {
                log[0],
                log[1],
                log[2],
                log[3],
                log[4]
            });
        }
    }
    
    private void clearFilters() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Clear all filters?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        startDateField.setText("");
        endDateField.setText("");
        userIdField.setText("");
        actionField.setText("");

        try {
            loadActivityLogTable(FileManager.getAllActivityLogs());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading activity logs.");
        }
    }
    
    private String validateSearchInput(String startDate, String endDate) {
        if (!startDate.isEmpty() && !FileManager.isValidDate(startDate)) {
            return "Start date must be valid in YYYY-MM-DD format.";
        }

        if (!endDate.isEmpty() && !FileManager.isValidDate(endDate)) {
            return "End date must be valid in YYYY-MM-DD format.";
        }

        if (!FileManager.isValidDateRange(startDate, endDate)) {
            return "Start date cannot be after end date.";
        }

        return "VALID";
    }
    
    private List<String[]> filterActivityLogs(
            List<String[]> allLogs,
            String startDate,
            String endDate,
            String userId,
            String action
    ) {
        List<String[]> filteredList = new ArrayList<>();

        for (String[] row : allLogs) {
            String rowUserId = row[1].toLowerCase();
            String rowAction = row[2].toLowerCase();
            String rowDate = row[4].substring(0, 10);

            boolean matches = true;

            if (!startDate.isEmpty() && rowDate.compareTo(startDate) < 0) {
                matches = false;
            }

            if (!endDate.isEmpty() && rowDate.compareTo(endDate) > 0) {
                matches = false;
            }

            if (!userId.isEmpty() && !rowUserId.contains(userId)) {
                matches = false;
            }

            if (!action.isEmpty() && !rowAction.contains(action)) {
                matches = false;
            }

            if (matches) {
                filteredList.add(row);
            }
        }

        return filteredList;
    }
    
    private void searchActivityLogs() {
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        String userId = userIdField.getText().trim().toLowerCase();
        String action = actionField.getText().trim().toLowerCase();

        String validationResult = validateSearchInput(startDate, endDate);

        if (!validationResult.equals("VALID")) {
            JOptionPane.showMessageDialog(this, validationResult);
            return;
        }

        try {
            List<String[]> allLogs = FileManager.getAllActivityLogs();

            List<String[]> filteredList =
                    filterActivityLogs(allLogs, startDate, endDate, userId, action);

            loadActivityLogTable(filteredList);

            if (filteredList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No matching activity logs found.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error searching activity logs.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        activityLogTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        startDateField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        endDateField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userIdField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        actionField = new javax.swing.JTextField();
        clearBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        previewBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Activity Logs");

        activityLogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Log ID", "User ID", "Action", "Details", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(activityLogTable);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        startDateField.setText("YYYY-MM-DD");

        jLabel3.setText("-");

        endDateField.setText("YYYY-MM-DD");

        jLabel2.setText("Date: ");

        jLabel4.setText("User ID:");

        jLabel5.setText("Action:");

        clearBtn.setText("Clear");
        clearBtn.addActionListener(this::clearBtnActionPerformed);

        searchBtn.setText("Search");
        searchBtn.addActionListener(this::searchBtnActionPerformed);

        previewBtn.setText("Preview");
        previewBtn.addActionListener(this::previewBtnActionPerformed);

        exportBtn.setText("Export");
        exportBtn.addActionListener(this::exportBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(userIdField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(actionField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(previewBtn)
                                .addGap(18, 18, 18)
                                .addComponent(exportBtn))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(352, 352, 352))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(349, 349, 349))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(searchBtn)
                            .addComponent(clearBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(previewBtn)
                                .addComponent(exportBtn))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(userIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(actionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(backBtn)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearFilters();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        searchActivityLogs();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ManagerPage(currentUserId).setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed
        showReportPreviewPopup("Activity Logs Report Preview");
    }//GEN-LAST:event_previewBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        exportReport("Activity_Logs_Report.txt");
    }//GEN-LAST:event_exportBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField actionField;
    private javax.swing.JTable activityLogTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField endDateField;
    private javax.swing.JButton exportBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton previewBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField startDateField;
    private javax.swing.JTextField userIdField;
    // End of variables declaration//GEN-END:variables
}
