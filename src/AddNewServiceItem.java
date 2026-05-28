import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;


public class AddNewServiceItem extends javax.swing.JFrame {    

    private final String currentUserId;
    
    public AddNewServiceItem(String currentUserId) {
        initComponents();
        this.currentUserId = currentUserId;
        loadCategoryComboBox();
    }
    
    
    private void loadCategoryComboBox() {
        cateComboBox.removeAllItems();

        try {
            List<String[]> serviceTypeList = FileManager.getAllServiceTypes();

            for (String[] serviceType : serviceTypeList) {
                String displayText = serviceType[0] + " - " + serviceType[1];
                cateComboBox.addItem(displayText);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading categories.");
        }
    }
    
    private boolean hasInput() {
        return cateComboBox.getSelectedItem() != null
                || !itemField.getText().trim().isEmpty()
                || !descriptionTextArea.getText().trim().isEmpty()
                || !priceField.getText().trim().isEmpty();
    }
    
    private String validateServiceItemInput(
            String cateId,
            String serviceName,
            String description,
            String price
    ) throws IOException {

        if (cateId == null || cateId.isEmpty()) {
            return "Please select a category.";
        }

        if (serviceName.isEmpty() ||
            description.isEmpty() ||
            price.isEmpty()) {

            return "Please fill in all fields.";
        }

        if (!FileManager.isValidPrice(price)) {
            return "Price must be a valid number greater than 0.";
        }

        if (FileManager.serviceItemExists(cateId, serviceName)) {
            return "This service item already exists under the selected category.";
        }

        return "VALID";
    }
    
    private void recordAddItemActivity(String serviceName) {
        try {
            FileManager.addActivityLog(
                    currentUserId,
                    "Add New Service Item",
                    serviceName + " Added"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to record activity log.");
        }
    }
    
    private void addServiceItem() {

        Object selectedObj = cateComboBox.getSelectedItem();

        if (selectedObj == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a category.");
            return;
        }

        String selectedCategory = selectedObj.toString();
        String cateId = selectedCategory.split(" - ")[0];

        String serviceName = itemField.getText().trim();
        String description = descriptionTextArea.getText().trim();
        String price = priceField.getText().trim();

        try {

            String validationResult =
                    validateServiceItemInput(
                            cateId,
                            serviceName,
                            description,
                            price
                    );

            if (!validationResult.equals("VALID")) {

                JOptionPane.showMessageDialog(
                        this,
                        validationResult
                );

                return;
            }

            boolean added =
                    FileManager.addServiceItem(
                            cateId,
                            serviceName,
                            description,
                            price,
                            currentUserId
                    );

            if (added) {

                recordAddItemActivity(serviceName);

                JOptionPane.showMessageDialog(
                        this,
                        "Service item added successfully."
                );

                new ServiceItem(currentUserId).setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add service item."
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error adding service item."
            );
        }
    }
    
    private void back() {

        if (!hasInput()) {
            new ServiceItem(currentUserId).setVisible(true);
            dispose();
            return;
        }

        Object[] options = {"No", "Yes"};

        int confirm = JOptionPane.showOptionDialog(
                this,
                "You have unsaved input. Are you sure you want to go back?",
                "Unsaved Input",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]
        );

        if (confirm == 1) {
            new ServiceItem(currentUserId).setVisible(true);
            dispose();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemField = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cateComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        addBtn.setText("Add");
        addBtn.addActionListener(this::addBtnActionPerformed);

        jLabel2.setText("Category:");

        jLabel1.setText("New Service Item");

        jLabel3.setText("Item Name:");

        cateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Description: ");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        jLabel5.setText("Price (RM):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(addBtn)
                            .addGap(60, 60, 60)
                            .addComponent(backBtn)
                            .addGap(71, 71, 71))
                        .addComponent(jLabel3)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(34, 34, 34)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(cateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(itemField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(34, 34, 34)
                            .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel1)))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(itemField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(addBtn))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        back();
    }//GEN-LAST:event_backBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addServiceItem();
    }//GEN-LAST:event_addBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JComboBox<String> cateComboBox;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JTextField itemField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField priceField;
    // End of variables declaration//GEN-END:variables
}
