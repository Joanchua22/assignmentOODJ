import javax.swing.*;
import java.awt.*;

public class StaffMain extends JFrame {

    private String currentUserId;

    public StaffMain(String currentUserId) {
        this.currentUserId = currentUserId;

        setTitle("APU ASC - Counter Staff");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l1 = new JLabel("Counter Staff", SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setBounds(0, 30, 800, 50);
        add(l1);

        //edit profile
        JButton b1 = new JButton("EDIT PROFILE");
        b1.setBounds(300, 120, 200, 40);
        add(b1);
        b1.addActionListener(e -> {
            new StaffEditProfile(currentUserId);
            dispose();
        });

        //edit customer
        JButton b2 = new JButton("EDIT CUSTOMER");
        b2.setBounds(300, 180, 200, 40);
        add(b2);
        b2.addActionListener(e -> {
            new StaffEditCustomer(currentUserId);
            dispose();
        });

        //appointment
        JButton b3 = new JButton("APPOINTMENT");
        b3.setBounds(300, 240, 200, 40);
        add(b3);
        b3.addActionListener(e -> {
            new StaffAppointment(currentUserId);
            dispose();
        });

        //payment
        JButton b4 = new JButton("PAYMENT");
        b4.setBounds(300, 300, 200, 40);
        add(b4);
        b4.addActionListener(e -> {
            new StaffPayment(currentUserId);
            dispose();
        });

        //logout
        JButton b5 = new JButton("LOGOUT");
        b5.setBounds(300, 360, 200, 40);
        add(b5);
        b5.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                new Login().setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }
}