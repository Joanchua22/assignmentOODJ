
package CounterStaff;

import java.awt.*;
import javax.swing.*;

public class SC_Appointment {
    
    public SC_Appointment(){
        JFrame frame = new JFrame("APU ASC - Counter Staff - Appointment");
        frame.setSize(800, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JLabel l1 = new JLabel ("Appointment Menu", SwingConstants.CENTER);
        l1. setFont(new Font("Arial", Font.BOLD, 30));
        l1.setBounds(0, 30, 800, 50);
        frame.add(l1);
        
        JButton b1 = new JButton("MAKE APPOINTMENT");
        b1.setBounds(300,120,200,40);
        frame.add(b1);
        b1.addActionListener(e ->{
            new SC_MakeAppointment();
            frame.dispose();
        });
        
        JButton b2 = new JButton("VIEW APPOINTMENT");
        b2.setBounds(300,180,200,40);
        frame.add(b2);
        b2.addActionListener(e ->{
            new SC_ViewAppointment();
            frame.dispose();
        });
        
        JButton b3 = new JButton("ASSIGN TECHNICIAN");
        b3.setBounds(300,240,200,40);
        frame.add(b3);
        b3.addActionListener(e ->{
            new SC_AssignTechnician();
            frame.dispose();
        });
        
        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);
        backBtn.addActionListener(e ->{
            new SC_Main();
            frame.dispose(); 
        });
        
        frame.setVisible(true);
    }
}
