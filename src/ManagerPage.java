
import java.io.IOException;
import javax.swing.JOptionPane;

public class ManagerPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManagerPage.class.getName());

    private final String currentUserId;

    public ManagerPage(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
    }
    

    public ManagerPage() {
        initComponents();
        this.currentUserId = "USR0001"; 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        manageStaffBtn = new javax.swing.JButton();
        setServicePriceBtn = new javax.swing.JButton();
        viewFeedbackCommentBtn = new javax.swing.JButton();
        viewReportsBtn = new javax.swing.JButton();
        myProfileBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        activityLogsBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Manager Dashboard");

        manageStaffBtn.setText("Manage Staff");
        manageStaffBtn.addActionListener(this::manageStaffBtnActionPerformed);

        setServicePriceBtn.setText("Set Service Prices");
        setServicePriceBtn.addActionListener(this::setServicePriceBtnActionPerformed);

        viewFeedbackCommentBtn.setText("View Feedbacks & Comments");
        viewFeedbackCommentBtn.addActionListener(this::viewFeedbackCommentBtnActionPerformed);

        viewReportsBtn.setText("View Reports");
        viewReportsBtn.addActionListener(this::viewReportsBtnActionPerformed);

        myProfileBtn.setText("My Profile");
        myProfileBtn.addActionListener(this::myProfileBtnActionPerformed);

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);

        activityLogsBtn.setText("Activity Logs");
        activityLogsBtn.addActionListener(this::activityLogsBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(viewFeedbackCommentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(manageStaffBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(setServicePriceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewReportsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(activityLogsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(manageStaffBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(setServicePriceBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewFeedbackCommentBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewReportsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(activityLogsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myProfileBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutBtn)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manageStaffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStaffBtnActionPerformed
        new ManageStaffPage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageStaffBtnActionPerformed

    private void setServicePriceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setServicePriceBtnActionPerformed
        new SetServicePrices(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_setServicePriceBtnActionPerformed

    private void myProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myProfileBtnActionPerformed
        new MyProfile(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_myProfileBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
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
            try {
                FileManager.addActivityLog(currentUserId, "LOGOUT", "Manager logged out");
            } catch (IOException ex) {
                System.getLogger(ManagerPage.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            new HomePage().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void viewFeedbackCommentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFeedbackCommentBtnActionPerformed
        new ViewFeedbackComment(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_viewFeedbackCommentBtnActionPerformed

    private void activityLogsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activityLogsBtnActionPerformed
        new ActivityLogs(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_activityLogsBtnActionPerformed

    private void viewReportsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportsBtnActionPerformed
        new ViewReport(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_viewReportsBtnActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ManagerPage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activityLogsBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton manageStaffBtn;
    private javax.swing.JButton myProfileBtn;
    private javax.swing.JButton setServicePriceBtn;
    private javax.swing.JButton viewFeedbackCommentBtn;
    private javax.swing.JButton viewReportsBtn;
    // End of variables declaration//GEN-END:variables
}
