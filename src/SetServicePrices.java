public class SetServicePrices extends javax.swing.JFrame {
    
    private final String currentUserId;

    public SetServicePrices(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        serviceCategoryBtn = new javax.swing.JButton();
        serviceItemBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Set Service Prices");

        serviceCategoryBtn.setText("Service Category");
        serviceCategoryBtn.addActionListener(this::serviceCategoryBtnActionPerformed);

        serviceItemBtn.setText("Sevice Items");
        serviceItemBtn.addActionListener(this::serviceItemBtnActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(serviceCategoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(serviceItemBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(serviceCategoryBtn)
                .addGap(18, 18, 18)
                .addComponent(serviceItemBtn)
                .addGap(18, 18, 18)
                .addComponent(backBtn)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void serviceCategoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceCategoryBtnActionPerformed
        new ServiceCategory(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_serviceCategoryBtnActionPerformed

    private void serviceItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceItemBtnActionPerformed
        new ServiceItem(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_serviceItemBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new Manager(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton serviceCategoryBtn;
    private javax.swing.JButton serviceItemBtn;
    // End of variables declaration//GEN-END:variables
}
