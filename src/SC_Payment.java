package CounterStaff;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class SC_Payment {

    DefaultTableModel model;
    JTable table;

    public SC_Payment(){

        JFrame frame = new JFrame("APU ASC - Counter Staff - Payment List");
        frame.setSize(800, 500); //nee to change
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Payment List", SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setBounds(0, 30, 800, 50);
        frame.add(l1);

        String[] columns = {
                "Payment ID", "Appointment ID", "Amount",
                "Payment Method", "Date", "Customer ID", "Status"
        };

        model = new DefaultTableModel(columns, 0){

            public boolean isCellEditable(int row, int col){
                return col == 6; //status -- paid/unpaid
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 80, 700, 250);
        frame.add(scroll);

        loadData();

        JComboBox<String> paymentBox =
                new JComboBox<>(new String[]{"Unpaid", "Paid"});

        table.getColumnModel().getColumn(6)
                .setCellEditor(new DefaultCellEditor(paymentBox));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){

            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int col){

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);

                if (!isSelected){
                    String status = table.getValueAt(row, 6).toString();

                    if (status.equals("Paid")){
                        c.setBackground(Color.GREEN);
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                }

                return c;
            }
        });

        //save button
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBounds(250, 350, 100, 40);
        frame.add(saveBtn);

        saveBtn.addActionListener(e -> saveData());

        //receipt button
        JButton receiptBtn = new JButton("RECEIPT");
        receiptBtn.setBounds(400, 350, 120, 40);
        frame.add(receiptBtn);

        receiptBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1){
                JOptionPane.showMessageDialog(frame, "Select a row!");
                return;
            }

            String paymentID = table.getValueAt(row, 0).toString();
            String appointmentID = table.getValueAt(row, 1).toString();
            String amount = table.getValueAt(row, 2).toString();
            String method = table.getValueAt(row, 3).toString();
            String date = table.getValueAt(row, 4).toString();
            String custID = table.getValueAt(row, 5).toString();
            String status = table.getValueAt(row, 6).toString();

            new SC_Receipt(paymentID, appointmentID, custID, amount, method, date, status);
            frame.dispose();
        });

        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);

        backBtn.addActionListener(e ->{
            new SC_Main();
            frame.dispose();
        });

        frame.setVisible(true);
    }

    //load data
    private void loadData(){

        try{
            BufferedReader br = new BufferedReader(
                    new FileReader("src/CounterStaff/payment.txt")); //payment file name //change?

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length < 7) continue;

                model.addRow(new Object[]{
                        data[0], //paymentID
                        data[1], //appointmentID
                        data[2], //amount
                        data[3], //paymentMethod
                        data[4], //date
                        data[5], //customerID
                        data[6]  //status
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
                    new FileWriter("src/CounterStaff/payment.txt")); //payment file name //change?

            for(int i = 0; i < model.getRowCount(); i++){

                bw.write(
                        model.getValueAt(i,0) + "," +
                        model.getValueAt(i,1) + "," +
                        model.getValueAt(i,2) + "," +
                        model.getValueAt(i,3) + "," +
                        model.getValueAt(i,4) + "," +
                        model.getValueAt(i,5) + "," +
                        model.getValueAt(i,6)
                );

                bw.newLine();
            }

            bw.close();
            JOptionPane.showMessageDialog(null, "Saved!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}