
package CounterStaff;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class SC_EditCustomer {
    
    DefaultTableModel model;
    JTable table;
    
    public SC_EditCustomer(){
        
        JFrame frame = new JFrame("APU ASC - Counter Staff -Edit Customer");
        frame.setSize(800, 500); //need to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JLabel l1 = new JLabel("Customer List", SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setBounds(0, 30, 800, 50);
        frame.add(l1);
        
        //customer list
        String[] columns = {"Customer ID", "User ID", "Name", "Phone"};
        
        model = new DefaultTableModel(columns, 0){
            public boolean isCellEditable(int row, int col){
                return col !=0; //customerID cannot edit
            }
        };
        
        table = new JTable(model);
        table.setRowHeight(25);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 80, 600, 250);
        frame.add(scroll);
        
        loadData();
        
        //search
        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(530, 30, 60, 25);
        frame.add(searchLabel);
        
        JTextField searchField = new JTextField();
        searchField.setBounds(530, 50, 120, 25);
        frame.add(searchField);
        
        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        
        searchField.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt){
                String text = searchField.getText();
                if(text.trim().length() == 0){
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
        
        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);
        backBtn.addActionListener(e ->{
            new SC_Main();
            frame.dispose();
        });
        
        //add button
        JButton addBtn = new JButton("ADD");
        addBtn.setBounds(140, 360, 100, 40);
        frame.add(addBtn);

        addBtn.addActionListener(e -> {

            String custID = generateCustomerID();
            String userID = JOptionPane.showInputDialog("Enter User ID:");
            String name = JOptionPane.showInputDialog("Enter Name:");
            String phone = JOptionPane.showInputDialog("Enter Phone:");

            if(userID == null || name == null || phone == null ||
               userID.trim().isEmpty() || name.trim().isEmpty() || phone.trim().isEmpty()){
                JOptionPane.showMessageDialog(frame, "All fields required!");
                return;
        }
                
            //duplicate check
            if (isDuplicateUserID(userID)) {
                JOptionPane.showMessageDialog(frame, "User ID already exists!");
                return;
            }

            if (isDuplicatePhone(phone)) {
                JOptionPane.showMessageDialog(frame, "Phone number already exists!");
                return;
            }

            model.addRow(new Object[]{custID, userID, name, phone});                
            });
        
        //save button
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBounds(290, 360, 100, 40);
        frame.add(saveBtn);

        saveBtn.addActionListener(e -> {

            if (!validateDuplicates()) {
                return; //stop saving if duplicate found
            }

            saveData();
        });
        
        //delete button
        JButton deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds(440, 360, 100, 40);
        frame.add(deleteBtn);

        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(frame, "Select a row!");
                return;
            }

            int modelRow = table.convertRowIndexToModel(row);

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Delete this customer?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm == JOptionPane.YES_OPTION){
                model.removeRow(modelRow);
            }
        });
        
        frame.setVisible(true);        
    }
    
    //load data
    private void loadData(){
        try{
            BufferedReader br = new BufferedReader(
                    new FileReader("src/CounterStaff/customer.txt"));

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length < 4) continue;

                model.addRow(new Object[]{
                        data[0],
                        data[1],
                        data[2],
                        data[3]
                });
            }

            br.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error loading data");
        }
    }
    
    //save data
    private void saveData(){
        try{
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/CounterStaff/customer.txt"));

            for(int i = 0; i < model.getRowCount(); i++){

                bw.write(
                        model.getValueAt(i,0) + "," +
                        model.getValueAt(i,1) + "," +
                        model.getValueAt(i,2) + "," +
                        model.getValueAt(i,3)
                );

                bw.newLine();
            }

            bw.close();

            JOptionPane.showMessageDialog(null, "Saved!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //auto generate customerID
    private String generateCustomerID(){

        int max = 0;

        try{
            BufferedReader br = new BufferedReader(
                    new FileReader("src/CounterStaff/customer.txt"));

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                int num = Integer.parseInt(data[0].replace("CUS",""));

                if(num > max) max = num;
            }

            br.close();

        }catch(Exception e){}

        return String.format("CUS%04d", max + 1);
    }
    
    //duplicate check (add)
    private boolean isDuplicateUserID(String userID){
        for(int i = 0; i < model.getRowCount(); i++){
            if(model.getValueAt(i,1).toString().equalsIgnoreCase(userID)){
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicatePhone(String phone){
        for(int i = 0; i < model.getRowCount(); i++){
            if(model.getValueAt(i,3).toString().equals(phone)){
                return true;
            }
        }
        return false;
    }
    
    //duplicate check (update)
    private boolean validateDuplicates(){

        for(int i = 0; i < model.getRowCount(); i++){

            String userID1 = model.getValueAt(i,1).toString();
            String phone1 = model.getValueAt(i,3).toString();

            for(int j = i + 1; j < model.getRowCount(); j++){

                String userID2 = model.getValueAt(j,1).toString();
                String phone2 = model.getValueAt(j,3).toString();

                if(userID1.equalsIgnoreCase(userID2)){
                    JOptionPane.showMessageDialog(null,
                            "Duplicate User ID found: " + userID1);
                    return false;
                }

                if(phone1.equals(phone2)){
                    JOptionPane.showMessageDialog(null,
                            "Duplicate Phone found: " + phone1);
                    return false;
                }
            }
        }

        return true;
    }
}
