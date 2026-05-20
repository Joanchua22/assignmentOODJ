import javax.swing.*;
import java.awt.*;

public class StaffAppointment {

    private String currentUserId;

    public StaffAppointment(String currentUserId) {

        this.currentUserId = currentUserId;

        JFrame frame = new JFrame("APU ASC - Appointment");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("APPOINTMENT MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(0, 40, 800, 40);
        frame.add(title);

        //make appointment
        JButton makeBtn = new JButton("MAKE APPOINTMENT");
        makeBtn.setBounds(280, 130, 240, 45);
        frame.add(makeBtn);

        makeBtn.addActionListener(e -> {
            new StaffMakeAppointment(currentUserId);
            frame.dispose();
        });

        //view appointment
        JButton viewBtn = new JButton("VIEW APPOINTMENT");
        viewBtn.setBounds(280, 210, 240, 45);
        frame.add(viewBtn);

        viewBtn.addActionListener(e -> {
            new StaffViewAppointment(currentUserId);
            frame.dispose();
        });

        //assign technician
        JButton assignBtn = new JButton("ASSIGN TECHNICIAN");
        assignBtn.setBounds(280, 290, 240, 45);
        frame.add(assignBtn);

        assignBtn.addActionListener(e -> {
            new StaffAssignTechnician(currentUserId);
            frame.dispose();
        });

        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(20, 20, 80, 30);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new StaffMain(currentUserId);
            frame.dispose();
        });

        frame.setLocation(0,0);
        frame.setVisible(true);
    }
}