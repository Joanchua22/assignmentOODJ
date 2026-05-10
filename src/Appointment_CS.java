
package CounterStaff;


public class Appointment_CS {
    String appointmentID;
    String custID;
    String custName;
    String phone;
    String vehicleID;
    String serviceType;
    String duration;
    String techID;
    String status;
    String createdAt;
    
    public Appointment_CS(String appointmentID, String custID, String custName, String phone,
            String vehicleID, String serviceType, String duration, String techID, String status, String createdAt){
    
    this.appointmentID = appointmentID;
    this.custID = custID;
    this.custName = custName;
    this.phone = phone;
    this.vehicleID = vehicleID;
    this.serviceType = serviceType;
    this.duration = duration;
    this.techID = techID;
    this.status = status;
    this.createdAt = createdAt;
    }
}
