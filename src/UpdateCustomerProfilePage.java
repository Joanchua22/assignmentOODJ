
public class UpdateCustomerProfilePage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UpdateCustomerProfilePage.class.getName());
    private String currentUserId;
    
    public UpdateCustomerProfilePage(String currentUserId) {
        this.currentUserId = currentUserId;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnEditDetails = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnManageVehicles = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Update Profile Page");

        btnEditDetails.setText("Edit Personal Details");
        btnEditDetails.addActionListener(this::btnEditDetailsActionPerformed);

        btnChangePass.setText("Change Password");
        btnChangePass.addActionListener(this::btnChangePassActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnManageVehicles.setText("Manage Vehicles");
        btnManageVehicles.addActionListener(this::btnManageVehiclesActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChangePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnManageVehicles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnBack)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(btnEditDetails)
                .addGap(36, 36, 36)
                .addComponent(btnChangePass)
                .addGap(35, 35, 35)
                .addComponent(btnManageVehicles)
                .addGap(34, 34, 34)
                .addComponent(btnBack)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDetailsActionPerformed
        new EditCustomerProfilePage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditDetailsActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new CustomerPage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        new ChangePasswordPage(currentUserId, "CustomerProfile").setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void btnManageVehiclesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageVehiclesActionPerformed
        new ManageVehiclesPage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageVehiclesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnEditDetails;
    private javax.swing.JButton btnManageVehicles;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
