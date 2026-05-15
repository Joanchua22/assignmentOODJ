public class ViewReport extends javax.swing.JFrame {
    
    private String currentUserId;

    public ViewReport(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        appointmentReportBtn = new javax.swing.JButton();
        serviceItemReportBtn = new javax.swing.JButton();
        technicianWorkloadReportBtn = new javax.swing.JButton();
        revenueReportBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("View Reports");

        appointmentReportBtn.setText("Appointment Report");
        appointmentReportBtn.addActionListener(this::appointmentReportBtnActionPerformed);

        serviceItemReportBtn.setText("Service Item Report");
        serviceItemReportBtn.addActionListener(this::serviceItemReportBtnActionPerformed);

        technicianWorkloadReportBtn.setText("Technician Workload Report");
        technicianWorkloadReportBtn.addActionListener(this::technicianWorkloadReportBtnActionPerformed);

        revenueReportBtn.setText("Revenue Report");
        revenueReportBtn.addActionListener(this::revenueReportBtnActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(163, 163, 163))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(revenueReportBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(technicianWorkloadReportBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(serviceItemReportBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(appointmentReportBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(appointmentReportBtn)
                .addGap(33, 33, 33)
                .addComponent(serviceItemReportBtn)
                .addGap(33, 33, 33)
                .addComponent(technicianWorkloadReportBtn)
                .addGap(33, 33, 33)
                .addComponent(revenueReportBtn)
                .addGap(33, 33, 33)
                .addComponent(backBtn)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void appointmentReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentReportBtnActionPerformed
        new AppointmentReport(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_appointmentReportBtnActionPerformed

    private void serviceItemReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceItemReportBtnActionPerformed
        new ServiceItemReport(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_serviceItemReportBtnActionPerformed

    private void revenueReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revenueReportBtnActionPerformed
        new RevenueReport(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_revenueReportBtnActionPerformed

    private void technicianWorkloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_technicianWorkloadReportBtnActionPerformed
        new TechnicianWorkloadReport(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_technicianWorkloadReportBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new Manager(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointmentReportBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton revenueReportBtn;
    private javax.swing.JButton serviceItemReportBtn;
    private javax.swing.JButton technicianWorkloadReportBtn;
    // End of variables declaration//GEN-END:variables
}
