import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TechnicianWorkloadReport extends BaseReport {
    
    private String currentUserId;
    
    public TechnicianWorkloadReport(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        loadTechnicianWorkloadTable();
    }
    
    private void loadTechnicianWorkloadTable() {
        try {
            List<String[]> list = FileManager.getTechnicianWorkloadReportList("", "");
            loadTechnicianWorkloadTable(list);
            updatehighestLowestCapacity(list);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading technician workload report.");
        }
    }
    
    private void loadTechnicianWorkloadTable(List<String[]> list) {
        DefaultTableModel model = (DefaultTableModel) technicianTable.getModel();
        model.setRowCount(0);

        for (String[] row : list) {
            model.addRow(new Object[]{
                row[0],
                row[1],
                row[2],
                row[3],
                row[4]
            });
        }
    }
    
    @Override
    public String generateReportText() {

        StringBuilder sb = new StringBuilder();

        sb.append("APU Automotive Service Centre\n");
        sb.append("Technician Workload Report\n");
        sb.append("Generated At: ").append(FileManager.getCurrentDateTime()).append("\n");
        sb.append("==============================================================\n\n");

        sb.append(String.format("%-18s %-18s %-18s %-18s %-10s\n",
                "Technician ID", "Username", "Assigned Count", "Completed Count", "Total"));

        sb.append("--------------------------------------------------------------------------------\n");

        DefaultTableModel model = (DefaultTableModel) technicianTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            sb.append(String.format("%-18s %-18s %-18s %-18s %-10s\n",
                    model.getValueAt(i, 0),
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3),
                    model.getValueAt(i, 4)
            ));
        }

        sb.append("\n==============================================================\n");
        sb.append("Technician Workload Summary\n\n");
        sb.append("Highest Capacity Technician : ")
          .append(highestCapacityField.getText())
          .append("\n");

        sb.append("Lowest Capacity Technician  : ")
          .append(lowestCapacityField.getText())
          .append("\n");


        return sb.toString();
    }
    

    
    private void updatehighestLowestCapacity(List<String[]> list) {
        if (list.isEmpty()) {
            highestCapacityField.setText("");
            lowestCapacityField.setText("");
            return;
        }

        String[] most = list.get(0);
        String[] least = list.get(list.size() - 1);

        highestCapacityField.setText(most[1] + " (" + most[4] + " Tasks)");
        lowestCapacityField.setText(least[1] + " (" + least[4] + " Tasks)");
    }
    
    private String validateSearchInput(
            String startDate,
            String endDate
    ) {

        if (!startDate.isEmpty()
                && !FileManager.isValidDate(startDate)) {

            return "Start date must be valid in YYYY-MM-DD format.";
        }

        if (!endDate.isEmpty()
                && !FileManager.isValidDate(endDate)) {

            return "End date must be valid in YYYY-MM-DD format.";
        }

        if (!FileManager.isValidDateRange(startDate, endDate)) {
            return "Start date cannot be after end date.";
        }

        return "VALID";
    }
    
    private List<String[]> filterTechnicianWorkload(
            List<String[]> fullList,
            String technician
    ) {

        List<String[]> filteredList = new ArrayList<>();

        for (String[] row : fullList) {

            String technicianId = row[0].toLowerCase();
            String username = row[1].toLowerCase();

            if (technician.isEmpty()
                    || technicianId.contains(technician)
                    || username.contains(technician)) {

                filteredList.add(row);
            }
        }

        return filteredList;
    }
    
    private void searchTechnicianWorkloadReport() {

        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();

        String technician =
                technicianField.getText().trim().toLowerCase();

        String validationResult =
                validateSearchInput(startDate, endDate);

        if (!validationResult.equals("VALID")) {

            JOptionPane.showMessageDialog(
                    this,
                    validationResult
            );

            return;
        }

        try {

            List<String[]> fullList =
                    FileManager.getTechnicianWorkloadReportList(
                            startDate,
                            endDate
                    );

            List<String[]> filteredList =
                    filterTechnicianWorkload(
                            fullList,
                            technician
                    );

            loadTechnicianWorkloadTable(filteredList);

            updatehighestLowestCapacity(filteredList);

            if (filteredList.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No matching technician workload found."
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching technician workload report."
            );
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
        jLabel10 = new javax.swing.JLabel();
        clearBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        technicianTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        highestCapacityField = new javax.swing.JTextField();
        lowestCapacityField = new javax.swing.JTextField();
        technicianField = new javax.swing.JTextField();
        previewBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Date: ");

        startDateField.setText("YYYY-MM-DD");

        jLabel3.setText("-");

        endDateField.setText("YYYY-MM-DD");

        jLabel1.setText("Technician Workload Report");

        jLabel10.setText("Technician:");

        clearBtn.setText("Clear");
        clearBtn.addActionListener(this::clearBtnActionPerformed);

        searchBtn.setText("Search");
        searchBtn.addActionListener(this::searchBtnActionPerformed);

        technicianTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Technician ID", "Username", "Assigned Count", "Completed Count", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(technicianTable);

        jLabel4.setText("Summary:");

        jLabel5.setText("Highest Capacity Technician:");

        jLabel6.setText("Lowest Capacity Technician:");

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        highestCapacityField.addActionListener(this::highestCapacityFieldActionPerformed);

        previewBtn.setText("Preview");
        previewBtn.addActionListener(this::previewBtnActionPerformed);

        exportBtn.setText("Export");
        exportBtn.addActionListener(this::exportBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(startDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addComponent(technicianField))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(clearBtn)
                                            .addGap(18, 18, 18)
                                            .addComponent(searchBtn))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(previewBtn)
                                            .addGap(18, 18, 18)
                                            .addComponent(exportBtn))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(highestCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lowestCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backBtn)))))
                .addGap(0, 68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(previewBtn)
                    .addComponent(exportBtn))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(clearBtn)
                    .addComponent(searchBtn)
                    .addComponent(technicianField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(highestCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(backBtn)
                    .addComponent(lowestCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        startDateField.setText("");
        endDateField.setText("");
        technicianField.setText("");

        loadTechnicianWorkloadTable();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        searchTechnicianWorkloadReport();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ViewReport(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void highestCapacityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highestCapacityFieldActionPerformed

    }//GEN-LAST:event_highestCapacityFieldActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed
        showReportPreviewPopup("Technician Workload Report Preview");
    }//GEN-LAST:event_previewBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        exportReport("Technician_Workload_Report.txt");
    }//GEN-LAST:event_exportBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField endDateField;
    private javax.swing.JButton exportBtn;
    private javax.swing.JTextField highestCapacityField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lowestCapacityField;
    private javax.swing.JButton previewBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField startDateField;
    private javax.swing.JTextField technicianField;
    private javax.swing.JTable technicianTable;
    // End of variables declaration//GEN-END:variables
}
