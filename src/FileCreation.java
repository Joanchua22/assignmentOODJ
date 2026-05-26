import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileCreation {

    public static void creation() {
        createFileIfNotExist("src/users.txt");
        createFileIfNotExist("src/customers.txt");
        createFileIfNotExist("src/vehicles.txt");
        createFileIfNotExist("src/service_type.txt");
        createFileIfNotExist("src/service_item.txt");
        createFileIfNotExist("src/appointments.txt");
        createFileIfNotExist("src/activity_logs.txt");
        createFileIfNotExist("src/payments.txt");
        createFileIfNotExist("src/technician_feedback.txt");
        createFileIfNotExist("src/customer_comments.txt");
    }
    

    private static void createFileIfNotExist(String filePath, String... defaultLines) {
        File file = new File(filePath);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (String line : defaultLines) {
                    writer.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error creating " + filePath + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}