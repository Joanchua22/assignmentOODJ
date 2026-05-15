import javax.swing.JOptionPane;
public class Technician extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Technician.class.getName());

    private String currentUserId;
    public Technician(String currentUserId) {        
        this.currentUserId = currentUserId;
        initComponents();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnUpdateProfile = new javax.swing.JButton();
        btnViewAppointment = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnAddFeedback = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnViewComment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnUpdateProfile.setText("Update Profile");
        btnUpdateProfile.addActionListener(this::btnUpdateProfileActionPerformed);

        btnViewAppointment.setText("View Appointment");
        btnViewAppointment.addActionListener(this::btnViewAppointmentActionPerformed);

        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        btnAddFeedback.setText("Add Feedback");
        btnAddFeedback.addActionListener(this::btnAddFeedbackActionPerformed);

        jLabel1.setText("Welcome Technician");

        btnViewComment.setText("View Comment");
        btnViewComment.addActionListener(this::btnViewCommentActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnViewAppointment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnViewComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(btnUpdateProfile)
                .addGap(32, 32, 32)
                .addComponent(btnViewComment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnViewAppointment)
                .addGap(43, 43, 43)
                .addComponent(btnAddFeedback)
                .addGap(40, 40, 40)
                .addComponent(btnLogout)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProfileActionPerformed
        new TechEditProfile(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUpdateProfileActionPerformed

    private void btnViewAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAppointmentActionPerformed
        new TechAppointment(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAppointmentActionPerformed

    private void btnAddFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFeedbackActionPerformed
        new TechAddFeedback(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddFeedbackActionPerformed

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
            new Home().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCommentActionPerformed
    new TechComment(currentUserId).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnViewCommentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFeedback;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUpdateProfile;
    private javax.swing.JButton btnViewAppointment;
    private javax.swing.JButton btnViewComment;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
