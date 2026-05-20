import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class StaffEditCustomer {

    DefaultTableModel model;
    JTable table;
    String currentUserId;

    JTextField searchField;
    TableRowSorter<DefaultTableModel> sorter;

    public StaffEditCustomer(String currentUserId) {
        this.currentUserId = currentUserId;

        JFrame frame = new JFrame("APU ASC - Counter Staff - Edit Customer");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //title
        JLabel title = new JLabel("CUSTOMER MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(0, 20, 800, 40);
        frame.add(title);

        //search bar
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(650, 60, 60, 25);
        frame.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(710, 60, 200, 25);
        frame.add(searchField);

        //table
        String[] columns = {
                "User ID", "Username", "TP Number",
                "Phone", "Email", "Status"
        };

        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);

        //sorter - search
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText();

                if (text.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        //edit (double click)
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {

                    int row = table.getSelectedRow();
                    if (row != -1) {

                        int modelRow = table.convertRowIndexToModel(row);
                        openEditPopup(modelRow);
                    }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 100, 900, 350);
        frame.add(scroll);

        loadData();

        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(20, 60, 80, 30);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new StaffMain(currentUserId);
            frame.dispose();
        });

        //add button
        JButton addBtn = new JButton("ADD");
        addBtn.setBounds(200, 470, 120, 40);
        frame.add(addBtn);
        addBtn.addActionListener(e -> addCustomer());

        //save button
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBounds(400, 470, 120, 40);
        frame.add(saveBtn);
        saveBtn.addActionListener(e -> saveData());

        //delete button
        JButton deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds(600, 470, 120, 40);
        frame.add(deleteBtn);
        deleteBtn.addActionListener(e -> deleteRow());

        frame.setVisible(true);
    }

    //load data
    private void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 9) continue;

                //role
                if (!data[3].equalsIgnoreCase("Customer")) continue;

                model.addRow(new Object[]{
                        data[0], //userID
                        data[1], //username
                        data[5], //TPnumber
                        data[6], //phone
                        data[7], //email
                        data[8] //status
                });
            }

            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading file users.txt");
        }
    }

    //edit (popup)
    private void openEditPopup(int row) {

        String phone = model.getValueAt(row, 3).toString();
        String email = model.getValueAt(row, 4).toString();
        String status = model.getValueAt(row, 5).toString();

        JFrame pop = new JFrame("Edit Customer");
        pop.setSize(400, 300);
        pop.setLayout(null);

        JLabel l1 = new JLabel("Phone:");
        l1.setBounds(30, 30, 100, 25);
        pop.add(l1);

        JTextField phoneF = new JTextField(phone);
        phoneF.setBounds(130, 30, 200, 25);
        pop.add(phoneF);

        JLabel l2 = new JLabel("Email:");
        l2.setBounds(30, 70, 100, 25);
        pop.add(l2);

        JTextField emailF = new JTextField(email);
        emailF.setBounds(130, 70, 200, 25);
        pop.add(emailF);

        JLabel l3 = new JLabel("Status:");
        l3.setBounds(30, 110, 100, 25);
        pop.add(l3);

        JComboBox<String> statusBox =
                new JComboBox<>(new String[]{"Active", "Inactive"});
        statusBox.setSelectedItem(status);
        statusBox.setBounds(130, 110, 200, 25);
        pop.add(statusBox);

        JButton save = new JButton("SAVE");
        save.setBounds(130, 170, 100, 30);
        pop.add(save);

        save.addActionListener(e -> {

            model.setValueAt(phoneF.getText(), row, 3);
            model.setValueAt(emailF.getText(), row, 4);
            model.setValueAt(statusBox.getSelectedItem(), row, 5);

            pop.dispose();
        });

        pop.setVisible(true);
    }

    //add customer
    private void addCustomer() {

        String userID = generateUserID();
        
        String username = JOptionPane.showInputDialog("Username:");
        String password = JOptionPane.showInputDialog("Password:");
        String name = JOptionPane.showInputDialog("Name:");
        String tp = JOptionPane.showInputDialog("TP Number:");
        String phone = JOptionPane.showInputDialog("Phone:");
        String email = JOptionPane.showInputDialog("Email:");

        if (username == null || password == null || name == null || 
                tp == null || phone == null || email == null) return;

        String role = "Customer";
    String status = "Active";
    String createdTime = java.time.LocalDateTime.now().toString();

    // 1. add to table
    model.addRow(new Object[]{
            userID,
            username,
            tp,
            phone,
            email,
            status
    });

    //append to file
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/users.txt", true))) {

        bw.write(userID + "," +
                 username + "," +
                 password + "," +
                 role + "," +
                 name + "," +
                 tp + "," +
                 phone + "," +
                 email + "," +
                 status + "," +
                 createdTime);

        bw.newLine();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    //save data
    private void saveData() {
    try {
        ArrayList<String> allLines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("src/users.txt"));
        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (data.length < 10) {
                allLines.add(line);
                continue;
            }

            // ONLY update customer rows
            if (data[3].equalsIgnoreCase("Customer")) {

                for (int i = 0; i < model.getRowCount(); i++) {

                    String tableID = model.getValueAt(i, 0).toString();

                    if (tableID.equals(data[0])) {

                        data[5] = model.getValueAt(i, 2).toString(); // TP
                        data[6] = model.getValueAt(i, 3).toString(); // phone
                        data[7] = model.getValueAt(i, 4).toString(); // email
                        data[8] = model.getValueAt(i, 5).toString(); // status
                    }
                }
            }

            allLines.add(String.join(",", data));
        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter("src/users.txt"));

        for (String l : allLines) {
            bw.write(l);
            bw.newLine();
        }

        bw.close();

        JOptionPane.showMessageDialog(null, "Saved successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    //delete data
    private void deleteRow() {

    int row = table.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(null, "Select a row!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete this customer?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    String deleteID = model.getValueAt(row, 0).toString();

    model.removeRow(row);

    try {
        ArrayList<String> allLines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("src/users.txt"));
        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (data.length < 10) {
                allLines.add(line);
                continue;
            }

            // skip deleted customer
            if (data[0].equals(deleteID)) {
                continue;
            }

            allLines.add(line);
        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter("src/users.txt"));

        for (String l : allLines) {
            bw.write(l);
            bw.newLine();
        }

        bw.close();

        JOptionPane.showMessageDialog(null, "Deleted successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    //userID generator
    private String generateUserID() {

        int max = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data[0].startsWith("USR")) {
                    int num = Integer.parseInt(data[0].replace("USR", ""));
                    if (num > max) max = num;
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("USR%04d", max + 1);
    }
}