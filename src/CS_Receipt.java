package CounterStaff;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class CS_Receipt implements Printable {

    private String content;

    public CS_Receipt(String paymentID, String appointmentID, String custID,
                      String amount, String method, String date, String status){

        JFrame frame = new JFrame("APU ASC - Counter Staff - Receipt");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        //receipt content
        StringBuilder sb = new StringBuilder();

        sb.append("      APU ASC SERVICE\n");
        sb.append("     Automotive Centre\n");
        sb.append("--------------------------------\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("--------------------------------\n");

        sb.append(String.format("%-15s : %s\n", "Payment ID", paymentID));
        sb.append(String.format("%-15s : %s\n", "Appointment ID", appointmentID));
        sb.append(String.format("%-15s : %s\n", "Customer ID", custID));
        sb.append(String.format("%-15s : %s\n", "Method", method));
        sb.append("--------------------------------\n");

        sb.append("SERVICE DETAILS\n");
        sb.append("--------------------------------\n");

        sb.append(String.format("%-15s RM %s\n", "Service", amount));

        sb.append("--------------------------------\n");
        sb.append(String.format("%-15s RM %s\n", "TOTAL", amount));
        sb.append("--------------------------------\n");

        sb.append(String.format("%-15s : %s\n", "Status", status));

        sb.append("\n");
        sb.append("   Thank you for visiting!\n");
        sb.append("       See you again!\n");
        sb.append("--------------------------------\n");

        this.content = sb.toString();
        
        //text area
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);
        
        area.setText(this.content);
        
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(100, 80, 600, 300);
        frame.add(scroll);

        //back button
        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10,20,80,40);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new CS_Payment();
            frame.dispose();
        });

        //print button
        JButton printBtn = new JButton("PRINT");
        printBtn.setBounds(320, 400, 120, 40);
        frame.add(printBtn);

        printBtn.addActionListener(e -> {
            try {
                JTextArea printArea = new JTextArea(content);

                printArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
                printArea.setColumns(32);
                printArea.setLineWrap(true);

                printArea.print();

            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    //print function
    @Override
    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException {

        if (page > 0) return NO_SUCH_PAGE;

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());

        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        String[] lines = content.split("\n");
        
        int y = 50;
        int lineHeight = 15;
        
        for (String line : lines){
            g.drawString(line, 50, y);
            y += lineHeight;
        }

        return PAGE_EXISTS;
    }
}