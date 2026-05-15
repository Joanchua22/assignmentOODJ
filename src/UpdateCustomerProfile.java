
public class UpdateCustomerProfile extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UpdateCustomerProfile.class.getName());
    private String currentUserId;
    
    public UpdateCustomerProfile(String currentUserId) {
        this.currentUserId = currentUserId;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        btnManageVehicles = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEditDetails = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnManageVehicles.setText("Manage Vehicles");
        btnManageVehicles.addActionListener(this::btnManageVehiclesActionPerformed);

        jLabel1.setText("Update Profile Page");

        btnEditDetails.setText("Edit Personal Details");
        btnEditDetails.addActionListener(this::btnEditDetailsActionPerformed);

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
                            .addComponent(btnManageVehicles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btnBack)))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(btnEditDetails)
                .addGap(39, 39, 39)
                .addComponent(btnManageVehicles)
                .addGap(40, 40, 40)
                .addComponent(btnBack)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new Customer(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnManageVehiclesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageVehiclesActionPerformed
        new ManageVehicles(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageVehiclesActionPerformed

    private void btnEditDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDetailsActionPerformed
        new MyProfile(currentUserId, "CustomerProfile").setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditDetailsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEditDetails;
    private javax.swing.JButton btnManageVehicles;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
