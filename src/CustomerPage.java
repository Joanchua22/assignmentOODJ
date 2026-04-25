
import javax.swing.JOptionPane;


public class CustomerPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerPage.class.getName());

    private String currentUserId;
    
    public CustomerPage(String currentUserId) {
        this.currentUserId = currentUserId;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnUpdateProfile = new javax.swing.JButton();
        btnServiceNPayment = new javax.swing.JButton();
        btnViewFeedback = new javax.swing.JButton();
        btnLeaveComment = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Customer Dashboard");

        btnUpdateProfile.setText("Update Profile");
        btnUpdateProfile.addActionListener(this::btnUpdateProfileActionPerformed);

        btnServiceNPayment.setText("View Service & Payment");
        btnServiceNPayment.addActionListener(this::btnServiceNPaymentActionPerformed);

        btnViewFeedback.setText("View Feedback");
        btnViewFeedback.addActionListener(this::btnViewFeedbackActionPerformed);

        btnLeaveComment.setText("Leave Comment");
        btnLeaveComment.addActionListener(this::btnLeaveCommentActionPerformed);

        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLeaveComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnServiceNPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(btnUpdateProfile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(btnUpdateProfile)
                .addGap(31, 31, 31)
                .addComponent(btnServiceNPayment)
                .addGap(32, 32, 32)
                .addComponent(btnViewFeedback)
                .addGap(31, 31, 31)
                .addComponent(btnLeaveComment)
                .addGap(32, 32, 32)
                .addComponent(btnLogout)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProfileActionPerformed
        new UpdateCustomerProfilePage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUpdateProfileActionPerformed

    private void btnServiceNPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiceNPaymentActionPerformed
        new ViewServiceNPaymentPage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnServiceNPaymentActionPerformed

    private void btnViewFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewFeedbackActionPerformed
        new ViewFeedbackPage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewFeedbackActionPerformed

    private void btnLeaveCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaveCommentActionPerformed
        new LeaveCommentPage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLeaveCommentActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Object[] options = { "No", "Yes" };

        int confirm = JOptionPane.showOptionDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0] 
        );
        if (confirm == 1) { 

            JOptionPane.showMessageDialog(this, "Logout successful.");
            new HomePage().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLeaveComment;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnServiceNPayment;
    private javax.swing.JButton btnUpdateProfile;
    private javax.swing.JButton btnViewFeedback;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
