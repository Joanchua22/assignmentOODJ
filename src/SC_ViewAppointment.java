package CounterStaff;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;


public class SC_ViewAppointment {
    
    private JTable table;
    private DefaultTableModel model;
    private JFrame frame;
    private static final int STATUS_COL = 9;
    
    public SC_ViewAppointment(){
    
        frame = new JFrame("APU ASC - Counter Staff - View Appointment");
        frame.setSize(800, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        //title
        JLabel title = new JLabel("APPOINTMENT LIST", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(0, 20, 800, 50);
        frame.add(title);
        
        //table
        String[] columns = {"No.", "Appointment ID", "Customer ID", "Name", "Phone", "Vehicle ID", "Service", "Duration","Technician ID" ,"Status", "Created At"};
        
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 9; //only status can edit
            }
        };
        
        table = new JTable(model);
        table.setRowHeight(25);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(50); //no
        table.getColumnModel().getColumn(1).setPreferredWidth(120); //appointmenID
        table.getColumnModel().getColumn(2).setPreferredWidth(100); //custID
        table.getColumnModel().getColumn(3).setPreferredWidth(150); //custName
        table.getColumnModel().getColumn(4).setPreferredWidth(130); //phone
        table.getColumnModel().getColumn(5).setPreferredWidth(120); //vehicleID
        table.getColumnModel().getColumn(6).setPreferredWidth(150); //serviceType
        table.getColumnModel().getColumn(7).setPreferredWidth(100); //duration
        table.getColumnModel().getColumn(8).setPreferredWidth(120); //techID
        table.getColumnModel().getColumn(9).setPreferredWidth(120); //status
        table.getColumnModel().getColumn(10).setPreferredWidth(180); //createdAt

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        
        //status color
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(Color.WHITE);
                }

                int modelRow = table.convertRowIndexToModel(row);
                String status = table.getModel().getValueAt(modelRow, STATUS_COL).toString();

                if (!isSelected) {
                    if (status.equals("Assigned")) {
                        c.setBackground(Color.YELLOW);
                    } else if (status.equals("Completed")) {
                        c.setBackground(Color.GREEN);
                    } else if (status.equals("Cancelled")) {
                        c.setBackground(Color.LIGHT_GRAY);
                    }
                }

                return c;
            }
        };

        table.setDefaultRenderer(Object.class, renderer);
        
        //scroll function
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        scrollPane.setBounds(100, 80, 600, 250);
        frame.add(scrollPane);
        
        //no. column
        try{
            BufferedReader br = new BufferedReader(
                new FileReader("src/CounterStaff/appointment.txt")); //app file name //change

            String line;
            int rowNumber = 1;

            while ((line = br.readLine()) != null){

                String[] data = line.split(",");

                Object[] rowData = new Object[11];

                rowData[0] = rowNumber; //No.

                for (int i = 0; i < data.length && i + 1 < rowData.length; i++){
                    rowData[i + 1] = data[i];
                }

                model.addRow(rowData);
                rowNumber++;
            }
            
            br.close();
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame, "No appointment data found.");
        }
        
        
        //status dropdown function
        setupStatusColumn(table);
        
        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);
        backBtn.addActionListener(e ->{
            new SC_Appointment();
            frame.dispose();
        });
        
        //save button
        JButton saveBtn =new JButton("SAVE");
        saveBtn.setBounds(250,350,100,40);
        frame.add(saveBtn);
        
        saveBtn.addActionListener(e ->{
            saveToFile(table);
        });
        
        frame.setVisible(true);
        
        //delete button
        JButton deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds(450, 350, 100, 40);
        frame.add(deleteBtn);
        
        deleteBtn.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1){
                JOptionPane.showMessageDialog(frame, "Please select a row to delete!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete this appointment?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION){

                int modelRow = table.convertRowIndexToModel(selectedRow);

                model.removeRow(modelRow);

                for (int i = 0; i < model.getRowCount(); i++){
                    model.setValueAt(i + 1, i, 0);
                }

                saveToFile(table);

                JOptionPane.showMessageDialog(frame, "Deleted successfully!");
            }
        });
        
        //search filter
        JTextField searchField = new JTextField();
        searchField.setBounds(580, 40, 120, 25);
        frame.add(searchField);
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        
        searchField.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt){
            
                String text = searchField.getText();
            
                if (text.trim().length() == 0){
                    sorter.setRowFilter(null);
                } else{
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
        
    }
    
    private void updateTechnicianAvailability(JTable table) {

        for (int i = 0; i < table.getRowCount(); i++) {

            String techID = table.getValueAt(i, 8).toString();
            String status = table.getValueAt(i, 9).toString();

            if (techID == null || techID.isEmpty()) {
                continue;
            }

            if (status.equals("Assigned")) {
                System.out.println("Technician " + techID + " is now BUSY");
            } 

            else if (status.equals("Completed") || status.equals("Cancelled")) {
                System.out.println("Technician " + techID + " is now AVAILABLE");
            }
        }

        table.repaint();
    }

    private void saveToFile(JTable table){
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src/CounterStaff/appointment.txt")); //app file name //change

            for(int i = 0; i < table.getRowCount(); i++){

                StringBuilder row = new StringBuilder();

                // skip No. column
                for (int j = 1; j < table.getColumnCount(); j++){
                    row.append(table.getValueAt(i, j)).append(",");
                }

                writer.write(row.toString().replaceAll(",$", ""));
                writer.newLine();
            }

            writer.close();
            JOptionPane.showMessageDialog(null, "Saved successfully!");

        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving file!");
        }
    }
    
    //status
    private void setupStatusColumn(JTable table){
        String[] statusOptions = {"Assigned", "Completed", "Cancelled"};
                
        JComboBox<String> comboBox = new JComboBox<>(statusOptions);
                
        table.getColumnModel().getColumn(9)
                .setCellEditor(new DefaultCellEditor(comboBox));
    }    
}