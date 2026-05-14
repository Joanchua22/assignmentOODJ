import java.io.IOException;
import javax.swing.JOptionPane;


public class MyProfile extends javax.swing.JFrame {
    
    private String currentUserId;
    private String originalPhone = "";
    private String originalEmail = "";
    private String originalName = "";
    private String originalUsername = "";
        
    public MyProfile(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        loadProfileData();
        saveBtn.addActionListener(this::saveBtnActionPerformed);
    }
    
    private void loadProfileData() {
        try {
            String[] user = FileManager.getUserById(currentUserId);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "User profile not found.");
                return;
            }

            userIdField.setText(user[0]);
            passwordField.setText(user[2]);
            usernameField.setText(user[1]);
            fullNameField.setText(user[4]);
            tpNumField.setText(user[5]);
            phoneField.setText(user[6]);
            emailField.setText(user[7]);

            userIdField.setEditable(false);
            passwordField.setEditable(false);
            tpNumField.setEditable(false);
            
            originalUsername = user[1];
            originalName = user[4];
            originalPhone = user[6];
            originalEmail = user[7];

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading profile data.");
        }
    }
    
    private boolean hasChanges() {
        return !phoneField.getText().trim().equals(originalPhone)
                || !emailField.getText().trim().equals(originalEmail)
                || !fullNameField.getText().trim().equals(originalName)
                || !usernameField.getText().trim().equals(originalUsername);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fullNameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tpNumField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        changePasswordBtn = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        userIdField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("My Profile");

        jLabel2.setText("Username");

        jLabel3.setText("User ID:");

        jLabel4.setText("Full Name:");

        jLabel5.setText("TP Number:");

        jLabel6.setText("Phone:");

        jLabel7.setText("Email:");

        saveBtn.setText("Save");
        saveBtn.addActionListener(this::saveBtnActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        jLabel10.setText("Password:");

        changePasswordBtn.setText("Change Password");
        changePasswordBtn.addActionListener(this::changePasswordBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameField)
                    .addComponent(fullNameField)
                    .addComponent(tpNumField)
                    .addComponent(phoneField)
                    .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(passwordField)
                    .addComponent(userIdField))
                .addGap(18, 18, 18)
                .addComponent(changePasswordBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(saveBtn)
                .addGap(53, 53, 53)
                .addComponent(backBtn)
                .addContainerGap(181, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(changePasswordBtn)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(backBtn))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changePasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordBtnActionPerformed
        new ChangePasswordPage(currentUserId, "ManagerProfile").setVisible(true);
        dispose();
    }//GEN-LAST:event_changePasswordBtnActionPerformed

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

            if (confirm != 1) {
                return;
            }
        }

        new ManagerPage(currentUserId).setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {     
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String name = fullNameField.getText().trim();
            String username = usernameField.getText().trim();

            if (phone.isEmpty() || email.isEmpty()|| name.isEmpty() || username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Field cannot be empty.");
                return;
            }

            if (FileManager.usernameExistsExcept(username, currentUserId)) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
                return;
            }
            

            if (!FileManager.isValidPhone(phone)) {
                JOptionPane.showMessageDialog(this, "Phone number must be 10 to 11 digits.");
                return;
            }

            if (!FileManager.isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email format.");
                return;
            }

            boolean updated = FileManager.updateProfileByUserId(currentUserId, username, name, phone, email);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Profile updated successfully.");

                originalUsername = username;
                originalName = name;
                originalPhone = phone;
                originalEmail = email;

                FileManager.addActivityLog(currentUserId, "Update Profile", "Updated phone/email in profile.");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. User not found.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating profile.");
        }
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton changePasswordBtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField fullNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField tpNumField;
    private javax.swing.JTextField userIdField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
