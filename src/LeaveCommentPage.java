import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LeaveCommentPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LeaveCommentPage.class.getName());
    
    private String currentUsername;
    
    private List<String[]> appointmentDetails = new ArrayList<>();
    
    public LeaveCommentPage(String username) {
        this.currentUsername = username;
        initComponents();
        loadAppointmentTable();
        addTableDoubleClickEvent();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        appointmentTable = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Leave Comment");

        appointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Appointment Date", "Vehicle", "Service Name", "Status", "Comment Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(appointmentTable);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("*Double Click to Leave Comment");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(btnBack)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new CustomerPage(currentUsername).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed
  
    private void loadAppointmentTable() {
        try {
            appointmentDetails = FileManager.getCustomerAppointmentsForComment(currentUsername);

            DefaultTableModel model = (DefaultTableModel) appointmentTable.getModel();
            model.setRowCount(0);

            for (String[] record : appointmentDetails) {
                model.addRow(new Object[] {
                    record[1], // appointment date
                    record[2], // vehicle
                    record[3], // service name
                    record[4],  // status
                    record[5]   //comment status
                });
            }

            if (appointmentDetails.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No completed appointments found.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading appointments.");
        }
    }

    private void addTableDoubleClickEvent() {
        appointmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = appointmentTable.getSelectedRow();
                    if (row >= 0) {
                        showCommentPopup(row);
                    }
                }
            }
        });
    }

    private void showCommentPopup(int row) {
        if (row < 0 || row >= appointmentDetails.size()) {
            return;
        }

        String[] details = appointmentDetails.get(row);

        String appointmentId = details[0];
        String appointmentDate = details[1];
        String vehicle = details[2];
        String serviceName = details[3];
        String status = details[4];
        String commentStatus = details[5];

        try {
            // If already submitted, show existing comment
            if ("Submitted".equalsIgnoreCase(commentStatus)) {
                String[] existingComment = FileManager.getCustomerCommentByAppointmentId(appointmentId);

                String commentText = "N/A";
                String commentDate = "N/A";

                if (existingComment != null) {
                    commentText = existingComment[2];
                    commentDate = existingComment[3];
                }

                javax.swing.JTextArea commentArea = new javax.swing.JTextArea(6, 25);
                commentArea.setText(commentText);
                commentArea.setLineWrap(true);
                commentArea.setWrapStyleWord(true);
                commentArea.setEditable(false);
                commentArea.setFocusable(false);
                commentArea.setHighlighter(null);
                commentArea.setCaretPosition(0);
                commentArea.setSelectionColor(commentArea.getBackground());
                commentArea.setSelectedTextColor(commentArea.getForeground());
                commentArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                javax.swing.JScrollPane commentScroll = new javax.swing.JScrollPane(commentArea);

                javax.swing.JPanel panel = new javax.swing.JPanel();
                panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
                panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));

                java.awt.Dimension smallGap = new java.awt.Dimension(0, 6);
                java.awt.Dimension bigGap = new java.awt.Dimension(0, 12);

                panel.add(new javax.swing.JLabel("Appointment Date: " + appointmentDate));
                panel.add(javax.swing.Box.createRigidArea(smallGap));
                panel.add(new javax.swing.JLabel("Vehicle: " + vehicle));
                panel.add(javax.swing.Box.createRigidArea(smallGap));
                panel.add(new javax.swing.JLabel("Service Name: " + serviceName));
                panel.add(javax.swing.Box.createRigidArea(smallGap));
                panel.add(new javax.swing.JLabel("Status: " + status));
                panel.add(javax.swing.Box.createRigidArea(bigGap));
                panel.add(new javax.swing.JLabel("Your Submitted Comment:"));
                panel.add(javax.swing.Box.createRigidArea(smallGap));
                panel.add(commentScroll);
                panel.add(javax.swing.Box.createRigidArea(bigGap));
                panel.add(new javax.swing.JLabel("Comment Date: " + commentDate));

                Object[] options = { "Close" };

                JOptionPane.showOptionDialog(
                        this,
                        panel,
                        "View Comment",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                return;
            }

            // If not submitted yet, allow user to submit
            javax.swing.JTextArea commentArea = new javax.swing.JTextArea(6, 25);
            commentArea.setLineWrap(true);
            commentArea.setWrapStyleWord(true);

            javax.swing.JScrollPane commentScroll = new javax.swing.JScrollPane(commentArea);

            javax.swing.JPanel panel = new javax.swing.JPanel();
            panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
            panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));

            java.awt.Dimension smallGap = new java.awt.Dimension(0, 6);
            java.awt.Dimension bigGap = new java.awt.Dimension(0, 12);

            panel.add(new javax.swing.JLabel("Appointment Date: " + appointmentDate));
            panel.add(javax.swing.Box.createRigidArea(smallGap));
            panel.add(new javax.swing.JLabel("Vehicle: " + vehicle));
            panel.add(javax.swing.Box.createRigidArea(smallGap));
            panel.add(new javax.swing.JLabel("Service Name: " + serviceName));
            panel.add(javax.swing.Box.createRigidArea(smallGap));
            panel.add(new javax.swing.JLabel("Status: " + status));
            panel.add(javax.swing.Box.createRigidArea(bigGap));
            panel.add(new javax.swing.JLabel("Comment (for counter staff & technician):"));
            panel.add(javax.swing.Box.createRigidArea(smallGap));
            panel.add(commentScroll);

            Object[] options = { "Close", "Submit" };

            int option = JOptionPane.showOptionDialog(
                    this,
                    panel,
                    "Leave Comment",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (option == 1) {
                String commentText = commentArea.getText().trim();

                if (commentText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your comment before submitting.");
                    return;
                }

                boolean submitted = FileManager.addCustomerComment(appointmentId, commentText);

                if (submitted) {
                    JOptionPane.showMessageDialog(this, "Comment submitted successfully.");
                    loadAppointmentTable(); // refresh table so comment column becomes Submitted
                } else {
                    JOptionPane.showMessageDialog(this, "A comment for this appointment already exists.");
                    loadAppointmentTable();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading or submitting comment.");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable appointmentTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
