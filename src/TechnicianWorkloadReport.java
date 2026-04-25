
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TechnicianWorkloadReport extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TechnicianWorkloadReport.class.getName());


    public TechnicianWorkloadReport() {
        initComponents();
        loadTechnicianWorkloadTable();
    }
    
    private void loadTechnicianWorkloadTable() {
        try {
            List<String[]> list = FileManager.getTechnicianWorkloadReportList("", "");
            loadTechnicianWorkloadTable(list);
            updateMostLeastBusy(list);
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
    
    private void updateMostLeastBusy(List<String[]> list) {
        if (list.isEmpty()) {
            mostBusyField.setText("");
            leastBusyField.setText("");
            return;
        }

        String[] most = list.get(0);
        String[] least = list.get(list.size() - 1);

        mostBusyField.setText(most[1] + " (" + most[4] + " jobs)");
        leastBusyField.setText(least[1] + " (" + least[4] + " jobs)");
        mostBusyField.setVisible(false);
        leastBusyField.setVisible(false);
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
        mostBusyField = new javax.swing.JTextField();
        leastBusyField = new javax.swing.JTextField();
        technicianField = new javax.swing.JTextField();

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

        jLabel5.setText("Most Busy Technician:");

        jLabel6.setText("Least Busy Technician:");

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(leastBusyField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(backBtn))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(mostBusyField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))))
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
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
                                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
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
                    .addComponent(mostBusyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(backBtn)
                    .addComponent(leastBusyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        String technician = technicianField.getText().trim().toLowerCase();

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
            List<String[]> fullList = FileManager.getTechnicianWorkloadReportList(startDate, endDate);
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

            loadTechnicianWorkloadTable(filteredList);
            updateMostLeastBusy(filteredList);

            if (filteredList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No matching technician workload found.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error searching technician workload report.");
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ViewReport().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new TechnicianWorkloadReport().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField endDateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField leastBusyField;
    private javax.swing.JTextField mostBusyField;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField startDateField;
    private javax.swing.JTextField technicianField;
    private javax.swing.JTable technicianTable;
    // End of variables declaration//GEN-END:variables
}
