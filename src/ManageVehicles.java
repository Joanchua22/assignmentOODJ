import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageVehicles extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageVehicles.class.getName());
    private String currentUserId;
    private List<String[]> vehicleDetails = new ArrayList<>();
    
    public ManageVehicles(String currentUserId) {
        this.currentUserId = currentUserId;
        initComponents();
        loadVehicleTable();
        addTableDoubleClickEvent();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vehicleTable = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnAddVehicle = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("My Vehicles");

        vehicleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Plate No.", "Vehicle Type", "Vehicle Model", "Year of Manufacture"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(vehicleTable);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnAddVehicle.setText("Add Vehicle");
        btnAddVehicle.addActionListener(this::btnAddVehicleActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("*Double Click to Manage Selected Vehicle");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnAddVehicle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnAddVehicle))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new UpdateCustomerProfile(currentUserId).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVehicleActionPerformed
        showAddVehiclePopup();
    }//GEN-LAST:event_btnAddVehicleActionPerformed

    private void loadVehicleTable() {
            try {
                vehicleDetails = FileManager.getCustomerVehicles(currentUserId);

                DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
                model.setRowCount(0);

                for (String[] vehicle : vehicleDetails) {
                    model.addRow(new Object[] {
                        vehicle[2],
                        vehicle[3],
                        vehicle[4],
                        vehicle[5]
                    });
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading vehicles.");
            }
        }

    private void addTableDoubleClickEvent() {
        vehicleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = vehicleTable.getSelectedRow();
                    if (row >= 0) {
                        showVehicleDetailsPopup(row);
                    }
                }
            }
        });
    }

    private void showVehicleDetailsPopup(int row) {
        if (row < 0 || row >= vehicleDetails.size()) {
            return;
        }

        String[] vehicle = vehicleDetails.get(row);

        String vehicleId = vehicle[0];
        String plateNo = vehicle[2];
        String type = vehicle[3];
        String model = vehicle[4];
        String year = vehicle[5];

        String message =
                "Vehicle ID: " + vehicleId + "\n" +
                "Plate No: " + plateNo + "\n" +
                "Type: " + type + "\n" +
                "Model: " + model + "\n" +
                "Year: " + year;

        Object[] options = { "Close", "Edit", "Delete" };

        int choice = JOptionPane.showOptionDialog(
                this,
                message,
                "Vehicle Details",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 1) {
            showEditVehiclePopup(vehicle);
        } else if (choice == 2) {
            deleteVehicleWithDoubleConfirm(vehicleId, plateNo);
        }
    }

    private void showAddVehiclePopup() {
        javax.swing.JTextField plateField = new javax.swing.JTextField();
        javax.swing.JComboBox<String> typeBox = new javax.swing.JComboBox<>(new String[] {
            "----", "Sedan", "Micro", "Hatchback", "Crossover",
            "Coupe", "Coupe SUV", "SUV", "Off-Roader", "Pick-Up",
            "MPV", "Van", "Sport", "Hyper", "Wagon"
        });
        javax.swing.JTextField modelField = new javax.swing.JTextField();
        javax.swing.JTextField yearField = new javax.swing.JTextField();

        Object[] message = {
            "Plate No:", plateField,
            "Vehicle Type:", typeBox,
            "Vehicle Model:", modelField,
            "Year:", yearField
        };

        Object[] options = { "Cancel", "Add" };

        int option = JOptionPane.showOptionDialog(
                this,
                message,
                "Add Vehicle",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0] // default = Cancel
        );

        if (option == 1) {
            String plateNo = plateField.getText().trim();
            String type = (String) typeBox.getSelectedItem();
            String model = modelField.getText().trim();
            String year = yearField.getText().trim();

            if (FileManager.isEmpty(plateNo) || FileManager.isEmpty(model) || FileManager.isEmpty(year)
                    || type == null || type.equals("----")) {
                JOptionPane.showMessageDialog(this, "Please fill in all vehicle fields.");
                return;
            }

            if (!FileManager.isFourDigitYear(year)) {
                JOptionPane.showMessageDialog(this, "Vehicle year must be exactly 4 digits.");
                return;
            }

            if (!FileManager.isNotFutureYear(year)) {
                JOptionPane.showMessageDialog(this, "Vehicle year cannot be in the future.");
                return;
            }

            try {
                boolean added = FileManager.addVehicle(currentUserId, plateNo, type, model, year);

                if (added) {
                    JOptionPane.showMessageDialog(this, "Vehicle added successfully.");
                    FileManager.addActivityLog(currentUserId, "Add Vehicle", "Added vehicle: " + plateNo);
                    loadVehicleTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Vehicle could not be added. Plate number may already exist.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding vehicle.");
            }
        }
    }
    
    private void showEditVehiclePopup(String[] vehicle) {
        String vehicleId = vehicle[0];
        String oldPlateNo = vehicle[2];
        String oldType = vehicle[3];
        String oldModel = vehicle[4];
        String oldYear = vehicle[5];

        javax.swing.JTextField plateField = new javax.swing.JTextField();
        javax.swing.JComboBox<String> typeBox = new javax.swing.JComboBox<>(new String[] {
            "-- Keep Current Type --",
            "Sedan", "Micro", "Hatchback", "Crossover",
            "Coupe", "Coupe SUV", "SUV", "Off-Roader", "Pick-Up",
            "MPV", "Van", "Sport", "Hyper", "Wagon"
        });
        javax.swing.JTextField modelField = new javax.swing.JTextField();
        javax.swing.JTextField yearField = new javax.swing.JTextField();

        Object[] message = {
            "Current Plate No: " + oldPlateNo,
            "New Plate No:", plateField,
            "Current Type: " + oldType,
            "New Vehicle Type:", typeBox,
            "Current Model: " + oldModel,
            "New Vehicle Model:", modelField,
            "Current Year: " + oldYear,
            "New Year:", yearField,
            "* Leave blank to keep current details"
        };

        Object[] options = { "Cancel", "Save" };

        int option = JOptionPane.showOptionDialog(
                this,
                message,
                "Edit Vehicle",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (option == 1) {
            String newPlateNo = plateField.getText().trim().isEmpty() ? oldPlateNo : plateField.getText().trim();

            String selectedType = (String) typeBox.getSelectedItem();
            String newType = (selectedType == null || selectedType.equals("-- Keep Current Type --")) ? oldType : selectedType;

            String newModel = modelField.getText().trim().isEmpty() ? oldModel : modelField.getText().trim();
            String newYear = yearField.getText().trim().isEmpty() ? oldYear : yearField.getText().trim();

            if (!FileManager.isFourDigitYear(newYear)) {
                JOptionPane.showMessageDialog(this, "Vehicle year must be exactly 4 digits.");
                return;
            }

            if (!FileManager.isNotFutureYear(newYear)) {
                JOptionPane.showMessageDialog(this, "Vehicle year cannot be in the future.");
                return;
            }

            try {
                boolean updated = FileManager.updateVehicle(vehicleId, oldPlateNo, newPlateNo, newType, newModel, newYear);

                if (updated) {
                    JOptionPane.showMessageDialog(this, "Vehicle updated successfully.");
                    FileManager.addActivityLog(currentUserId, "Update Vehicle", "Updated vehicle: " + oldPlateNo);
                    loadVehicleTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Vehicle could not be updated. Plate number may already exist.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating vehicle.");
            }
        }
    }
    
    private void deleteVehicleWithDoubleConfirm(String vehicleId, String plateNo) {
        Object[] options = { "Cancel", "Delete" };

        // First confirmation
        int confirm1 = JOptionPane.showOptionDialog(
                this,
                "Are you sure you want to delete this vehicle?",
                "Confirm Delete",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0] // default = Cancel
        );

        if (confirm1 != 1) {
            return;
        }

        // Second confirmation
        int confirm2 = JOptionPane.showOptionDialog(
                this,
                "This action cannot be undone.\nDelete permanently?",
                "Final Confirmation",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0] // default = Cancel
        );

        if (confirm2 != 1) {
            return;
        }

        try {
            boolean deleted = FileManager.deleteVehicle(vehicleId);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Vehicle deleted successfully.");
                FileManager.addActivityLog(currentUserId, "Delete Vehicle", "Deleted vehicle: " + plateNo);
                loadVehicleTable();
            } else {
                JOptionPane.showMessageDialog(this, "Vehicle not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting vehicle.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddVehicle;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable vehicleTable;
    // End of variables declaration//GEN-END:variables
}
