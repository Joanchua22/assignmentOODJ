import javax.swing.*;
import java.io.IOException;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        forgotPasswordLabel.setText("<html><u>Forget Password?</u></html>");
        forgotPasswordLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            new ForgetPassword().setVisible(true);
            dispose();
        }
    });
    }
    
    private boolean validateLoginInput(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return false;
        }
        return true;
    }
    
    private String[] verifyLogin(String username, String password) {
        try {
            return FileManager.verifyUser(username, password);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading user file.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private void recordLoginActivity(String currentUserId) {
        try {
            FileManager.addActivityLog(currentUserId, "Login", "User logged in");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to record activity log.");
        }
    }
    
    private void openPageByRole(String role, String currentUserId) {
        this.dispose();

        switch (role.toLowerCase()) {
            case "manager" -> new Manager(currentUserId).setVisible(true);
            // case "counter staff" -> new CounterStaffPage(currentUserId).setVisible(true);
            case "technician" -> new TechnicianPage(currentUserId).setVisible(true);
            case "customer" -> new Customer(currentUserId).setVisible(true);
            default -> JOptionPane.showMessageDialog(this, "Role not recognized. Please contact staff.");
        }
    }
    
    public void userLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (!validateLoginInput(username, password)) {
            return;
        }

        String[] userData = verifyLogin(username, password);

        if (userData == null) {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String currentUserId = userData[0];
        String role = userData[1];

        if (role.equalsIgnoreCase("INACTIVE")) {
            JOptionPane.showMessageDialog(this, "Your account is inactive. Please contact staff.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + username + "!");

        recordLoginActivity(currentUserId);
        openPageByRole(role, currentUserId);
    }
    
    private void openHomePage(){
        new Home().setVisible(true);
        this.dispose();
    }
      

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLogin = new javax.swing.JLabel();
        txtUsername = new javax.swing.JLabel();
        txtPassword = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        forgotPasswordLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtLogin.setText("Login Page");

        txtUsername.setText("Username:");

        txtPassword.setText("Password:");

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnLogin.setText("Login");
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        forgotPasswordLabel.setText("Forget Password?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(passwordField)
                                    .addComponent(forgotPasswordLabel)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnBack)
                        .addGap(48, 48, 48)
                        .addComponent(btnLogin)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(txtLogin)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forgotPasswordLabel)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnLogin))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        openHomePage();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        userLogin();
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel forgotPasswordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel txtLogin;
    private javax.swing.JLabel txtPassword;
    private javax.swing.JLabel txtUsername;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
