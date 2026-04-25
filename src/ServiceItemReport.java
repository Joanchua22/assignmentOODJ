import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ServiceItemReport extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ServiceItemReport.class.getName());


    public ServiceItemReport() {
        initComponents();
        loadServiceItemReportTable();
    }
    
    private void loadServiceItemReportTable() {
        try {
            List<String[]> reportList = FileManager.getServiceItemReportList("", ""); // ✅ pass empty date
            loadServiceItemReportTable(reportList);
            updateMostLeastBooked(reportList);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading service item report.");
        }
    }
    
    private void loadServiceItemReportTable(List<String[]> reportList) {
        DefaultTableModel model = (DefaultTableModel) serviceItemTable.getModel();
        model.setRowCount(0);

        for (String[] row : reportList) {
            model.addRow(new Object[]{
                row[0], // Service Item ID
                row[1], // Service Name
                row[2], // Service Category
                row[3]  // Count Booked
            });
        }
    }
    
    private void updateMostLeastBooked(List<String[]> reportList) {
        if (reportList.isEmpty()) {
            mostBookedField.setText("");
            leastBookedField.setText("");
            return;
        }

        String[] most = reportList.get(0);
        String[] least = reportList.get(reportList.size() - 1);

        mostBookedField.setText(most[1] + " (" + most[3] + ")");
        leastBookedField.setText(least[1] + " (" + least[3] + ")");
        mostBookedField.setEditable(false);
        leastBookedField.setEditable(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        startDateField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        endDateField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        serviceItemTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        serviceItemIdField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        serviceNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        serviceCategoryField = new javax.swing.JTextField();
        clearBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        mostBookedField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        leastBookedField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Service Item Report");

        jLabel2.setText("Date: ");

        startDateField.setText("YYYY-MM-DD");

        jLabel3.setText("-");

        endDateField.setText("YYYY-MM-DD");

        serviceItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Service Item ID", "Service Name", "Service Category", "Booked Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(serviceItemTable);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        jLabel4.setText("Service Item ID:");

        jLabel5.setText("Service Name:");

        jLabel6.setText("Service Category:");

        clearBtn.setText("Clear");
        clearBtn.addActionListener(this::clearBtnActionPerformed);

        searchBtn.setText("Search");
        searchBtn.addActionListener(this::searchBtnActionPerformed);

        jLabel7.setText("Service Item Summary:");

        jLabel8.setText("Most Booked:");

        jLabel9.setText("Least Booked:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(501, 501, 501)
                                    .addComponent(backBtn)
                                    .addGap(28, 28, 28))
                                .addComponent(jLabel7)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(29, 29, 29)
                                    .addComponent(mostBookedField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(29, 29, 29)
                                    .addComponent(leastBookedField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(startDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(serviceItemIdField)
                                    .addComponent(serviceNameField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(serviceCategoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(clearBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(searchBtn)))))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(serviceItemIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(serviceCategoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(serviceNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn)
                    .addComponent(searchBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(mostBookedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(leastBookedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBtn)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ViewReport().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        startDateField.setText("");
        endDateField.setText("");
        serviceItemIdField.setText("");
        serviceNameField.setText("");
        serviceCategoryField.setText("");

        loadServiceItemReportTable();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        String itemId = serviceItemIdField.getText().trim().toLowerCase();
        String serviceName = serviceNameField.getText().trim().toLowerCase();
        String category = serviceCategoryField.getText().trim().toLowerCase();

        // date validation
        if (!startDate.isEmpty() && !FileManager.isValidDate(startDate)) {
            JOptionPane.showMessageDialog(this, "Start date must be YYYY-MM-DD.");
            return;
        }

        if (!endDate.isEmpty() && !FileManager.isValidDate(endDate)) {
            JOptionPane.showMessageDialog(this, "End date must be YYYY-MM-DD.");
            return;
        }

        if (!FileManager.isValidDateRange(startDate, endDate)) {
            JOptionPane.showMessageDialog(this, "Start date cannot be after end date.");
            return;
        }

        try {
            List<String[]> fullList = FileManager.getServiceItemReportList(startDate, endDate);
            List<String[]> filteredList = new ArrayList<>();

            for (String[] row : fullList) {
                String rowId = row[0].toLowerCase();
                String rowName = row[1].toLowerCase();
                String rowCategory = row[2].toLowerCase();

                boolean matches = true;

                if (!itemId.isEmpty() && !rowId.contains(itemId)) {
                    matches = false;
                }

                if (!serviceName.isEmpty() && !rowName.contains(serviceName)) {
                    matches = false;
                }

                if (!category.isEmpty() && !rowCategory.contains(category)) {
                    matches = false;
                }

                if (matches) {
                    filteredList.add(row);
                }
            }

            loadServiceItemReportTable(filteredList);
            updateMostLeastBooked(filteredList);

            if (filteredList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No matching service items found.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error searching service item report.");
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new ServiceItemReport().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField endDateField;
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
    private javax.swing.JTextField leastBookedField;
    private javax.swing.JTextField mostBookedField;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField serviceCategoryField;
    private javax.swing.JTextField serviceItemIdField;
    private javax.swing.JTable serviceItemTable;
    private javax.swing.JTextField serviceNameField;
    private javax.swing.JTextField startDateField;
    // End of variables declaration//GEN-END:variables
}
