
public class Main {
    public static void main(String[] args) {
        FileCreation.creation();

        javax.swing.SwingUtilities.invokeLater(() -> new Home().setVisible(true));
    }
}