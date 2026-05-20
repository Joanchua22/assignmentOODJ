
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
    
    public StaffViewAppointment(String currentUserId){
        
        this.currentUserId = currentUserId;
    
        frame = new JFrame("APU ASC - Counter Staff - View Appointment");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        //title
        JLabel title = new JLabel("Appointment List", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(40, 10, 800, 40);
        frame.add(title);
        
        //search filter
        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(200, 80, 60, 25);
        frame.add(searchLabel);
        
        JTextField searchField = new JTextField();
        searchField.setBounds(250, 80, 550, 25);
        frame.add(searchField);
        
        
        
        //table
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
            public boolean isCellEditable(int row, int column) {
                return column == STATUS_COL; //only status can edit
            }
        };
        
        table = new JTable(model);
        table.setRowHeight(25);        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(50); //no.
        table.getColumnModel().getColumn(1).setPreferredWidth(120); //appointment id
        table.getColumnModel().getColumn(2).setPreferredWidth(120); //vehicle id
        table.getColumnModel().getColumn(3).setPreferredWidth(120); //cust id
        table.getColumnModel().getColumn(4).setPreferredWidth(120); //staff id
        table.getColumnModel().getColumn(5).setPreferredWidth(120); //tech id
        table.getColumnModel().getColumn(6).setPreferredWidth(120); //serv id
        table.getColumnModel().getColumn(7).setPreferredWidth(100); //date
        table.getColumnModel().getColumn(8).setPreferredWidth(100); //start time
        table.getColumnModel().getColumn(9).setPreferredWidth(100); //end time
        table.getColumnModel().getColumn(10).setPreferredWidth(120); //status
        table.getColumnModel().getColumn(11).setPreferredWidth(100); //remark
        table.getColumnModel().getColumn(12).setPreferredWidth(150); //last updated

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

        //sorter
        table.setDefaultRenderer(Object.class, renderer);
        
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
        
        //scroll function
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        scrollPane.setBounds(100, 120, 800, 320);
        frame.add(scrollPane);
        
        //no. column
        try{
            BufferedReader br = new BufferedReader(
                new FileReader("src/appointments.txt")); //app file name

            String line;
            int rowNumber = 1;

            while ((line = br.readLine()) != null){

                String[] data = line.split(",");

                Object[] rowData = new Object[13];

                rowData[0] = rowNumber; //no.

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
            new StaffAppointment(currentUserId);
            frame.dispose();
        });
        
        //save button
        JButton saveBtn =new JButton("SAVE");
        saveBtn.setBounds(330,500,100,30);
        frame.add(saveBtn);
        
        saveBtn.addActionListener(e ->{
            saveToFile(table);
        });
        
        frame.setVisible(true);
        
        //delete button
        JButton deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds(480, 500, 100, 30);
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
    }
    
    private void updateTechnicianAvailability(JTable table) {

        for (int i = 0; i < table.getRowCount(); i++) {

            String techID = table.getValueAt(i, 5).toString();
            String status = table.getValueAt(i, 10).toString();

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

    //save data
    private void saveToFile(JTable table){
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("src/appointments.txt")); //app file name

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
                
        table.getColumnModel().getColumn(10)
                .setCellEditor(new DefaultCellEditor(comboBox));
    }    
}