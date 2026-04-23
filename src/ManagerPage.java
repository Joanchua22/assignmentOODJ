public class ManagerPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManagerPage.class.getName());

    private final String currentUserId;

    public ManagerPage(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
    }
    

    public ManagerPage() {
        initComponents();
        this.currentUserId = "USR001"; // test user
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

        viewFeedbackCommentBtn.setText("View Feedback & Comments");

        viewReportsBtn.setText("View Reports");

        myProfileBtn.setText("My Profile");
        myProfileBtn.addActionListener(this::myProfileBtnActionPerformed);

        logoutBtn.setText("Logout");

        activityLogsBtn.setText("Activity Logs");

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
                .addContainerGap(122, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(activityLogsBtn)
                .addGap(18, 18, 18)
                .addComponent(myProfileBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutBtn)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manageStaffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStaffBtnActionPerformed
        new ManageStaffPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageStaffBtnActionPerformed

    private void setServicePriceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setServicePriceBtnActionPerformed
        new SetServicePrices().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_setServicePriceBtnActionPerformed

    private void myProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myProfileBtnActionPerformed
        
    }//GEN-LAST:event_myProfileBtnActionPerformed


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
