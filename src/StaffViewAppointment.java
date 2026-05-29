import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class StaffViewAppointment {

    private JTable table;
    private DefaultTableModel model;
    private JFrame frame;
    private String currentUserId;

    private static final int STATUS_COL = 10;

    public StaffViewAppointment(String currentUserId) {

        this.currentUserId = currentUserId;

        frame = new JFrame("APU ASC - Counter Staff - View Appointment");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("Appointment List", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(40, 10, 800, 40);
        frame.add(title);

        //search
        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(200, 80, 60, 25);
        frame.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(250, 80, 550, 25);
        frame.add(searchField);

        //columns
        String[] columns = {
                "No.",
                "Appointment ID",
                "Vehicle ID",
                "Customer ID",
                "Staff ID",
                "Technician ID",
                "Service ID",
                "Date",
                "Start Time",
                "End Time",
                "Status",
                "Remark",
                "Last Updated"
        };

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == STATUS_COL || column == 11;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //column sizes
        int[] widths = {50,120,120,120,120,120,120,100,100,100,120,150,150};
        for (int i = 0; i < widths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        //status
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (isSelected) return c;

                c.setBackground(Color.WHITE);

                int modelRow = table.convertRowIndexToModel(row);

                Object val = table.getModel().getValueAt(modelRow, STATUS_COL);
                String status = (val == null) ? "" : val.toString();

                switch (status) {
                    case "Pending":
                        c.setBackground(Color.YELLOW);
                        break;
                    case "Assigned":
                        c.setBackground(Color.GREEN);
                        break;
                    case "Completed":
                        c.setBackground(new Color(173, 216, 230));
                        break;
                    case "Cancelled":
                        c.setBackground(Color.LIGHT_GRAY);
                        break;
                }

                return c;
            }
        };

        table.setDefaultRenderer(Object.class, renderer);

        //sorter + search
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String text = searchField.getText();
                if (text.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 120, 800, 320);
        frame.add(scrollPane);

        //load appointments.txt
        try (BufferedReader br = new BufferedReader(new FileReader("src/appointments.txt"))) {

            String line;
            int rowNumber = 1;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 12) continue;

                Object[] rowData = new Object[13];
                rowData[0] = rowNumber;
                rowData[1] = data[0];  //appointmentID
                rowData[2] = data[1];  //vehicleID
                rowData[3] = data[2];  //custID
                rowData[4] = data[3];  //staffID
                rowData[5] = data[4];  //techID
                rowData[6] = data[5];  //servID
                rowData[7] = data[6];  //date
                rowData[8] = data[7];  //startTime
                rowData[9] = data[8];  //endTime
                rowData[10] = data[9]; //status
                rowData[11] = data[10]; //remark
                rowData[12] = data[11]; //lastUpdated

                model.addRow(rowData);
                rowNumber++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "No appointment data found.");
        }

        setupStatusColumn(table);

        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10, 20, 80, 40);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new StaffAppointment(currentUserId);
            frame.dispose();
        });

        //save button
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBounds(330, 500, 100, 30);
        frame.add(saveBtn);

        saveBtn.addActionListener(e -> saveToFile(table));

        //delete button
        JButton deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds(480, 500, 100, 30);
        frame.add(deleteBtn);

        deleteBtn.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Delete this appointment?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                int modelRow = table.convertRowIndexToModel(selectedRow);
                
                //get appointment id before delete
                String appointmentID = model.getValueAt(modelRow, 1).toString();
                
                //remove frm table
                model.removeRow(modelRow);

                //renumber
                for (int i = 0; i < model.getRowCount(); i++) {
                    model.setValueAt(i + 1, i, 0);
                }

                saveToFile(table);
                
                deleteFromPayments(appointmentID);
                
                JOptionPane.showMessageDialog(frame, "Deleted successfully!");
            }
        });

        frame.setVisible(true);
    }
    
    //delete in payments.txt
    private void deleteFromPayments(String appointmentID) {

        File inputFile = new File("src/payments.txt");
        File tempFile = new File("src/payments_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 2) continue;

                String payAppID = data[1].trim();

                if (!payAppID.equals(appointmentID.trim())) {
                    bw.write(line);
                    bw.newLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (inputFile.exists()) inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    //status editor
    private void setupStatusColumn(JTable table) {
        String[] statusOptions = {"Pending", "Assigned", "Completed", "Cancelled"};
        JComboBox<String> comboBox = new JComboBox<>(statusOptions);
        table.getColumnModel().getColumn(STATUS_COL)
                .setCellEditor(new DefaultCellEditor(comboBox));
    }

    //save file
    private void saveToFile(JTable table) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("src/appointments.txt"))) {

            for (int i = 0; i < table.getRowCount(); i++) {

                StringBuilder row = new StringBuilder();

                for (int j = 1; j < table.getColumnCount(); j++) {

                    Object value = table.getValueAt(i, j);

                    //last Updated column
                    if (j == 12) {

                        Object existing = model.getValueAt(i, 12);

                        if (existing == null || existing.toString().isEmpty()) {
                            row.append(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .format(new java.util.Date()));
                        } else {
                            row.append(existing); //keep old timestamp
                        }

                    } else {
                        row.append(value);
                    }

                    if (j < table.getColumnCount() - 1) {
                        row.append(",");
                    }
                }

                writer.write(row.toString());
                writer.newLine();
            }

            JOptionPane.showMessageDialog(null, "Saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving file!");
        }
    }
}