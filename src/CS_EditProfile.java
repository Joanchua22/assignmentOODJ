
package CounterStaff;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CS_EditProfile {

    public CS_EditProfile(){

        JFrame frame = new JFrame("APU ASC - Counter Staff -Edit Profile");
        frame.setSize(800, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JLabel l1 = new JLabel("EDIT PROFILE", SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setBounds(0, 30, 800, 50);
        frame.add(l1);

        //labels
        JLabel staffIDL1 = new JLabel("Staff ID:");
        staffIDL1.setBounds(250, 120, 100, 30);
        frame.add(staffIDL1);

        JLabel userIDL2 = new JLabel("User ID:");
        userIDL2.setBounds(250, 170, 100, 30);
        frame.add(userIDL2);

        JLabel passwL3 = new JLabel("Password:");
        passwL3.setBounds(250, 220, 100, 30);
        frame.add(passwL3);

        //fields
        JTextField staffIDF1 = new JTextField();
        staffIDF1.setBounds(350, 120, 150, 30);
        staffIDF1.setEditable(false); //cannot change staffID
        frame.add(staffIDF1);

        JTextField userIDF2 = new JTextField();
        userIDF2.setBounds(350, 170, 150, 30);
        userIDF2.setEditable(false); //cannot change userID
        frame.add(userIDF2); 

        JTextField passwF3 = new JTextField();
        passwF3.setBounds(350, 220, 150, 30);
        frame.add(passwF3);

        //load current user data
        ArrayList<String[]> list = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(
                    new FileReader("src/CounterStaff/staff.txt")); //staff file name //change

            String line;

            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                list.add(data);

                if(data[0].equals(SC_Login.currentUser)){ //change
                    staffIDF1.setText(data[0]);
                    userIDF2.setText(data[1]);
                    passwF3.setText(data[2]);
                }
            }

            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        //save button
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBounds(340, 300, 100, 40);
        frame.add(saveBtn);

        saveBtn.addActionListener(e -> {

            String newPass = passwF3.getText();

            if(newPass.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Fill all fields!");
                return;
            }

            //update list
            for(String[] data : list){
                if(data[0].equals(SC_Login.currentUser)){ //change
                    data[1] = newPass;
                }
            }

            //save back to file
            try{
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("src/CounterStaff/staff.txt")); //staff file name //change

                for(String[] data : list){
                    bw.write(String.join(",", data));
                    bw.newLine();
                }

                bw.close();

                JOptionPane.showMessageDialog(frame, "Profile updated!");

            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        //back
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10, 10, 80, 30);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new CS_Main();
            frame.dispose();
        });

        frame.setVisible(true);
    }
}