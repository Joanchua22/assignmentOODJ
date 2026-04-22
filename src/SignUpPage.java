import javax.swing.*;
import java.io.IOException;

public class SignUpPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SignUpPage.class.getName());

    public SignUpPage() {
        initComponents();     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSignUp = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JLabel();
        txtPassword = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tpNumField = new javax.swing.JTextField();
        txtName = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        phoneNumField = new javax.swing.JTextField();
        txtPhoneNum = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        vrnField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        vehicleModelField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        vehicleType = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        YOM = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSignUp.setText("Sign Up Page");

        txtUsername.setText("Username:");

        txtPassword.setText("Password:");

        jLabel2.setText("TP Number:");

        txtName.setText("Name:");

        txtPhoneNum.setText("Phone Number:");

        txtEmail.setText("Email Address:");

        jLabel1.setText("Vehicle Registration Number:");

        jLabel3.setText("Vehicle Model:");

        jLabel4.setText("Vehicle Type:");

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnSignUp.setText("Sign Up");
        btnSignUp.addActionListener(this::btnSignUpActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("(Eg: Proton Saga)");

        vehicleType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sedan", "Micro", "Hatchback", "Crossover",
            "Coupe", "Coupe SUV", "SUV", "Off-Roader", "Pick-Up",
            "MPV", "Van", "Sport", "Hyper", "Wagon"}));
vehicleType.addItemListener(this::vehicleTypeItemStateChanged);

jLabel6.setText("Year of Manufacture: ");

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(txtSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnBack)
                .addGap(64, 64, 64)
                .addComponent(btnSignUp)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    .addGroup(layout.createSequentialGroup()
        .addGap(36, 36, 36)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtUsername)
            .addComponent(jLabel2)
            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtEmail)
            .addComponent(jLabel1)
            .addComponent(jLabel3)
            .addComponent(jLabel4)
            .addComponent(txtPhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel6))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(jLabel5)
            .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
            .addComponent(phoneNumField)
            .addComponent(tpNumField)
            .addComponent(passwordField)
            .addComponent(usernameField)
            .addComponent(emailField)
            .addComponent(vrnField)
            .addComponent(vehicleModelField)
            .addComponent(vehicleType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(YOM))
        .addGap(57, 57, 57))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(37, 37, 37)
            .addComponent(txtSignUp)
            .addGap(28, 28, 28)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtUsername)
                    .addGap(29, 29, 29)
                    .addComponent(txtPassword)
                    .addGap(29, 29, 29)
                    .addComponent(jLabel2)
                    .addGap(29, 29, 29)
                    .addComponent(txtName)
                    .addGap(34, 34, 34)
                    .addComponent(txtPhoneNum)
                    .addGap(32, 32, 32)
                    .addComponent(txtEmail))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23)
                    .addComponent(tpNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(phoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(29, 29, 29)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel1)
                .addComponent(vrnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(25, 25, 25)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(vehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(21, 21, 21)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(vehicleModelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(YOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(22, 22, 22)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnBack)
                .addComponent(btnSignUp))
            .addGap(19, 19, 19))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        try {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String tp = tpNumField.getText().trim();
            String name = nameField.getText().trim();
            String phone = phoneNumField.getText().trim();
            String email = emailField.getText().trim();
            String vrn = vrnField.getText().trim();
            String model = vehicleModelField.getText().trim();
            String selectedvehicleType = (String) vehicleType.getSelectedItem();
            String vehicleYear = YOM.getText().trim();


            if (FileManager.isEmpty(username) || FileManager.isEmpty(password) ||
                FileManager.isEmpty(tp) || FileManager.isEmpty(name) ||
                FileManager.isEmpty(phone) || FileManager.isEmpty(email) ||
                FileManager.isEmpty(vrn) || FileManager.isEmpty(model) ||
                FileManager.isEmpty(selectedvehicleType) || FileManager.isEmpty(vehicleYear)) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields before signing up.");
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
            
            if (!FileManager.isFourDigitYear(vehicleYear)) {
                JOptionPane.showMessageDialog(this, "Year of Manufacture must be 4 digits.");
                return;
            }

            if (!FileManager.isNotFutureYear(vehicleYear)) {
                JOptionPane.showMessageDialog(this, "Year of Manufacture cannot be more than current year.");
                return;
            }

            if (FileManager.usernameExists(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
                return;
            }

            if (FileManager.tpExists(tp)) {
                JOptionPane.showMessageDialog(this, "TP Number already exists.");
                return;
            }

            if (FileManager.phoneExists(phone)) {
                JOptionPane.showMessageDialog(this, "Phone number already exists.");
                return;
            }

            if (FileManager.emailExists(email)) {
                JOptionPane.showMessageDialog(this, "Email already exists.");
                return;
            }

            if (FileManager.vehicleRegExists(vrn)) {
                JOptionPane.showMessageDialog(this, "Vehicle registration number already exists.");
                return;
            }

            FileManager.registerCustomer(username, password, tp, name, phone, email, vrn, selectedvehicleType, model, vehicleYear);

            JOptionPane.showMessageDialog(this, "Sign up successful! You can now login.");
            this.dispose();
            new LoginPage().setVisible(true);

        } catch (IOException e) {
            logger.severe("Error during sign up: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "An error occurred while signing up. Please try again.");
        }
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void vehicleTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_vehicleTypeItemStateChanged

    }//GEN-LAST:event_vehicleTypeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField YOM;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nameField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneNumField;
    private javax.swing.JTextField tpNumField;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtPassword;
    private javax.swing.JLabel txtPhoneNum;
    private javax.swing.JLabel txtSignUp;
    private javax.swing.JLabel txtUsername;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextField vehicleModelField;
    private javax.swing.JComboBox<String> vehicleType;
    private javax.swing.JTextField vrnField;
    // End of variables declaration//GEN-END:variables
}
