import javax.swing.*;
import java.awt.*;
import java.io.*;

public class StaffEditProfile {

    private String currentUserId;
    private JFrame frame;

    //fields
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JTextField fullNameField;
    private JTextField tpField;
    private JTextField phoneField;
    private JTextField emailField;

    public StaffEditProfile(String currentUserId) {
        this.currentUserId = currentUserId;

        frame = new JFrame("APU ASC - Staff Edit Profile");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //title
        JLabel title = new JLabel("EDIT PROFILE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(0, 20, 800, 40);
        frame.add(title);

        int labelX = 200;
        int fieldX = 330;
        int y = 100;
        int gap = 50;

        //userID
        frame.add(new JLabel("User ID:")).setBounds(labelX, y, 120, 30);
        userIdField = new JTextField();
        userIdField.setBounds(fieldX, y, 200, 30);
        userIdField.setEditable(false);
        frame.add(userIdField);

        y += gap;

        //password
        frame.add(new JLabel("Password:")).setBounds(labelX, y, 120, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(fieldX, y, 200, 30);
        frame.add(passwordField);

        JButton changePassBtn = new JButton("Change Password");
        changePassBtn.setBounds(fieldX + 210, y, 150, 30);
        frame.add(changePassBtn);

        changePassBtn.addActionListener(e -> {
            new ChangePassword(currentUserId, "StaffEditProfile").setVisible(true);
            frame.dispose();
        });

        y += gap;

        //username
        frame.add(new JLabel("Username:")).setBounds(labelX, y, 120, 30);
        usernameField = new JTextField();
        usernameField.setBounds(fieldX, y, 200, 30);
        frame.add(usernameField);

        y += gap;

        //full name
        frame.add(new JLabel("Full Name:")).setBounds(labelX, y, 120, 30);
        fullNameField = new JTextField();
        fullNameField.setBounds(fieldX, y, 200, 30);
        frame.add(fullNameField);

        y += gap;

        //tp number
        frame.add(new JLabel("TP Number:")).setBounds(labelX, y, 120, 30);
        tpField = new JTextField();
        tpField.setBounds(fieldX, y, 200, 30);
        tpField.setEditable(false);
        frame.add(tpField);

        y += gap;

        //phone
        frame.add(new JLabel("Phone:")).setBounds(labelX, y, 120, 30);
        phoneField = new JTextField();
        phoneField.setBounds(fieldX, y, 200, 30);
        frame.add(phoneField);

        y += gap;

        //email
        frame.add(new JLabel("Email:")).setBounds(labelX, y, 120, 30);
        emailField = new JTextField();
        emailField.setBounds(fieldX, y, 200, 30);
        frame.add(emailField);

        //save button
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBounds(300, 470, 100, 40);
        frame.add(saveBtn);

        saveBtn.addActionListener(e -> saveProfile());

        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(420, 470, 100, 40);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new StaffMain(currentUserId);
            frame.dispose();
        });

        loadData();

        frame.setVisible(true);
    }

    //load data
    private void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users.txt"));
            String line;

            System.out.println(new File("src/users.txt").getAbsolutePath());
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].equals(currentUserId)) {
                    userIdField.setText(data[0]);
                    passwordField.setText(data[2]);
                    usernameField.setText(data[1]);
                    fullNameField.setText(data[4]);
                    tpField.setText(data[5]);
                    phoneField.setText(data[6]);
                    emailField.setText(data[7]);
                    break;
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //save
    private void saveProfile() {
        try {
            File file = new File("src/users.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].equals(currentUserId)) {
                    data[1] = usernameField.getText();
                    data[2] = new String(passwordField.getPassword());
                    data[4] = fullNameField.getText();
                    data[5] = tpField.getText();
                    data[6] = phoneField.getText();
                    data[7] = emailField.getText();

                    line = String.join(",", data);
                }

                sb.append(line).append("\n");
            }

            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();

            JOptionPane.showMessageDialog(frame, "Profile updated!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error updating profile");
        }
    }
}