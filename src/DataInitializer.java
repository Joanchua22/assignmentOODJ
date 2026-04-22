import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class DataInitializer {

    public static void initialize() {
        createFileIfNotExist("src/users.txt",
                "USR0001,admin,admin123,Manager,Chow,TP076245,0162181000,chow@gmail.com,Active",
                "USR0002,customer1,cus123,Customer,Yan,TP076666,0123456789,cus@gmail.com,Active"
        );

        createFileIfNotExist("src/customers.txt",
                "CUS0001,USR0002"
        );

        createFileIfNotExist("src/vehicles.txt",
                "VEH0001,CUS0001,VFA929,SUV,Proton X70,2022"
        );

        createFileIfNotExist("src/service_type.txt",
                "SER001,Normal Service,1,80.00,USR0001",
                "SER002,Major Service,3,200.00,USR0001"
        );

        createFileIfNotExist("src/appointments.txt");
        createFileIfNotExist("src/payments.txt");
        createFileIfNotExist("src/receipts.txt");
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