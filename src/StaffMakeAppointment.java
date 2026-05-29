import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class StaffMakeAppointment {

    JFrame frame;

    JComboBox<String> serviceBox;

    Map<String, String[]> serviceMap = new HashMap<>(); 

    Map<String, String> serviceTypeMap = new HashMap<>();

    private String currentUserId;

    public StaffMakeAppointment(String currentUserId) {

        this.currentUserId = currentUserId;

        frame = new JFrame("APU ASC - Counter Staff - Make Appointment");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("MAKE APPOINTMENT", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(0, 20, 800, 40);
        frame.add(title);

        //service dropdown
        JLabel serviceLabel = new JLabel("Select Service:");
        serviceLabel.setBounds(200, 80, 200, 30);
        frame.add(serviceLabel);

        serviceBox = new JComboBox<>();
        serviceBox.setBounds(320, 80, 250, 30);
        frame.add(serviceBox);

        loadServices();
        loadServiceTypes();

        //customer info
        //username
        JLabel custLabel = new JLabel("Username:");
        custLabel.setBounds(200, 130, 200, 30);
        frame.add(custLabel);

        JTextField usernameF = new JTextField();
        usernameF.setBounds(320, 130, 200, 30);
        frame.add(usernameF);
        
        //search button
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(540,130,100,30);
        frame.add(searchBtn);

        //vehicleID
        JLabel vehicleIDLabel = new JLabel("Vehicle ID:");
        vehicleIDLabel.setBounds(200, 180, 200, 30);
        frame.add(vehicleIDLabel);

        JComboBox<String> vehicleBox = new JComboBox<>();
        vehicleBox.setBounds(320, 180, 200, 30);
        frame.add(vehicleBox);
        
        searchBtn.addActionListener(e -> {

            vehicleBox.removeAllItems();

            String enteredUsername = usernameF.getText().trim();

            String userID = "";
            String custID = "";

            try {

                BufferedReader userBR = new BufferedReader(
                        new FileReader("src/users.txt"));

                String line;

                while ((line = userBR.readLine()) != null) {

                    String[] data = line.split(",");

                    String fileUserID = data[0];
                    String fileUsername = data[1];

                    if (fileUsername.equals(enteredUsername)) {

                        userID = fileUserID;
                        break;
                    }
                }

                userBR.close();

                if (userID.isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Username not found!");

                    return;
                }

                BufferedReader custBR = new BufferedReader(
                        new FileReader("src/customers.txt"));

                while ((line = custBR.readLine()) != null) {

                    String[] data = line.split(",");

                    String fileCustID = data[0];
                    String fileUserID = data[1];

                    if (fileUserID.equals(userID)) {

                        custID = fileCustID;
                        break;
                    }
                }

                custBR.close();

                if (custID.isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Customer record not found!");

                    return;
                }

                //show vehicleID
                BufferedReader vehicleBR = new BufferedReader(
                        new FileReader("src/vehicles.txt"));

                while ((line = vehicleBR.readLine()) != null) {

                    String[] data = line.split(",");

                    String vehicleID = data[0];
                    String vehicleCustID = data[1];

                    if (vehicleCustID.equals(custID)) {

                        vehicleBox.addItem(vehicleID);
                    }
                }

                vehicleBR.close();

                if (vehicleBox.getItemCount() == 0) {

                    JOptionPane.showMessageDialog(frame,
                            "No vehicle found!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //time
        JLabel startTimeLabel = new JLabel("Start Time (HH:MM):");
        startTimeLabel.setBounds(200, 230, 200, 30);
        frame.add(startTimeLabel);

        JTextField startTimeF = new JTextField();
        startTimeF.setBounds(320, 230, 200, 30);
        frame.add(startTimeF);

        //remark
        JLabel remarkLabel = new JLabel("Remark:");
        remarkLabel.setBounds(200, 280, 200, 30);
        frame.add(remarkLabel);

        JTextArea remarkArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(remarkArea);
        scrollPane.setBounds(320, 280, 250, 80);
        frame.add(scrollPane);
        
        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10, 10, 80, 30);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new StaffAppointment(currentUserId);
            frame.dispose();
        });

        //create button
        JButton createBtn = new JButton("CREATE APPOINTMENT");
        createBtn.setBounds(300, 400, 200, 40);
        frame.add(createBtn);

        createBtn.addActionListener(e -> {

            String username = usernameF.getText().trim();

            if (vehicleBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(frame, "Please select vehicle!");
                return;
            }

            String vehicleID = vehicleBox.getSelectedItem().toString();
            String startTime = startTimeF.getText().trim();

            if (!startTime.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
                JOptionPane.showMessageDialog(frame, "Invalid time format!");
                return;
            }

            String serviceName = (String) serviceBox.getSelectedItem();

            if (username.isEmpty() || serviceName == null || startTime.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!");
                return;
            }

            String userID = "";
            String custID = "";

            try {
                BufferedReader userBR = new BufferedReader(new FileReader("src/users.txt"));

                String line;
                while ((line = userBR.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[1].equals(username)) {
                        userID = data[0];
                        break;
                    }
                }
                userBR.close();

                BufferedReader custBR = new BufferedReader(new FileReader("src/customers.txt"));

                while ((line = custBR.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[1].equals(userID)) {
                        custID = data[0];
                        break;
                    }
                }
                custBR.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (custID.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Invalid customer!");
                return;
            }

            String[] serviceData = serviceMap.get(serviceName);
            String serviceIDValue = serviceData[0];
            String serviceTypeID = serviceData[1];

            double durationHours = Double.parseDouble(serviceTypeMap.get(serviceTypeID));
            int durationMinutes = (int) (durationHours * 60);

            String endTime = calculateEndTime(startTime, durationMinutes);

            String appointmentID = generateAppointmentID();
            String staffID = currentUserId;
            String techID = "Unassigned";
            String status = "Pending";

            String createdAt = new java.text.SimpleDateFormat("yyyy-MM-dd")
                    .format(new java.util.Date());

            String lastUpdated = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new java.util.Date());

            String remark = remarkArea.getText();
            if (remark == null) remark = "";
            remark = remark.trim();

            //save to appointments.txt
            try {
                FileWriter fw = new FileWriter("src/appointments.txt", true);

                fw.write(
                    appointmentID + "," +
                    vehicleID + "," +
                    custID + "," +
                    staffID + "," +
                    techID + "," +
                    serviceIDValue + "," +
                    createdAt + "," +
                    startTime + "," +
                    endTime + "," +
                    status + "," +
                    remark + "," +
                    lastUpdated
                );

                fw.write("\n");
                fw.close();
                
                //save to payments.txt
                String paymentID = generatePaymentID();

                String amount = "100"; 

                String paymentMethod = "-";

                String paymentStatus = "Unpaid";

                FileWriter paymentFW =
                        new FileWriter("src/payments.txt", true);

                paymentFW.write(
                    paymentID + "," +
                    appointmentID + "," +
                    amount + "," +
                    paymentMethod + "," +
                    createdAt + "," +
                    staffID + "," +
                    paymentStatus + "\n"
                );

                paymentFW.close();

                JOptionPane.showMessageDialog(frame, "Appointment Created!");

                usernameF.setText("");
                startTimeF.setText("");
                remarkArea.setText("");
                vehicleBox.removeAllItems();
                serviceBox.setSelectedIndex(0);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }
    
    //generate paymentID
    private String generatePaymentID() {

        int max = 0;

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/payments.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length > 0) {

                    String id = data[0];

                    if (id.startsWith("PAY")) {
                        int num = Integer.parseInt(id.substring(3));
                        if (num > max) max = num;
                    }
                }
            }

        } catch (Exception e) {}

        return String.format("PAY%04d", max + 1);
    }

    //load service_item.txt
    private void loadServices() {

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/service_item.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String serviceID = data[0];
                String serviceTypeID = data[1];
                String serviceName = data[2];

                serviceBox.addItem(serviceName);
                serviceMap.put(serviceName, new String[]{serviceID, serviceTypeID});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading service_item.txt");
        }
    }

    //load service_type.txt
    private void loadServiceTypes() {

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/service_type.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String typeID = data[0];
                String duration = data[2]; // hours

                serviceTypeMap.put(typeID, duration);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading service_type.txt");
        }
    }

    //calculate end time
    private String calculateEndTime(String startTime, int durationMinutes) {

        String[] parts = startTime.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        minute += durationMinutes;

        hour += minute / 60;
        minute = minute % 60;

        hour = hour % 24;

        return String.format("%02d:%02d", hour, minute);
    }

    //generate appointment ID
    private String generateAppointmentID() {

        int max = 0;

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/appointments.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length > 0) {

                    String id = data[0];

                    if (id.startsWith("APT")) {
                        int num = Integer.parseInt(id.substring(3));
                        if (num > max) max = num;
                    }
                }
            }

        } catch (Exception e) {}

        return String.format("APT%04d", max + 1);
    }
}