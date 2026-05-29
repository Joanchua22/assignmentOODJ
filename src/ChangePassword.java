import java.io.IOException;
import javax.swing.JOptionPane;


public class ChangePassword extends javax.swing.JFrame {
    
    private String currentUserId;
    private String returnPage;
    
    public ChangePassword(String currentUserId, String returnPage) {
        this.currentUserId = currentUserId;
        this.returnPage = returnPage;
        initComponents();
    }
    
    private String validatePasswordInput(
            String currentPassword,
            String newPassword,
            String confirmPassword
    ) {

        if (currentPassword.isEmpty()
                || newPassword.isEmpty()
                || confirmPassword.isEmpty()) {

            return "Please fill in all password fields.";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "New password and confirm password do not match.";
        }

        if (newPassword.equals(currentPassword)) {
            return "New password cannot be the same as current password.";
        }

        return "VALID";
    }
    
    private void clearPasswordFields() {
        CurrentPasswordField.setText("");
        NewPasswordField.setText("");
        ConfirmPasswordField.setText("");
    }
    
    private void recordPasswordUpdateActivity() {

        try {

            FileManager.addActivityLog(
                    currentUserId,
                    "Update Password",
                    "Password Updated"
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to record activity log."
            );
        }
    }
    
    private void updatePassword() {

        try {
            String currentPassword =
                    new String(CurrentPasswordField.getPassword()).trim();

            String newPassword =
                    new String(NewPasswordField.getPassword()).trim();

            String confirmPassword =
                    new String(ConfirmPasswordField.getPassword()).trim();

            String validationResult =
                    validatePasswordInput(
                            currentPassword,
                            newPassword,
                            confirmPassword
                    );

            if (!validationResult.equals("VALID")) {
                JOptionPane.showMessageDialog(
                        this,
                        validationResult
                );
                return;
            }

            boolean isCurrentPasswordCorrect =
                    FileManager.checkCurrentPasswordByUserId(
                            currentUserId,
                            currentPassword
                    );

            if (!isCurrentPasswordCorrect) {
                JOptionPane.showMessageDialog(
                        this,
                        "Current password is incorrect."
                );
                return;
            }

            boolean updated =
                    FileManager.updatePasswordByUserId(
                            currentUserId,
                            newPassword
                    );

            if (updated) {
                recordPasswordUpdateActivity();

                JOptionPane.showMessageDialog(
                        this,
                        "Password updated successfully."
                );
                clearPasswordFields();
            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "User not found."
                );
            }
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while updating the password."
            );
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        CurrentPasswordField = new javax.swing.JPasswordField();
        NewPasswordField = new javax.swing.JPasswordField();
        ConfirmPasswordField = new javax.swing.JPasswordField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Current Password:");

        jLabel2.setText("New Password:");

        jLabel3.setText("Confirm Password:");

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnSave.setText("Save");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnSave))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CurrentPasswordField)
                            .addComponent(NewPasswordField)
                            .addComponent(ConfirmPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CurrentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnSave))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        updatePassword();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        if (returnPage.equalsIgnoreCase("ManagerProfile")) {
            new MyProfile(currentUserId, "ManagerProfile").setVisible(true);
        } else if (returnPage.equalsIgnoreCase("CustomerProfile")) {
            new MyProfile(currentUserId, "CustomerProfile").setVisible(true);
        } else if (returnPage.equalsIgnoreCase("StaffEditProfile")) {
            new StaffEditProfile(currentUserId);
        } 
        
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JPasswordField CurrentPasswordField;
    private javax.swing.JPasswordField NewPasswordField;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    // End of variables declaration//GEN-END:variables
}
