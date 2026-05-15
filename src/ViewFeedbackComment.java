import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ViewFeedbackComment extends javax.swing.JFrame {
    
    private final String currentUserId;

    public ViewFeedbackComment(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        loadFeedbackTable();
        loadCommentTable();
    }
    
    private void loadFeedbackTable() {
        DefaultTableModel model = (DefaultTableModel) feedbackTable.getModel();
        model.setRowCount(0);

        try {
            List<String[]> feedbackList = FileManager.getAllTechnicianFeedbacks();

            for (String[] row : feedbackList) {
                model.addRow(new Object[] {
                    row[0], 
                    row[1],
                    row[2], 
                    row[3], 
                    row[4]  
                });
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading technician feedbacks.");
        }
    }
    
    private void loadCommentTable() {
        DefaultTableModel model = (DefaultTableModel) commentTable.getModel();
        model.setRowCount(0);

        try {
            List<String[]> commentList = FileManager.getAllCustomerComments();

            for (String[] row : commentList) {
                model.addRow(new Object[] {
                    row[0],
                    row[1],
                    row[2],
                    row[3], 
                    row[4]  
                });
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading customer comments.");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        feedbackTable = new javax.swing.JTable();
        feedbackBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentTable = new javax.swing.JTable();
        commentBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("View Feedbacks & Comments");

        jLabel2.setText("Latest Technician Feedbacks:");

        feedbackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Feedback ID", "Appointment ID", "Content", "Date", "Technician"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(feedbackTable);

        feedbackBtn.setText("View More");
        feedbackBtn.addActionListener(this::feedbackBtnActionPerformed);

        commentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Comment ID", "Appointment ID", "Content", "Date", "Customer"
            }
        ));
        jScrollPane2.setViewportView(commentTable);

        commentBtn.setText("View More");
        commentBtn.addActionListener(this::commentBtnActionPerformed);

        jLabel3.setText("Latest Customer Comments:");

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(feedbackBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(commentBtn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(backBtn)
                                .addGap(284, 284, 284)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(feedbackBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addComponent(commentBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backBtn)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void feedbackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedbackBtnActionPerformed
        new ViewTechnicianFeedback(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_feedbackBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new Manager(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void commentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentBtnActionPerformed
        new ViewCustomerComment(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_commentBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton commentBtn;
    private javax.swing.JTable commentTable;
    private javax.swing.JButton feedbackBtn;
    private javax.swing.JTable feedbackTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
