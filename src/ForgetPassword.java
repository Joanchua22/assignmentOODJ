import java.io.IOException;
import javax.swing.JOptionPane;

public class ForgetPassword extends javax.swing.JFrame {

    public ForgetPassword() {
        initComponents();
    }
    
    private String validateResetPasswordInput(
            String email,
            String tp,
            String newPassword,
            String confirmPassword
    ) {

        if (email.isEmpty() || tp.isEmpty()
                || newPassword.isEmpty()
                || confirmPassword.isEmpty()) {

            return "Please fill in all fields.";
        }

        if (!FileManager.isValidEmail(email)) {
            return "Invalid email format.";
        }

        if (!FileManager.isValidTP(tp)) {
            return "TP Number must start with TP followed by 6 digits.";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "New password and confirm password do not match.";
        }

        return "VALID";
    }
    
    private void sendResetPasswordEmail(String email) {

        String subject =
                "APU ASC - Password Reset Successful";

        String message =
                "Dear User,\n\n" +
                "Your password has been reset successfully.\n\n" +
                "If this action was not performed by you, " +
                "please contact support immediately.\n\n" +
                "Thank you.\n" +
                "APU Automotive Service Centre";

        EmailSender.sendEmail(email, subject, message);
    }
    
    private void resetPassword() {

        String email = emailField.getText().trim();
        String tp = tpField.getText().trim();

        String newPassword =
                new String(newPasswordField.getPassword()).trim();

        String confirmPassword =
                new String(confirmPasswordField.getPassword()).trim();

        String validationResult =
                validateResetPasswordInput(
                        email,
                        tp,
                        newPassword,
                        confirmPassword
                );

        if (!validationResult.equals("VALID")) {

            JOptionPane.showMessageDialog(
                    this,
                    validationResult
            );

            return;
        }

        try {

            String userId =
                    FileManager.getUserIdByEmailAndTP(email, tp);

            if (userId == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Email and TP Number do not match any account."
                );

                return;
            }

            boolean updated =
                    FileManager.updatePasswordByUserId(
                            userId,
                            newPassword
                    );

            if (updated) {

                sendResetPasswordEmail(email);

                JOptionPane.showMessageDialog(
                        this,
                        "Password reset successfully. Please login again."
                );

                new LoginPage().setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to reset password."
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error resetting password."
            );
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        resetPasswordBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        newPasswordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();
        tpField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Email: ");

        jLabel2.setText("TP Number: ");

        jLabel3.setText("New Password: ");

        jLabel4.setText("Comfirm Password: ");

        resetPasswordBtn.setText("Reset Password");
        resetPasswordBtn.addActionListener(this::resetPasswordBtnActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailField)
                    .addComponent(newPasswordField)
                    .addComponent(confirmPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(tpField))
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(resetPasswordBtn)
                .addGap(18, 18, 18)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetPasswordBtn)
                    .addComponent(backBtn))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new LoginPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void resetPasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordBtnActionPerformed
        resetPassword();
    }//GEN-LAST:event_resetPasswordBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JButton resetPasswordBtn;
    private javax.swing.JTextField tpField;
    // End of variables declaration//GEN-END:variables
}
