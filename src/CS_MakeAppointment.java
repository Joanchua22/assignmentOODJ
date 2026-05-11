
package CounterStaff;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class CS_MakeAppointment {
    
    ArrayList<Appointment_CS> appointmentList = new ArrayList<>();
    
    public CS_MakeAppointment(){
    
        //frame title
        JFrame frame = new JFrame("APU ASC - Counter Staff - Make Appointment");
        frame.setSize(800, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        
        //title
        JLabel title = new JLabel("APPOINTMENT PAGE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(0, 20, 800, 50);
        frame.add(title);        
        
        //table
        JLabel tableTitle = new JLabel("Service Types");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
        tableTitle.setBounds(330, 70, 200, 30);
        frame.add(tableTitle);
        
        String[][]data = {
            {"Normal Service", "1 Hour"},
            {"Major Service", "3 Hours"}
        };
        
        String[] columns = {"Service Type", "Duration"};
        
        JTable table = new JTable(data, columns){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(25);
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    
        //table scroll function
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(250, 110, 300, 76);
        frame.add(scrollPane);                        
        
        //customer info
        JLabel cusInfo = new JLabel("Customer Information");
        cusInfo.setFont(new Font("Arial", Font.BOLD, 18));
        cusInfo.setBounds(280, 200, 200, 30);
        frame.add(cusInfo);
        
        //cust id
        JLabel custIDL1 = new JLabel("Customer ID:");
        custIDL1.setBounds(200, 240, 200, 40);
        frame.add(custIDL1);
        
        JTextField custIDF1 = new JTextField();
        custIDF1.setBounds(300, 246, 200, 30);
        frame.add(custIDF1);
        
        //cust name
        JLabel custNameL2 = new JLabel("Customer Name:");
        custNameL2.setBounds(200, 280, 200, 40);
        frame.add(custNameL2);
        
        JTextField custNameF2 = new JTextField();
        custNameF2.setBounds(300, 286, 200, 30);
        frame.add(custNameF2);
        
        //cust phone
        JLabel phoneL3 = new JLabel("Phone Number:");
        phoneL3.setBounds(200, 320, 200, 40);
        frame.add(phoneL3);
        
        JTextField phoneF3 = new JTextField();
        phoneF3.setBounds(300, 326, 200, 30);
        frame.add(phoneF3);
        
         //vehicle id
        JLabel vehicleIDL4 = new JLabel("Vehicle ID:");
        vehicleIDL4.setBounds(200, 360, 200, 40);
        frame.add(vehicleIDL4);
        
        JTextField vehicleIDF4 = new JTextField();
        vehicleIDF4.setBounds(300, 366, 200, 30);
        frame.add(vehicleIDF4);
                
        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);
        backBtn.addActionListener(e ->{
            new CS_Appointment();
            frame.dispose();
        });

        //create appointment button
        JButton appBtn = new JButton("CREATE APPOINTMENT");
        appBtn.setBounds(300, 400, 200, 40);
        frame.add(appBtn);
        
        appBtn.addActionListener(e ->{
            
            int row = table.getSelectedRow();
            
            
            String custID = custIDF1.getText();
            String custName = custNameF2.getText();
            String phone = phoneF3.getText();
            String vehicleID = vehicleIDF4.getText();
        
            if (row == -1 || custID.isEmpty() || custName.isEmpty() || phone.isEmpty() || vehicleID.isEmpty()){
                JOptionPane.showMessageDialog(frame,
                        "Please select a service type and fill in ALL fields!");
                return;
            }
        
            String appointmentID = generateAppointmentID();
            
            String serviceType = table.getValueAt(row, 0).toString();
            String duration = table.getValueAt(row, 1).toString();

            //default status
            String status = "Assigned";
            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String createdAt = now.format(formatter);
            String techID = "NULL";
            
            Appointment_CS appt = new Appointment_CS(
                    appointmentID,
                    custID,
                    custName,
                    phone,
                    vehicleID,
                    serviceType,
                    duration,
                    techID,
                    status,
                    createdAt
            );
            
            appointmentList.add(appt);
            
            JOptionPane.showMessageDialog(frame,
                    "Appointment Created!\n\n" +
                    "Cutomer ID: " + custID + "\n" +
                    "Cutomer Name: " + custName + "\n" +
                    "Phone Number: " + phone + "\n" +
                    "Vehicle ID: " + vehicleID + "\n" +
                    "Service: " + serviceType + "\n" +
                    "Duration: " + duration);
            
            custIDF1.setText("");
            custNameF2.setText("");
            phoneF3.setText("");
            vehicleIDF4.setText("");
            table.clearSelection();
            
            try {
                String path = "src/CounterStaff/appointment.txt"; //app file name //change
                
                java.io.File file = new java.io.File(path);
                System.out.println("Saving to: " + file.getAbsolutePath());

                file.getParentFile().mkdirs();
                file.createNewFile();
                
                FileWriter fw = new FileWriter(file, true);
                
                //file path based on the latest one
                System.out.println("Saving to: " + path);

                fw.write(appointmentID + "," + 
                        custID + "," + 
                        custName + "," + 
                        phone + "," +
                        vehicleID + "," + 
                        serviceType + "," + 
                        duration + "," + 
                        techID + "," + 
                        status + "," + 
                        createdAt + "\n");

                fw.close();

                System.out.println("Saved successfully!");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        });
    }
    
    
    private String generateAppointmentID(){
        int max = 0;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/CounterStaff/appointment.txt")); //app file name //change

            String line;

            while ((line = br.readLine()) != null){
                String[] data = line.split(",");

                if (data.length > 0){
                    String id = data[0]; 

                    int num = Integer.parseInt(id.replace("APT", ""));

                    if (num > max){
                        max = num;
                    }
                }
            }

            br.close();

        } catch (Exception e){
        
        }

        return String.format("APT%04d", max + 1);
    }
    
}
    
