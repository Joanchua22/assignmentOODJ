import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    
    public static final String USER_FILE = "src/users.txt";
    public static final String CUSTOMER_FILE = "src/customers.txt";
    public static final String VEHICLE_FILE = "src/vehicles.txt";
    public static final String APPOINTMENT_FILE = "src/appointments.txt";
    public static final String SERVICE_TYPE_FILE = "src/service_type.txt";
    public static final String PAYMENT_FILE = "src/payments.txt";
    public static final String FEEDBACK_FILE = "src/technician_feedback.txt"; 
    public static final String CUSTOMER_COMMENT_FILE = "src/customer_comments.txt";
    public static final String SERVICE_ITEM_FILE = "src/service_item.txt";
    
    public static boolean isEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    
    public static boolean isValidTP(String tp){
        return tp != null && tp.matches("^TP\\d{6}$");
    }
    
    public static boolean isValidPhone(String phone){
        return phone != null && phone.matches("^\\d{10,11}$");
    }
    
    public static boolean isValidEmail(String email){
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }
    
    public static boolean usernameExists(String username) throws IOException {
        return valueExistsInColumn(USER_FILE, username, 1);
    }
    
    public static boolean tpExists(String tp) throws IOException{
        return valueExistsInColumn(USER_FILE, tp, 5);
    }
    
    public static boolean phoneExists(String phone) throws IOException{
        return valueExistsInColumn(USER_FILE, phone, 6);
    }
    
    public static boolean emailExists(String email) throws IOException{
        return valueExistsInColumn(USER_FILE, email, 7);
    }
    
    public static boolean vehicleRegExists(String vrn) throws IOException{
        return valueExistsInColumn(VEHICLE_FILE, vrn, 2);
    }
    
    public static boolean isFourDigitYear(String vehicleYear) {
        return vehicleYear != null && vehicleYear.matches("^\\d{4}$");
    }

    public static boolean isNotFutureYear(String vehicleYear) {
        if (!isFourDigitYear(vehicleYear)) {
            return false;
        }

        int currentYear = java.time.Year.now().getValue();
        return Integer.parseInt(vehicleYear) <= currentYear;
    }
    
    public static boolean isValidDate(String dateText) {
        try {
            LocalDate.parse(dateText);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isNotFutureDate(String dateText) {
        try {
            LocalDate inputDate = LocalDate.parse(dateText);
            LocalDate today = LocalDate.now();
            return !inputDate.isAfter(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean valueExistsInColumn(String filePath, String value, int columnIndex) throws IOException{
        File file = new File(filePath);
        
        if (!file.exists()){
            return false;
        }
        
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines){
            if (line.trim().isEmpty()){
                continue;
            }
            
            String[] parts = line.split(",");
            if (parts.length > columnIndex && parts[columnIndex].trim().equalsIgnoreCase(value.trim())){
                return true;
            }
        }
        return false;
    }
    

    public static String generateNextId(String filePath, String prefix) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return prefix + "0001";
        }

        int max = 0;
        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].startsWith(prefix)) {
                String numberPart = parts[0].substring(prefix.length());
                try {
                    int num = Integer.parseInt(numberPart);
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }

        return String.format("%s%04d", prefix, max + 1);
    }
    
    public static void appendLine(String filePath, String line) throws IOException {
        Files.write(
                new File(filePath).toPath(),
                (line + System.lineSeparator()).getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }
    
    public static void registerCustomer(String username, String password, String tp,
                                        String name, String phone, String email,
                                        String vrn, String vehicleType, String vehicleModel, String vehicleYear) {

        String userId = "";
        String customerId = "";
        String vehicleId = "";

        try {
            userId = generateNextId(USER_FILE, "USR");
            customerId = generateNextId(CUSTOMER_FILE, "CUS");
            vehicleId = generateNextId(VEHICLE_FILE, "VEH");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String userLine = String.join(",",
                userId, username, password, "Customer", name, tp, phone, email, "Active");

        String customerLine = String.join(",",
                customerId, userId);

        String vehicleLine = String.join(",",
                vehicleId, customerId, vrn, vehicleType, vehicleModel, vehicleYear);

        try {
            appendLine(USER_FILE, userLine);
            appendLine(CUSTOMER_FILE, customerLine);
            appendLine(VEHICLE_FILE, vehicleLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void addStaff(String username, String password, String role,
                            String fullName, String tp, String phone,
                            String email, String status) throws IOException {

        String userId = generateNextId(USER_FILE, "USR");
        
        String createdAt = getCurrentDateTime();
        
        String userLine = String.join(",",
                userId, username, password, role, fullName, tp, phone, email, status, createdAt);

        appendLine(USER_FILE, userLine);
    }
    
    public static String getCurrentDateTime() {
        return java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public static boolean checkCurrentPassword(String username, String currentPassword) throws IOException {
        File file = new File(USER_FILE);
        if (!file.exists()) return false;

        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 9) continue;

            if (parts[1].equals(username) && parts[2].equals(currentPassword)) {
                return true;
            }
        }
        return false;
    }

    public static boolean updatePassword(String username, String newPassword) throws IOException {
        File file = new File(USER_FILE);
        if (!file.exists()) return false;

        List<String> lines = Files.readAllLines(file.toPath());
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 9) {
                updatedLines.add(line);
                continue;
            }

            if (parts[1].equals(username)) {
                parts[2] = newPassword; 
                String updatedLine = String.join(",", parts);
                updatedLines.add(updatedLine);
                found = true;
            } else {
                updatedLines.add(line);
            }
        }

        if (found) {
            Files.write(file.toPath(), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        }

        return found;
    }
    
    public static List<String[]> getAllStaff() throws IOException{
        List<String[]> staffList = new ArrayList<>();
        File file = new File(USER_FILE);
        
        if (!file.exists()){
            return staffList;
        }
        
        for (String line: Files.readAllLines(file.toPath())){
            if(line.trim().isEmpty()){
                continue;
            }
            
            String[] parts = line.split(",");
            if (parts.length >= 9){
                String role = parts[3].trim();
                
                if(role.equalsIgnoreCase("Manager") || role.equalsIgnoreCase("Counter Staff") || role.equalsIgnoreCase("Technician")){
                    staffList.add(parts);
                }
            }
        }
        return staffList;
    }
    
    private static boolean isStaffRole(String role) {
        return role.equalsIgnoreCase("Manager") ||
               role.equalsIgnoreCase("Counter Staff") ||
               role.equalsIgnoreCase("Technician");
    }
    
    public static List<String[]> searchStaff(String keyword) throws IOException{
        List<String[]> resultList = new ArrayList<>();
        File file = new File(USER_FILE);
        
        if (!file.exists()){
            return resultList;
        }
        
        String searchText = keyword.trim().toLowerCase();
        
        for (String line: Files.readAllLines(file.toPath())){
            if (line.trim().isEmpty()){
                continue;
            }
            
            String[] parts = line.split(",");
            if (parts.length >= 9){
                String userId = parts[0].trim();
                String username = parts[1].trim();
                String role = parts[3].trim();
                String tp = parts[5].trim();
                String status = parts[8].trim();
                
                
                if (isStaffRole(role) &&
                    (userId.toLowerCase().contains(searchText) ||
                     username.toLowerCase().contains(searchText) ||
                     tp.toLowerCase().contains(searchText) ||
                     role.toLowerCase().contains(searchText) ||
                     status.toLowerCase().contains(searchText))) {
                    resultList.add(parts);
                }
            }
        }

        return resultList;
    }
    
    public static String[] getStaffById(String userId) throws IOException {
        File file = new File(USER_FILE);

        if (!file.exists()) {
            return null;
        }

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 9 && parts[0].trim().equalsIgnoreCase(userId)) {
                return parts;
            }
        }

        return null;
    }
    
    public static boolean usernameExistsExcept(String username, String userId) throws IOException {
        return valueExistsInColumnExcept(USER_FILE, username, 1, userId);
    }

    public static boolean tpExistsExcept(String tp, String userId) throws IOException {
        return valueExistsInColumnExcept(USER_FILE, tp, 5, userId);
    }

    public static boolean phoneExistsExcept(String phone, String userId) throws IOException {
        return valueExistsInColumnExcept(USER_FILE, phone, 6, userId);
    }

    public static boolean emailExistsExcept(String email, String userId) throws IOException {
        return valueExistsInColumnExcept(USER_FILE, email, 7, userId);
    }
    
    public static boolean valueExistsInColumnExcept(String filePath, String value, int columnIndex, String userId) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        }

        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            if (parts.length > columnIndex) {
                String currentUserId = parts[0].trim();
                String currentValue = parts[columnIndex].trim();

                if (!currentUserId.equalsIgnoreCase(userId) &&
                    currentValue.equalsIgnoreCase(value.trim())) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public static boolean deleteStaff(String userId) throws IOException {
        File file = new File(USER_FILE);
        
        if (!file.exists()){
            return false;
        }
        
        List<String> updatedLines = new ArrayList<>();
        boolean deleted = false;
        
        for (String line: Files.readAllLines(file.toPath())){
            if (line.trim().isEmpty()){
                continue;
            }
            
            String[] parts = line.split(",");
            
            if (parts.length >= 9) {
                String currentId = parts[0].trim();
                
                if(currentId.equalsIgnoreCase(userId)){
                    deleted = true;
                } else{
                    updatedLines.add(line);
                }
            }
        }
        
        Files.write(file.toPath(), updatedLines);
        return deleted;
    }
    
    public static boolean updateStaff(String userId, String username, String role,
                                    String fullName, String tp, String phone,
                                    String email, String status) throws IOException {

      File file = new File(USER_FILE);

      if (!file.exists()) {
          return false;
      }

      List<String> updatedLines = new ArrayList<>();
      boolean updated = false;

      for (String line : Files.readAllLines(file.toPath())) {
          if (line.trim().isEmpty()) {
              continue;
          }

          String[] parts = line.split(",");

          if (parts.length >= 10) {
              String currentId = parts[0].trim();

              if (currentId.equals(userId)) {
                  String password = parts[2].trim();

                  String updatedLine = String.join(",",
                          userId, username, password, role, fullName, tp, phone, email, status);

                  updatedLines.add(updatedLine);
                  updated = true;
              } else {
                  updatedLines.add(line);
              }
          }
      }

      Files.write(file.toPath(), updatedLines);

      return updated;
    }
    
    public static String checkUniqueFieldsForUpdate(
        String originalUsername,
        String newUsername,
        String newTP,
        String newPhone,
        String newEmail,
        String originalTP,
        String originalPhone,
        String originalEmail
    ) throws IOException {

        File file = new File(USER_FILE);
        if (!file.exists()) {
            return null;
        }

        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 9) {
                continue;
            }

            String username = parts[1].trim();
            String tp = parts[5].trim();
            String phone = parts[6].trim();
            String email = parts[7].trim();

            if (username.equalsIgnoreCase(originalUsername)) {
                continue;
            }

            if (!newUsername.equalsIgnoreCase(originalUsername) && username.equalsIgnoreCase(newUsername)) {
                return "Username already exists. Please choose another.";
            }

            if (!newTP.equalsIgnoreCase(originalTP) && tp.equalsIgnoreCase(newTP)) {
                return "TP Number already exists. Please check again.";
            }

            if (!newPhone.equalsIgnoreCase(originalPhone) && phone.equalsIgnoreCase(newPhone)) {
                return "Phone number already exists. Please check again.";
            }

            if (!newEmail.equalsIgnoreCase(originalEmail) && email.equalsIgnoreCase(newEmail)) {
                return "Email already exists. Please check again.";
            }
        }

        return null;
    }
    
    public static boolean vehicleRegExistsForUpdate(String originalVRN, String newVRN) throws IOException {
        File file = new File(VEHICLE_FILE);
        if (!file.exists()) {
            return false;
        }

        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 6) {
                continue;
            }

            String existingVRN = parts[2].trim();

            if (existingVRN.equalsIgnoreCase(newVRN) && !newVRN.equalsIgnoreCase(originalVRN)) {
                return true;
            }
        }

        return false;
    }
    
    public static List<String[]> getCustomerHistory(String currentUsername) throws IOException {
        List<String[]> historyList = new ArrayList<>();

        String userId = "";
        String customerId = "";

        File userFile = new File(USER_FILE);
        if (userFile.exists()) {
            List<String> userLines = Files.readAllLines(userFile.toPath());
            for (String line : userLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[1].trim().equalsIgnoreCase(currentUsername)) {
                    userId = parts[0].trim();
                    break;
                }
            }
        }

        if (userId.isEmpty()) {
            return historyList;
        }

        File customerFile = new File(CUSTOMER_FILE);
        if (customerFile.exists()) {
            List<String> customerLines = Files.readAllLines(customerFile.toPath());
            for (String line : customerLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(userId)) {
                    customerId = parts[0].trim();
                    break;
                }
            }
        }

        if (customerId.isEmpty()) {
            return historyList;
        }

        File appointmentFile = new File(APPOINTMENT_FILE);
        File serviceTypeFile = new File(SERVICE_TYPE_FILE);
        File paymentFile = new File(PAYMENT_FILE);
        File vehicleFile = new File(VEHICLE_FILE);

        List<String> appointmentLines = appointmentFile.exists() ? Files.readAllLines(appointmentFile.toPath()) : new ArrayList<>();
        List<String> serviceTypeLines = serviceTypeFile.exists() ? Files.readAllLines(serviceTypeFile.toPath()) : new ArrayList<>();
        List<String> paymentLines = paymentFile.exists() ? Files.readAllLines(paymentFile.toPath()) : new ArrayList<>();
        List<String> vehicleLines = vehicleFile.exists() ? Files.readAllLines(vehicleFile.toPath()) : new ArrayList<>();

        for (String appointmentLine : appointmentLines) {
            if (appointmentLine.trim().isEmpty()) continue;

            String[] appt = appointmentLine.split(",");
            if (appt.length < 12) continue;

            String appointmentId = appt[0].trim();
            String vehicleId = appt[1].trim();
            String appointmentCustomerId = appt[2].trim();
            String serviceTypeId = appt[5].trim();
            String appointmentDate = appt[6].trim();
            String startTime = appt[7].trim();
            String endTime = appt[8].trim();
            String appointmentStatus = appt[9].trim();
            String remarks = appt[10].trim();

            if (!appointmentCustomerId.equalsIgnoreCase(customerId)) {
                continue;
            }
            
            String vehiclePlate = "N/A";
            for (String vehicleLine : vehicleLines) {
                if (vehicleLine.trim().isEmpty()) continue;

                String[] vehicleParts = vehicleLine.split(",");
                if (vehicleParts.length >= 6 && vehicleParts[0].trim().equalsIgnoreCase(vehicleId)) {
                    vehiclePlate = vehicleParts[2].trim();
                    break;
                }
            }
            String serviceTypeName = serviceTypeId;

            // service_type.txt
            // 0 = service_type_id
            // 1 = service_type_name
            // 2 = duration
            // 3 = price
            // 4 = updated_by
            for (String serviceLine : serviceTypeLines) {
                if (serviceLine.trim().isEmpty()) continue;

                String[] svc = serviceLine.split(",");
                if (svc.length >= 4 && svc[0].trim().equalsIgnoreCase(serviceTypeId)) {
                    serviceTypeName = svc[1].trim();
                    break;
                }
            }

            String amount = "-";
            String paymentMethod = "-";
            String paymentDate = "-";
            String paymentStatus = "Unpaid";

            for (String paymentLine : paymentLines) {
                if (paymentLine.trim().isEmpty()) continue;

                String[] pay = paymentLine.split(",");
                if (pay.length >= 7 && pay[1].trim().equalsIgnoreCase(appointmentId)) {
                    amount = pay[2].trim();
                    paymentMethod = pay[3].trim();
                    paymentDate = pay[4].trim();
                    paymentStatus = pay[6].trim();
                    break;
                }
            }

            historyList.add(new String[] {
                appointmentId,
                vehiclePlate,
                serviceTypeName,   
                appointmentDate,   
                amount,            
                paymentStatus,     
                paymentMethod,     
                paymentDate,       
                remarks,           
                startTime,         
                endTime,           
                appointmentStatus,  
                vehicleId 
            });
        }

        return historyList;
    }
    
    public static String[] verifyUser(String username, String password) throws IOException {
        File file = new File(USER_FILE);

        if (!file.exists()) {
            return null;
        }

        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            // 0=userId, 1=username, 2=password, 3=role, 4=name, 5=tp, 6=phone, 7=email, 8=status, 9=created_at
            if (parts.length >= 10) {
                String userId = parts[0].trim();
                String fileUsername = parts[1].trim();
                String filePassword = parts[2].trim();
                String role = parts[3].trim();
                String status = parts[8].trim();

                if (fileUsername.equals(username) && filePassword.equals(password)) {
                    if (status.equalsIgnoreCase("Active")) {
                        return new String[]{userId, role};
                    } else {
                        return new String[]{userId, "INACTIVE"};
                    }
                }
            }
        }

        return null;
    }
    
    public static List<String[]> getCustomerVehicles(String currentUsername) throws IOException {
        List<String[]> vehicleList = new ArrayList<>();

        String userId = "";
        String customerId = "";

        // find userId from users.txt
        File userFile = new File(USER_FILE);
        if (userFile.exists()) {
            List<String> userLines = Files.readAllLines(userFile.toPath());
            for (String line : userLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[1].trim().equalsIgnoreCase(currentUsername)) {
                    userId = parts[0].trim();
                    break;
                }
            }
        }

        if (userId.isEmpty()) {
            return vehicleList;
        }

        // find customerId from customers.txt
        File customerFile = new File(CUSTOMER_FILE);
        if (customerFile.exists()) {
            List<String> customerLines = Files.readAllLines(customerFile.toPath());
            for (String line : customerLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(userId)) {
                    customerId = parts[0].trim();
                    break;
                }
            }
        }

        if (customerId.isEmpty()) {
            return vehicleList;
        }

        // load vehicles for this customer
        File vehicleFile = new File(VEHICLE_FILE);
        if (vehicleFile.exists()) {
            List<String> vehicleLines = Files.readAllLines(vehicleFile.toPath());
            for (String line : vehicleLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[1].trim().equalsIgnoreCase(customerId)) {
                    vehicleList.add(new String[] {
                        parts[0].trim(), // vehicleId
                        parts[1].trim(), // customerId
                        parts[2].trim(), // plate no
                        parts[3].trim(), // type
                        parts[4].trim(), // model
                        parts[5].trim()  // year
                    });
                }
            }
        }

        return vehicleList;
    }
    
    public static boolean deleteVehicle(String vehicleId) throws IOException {
        File vehicleFile = new File(VEHICLE_FILE);
        if (!vehicleFile.exists()) {
            return false;
        }

        List<String> lines = Files.readAllLines(vehicleFile.toPath());
        List<String> updatedLines = new ArrayList<>();
        boolean deleted = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length >= 6 && parts[0].trim().equalsIgnoreCase(vehicleId)) {
                deleted = true;
                continue;
            }

            updatedLines.add(line);
        }

        if (deleted) {
            Files.write(vehicleFile.toPath(), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        }

        return deleted;
    }
    
    public static boolean addVehicle(String currentUsername, String plateNo, String type, String model, String year) throws IOException {
        String userId = "";
        String customerId = "";

        File userFile = new File(USER_FILE);
        if (userFile.exists()) {
            List<String> userLines = Files.readAllLines(userFile.toPath());
            for (String line : userLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[1].trim().equalsIgnoreCase(currentUsername)) {
                    userId = parts[0].trim();
                    break;
                }
            }
        }

        if (userId.isEmpty()) {
            return false;
        }

        File customerFile = new File(CUSTOMER_FILE);
        if (customerFile.exists()) {
            List<String> customerLines = Files.readAllLines(customerFile.toPath());
            for (String line : customerLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(userId)) {
                    customerId = parts[0].trim();
                    break;
                }
            }
        }

        if (customerId.isEmpty()) {
            return false;
        }

        if (vehicleRegExists(plateNo)) {
            return false;
        }

        String vehicleId = generateNextId(VEHICLE_FILE, "VEH");
        String vehicleLine = String.join(",", vehicleId, customerId, plateNo, type, model, year);
        appendLine(VEHICLE_FILE, vehicleLine);

        return true;
    }
    
    public static boolean updateVehicle(String vehicleId, String oldPlateNo, String newPlateNo,
                                    String newType, String newModel, String newYear) throws IOException {
        File vehicleFile = new File(VEHICLE_FILE);
        if (!vehicleFile.exists()) {
            return false;
        }

        List<String> lines = Files.readAllLines(vehicleFile.toPath());
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 6) {
                updatedLines.add(line);
                continue;
            }

            String currentVehicleId = parts[0].trim();
            String customerId = parts[1].trim();
            String plateNo = parts[2].trim();

            if (!newPlateNo.equalsIgnoreCase(oldPlateNo) && plateNo.equalsIgnoreCase(newPlateNo)) {
                return false; // duplicate plate number
            }

            if (currentVehicleId.equalsIgnoreCase(vehicleId)) {
                String updatedVehicle = String.join(",",
                        currentVehicleId,
                        customerId,
                        newPlateNo,
                        newType,
                        newModel,
                        newYear
                );
                updatedLines.add(updatedVehicle);
                found = true;
            } else {
                updatedLines.add(line);
            }
        }

        if (found) {
            Files.write(vehicleFile.toPath(), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        }

        return found;
    }
    
    public static List<String[]> getCustomerFeedbackHistory(String currentUsername) throws IOException {
        List<String[]> feedbackList = new ArrayList<>();

        String userId = "";
        String customerId = "";

        // 1. Find userId from users.txt
        File userFile = new File(USER_FILE);
        if (userFile.exists()) {
            List<String> userLines = Files.readAllLines(userFile.toPath());
            for (String line : userLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[1].trim().equalsIgnoreCase(currentUsername)) {
                    userId = parts[0].trim();
                    break;
                }
            }
        }

        if (userId.isEmpty()) {
            return feedbackList;
        }

        // 2. Find customerId from customers.txt
        File customerFile = new File(CUSTOMER_FILE);
        if (customerFile.exists()) {
            List<String> customerLines = Files.readAllLines(customerFile.toPath());
            for (String line : customerLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(userId)) {
                    customerId = parts[0].trim();
                    break;
                }
            }
        }

        if (customerId.isEmpty()) {
            return feedbackList;
        }

        // 3. Load vehicles into map
        java.util.Map<String, String> vehicleMap = new java.util.HashMap<>();
        File vehicleFile = new File(VEHICLE_FILE);
        if (vehicleFile.exists()) {
            List<String> vehicleLines = Files.readAllLines(vehicleFile.toPath());
            for (String line : vehicleLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    vehicleMap.put(parts[0].trim(), parts[2].trim()); // vehicleId -> plate no
                }
            }
        }

        // 4. Load service types into map
        java.util.Map<String, String> serviceTypeMap = new java.util.HashMap<>();
        File serviceTypeFile = new File(SERVICE_TYPE_FILE);
        if (serviceTypeFile.exists()) {
            List<String> serviceLines = Files.readAllLines(serviceTypeFile.toPath());
            for (String line : serviceLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    serviceTypeMap.put(parts[0].trim(), parts[1].trim()); // serviceTypeId -> service type name
                }
            }
        }

        // 5. Load technician feedback into map
        java.util.Map<String, String[]> feedbackMap = new java.util.HashMap<>();
        File feedbackFile = new File(FEEDBACK_FILE);
        if (feedbackFile.exists()) {
            List<String> feedbackLines = Files.readAllLines(feedbackFile.toPath());
            for (String line : feedbackLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String appointmentId = parts[1].trim();
                    String feedbackText = parts[2].trim();
                    String feedbackDate = parts[3].trim();

                    feedbackMap.put(appointmentId, new String[] { feedbackText, feedbackDate });
                }
            }
        }

        // 6. Load appointments for this customer
        File appointmentFile = new File(APPOINTMENT_FILE);
        if (appointmentFile.exists()) {
            List<String> appointmentLines = Files.readAllLines(appointmentFile.toPath());
            for (String line : appointmentLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 11) continue;

                String appointmentId = parts[0].trim();
                String vehicleId = parts[1].trim();
                String appointmentCustomerId = parts[2].trim();
                String serviceTypeId = parts[5].trim();
                String appointmentDate = parts[6].trim();
                String status = parts[9].trim();
                String serviceName = parts[10].trim(); // remarks

                if (!appointmentCustomerId.equalsIgnoreCase(customerId)) {
                    continue;
                }

                if (!feedbackMap.containsKey(appointmentId)) {
                    continue; // show only appointments that have feedback
                }

                String vehiclePlate = vehicleMap.getOrDefault(vehicleId, "N/A");
                String serviceType = serviceTypeMap.getOrDefault(serviceTypeId, serviceTypeId);
                String feedbackText = feedbackMap.get(appointmentId)[0];
                String feedbackDate = feedbackMap.get(appointmentId)[1];

                feedbackList.add(new String[] {
                    appointmentDate, // 0
                    vehiclePlate,    // 1
                    serviceType,     // 2
                    serviceName,     // 3
                    status,          // 4
                    feedbackText,    // 5
                    feedbackDate     // 6
                });
            }
        }

        return feedbackList;
    }
    
    public static List<String[]> getCustomerAppointmentsForComment(String currentUsername) throws IOException {
        List<String[]> appointmentList = new ArrayList<>();

        String userId = "";
        String customerId = "";

        // 1. Get userId from users.txt
        File userFile = new File(USER_FILE);
        if (userFile.exists()) {
            List<String> userLines = Files.readAllLines(userFile.toPath());
            for (String line : userLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[1].trim().equalsIgnoreCase(currentUsername)) {
                    userId = parts[0].trim();
                    break;
                }
            }
        }

        if (userId.isEmpty()) {
            return appointmentList;
        }

        // 2. Get customerId from customers.txt
        File customerFile = new File(CUSTOMER_FILE);
        if (customerFile.exists()) {
            List<String> customerLines = Files.readAllLines(customerFile.toPath());
            for (String line : customerLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(userId)) {
                    customerId = parts[0].trim();
                    break;
                }
            }
        }

        if (customerId.isEmpty()) {
            return appointmentList;
        }

        // 3. Build vehicle map: vehicleId -> plate no
        java.util.Map<String, String> vehicleMap = new java.util.HashMap<>();
        File vehicleFile = new File(VEHICLE_FILE);
        if (vehicleFile.exists()) {
            List<String> vehicleLines = Files.readAllLines(vehicleFile.toPath());
            for (String line : vehicleLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    vehicleMap.put(parts[0].trim(), parts[2].trim());
                }
            }
        }

        // 4. Build comment map: appointmentId -> Submitted
        java.util.Set<String> commentedAppointments = new java.util.HashSet<>();
        File commentFile = new File(CUSTOMER_COMMENT_FILE);
        if (commentFile.exists()) {
            List<String> commentLines = Files.readAllLines(commentFile.toPath());
            for (String line : commentLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    commentedAppointments.add(parts[1].trim());
                }
            }
        }

        // 5. Load completed appointments only
        File appointmentFile = new File(APPOINTMENT_FILE);
        if (appointmentFile.exists()) {
            List<String> appointmentLines = Files.readAllLines(appointmentFile.toPath());
            for (String line : appointmentLines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 11) continue;

                String appointmentId = parts[0].trim();
                String vehicleId = parts[1].trim();
                String appointmentCustomerId = parts[2].trim();
                String appointmentDate = parts[6].trim();
                String status = parts[9].trim();
                String serviceName = parts[10].trim(); // remarks

                if (!appointmentCustomerId.equalsIgnoreCase(customerId)) {
                    continue;
                }

                if (!status.equalsIgnoreCase("Completed")) {
                    continue;
                }

                String vehiclePlate = vehicleMap.getOrDefault(vehicleId, "N/A");
                String commentStatus = commentedAppointments.contains(appointmentId) ? "Submitted" : "-";

                appointmentList.add(new String[] {
                    appointmentId,     // 0
                    appointmentDate,   // 1
                    vehiclePlate,      // 2
                    serviceName,       // 3
                    status,            // 4
                    commentStatus      // 5
                });
            }
        }

        return appointmentList;
    }

    public static boolean customerCommentExists(String appointmentId) throws IOException {
        File file = new File(CUSTOMER_COMMENT_FILE);
        if (!file.exists()) {
            return false;
        }

        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            int firstComma = line.indexOf(',');
            int secondComma = line.indexOf(',', firstComma + 1);

            if (firstComma == -1 || secondComma == -1) {
                continue;
            }

            String apptId = line.substring(firstComma + 1, secondComma).trim();
            if (apptId.equalsIgnoreCase(appointmentId)) {
                return true;
            }
        }

        return false;
    }
    
    public static boolean addCustomerComment(String appointmentId, String commentText) throws IOException {
        File file = new File(CUSTOMER_COMMENT_FILE);

        if (customerCommentExists(appointmentId)) {
            return false;
        }

        String newId = generateNextId(CUSTOMER_COMMENT_FILE, "CMT");
        String commentDate = java.time.LocalDate.now().toString();

        String newLine = String.join(",",
                newId,
                appointmentId,
                commentText,
                commentDate
        );

        appendLine(CUSTOMER_COMMENT_FILE, newLine);
        return true;
    }
    
    public static String[] getCustomerCommentByAppointmentId(String appointmentId) throws IOException {
        File file = new File(CUSTOMER_COMMENT_FILE);

        if (!file.exists()) {
            return null;
        }

        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            int firstComma = line.indexOf(',');
            int secondComma = line.indexOf(',', firstComma + 1);
            int lastComma = line.lastIndexOf(',');

            if (firstComma == -1 || secondComma == -1 || lastComma == -1 || secondComma == lastComma) {
                continue;
            }

            String commentId = line.substring(0, firstComma).trim();
            String apptId = line.substring(firstComma + 1, secondComma).trim();
            String commentText = line.substring(secondComma + 1, lastComma).trim();
            String commentDate = line.substring(lastComma + 1).trim();

            if (apptId.equalsIgnoreCase(appointmentId)) {
                return new String[] {
                    commentId,
                    apptId,
                    commentText,
                    commentDate
                };
            }
        }

        return null;
    }
    
    public static List<String[]> getAllServiceTypes() throws IOException {
        List<String[]> serviceTypeList = new ArrayList<>();
        File file = new File(SERVICE_TYPE_FILE);

        if (!file.exists()) {
            return serviceTypeList;
        }

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 5) {
                serviceTypeList.add(parts);
            }
        }

        return serviceTypeList;
    }
    
    public static boolean addServiceType(String name, String duration, String updatedBy) throws IOException {
        String serviceTypeId = generateNextId(SERVICE_TYPE_FILE, "SC");
        String createdAt = getCurrentDateTime();

        String newLine = String.join(",",
                serviceTypeId,
                name,
                duration,
                updatedBy,
                createdAt
        );

        appendLine(SERVICE_TYPE_FILE, newLine);
        return true;
    }
    
    public static String[] getServiceTypeById(String serviceTypeId) throws IOException {
        File file = new File(SERVICE_TYPE_FILE);

        if (!file.exists()) {
            return null;
        }

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[0].trim().equalsIgnoreCase(serviceTypeId)) {
                return parts;
            }
        }

        return null;
    }
    
    public static boolean updateServiceType(String serviceTypeId, String name, String duration, String updatedBy) throws IOException {
        File file = new File(SERVICE_TYPE_FILE);

        if (!file.exists()) {
            return false;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;
        String updatedAt = getCurrentDateTime();

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            if (parts.length >= 5) {
                String currentId = parts[0].trim();

                if (currentId.equalsIgnoreCase(serviceTypeId)) {
                    String updatedLine = String.join(",",
                            serviceTypeId,
                            name,
                            duration,
                            updatedBy,
                            updatedAt
                    );
                    updatedLines.add(updatedLine);
                    updated = true;
                } else {
                    updatedLines.add(line);
                }
            }
        }

        Files.write(file.toPath(), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        return updated;
    }
    
    public static boolean deleteServiceType(String serviceTypeId) throws IOException {
        File file = new File(SERVICE_TYPE_FILE);

        if (!file.exists()) {
            return false;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean deleted = false;

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            if (parts.length >= 5) {
                String currentId = parts[0].trim();

                if (currentId.equalsIgnoreCase(serviceTypeId)) {
                    deleted = true;
                } else {
                    updatedLines.add(line);
                }
            }
        }

        Files.write(file.toPath(), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        return deleted;
    }
    
    public static boolean serviceTypeNameExists(String name) throws IOException {
        return valueExistsInColumn(SERVICE_TYPE_FILE, name, 1);
    }
    
    public static boolean serviceTypeNameExistsExcept(String name, String serviceTypeId) throws IOException {
        return valueExistsInColumnExcept(SERVICE_TYPE_FILE, name, 1, serviceTypeId);
    }
    
    public static boolean isValidDuration(String duration) {
        try {
            double value = Double.parseDouble(duration);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static List<String[]> getAllServiceItems() throws IOException {
        List<String[]> serviceItemList = new ArrayList<>();
        File file = new File(SERVICE_ITEM_FILE);

        if (!file.exists()) {
            return serviceItemList;
        }

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 7) {
                serviceItemList.add(new String[] {
                    parts[0].trim(), // service_item_id
                    parts[1].trim(), // service_cate_id
                    parts[2].trim(), // service_name
                    parts[3].trim(), // description
                    parts[4].trim()  // price
                });
            }
        }

        return serviceItemList;
    }
    
    public static boolean addServiceItem(String cateId, String serviceName, String description, String price, String updatedBy) throws IOException {
        String serviceItemId = generateNextId(SERVICE_ITEM_FILE, "SER");
        String updatedAt = getCurrentDateTime();

        String newLine = String.join(",",
                serviceItemId,
                cateId,
                serviceName,
                description,
                price,
                updatedBy,
                updatedAt
        );

        appendLine(SERVICE_ITEM_FILE, newLine);
        return true;
    }
    
    public static boolean serviceItemExists(String cateId, String serviceName) throws IOException {
        File file = new File(SERVICE_ITEM_FILE);

        if (!file.exists()) {
            return false;
        }

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 7) {
                String currentCateId = parts[1].trim();
                String currentServiceName = parts[2].trim();

                if (currentCateId.equalsIgnoreCase(cateId)
                        && currentServiceName.equalsIgnoreCase(serviceName)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public static boolean isValidPrice(String price) {
        try {
            double value = Double.parseDouble(price);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean deleteServiceItem(String serviceItemId) throws IOException {
        File file = new File(SERVICE_ITEM_FILE);

        if (!file.exists()) {
            return false;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean deleted = false;

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            if (parts.length >= 7) {
                String currentId = parts[0].trim();

                if (currentId.equalsIgnoreCase(serviceItemId)) {
                    deleted = true;
                } else {
                    updatedLines.add(line);
                }
            }
        }

        Files.write(file.toPath(), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        return deleted;
    }
    
    public static String[] getServiceItemById(String serviceItemId) throws IOException {
        File file = new File(SERVICE_ITEM_FILE);

        if (!file.exists()) {
            return null;
        }

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 7 && parts[0].trim().equalsIgnoreCase(serviceItemId)) {
                return parts;
            }
        }

        return null;
    }
    
    public static boolean updateServiceItem(String serviceItemId, String cateId, String serviceName,
                                            String description, String price, String updatedBy) throws IOException {
        File file = new File(SERVICE_ITEM_FILE);

        if (!file.exists()) {
            return false;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;
        String updatedAt = getCurrentDateTime();

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            if (parts.length >= 7) {
                String currentId = parts[0].trim();

                if (currentId.equalsIgnoreCase(serviceItemId)) {
                    String updatedLine = String.join(",",
                            serviceItemId,
                            cateId,
                            serviceName,
                            description,
                            price,
                            updatedBy,
                            updatedAt
                    );
                    updatedLines.add(updatedLine);
                    updated = true;
                } else {
                    updatedLines.add(line);
                }
            }
        }

        Files.write(file.toPath(), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        return updated;
    }
    
    public static boolean serviceItemExistsExcept(String cateId, String serviceName, String serviceItemId) throws IOException {
        File file = new File(SERVICE_ITEM_FILE);

        if (!file.exists()) {
            return false;
        }

        for (String line : Files.readAllLines(file.toPath())) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 7) {
                String currentId = parts[0].trim();
                String currentCateId = parts[1].trim();
                String currentServiceName = parts[2].trim();

                if (!currentId.equalsIgnoreCase(serviceItemId)
                        && currentCateId.equalsIgnoreCase(cateId)
                        && currentServiceName.equalsIgnoreCase(serviceName)) {
                    return true;
                }
            }
        }

        return false;
    }
    
}