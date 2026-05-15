
public class Main {
    public static void main(String[] args) {
        DataInitializer.initialize();

        javax.swing.SwingUtilities.invokeLater(() -> new Home().setVisible(true));
    }
}