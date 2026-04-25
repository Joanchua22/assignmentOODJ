import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ServiceCategory extends javax.swing.JFrame {
    private final String currentUserId;
    
    public ServiceCategory(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        loadServiceTypeTable();
        
        cateTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    try {
                        openSelectedCateProfile();
                    } catch (IOException ex) {
                        System.getLogger(ServiceCategory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
            }
        });
    }
    
    private void openSelectedCateProfile() throws IOException {
        int selectedRow = cateTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a category first.");
            return;
        }

        String serviceTypeId = cateTable.getValueAt(selectedRow, 0).toString();

        new UpdateServiceCate(serviceTypeId, currentUserId).setVisible(true);
        dispose();
    }

    private void loadServiceTypeTable() {
        DefaultTableModel model = (DefaultTableModel) cateTable.getModel();
        model.setRowCount(0);

        try {
            List<String[]> serviceTypeList = FileManager.getAllServiceTypes();

            for (String[] serviceType : serviceTypeList) {
                model.addRow(new Object[]{
                    serviceType[0],
                    serviceType[1],
                    serviceType[2]
                });
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading service categories.");
        }
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        categoryTable = new javax.swing.JScrollPane();
        cateTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Service Category");

        cateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cate ID", "Name", "Duration"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        categoryTable.setViewportView(cateTable);
        if (cateTable.getColumnModel().getColumnCount() > 0) {
            cateTable.getColumnModel().getColumn(0).setResizable(false);
            cateTable.getColumnModel().getColumn(1).setResizable(false);
            cateTable.getColumnModel().getColumn(2).setResizable(false);
        }

        addBtn.setText("Add");
        addBtn.addActionListener(this::addBtnActionPerformed);

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(this::deleteBtnActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryTable, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(addBtn)
                                .addGap(18, 18, 18)
                                .addComponent(deleteBtn)
                                .addGap(18, 18, 18)
                                .addComponent(backBtn))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(categoryTable, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(deleteBtn)
                    .addComponent(backBtn))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        new AddServiceCate(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new SetServicePrices(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int selectedRow = cateTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a category to delete.");
            return;
        }

        String serviceTypeId = cateTable.getValueAt(selectedRow, 0).toString();
        String serviceName = cateTable.getValueAt(selectedRow, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete category \"" + serviceName + "\"?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean deleted = FileManager.deleteServiceType(serviceTypeId);

                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Category deleted successfully.");
                    FileManager.addActivityLog(currentUserId, "Delete Service Category", serviceTypeId + " Added");
                    loadServiceTypeTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Delete failed.");
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error deleting category.");
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JTable cateTable;
    private javax.swing.JScrollPane categoryTable;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
