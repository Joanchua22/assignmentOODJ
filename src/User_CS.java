package CounterStaff;

public class User_CS {
    String user_id, username, password, role;
    String full_name, phone_no, email, address, status, created_at;

    public User_CS(String user_id, String username, String password, String role,
                String full_name, String phone_no, String email,
                String address, String status, String created_at) {

        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.full_name = full_name;
        this.phone_no = phone_no;
        this.email = email;
        this.address = address;
        this.status = status;
        this.created_at = created_at;
    }

    public String toFileString() {
        return user_id + "," + username + "," + password + "," + role + "," +
               full_name + "," + phone_no + "," + email + "," +
               address + "," + status + "," + created_at;
    }
}