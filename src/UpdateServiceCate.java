import java.io.IOException;
import javax.swing.JOptionPane;


public class UpdateServiceCate extends javax.swing.JFrame {
    
    private final String serviceTypeId;
    private final String currentUserId;
    private String originalName = "";
    private String originalDuration = "";

    public UpdateServiceCate(String serviceTypeId, String currentUserId) throws IOException {
        initComponents();
        this.serviceTypeId = serviceTypeId;
        this.currentUserId = currentUserId;
        loadServiceTypeData();
    }

    
    private void loadServiceTypeData() {
        try {
            String[] serviceType = FileManager.getServiceTypeById(serviceTypeId);

            if (serviceType != null) {
                originalName = serviceType[1].trim();
                originalDuration = serviceType[2].trim();

                nameField.setText(originalName);
                durationField.setText(originalDuration);
            } else {
                JOptionPane.showMessageDialog(this, "Category not found.");
                new ServiceCategory(currentUserId).setVisible(true);
                dispose();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data.");
        }
    }
    
    private boolean hasChanges() {
        String currentName = nameField.getText().trim();
        String currentDuration = durationField.getText().trim();

        return !currentName.equals(originalName) || !currentDuration.equals(originalDuration);
    }
    
    private String validateServiceCategoryUpdate(
            String name,
            String duration
    ) throws IOException {

        if (name.isEmpty() || duration.isEmpty()) {
            return "Please fill in all fields.";
        }

        if (!FileManager.isValidDuration(duration)) {
            return "Duration must be a valid number greater than 0 (e.g., 1 or 1.5).";
        }

        if (FileManager.serviceTypeNameExistsExcept(name, serviceTypeId)) {
            return "Category name already exists.";
        }

        return "VALID";
    }
    
    private void recordUpdateServiceCategoryActivity() {
        try {
            FileManager.addActivityLog(
                    currentUserId,
                    "Update Service Category",
                    serviceTypeId + " Updated"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to record activity log.");
        }
    }
    
    private void updateServiceCategory() {
        String name = nameField.getText().trim();
        String duration = durationField.getText().trim();

        try {
            String validationResult =
                    validateServiceCategoryUpdate(name, duration);

            if (!validationResult.equals("VALID")) {
                JOptionPane.showMessageDialog(this, validationResult);
                return;
            }

            boolean updated =
                    FileManager.updateServiceType(
                            serviceTypeId,
                            name,
                            duration,
                            currentUserId
                    );

            if (updated) {
                recordUpdateServiceCategoryActivity();

                JOptionPane.showMessageDialog(
                        this,
                        "Service category updated successfully."
                );

                new ServiceCategory(currentUserId).setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating category.");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        durationField = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        saveBtn.setText("Save");
        saveBtn.addActionListener(this::saveBtnActionPerformed);

        jLabel2.setText("Name: ");

        jLabel1.setText("Update Service Category");

        jLabel3.setText("Duration (h): ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(saveBtn)
                        .addGap(33, 33, 33)
                        .addComponent(backBtn)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(saveBtn))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        if (hasChanges()) {
            Object[] options = {"No", "Yes"};

            int confirm = JOptionPane.showOptionDialog(
                    this,
                    "You have unsaved changes. Are you sure you want to go back?",
                    "Unsaved Changes",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (confirm == 1) {
                new ServiceCategory(currentUserId).setVisible(true);
                dispose();
            }
        } else {
            new ServiceCategory(currentUserId).setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_backBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        updateServiceCategory();
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField durationField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables
}
