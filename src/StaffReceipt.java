import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class StaffReceipt implements Printable {

    private String content;
    private String currentUserId;
    private String staffID;

    public StaffReceipt(String paymentID,
                        String appointmentID,
                        String amount,
                        String method,
                        String date,
                        String status,
                        String staffID,
                        String currentUserId) {

        this.currentUserId = currentUserId;
        this.staffID = staffID;

        JFrame frame = new JFrame("APU ASC - Counter Staff - Receipt");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        StringBuilder sb = new StringBuilder();

        sb.append("      APU ASC SERVICE\n");
        sb.append("     Automotive Centre\n");
        sb.append("--------------------------------\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("--------------------------------\n");

        sb.append(String.format("%-15s : %s\n", "Payment ID", paymentID));
        sb.append(String.format("%-15s : %s\n", "Appointment ID", appointmentID));
        sb.append(String.format("%-15s : %s\n", "Staff ID", staffID));
        sb.append(String.format("%-15s : %s\n", "Method", method));
        sb.append("--------------------------------\n");

        sb.append("SERVICE DETAILS\n");
        sb.append("--------------------------------\n");

        sb.append(String.format("%-15s RM %s\n", "Service Amount", amount));

        sb.append("--------------------------------\n");
        sb.append(String.format("%-15s RM %s\n", "TOTAL", amount));
        sb.append("--------------------------------\n");

        sb.append(String.format("%-15s : %s\n", "Status", status));

        sb.append("\nTHANK YOU!\n");

        this.content = sb.toString();

        JTextArea area = new JTextArea(content);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(100, 80, 600, 300);
        frame.add(scroll);

        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(10, 20, 80, 40);
        frame.add(backBtn);

        backBtn.addActionListener(e -> {
            new StaffPayment(currentUserId);
            frame.dispose();
        });

        JButton printBtn = new JButton("PRINT");
        printBtn.setBounds(320, 400, 120, 40);
        frame.add(printBtn);

        printBtn.addActionListener(e -> {
            try {
                JTextArea printArea = new JTextArea(content);
                printArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
                printArea.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException {

        if (page > 0) return NO_SUCH_PAGE;

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());

        g.setFont(new Font("Monospaced", Font.PLAIN, 12));

        int y = 50;
        for (String line : content.split("\n")) {
            g.drawString(line, 50, y);
            y += 15;
        }

        return PAGE_EXISTS;
    }
}