import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class TechAppointment extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TechAppointment.class.getName());

    private String currentUserId;

public TechAppointment(String currentUserId) {
    this.currentUserId = currentUserId;
    initComponents();
    loadAppointments();
}

private void loadAppointments() {
    DefaultTableModel model = (DefaultTableModel) tblAppointments.getModel();
    model.setRowCount(0);
    try (BufferedReader br = new BufferedReader(new FileReader(FileManager.APPOINTMENT_FILE))) {
        String line;

        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");
            if (d.length <= 11) continue;
            if (!d[4].trim().equalsIgnoreCase(currentUserId.trim())) continue;
            model.addRow(new Object[]{
                d[0], d[1], d[2], d[5],
                d[6], d[7], d[8], d[9], d[10] , d[11]
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private String calculateEndTime(String startTime, int duration) {
    String[] time = startTime.split(":");
    int hour = Integer.parseInt(time[0]);
    int minute = Integer.parseInt(time[1]);
    hour = (hour + duration) % 24;
    return String.format("%02d:%02d", hour, minute);
}

private int getServiceDuration(String serviceId) {
    try (BufferedReader br = new BufferedReader(new FileReader(FileManager.SERVICE_TYPE_FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");
            if (d[0].trim().equalsIgnoreCase(serviceId.trim())) {
                return Integer.parseInt(d[2].trim());
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 1;
}

private void completeAppointment() {
    int row = tblAppointments.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Select a row first");
        return;
    }
    String appointmentId = tblAppointments.getValueAt(row, 0).toString();
    File input = new File(FileManager.APPOINTMENT_FILE);
    File temp = new File("temp.txt");
    try (BufferedReader br = new BufferedReader(new FileReader(input));
         PrintWriter pw = new PrintWriter(new FileWriter(temp))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");
            if(d[0].equals(appointmentId)) {
                String serviceId = d[5];  
                String startTime = d[7];  
                int duration = getServiceDuration(serviceId);
                String endTime = calculateEndTime(startTime, duration);
                d[8] = endTime; 
                d[9] = "Completed";                
                d[11] = java.time.LocalDate.now()
                        .toString()
                        .replace("T", " ");
                line = String.join(",", d);
            }
            pw.println(line);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    input.delete();
    temp.renameTo(input);
    loadAppointments();
}

private void searchAppointments() {
    String key = txtSearch.getText().trim().toLowerCase();
    DefaultTableModel model =
            (DefaultTableModel) tblAppointments.getModel();

    model.setRowCount(0);
    try (BufferedReader br = new BufferedReader(
        new FileReader(FileManager.APPOINTMENT_FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");

            if (!d[4].equals(currentUserId)) {
                continue;
            }
            boolean match = false;

            for (String field : d) {
                if (field.toLowerCase().contains(key)) {
                    match = true;
                    break;
                }
            }
            if (match) {
                model.addRow(new Object[]{
                    d[0],
                    d[1],
                    d[2],
                    d[5],
                    d[6],
                    d[7],
                    d[8],
                    d[9],
                    d[10]
                });
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void clearSearch() {
    txtSearch.setText("");
    loadAppointments();
}   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnComplete = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "appointment_id", "vehicle_id", "customer_id", "service_type_id", "date", "start", "end", "status", "remarks", "created at"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAppointments);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnComplete.setText("Complete");
        btnComplete.addActionListener(this::btnCompleteActionPerformed);

        txtSearch.addActionListener(this::txtSearchActionPerformed);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnSearch)
                        .addGap(59, 59, 59)
                        .addComponent(btnClear)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnComplete)
                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnComplete))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
    completeAppointment();
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
   searchAppointments();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
    clearSearch();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
    new Technician(currentUserId).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnComplete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
