package CounterStaff;

import javax.swing.*;
import java.awt.*;

public class SC_Main {

    public SC_Main() {
        JFrame frame = new JFrame("APU ASC - Counter Staff");
        frame.setSize(800, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Counter Staff", SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setBounds(0, 30, 800, 50);
        frame.add(l1);

        JButton b1 = new JButton("EDIT PROFILE");
        b1.setBounds(300, 120, 200, 40);
        frame.add(b1);

        JButton b2 = new JButton("EDIT CUSTOMER");
        b2.setBounds(300, 180, 200, 40);
        frame.add(b2);
        b2.addActionListener(e -> {
            new SC_EditCustomer();
            frame.dispose();
        });

        JButton b3 = new JButton("APPOINTMENT");
        b3.setBounds(300, 240, 200, 40);
        frame.add(b3);
        b3.addActionListener(e -> {
            new SC_Appointment();
            frame.dispose();
        });

        JButton b4 = new JButton("PAYMENT");
        b4.setBounds(300, 300, 200, 40);
        frame.add(b4);
        b4.addActionListener(e -> {
            new SC_Payment();
            frame.dispose();
        });

        JButton b5 = new JButton("LOGOUT");
        b5.setBounds(300, 360, 200, 40);
        frame.add(b5);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SC_Main();
    }

}
