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
        JLabel custLabel = new JLabel("Customer ID:");
        custLabel.setBounds(200, 130, 200, 30);
        frame.add(custLabel);

        JTextField custIDF = new JTextField();
        custIDF.setBounds(320, 130, 200, 30);
        frame.add(custIDF);

        JLabel vehicleIDLabel = new JLabel("Vehicle ID:");
        vehicleIDLabel.setBounds(200, 180, 200, 30);
        frame.add(vehicleIDLabel);

        JTextField vehicleIDF = new JTextField();
        vehicleIDF.setBounds(320, 180, 200, 30);
        frame.add(vehicleIDF);

        JLabel startTimeLabel = new JLabel("Start Time (HH:MM):");
        startTimeLabel.setBounds(200, 230, 200, 30);
        frame.add(startTimeLabel);

        JTextField startTimeF = new JTextField();
        startTimeF.setBounds(320, 230, 200, 30);
        frame.add(startTimeF);

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
        createBtn.setBounds(300, 330, 200, 40);
        frame.add(createBtn);

        createBtn.addActionListener(e -> {

            String custID = custIDF.getText().trim();
            String vehicleID = vehicleIDF.getText().trim();
            String startTime = startTimeF.getText().trim();

            String serviceName = (String) serviceBox.getSelectedItem();

            if (custID.isEmpty() || vehicleID.isEmpty() || serviceName == null || startTime.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!");
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
            String techID = "NULL";
            String status = "Assigned";

            String createdAt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new java.util.Date());

            try {
                File file = new File("src/appointments.txt");
                file.createNewFile();

                FileWriter fw = new FileWriter(file, true);

                fw.write(
                        appointmentID + "," +
                        vehicleID + "," +
                        custID + "," +
                        staffID + "," +
                        techID + "," +
                        serviceIDValue + "," +
                        createdAt.split(" ")[0] + "," +
                        startTime + "," +
                        endTime + "," +
                        status + "," +
                        "OK," +
                        createdAt + "\n"
                );

                fw.close();

                JOptionPane.showMessageDialog(frame, "Appointment Created!");

                custIDF.setText("");
                vehicleIDF.setText("");
                startTimeF.setText("");
                serviceBox.setSelectedIndex(0);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
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