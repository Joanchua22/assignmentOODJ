import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class StaffPayment {

    DefaultTableModel model;
    JTable table;

    private String currentUserId;

    public StaffPayment(String currentUserId){

        this.currentUserId = currentUserId;

        JFrame frame = new JFrame("APU ASC - Counter Staff - Payment List");
        frame.setSize(850, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("Payment List", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(0, 20, 850, 50);
        frame.add(title);

        String[] columns = {
                "Payment ID",
                "Appointment ID",
                "Amount",
                "Method",
                "Date",
                "Status",
                "Staff ID"
        };

        model = new DefaultTableModel(columns, 0){

            public boolean isCellEditable(int row, int col){
                return col == 3 || col == 5; // only status editable
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 80, 750, 250);
        frame.add(scroll);

        loadData();
        
        //payment method dropdown
        JComboBox<String> methodBox =
                new JComboBox<>(new String[]{
                        "Cash",
                        "Card",
                        "Tng",
                        "Online Transfer"
                });
        
        table.getColumnModel().getColumn(3)
                .setCellEditor(new DefaultCellEditor(methodBox));
        
        
        //status dropdown
        JComboBox<String> statusBox =
                new JComboBox<>(new String[]{"Unpaid", "Paid", "Cancelled"});

        table.getColumnModel().getColumn(5)
                .setCellEditor(new DefaultCellEditor(statusBox));

        // color
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){

            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int col){

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);

                if (!isSelected){
                    String status = table.getValueAt(row, 5).toString();

                    if (status.equalsIgnoreCase("Paid")){
                        c.setBackground(Color.GREEN);
                    } else if (status.equalsIgnoreCase("Unpaid")){
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(Color.LIGHT_GRAY);
                    }
                }

                return c;
            }
        });

        // SAVE
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBounds(250, 360, 100, 40);
        frame.add(saveBtn);

        saveBtn.addActionListener(e -> saveData());

        // RECEIPT
        JButton receiptBtn = new JButton("RECEIPT");
        receiptBtn.setBounds(400, 360, 120, 40);
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
            String status = table.getValueAt(row, 5).toString();
            String staffID = table.getValueAt(row, 6).toString();

            // only paid can print receipt
            if (!status.equalsIgnoreCase("Paid")) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Receipt can only be viewed for PAID payments!"
                );

                return;
            }

            new StaffReceipt(
                    paymentID,
                    appointmentID,
                    amount,
                    method,
                    date,
                    status,
                    staffID,
                    currentUserId
            );

            frame.dispose();
        });

        // BACK
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new StaffMain(currentUserId);
            frame.dispose();
        });

        frame.setVisible(true);
    }

    // LOAD
    private void loadData(){

        try{
            BufferedReader br = new BufferedReader(
                    new FileReader("src/payments.txt"));

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length < 7) continue;

                model.addRow(new Object[]{
                        data[0], // paymentID
                        data[1], // appointmentID
                        data[2], // amount
                        data[3], // method
                        data[4], // date
                        data[5], // status
                        data[6]  // staffID
                });
            }

            br.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error loading payments.txt");
        }
    }

    // SAVE
    private void saveData(){

        try{
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/payments.txt"));

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