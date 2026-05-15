import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ServiceItem extends javax.swing.JFrame {
    
    private final String currentUserId;
    
    public ServiceItem(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        loadServiceItemTable(); 
        
        itemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    openSelectedItemProfile();
                }
            }
        });
    }

    
    private void openSelectedItemProfile() {
        int selectedRow = itemsTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a service item first.");
            return;
        }

        String serviceItemId = itemsTable.getValueAt(selectedRow, 0).toString();

        new UpdateServiceItem(serviceItemId, currentUserId).setVisible(true);
        dispose();
    }
    
    private void loadServiceItemTable() {
        DefaultTableModel model = (DefaultTableModel) itemsTable.getModel();
        model.setRowCount(0);

        try {
            List<String[]> serviceItemList = FileManager.getAllServiceItems();

            for (String[] item : serviceItemList) {
                model.addRow(new Object[] {
                    item[0], // item id
                    item[1], // cate id
                    item[2], // name
                    item[3], // description
                    item[4]  // price
                });
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading service items.");
        }
    }
    
    private void deleteSelectedServiceItem() {
        int selectedRow = itemsTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a service item to delete.");
            return;
        }

        String serviceItemId = itemsTable.getValueAt(selectedRow, 0).toString();
        String serviceName = itemsTable.getValueAt(selectedRow, 2).toString();

        if (!confirmDeleteServiceItem(serviceName)) {
            return;
        }

        try {
            boolean deleted = FileManager.deleteServiceItem(serviceItemId);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Service item deleted successfully.");
                recordDeleteServiceItemActivity(serviceItemId, serviceName);
                loadServiceItemTable();
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting service item.");
        }
    }
    
    private boolean confirmDeleteServiceItem(String serviceName) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete service item \"" + serviceName + "\"?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        return confirm == JOptionPane.YES_OPTION;
    }
    
    private void recordDeleteServiceItemActivity(String serviceItemId, String serviceName) {
        try {
            FileManager.addActivityLog(
                    currentUserId,
                    "Delete Service Item",
                    serviceItemId + " " + serviceName + " Deleted"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to record activity log.");
        }
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        categoryTable = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Service Items");

        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Cate ID", "Name", "Description", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        categoryTable.setViewportView(itemsTable);
        if (itemsTable.getColumnModel().getColumnCount() > 0) {
            itemsTable.getColumnModel().getColumn(0).setResizable(false);
            itemsTable.getColumnModel().getColumn(1).setResizable(false);
            itemsTable.getColumnModel().getColumn(2).setResizable(false);
            itemsTable.getColumnModel().getColumn(3).setResizable(false);
            itemsTable.getColumnModel().getColumn(4).setResizable(false);
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
                        .addGap(36, 36, 36)
                        .addComponent(categoryTable, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(jLabel1)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addBtn)
                .addGap(47, 47, 47)
                .addComponent(deleteBtn)
                .addGap(42, 42, 42)
                .addComponent(backBtn)
                .addGap(246, 246, 246))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(categoryTable, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(deleteBtn)
                    .addComponent(backBtn))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        new AddNewServiceItem(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new SetServicePrices(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        deleteSelectedServiceItem();
    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JScrollPane categoryTable;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTable itemsTable;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
