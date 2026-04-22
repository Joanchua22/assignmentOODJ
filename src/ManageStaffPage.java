
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageStaffPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageStaffPage.class.getName());
    private DefaultTableModel tableModel;
    
    public ManageStaffPage() {
        initComponents();
        initializeTable();
        loadAllStaffToTable();
        
        staffTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    openSelectedStaffProfile();
                }
            }
        });
    }
    
    private void initializeTable() {
        tableModel = (DefaultTableModel) staffTable.getModel();
        tableModel.setRowCount(0);
    }
    
    private void loadAllStaffToTable() {
        try {
            tableModel.setRowCount(0);

            List<String[]> staffList = FileManager.getAllStaff();

            for (String[] staff : staffList) {
                tableModel.addRow(new Object[]{
                    staff[0], // user_id
                    staff[1], // username
                    staff[5], // tp number
                    staff[3], // role
                    staff[8]  // status
                });
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading staff records.");
        }
    }
    
    private void openSelectedStaffProfile(){
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Please select a staff record.");
            return;
        }
        
        String userId = tableModel.getValueAt(selectedRow, 0).toString();
        
        new StaffProfilePage(userId).setVisible(true);
        this.dispose();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        staffTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        searchBarField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Manage Staff");

        staffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "User ID", "Username", "TP Number", "Role", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(staffTable);
        if (staffTable.getColumnModel().getColumnCount() > 0) {
            staffTable.getColumnModel().getColumn(0).setResizable(false);
            staffTable.getColumnModel().getColumn(1).setResizable(false);
            staffTable.getColumnModel().getColumn(2).setResizable(false);
            staffTable.getColumnModel().getColumn(3).setResizable(false);
            staffTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setText("Staff Records: ");

        addBtn.setText("Add New Staff");
        addBtn.addActionListener(this::addBtnActionPerformed);

        jLabel4.setText("Search Bar:");

        searchBtn.setText("Search");
        searchBtn.addActionListener(this::searchBtnActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(searchBarField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchBtn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(addBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel1)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(searchBtn)
                    .addComponent(searchBarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(backBtn))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String keyword = searchBarField.getText().trim();
        
        if(keyword.isEmpty()){
            loadAllStaffToTable();
            return;
        }
        
        try{
            tableModel.setRowCount(0);
            
            List<String[]> resultList = FileManager.searchStaff(keyword);
            
            for (String[] staff : resultList) {
                tableModel.addRow(new Object[]{
                    staff[0], // user_id
                    staff[1], // username
                    staff[5], // tp number
                    staff[3], // role
                    staff[8]  // status
                });
            }
            if (resultList.isEmpty()){
                JOptionPane.showMessageDialog(this, "No matching staff found.");
            }
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "Error searching staff records");
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        new AddNewStaffPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ManagerPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ManageStaffPage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchBarField;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable staffTable;
    // End of variables declaration//GEN-END:variables
}
