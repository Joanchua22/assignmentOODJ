import java.io.IOException;
import javax.swing.JOptionPane;

public class StaffProfilePage extends javax.swing.JFrame {
    
    private final String currentUserId;
    private String originalUsername;
    private String originalRole;
    private String originalName;
    private String originalTP;
    private String originalPhone;
    private String originalEmail;
    private String originalStatus;

    public StaffProfilePage(String userId) {
        initComponents();
        this.currentUserId = userId;
        passwordField.setEditable(false);
        loadStaffProfile();
    }
    
    private void loadStaffProfile(){
        try {
            String[] staff = FileManager.getStaffById(currentUserId);
            if (staff == null){
                JOptionPane.showMessageDialog(this, "Staff record not found.");
                return;
            }
            
            usernameField.setText(staff[1]);
            passwordField.setText("********");
            roleComboBox.setSelectedItem(staff[3]);
            fullNameField.setText(staff[4]);
            tpField.setText(staff[5]);
            phoneField.setText(staff[6]);
            emailField.setText(staff[7]);
            statusComboBox.setSelectedItem(staff[8]);
            
            originalUsername = staff[1];
            originalRole = staff[3];
            originalName = staff[4];
            originalTP = staff[5];
            originalPhone = staff[6];
            originalEmail = staff[7];
            originalStatus = staff[8];
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading staff profile.");}
    }
    
    private boolean isDataChanged() {
        return !usernameField.getText().trim().equals(originalUsername) ||
               !((String) roleComboBox.getSelectedItem()).equals(originalRole) ||
               !fullNameField.getText().trim().equals(originalName) ||
               !tpField.getText().trim().equals(originalTP) ||
               !phoneField.getText().trim().equals(originalPhone) ||
               !emailField.getText().trim().equals(originalEmail) ||
               !((String) statusComboBox.getSelectedItem()).equals(originalStatus);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        roleComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fullNameField = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        deleteBtn = new javax.swing.JButton();
        emailField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setText("Phone No. :");

        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Counter Staff", "Technician"}));

        jLabel8.setText("Email: ");

        jLabel9.setText("Status:");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        jLabel1.setText("Staff Profile");

        jLabel2.setText("Username:");

        jLabel3.setText("Password: ");

        jLabel4.setText("Role: ");

        jLabel5.setText("Full Name:");

        updateBtn.setText("Update");
        updateBtn.addActionListener(this::updateBtnActionPerformed);

        jLabel6.setText("TP Number: ");

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(this::deleteBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3))
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))
                                        .addGap(36, 36, 36))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(55, 55, 55)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernameField)
                                    .addComponent(roleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(passwordField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(emailField)
                                            .addComponent(phoneField)
                                            .addComponent(tpField)
                                            .addComponent(fullNameField)
                                            .addComponent(statusComboBox, 0, 152, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(updateBtn)
                                .addGap(70, 70, 70)
                                .addComponent(deleteBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(backBtn)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn)
                    .addComponent(deleteBtn))
                .addGap(18, 18, 18)
                .addComponent(backBtn)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        if (isDataChanged()) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "You have unsaved changes. Are you sure you want to go back?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.NO_OPTION) {
                return;
            }
        }

        new ManageStaffPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
    try {
        String username = usernameField.getText().trim();
        String role = (String) roleComboBox.getSelectedItem();
        String fullName = fullNameField.getText().trim();
        String tp = tpField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String status = (String) statusComboBox.getSelectedItem();

        if (FileManager.isEmpty(username) || FileManager.isEmpty(role) ||
            FileManager.isEmpty(fullName) || FileManager.isEmpty(tp) ||
            FileManager.isEmpty(phone) || FileManager.isEmpty(email) ||
            FileManager.isEmpty(status)) {

            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (!FileManager.isValidTP(tp)) {
            JOptionPane.showMessageDialog(this, "TP Number must start with TP followed by 6 digits.");
            return;
        }

        if (!FileManager.isValidPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10-11 digits.");
            return;
        }

        if (!FileManager.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return;
        }

        if (FileManager.usernameExistsExcept(username, currentUserId)) {
            JOptionPane.showMessageDialog(this, "Username already exists.");
            return;
        }

        if (FileManager.tpExistsExcept(tp, currentUserId)) {
            JOptionPane.showMessageDialog(this, "TP Number already exists.");
            return;
        }

        if (FileManager.phoneExistsExcept(phone, currentUserId)) {
            JOptionPane.showMessageDialog(this, "Phone number already exists.");
            return;
        }

        if (FileManager.emailExistsExcept(email, currentUserId)) {
            JOptionPane.showMessageDialog(this, "Email already exists.");
            return;
        }

        boolean success = FileManager.updateStaff(
                currentUserId, username, role, fullName, tp, phone, email, status
        );

        if (success) {
            JOptionPane.showMessageDialog(this, "Staff profile updated successfully.");

            originalUsername = username;
            originalRole = role;
            originalName = fullName;
            originalTP = tp;
            originalPhone = phone;
            originalEmail = email;
            originalStatus = status;
            
            this.dispose();
            new ManageStaffPage().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Staff profile update failed.");
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error updating staff profile.");}
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to delete this staff record?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
                );
        
        if (confirm != JOptionPane.YES_OPTION){
            return;
        }
        
        try {
            boolean success = FileManager.deleteStaff(currentUserId);
            
            if(success){
                JOptionPane.showMessageDialog(this, "Staff record deleted successful.");
                new ManageStaffPage().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Staff record not found.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting staff record.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField fullNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JTextField tpField;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
