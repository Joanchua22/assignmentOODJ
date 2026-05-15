import java.io.IOException;
import javax.swing.JOptionPane;

public class AddNewStaff extends javax.swing.JFrame {

    private final String currentUserId;

    public AddNewStaff(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
    }
    
    private String validateStaffInput(String username,String password,String role,
            String fullName,String tp,String phone,String email,String status) throws IOException {

        if (FileManager.isEmpty(username) ||
            FileManager.isEmpty(password) ||
            FileManager.isEmpty(role) ||
            FileManager.isEmpty(fullName) ||
            FileManager.isEmpty(tp) ||
            FileManager.isEmpty(phone) ||
            FileManager.isEmpty(email) ||
            FileManager.isEmpty(status)) {

            return "Please fill in all fields.";
        }

        if (!FileManager.isValidTP(tp)) {
            return "TP Number must start with TP followed by 6 digits.";
        }

        if (!FileManager.isValidPhone(phone)) {
            return "Phone number must be 10-11 digits.";
        }

        if (!FileManager.isValidEmail(email)) {
            return "Please enter a valid email address.";
        }

        if (FileManager.usernameExists(username)) {
            return "Username already exists.";
        }

        if (FileManager.tpExists(tp)) {
            return "TP Number already exists.";
        }

        if (FileManager.phoneExists(phone)) {
            return "Phone number already exists.";
        }

        if (FileManager.emailExists(email)) {
            return "Email already exists.";
        }

        return "VALID";
    } 
    
    private void sendStaffAccountEmail(String fullName,String role,String username,String password,String email) {

        String subject = "APU ASC - Staff Account Created";

        String message =
                "Dear " + fullName + ",\n\n" +
                "Your staff account has been created successfully.\n\n" +
                "Role: " + role + "\n" +
                "Username: " + username + "\n" +
                "Password: " + password + "\n\n" +
                "Please login and change your password after your first login.\n\n" +
                "Thank you.\n" +
                "APU Automotive Service Centre";

        EmailSender.sendEmail(email, subject, message);
    }
    
    
    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        fullnameField.setText("");
        tpField.setText("");
        phoneField.setText("");
        emailField.setText("");
        roleComboBox.setSelectedIndex(0);
        statusComboBox.setSelectedIndex(0);
    }
    
    private void recordAddStaffActivity(String role, String username) {
        try {
            FileManager.addActivityLog(
                    currentUserId,
                    "Add New Staff",
                    role + " " + username + " Added"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to record activity log.");
        }
    }
    
    private void addStaff() {

        try {

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String role = (String) roleComboBox.getSelectedItem();
            String fullName = fullnameField.getText().trim();
            String tp = tpField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String status = (String) statusComboBox.getSelectedItem();

            String validationResult =
                    validateStaffInput(username,password,role,fullName,tp,phone,email,status);

            if (!validationResult.equals("VALID")) {

                JOptionPane.showMessageDialog(this, validationResult);
                return;
            }

            FileManager.addStaff(username,password,role,fullName,tp,phone,email,status);

            sendStaffAccountEmail(fullName,role,username,password,email);

            recordAddStaffActivity(role, username);

            JOptionPane.showMessageDialog(
                    this,
                    "Staff added successfully."
            );

            clearFields();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error adding staff."
            );
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        roleComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        fullnameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tpField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        addBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Add New Staff");

        jLabel2.setText("Username:");

        jLabel3.setText("Password: ");

        jLabel4.setText("Role: ");

        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Counter Staff", "Technician"}));

        jLabel5.setText("Full Name:");

        jLabel6.setText("TP Number: ");

        jLabel7.setText("Phone No. :");

        jLabel8.setText("Email: ");

        jLabel9.setText("Status:");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        addBtn.setText("Add");
        addBtn.addActionListener(this::addBtnActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
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
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameField)
                            .addComponent(roleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailField)
                                    .addComponent(phoneField)
                                    .addComponent(tpField)
                                    .addComponent(fullnameField)
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(addBtn)
                        .addGap(52, 52, 52)
                        .addComponent(backBtn)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
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
                    .addComponent(fullnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(backBtn))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addStaff();
    }//GEN-LAST:event_addBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new ManageStaffPage(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField fullnameField;
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
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
