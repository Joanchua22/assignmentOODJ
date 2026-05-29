
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class StaffAssignTechnician {
    
    JTable appTable, techTable;
    DefaultTableModel appModel, techModel;
    
    private String currentUserId;

    public StaffAssignTechnician(String currentUserId){
        
        this.currentUserId = currentUserId;
    
        JFrame frame = new JFrame("APU ASC - Counter Staff - Assign Technician");
        frame.setSize(1000, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        //title
        JLabel title = new JLabel("Assign Technician", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(40, 20, 800, 50);
        frame.add(title);
        
        //appointment table
        JLabel appLabel = new JLabel("Appointment List");
        appLabel.setBounds(250, 65, 200, 30);
        frame.add(appLabel);
        
        String[] appColumns = {
            "No.", 
            "Appointment ID", 
            "Customer ID", 
            "Vehicle ID", 
            "Service ID", 
            "Status", 
            "Technician ID"
        };
        
        appModel = new DefaultTableModel(appColumns, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        appTable = new JTable(appModel);
        JScrollPane appScroll = new JScrollPane(appTable);
        appScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        appScroll.setBounds(50, 100, 480, 250);
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
        techLabel.setBounds(710, 65, 100, 30);
        frame.add(techLabel);
        
        String[] techColumns = {"Technician ID", "Status"};
        
        techModel = new DefaultTableModel(techColumns, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        techTable = new JTable(techModel);
        JScrollPane techScroll = new JScrollPane(techTable);
        techScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        techScroll.setBounds(650, 100, 223, 250);
        frame.add(techScroll);
        
        techTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        techTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        techTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        
        //load appointments data
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/appointments.txt")); //app file name
            String line;
            int no = 1;
            
            while((line = br.readLine()) !=null){
                
                if(line.trim().isEmpty()) continue;
                
                String[] data = line.split(",");
                
                if (data.length < 10) continue;
                
                String status = data[9];
                
                if (status.equalsIgnoreCase("Assigned") ||
                    status.equalsIgnoreCase("Completed") ||
                    status.equalsIgnoreCase("Cancelled")){
                    continue;
                }
                    
                    Object[] row = {
                            no++,
                            data[0], //appointmentID
                            data[2], //customerID
                            data[1], //vehicleID
                            data[5], //service
                            data[9], //status
                            data[4]  //technicianID
                    };        
                    
                    appModel.addRow(row);
                }
 
            br.close();
        
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "No appointment data.");
        }
        
        //load technician data
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/users.txt")); //technician file name //change
            String line;

            while((line = br.readLine()) != null){
                
                String[] data = line.split(",");
                
                if(data.length < 4) continue;
                
                String role = data[3].trim();
                
                //only technician
                if (role.equalsIgnoreCase("Technician")) {
                    techModel.addRow(new Object[]{
                        data[0], //techID
                        "Available"
                    });
                }
            }

            br.close();
            
            updateTechnicianAvailability();
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Error loading technicians.");
        }
        
        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);
        backBtn.addActionListener(e ->{
            new StaffAppointment(currentUserId);
            frame.dispose();
        });  
        
        //assign button
        JButton assignBtn = new JButton("ASSIGN");
        assignBtn.setBounds(400,380,120,40);
        frame.add(assignBtn);
        
        assignBtn.addActionListener(e -> {

            int appRow = appTable.getSelectedRow();
            int techRow = techTable.getSelectedRow();

            if (appRow == -1 || techRow == -1) {
                JOptionPane.showMessageDialog(frame, "Select appointment and technician!");
                return;
            }

            String techStatus = String.valueOf(techTable.getValueAt(techRow, 1));

            if (!techStatus.equalsIgnoreCase("Available")) {
                JOptionPane.showMessageDialog(frame, "Technician is not available!");
                return;
            }

            String techID = techTable.getValueAt(techRow, 0).toString();

            appTable.setValueAt(techID, appRow, 6);
            appTable.setValueAt("Assigned", appRow, 5);

            techTable.setValueAt("Unavailable", techRow, 1);

            saveAppointments(appTable);

            JOptionPane.showMessageDialog(null, "Assigned successfully!");
        });
        
        frame.setVisible(true);
        
    }
        
    //update technician availability
    private void updateTechnicianAvailability() {

    try {
        BufferedReader techReader = new BufferedReader(new FileReader("src/users.txt"));
        ArrayList<String[]> techList = new ArrayList<>();

        String line;

        while ((line = techReader.readLine()) != null) {
            techList.add(line.split(","));
        }
        techReader.close();

        BufferedReader appReader = new BufferedReader(new FileReader("src/appointments.txt"));
        ArrayList<String[]> appointments = new ArrayList<>();

        while ((line = appReader.readLine()) != null) {
            appointments.add(line.split(","));
        }
        appReader.close();

        for (String[] tech : techList) {

            if (tech.length < 5) continue;
            if (!tech[3].equalsIgnoreCase("Technician")) continue;

            String techID = tech[0];

            boolean hasAssigned = false;

            for (String[] app : appointments) {

                if (app.length < 10) continue;

                String assignedTech = app[4]; 
                String status = app[9];

                if (techID.equals(assignedTech) && status.equalsIgnoreCase("Assigned")) {
                    hasAssigned = true;
                    break;
                }
            }

            //status
            tech[4] = hasAssigned ? "Unavailable" : "Available";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.txt"));

        for (String[] tech : techList) {
            writer.write(String.join(",", tech));
            writer.newLine();
        }

        writer.close();

        techModel.setRowCount(0);

        for (String[] tech : techList) {
            if (tech.length >= 5 && tech[3].equalsIgnoreCase("Technician")) {
                techModel.addRow(new Object[]{
                    tech[0],
                    tech[4]
                });
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}



    //save appointment
    private void saveAppointments(JTable appTable) {
        try{
            File file = new File("src/appointments.txt"); //app file name

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
                        data[4] = techID;
                        data[9] = status;
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/users.txt"));

            for(int i = 0; i < table.getRowCount(); i++){

                bw.write(
                        table.getValueAt(i,0) + "," +
                        table.getValueAt(i,1)
                );
                bw.newLine();
            }

            bw.close();

        } catch(Exception e){
            e.printStackTrace();
        }
     }    
}