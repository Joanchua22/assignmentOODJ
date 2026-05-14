import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public abstract class BaseReport extends javax.swing.JFrame {

    public abstract String generateReportText();

    public void showReportPreviewPopup(String title) {

        String reportText = generateReportText();

        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(900, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JTextArea previewArea = new JTextArea(reportText);

        previewArea.setEditable(false);
        previewArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(previewArea);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeBtn);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void exportReport(String defaultFileName) {

        String reportText = generateReportText();

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Export Report");
        fileChooser.setSelectedFile(new java.io.File(defaultFileName));

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            try {
                java.io.File file = fileChooser.getSelectedFile();

                java.nio.file.Files.write(
                        file.toPath(),
                        reportText.getBytes(),
                        java.nio.file.StandardOpenOption.CREATE,
                        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
                );

                JOptionPane.showMessageDialog(this,
                        "Report exported successfully.");

            } catch (IOException e) {

                JOptionPane.showMessageDialog(this,
                        "Error exporting report.");
            }
        }
    }
}