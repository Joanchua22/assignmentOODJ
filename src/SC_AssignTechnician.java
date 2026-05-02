package CounterStaff;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class SC_AssignTechnician {
    
    JTable appTable, techTable;
    DefaultTableModel appModel, techModel;

    public SC_AssignTechnician(){
    
        JFrame frame = new JFrame("APU ASC - Counter Staff - Assign Technician");
        frame.setSize(1000, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        //title
        JLabel title = new JLabel("ASSIGN TECHNICIAN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(0, 20, 800, 50);
        frame.add(title);
        
        //appointment table
        JLabel appLabel = new JLabel("Appointment List");
        appLabel.setBounds(190, 65, 200, 30);
        frame.add(appLabel);
        
        String[] appColumns = {"No.", "Appointment ID", "Customer ID", "Vehicle ID", "Service", "Status", "Technician ID"};
        
        appModel = new DefaultTableModel(appColumns, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        appTable = new JTable(appModel);
        JScrollPane appScroll = new JScrollPane(appTable);
        appScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        appScroll.setBounds(50, 100, 400, 250);
        frame.add(appScroll);
        
        appTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        appTable.getColumnModel().getColumn(0).setPreferredWidth(50); //no
        appTable.getColumnModel().getColumn(1).setPreferredWidth(120); //appointmenID
        appTable.getColumnModel().getColumn(2).setPreferredWidth(100); //custID
        appTable.getColumnModel().getColumn(3).setPreferredWidth(120); //vehicleID
        appTable.getColumnModel().getColumn(4).setPreferredWidth(140); //serviceType
        appTable.getColumnModel().getColumn(5).setPreferredWidth(100); //status
        appTable.getColumnModel().getColumn(6).setPreferredWidth(120); //technicianID
        
        //technician table
        JLabel techLabel = new JLabel("Technician list");
        techLabel.setBounds(680, 65, 200, 30);
        frame.add(techLabel);
        
        String[] techColumns = {"Technician ID", "Name", "Status"};
        
        techModel = new DefaultTableModel(techColumns, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        techTable = new JTable(techModel);
        JScrollPane techScroll = new JScrollPane(techTable);
        techScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        techScroll.setBounds(550, 100, 350, 250);
        frame.add(techScroll);
        
        techTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        techTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        techTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        techTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        
        //load appointments data
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/CounterStaff/appointment.txt")); //app file name //change
            String line;
            int no = 1;
            
            while((line = br.readLine()) !=null){
                
                if(line.trim().isEmpty()) continue;
                
                String[] data = line.split(",");
                
                if(data.length < 9) continue;
                
                if (!data[8].equals("Completed")){
                    
                    Object[] row = {
                            no++,
                            data[0], //appointmentID
                            data[1], //customerID
                            data[4], //vehicleID
                            data[5], //service
                            data[8], //status
                            data[7]  //technicianID
                    };        
                    
                    appModel.addRow(row);
                }
            }
            
            br.close();
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(frame, "No appointment data.");
        }
        
        //load technician data
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/CounterStaff/technician.txt")); //technician file name //change
            String line;

            while((line = br.readLine()) != null){
                
                String[] data = line.split(",");
                
                if(data.length < 3) continue;
                
                techModel.addRow(data);
            }

            br.close();
            
            updateTechnicianAvailability();
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(frame, "No technician data.");
        }
        
        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);
        backBtn.addActionListener(e ->{
            new SC_Appointment();
            frame.dispose();
        });  
        
        //assign button
        JButton assignBtn = new JButton("ASSIGN");
        assignBtn.setBounds(350,380,120,40);
        frame.add(assignBtn);
        assignBtn.addActionListener(e ->{
            int appRow = appTable.getSelectedRow();
            int techRow = techTable.getSelectedRow();
            
            if(appRow == -1 || techRow == -1){
                JOptionPane.showMessageDialog(frame, "Select appointment and technician!");
                return;
            }
            
            String currentTech = appTable.getValueAt(appRow,6).toString();

            if(!currentTech.equals("NULL") && !currentTech.trim().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Appointment already assigned!");
                return;
            }
            
            String techStatus = techTable.getValueAt(techRow, 2).toString();
            
            if (!techStatus.equalsIgnoreCase("Available")){
                JOptionPane.showMessageDialog(frame, "Technician not available!");
                return;
            }

            String techID = techTable.getValueAt(techRow, 0).toString();

            appTable.setValueAt(techID, appRow, 6);
            appTable.setValueAt("Assigned", appRow, 5);

            techTable.setValueAt("Unavailable", techRow, 2);

            //updateTechnicianAvailability();
            saveAppointments(appTable);
            saveTechnicians(techTable);

            JOptionPane.showMessageDialog(null, "Assigned successfully!");
                    
        });  
        
        frame.setVisible(true);
        
    }
        
    //update technician availability
    private void updateTechnicianAvailability() {

    try {
        File techFile = new File("src/CounterStaff/technician.txt"); //tech file name //change
        ArrayList<String[]> techList = new ArrayList<>();

        BufferedReader techReader = new BufferedReader(new FileReader(techFile));
        String line;

        while ((line = techReader.readLine()) != null) {
            techList.add(line.split(","));
        }
        techReader.close();

        BufferedReader appReader = new BufferedReader(
                new FileReader("src/CounterStaff/appointment.txt")); //tech file name //change

        while ((line = appReader.readLine()) != null) {

            String[] data = line.split(",");

            if (data.length < 9) continue;

            String techID = data[7];
            String status = data[8];

            if (techID == null || techID.equals("NULL") || techID.trim().isEmpty()) {
                continue;
            }

            for (String[] tech : techList) {

                if (tech[0].equals(techID)) {

                    if (status.equalsIgnoreCase("Completed") || status.equalsIgnoreCase("Cancelled")) {
                        tech[2] = "Available";
                    }

                    if (status.equalsIgnoreCase("Assigned")) {
                        tech[2] = "Unavailable";
                    }
                }
            }
        }

        appReader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(techFile));

        for (String[] tech : techList) {
            writer.write(String.join(",", tech));
            writer.newLine();
        }

        writer.close();

        techModel.setRowCount(0);

        for (String[] tech : techList) {
            techModel.addRow(tech);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    //save appointment
    private void saveAppointments(JTable appTable) {
        try{
            File file = new File("src/CounterStaff/appointment.txt"); //app file name //change

            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String[]> allData = new ArrayList<>();

            String line;

            while ((line = br.readLine()) != null){
                allData.add(line.split(","));
            }

            br.close();

            for(int i = 0; i < appTable.getRowCount(); i++){

                String appointmentID = appTable.getValueAt(i,1).toString();
                String status = appTable.getValueAt(i,5).toString();
                String techID = appTable.getValueAt(i,6).toString();

                for (String[] data : allData){
                    if (data[0].equals(appointmentID)) {
                        data[7] = techID;
                        data[8] = status;
                    }
                }
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (String[] data : allData){
                bw.write(String.join(",", data));
                bw.newLine();
            }

            bw.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
        
    //save technician
    private void saveTechnicians(JTable table){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/CounterStaff/technician.txt")); //tech file name //change

            for(int i = 0; i < table.getRowCount(); i++){

                bw.write(
                        table.getValueAt(i,0) + "," +
                        table.getValueAt(i,1) + "," +
                        table.getValueAt(i,2)
                );
                bw.newLine();
            }

            bw.close();

        } catch(Exception e){
            e.printStackTrace();
        }
     }    
}