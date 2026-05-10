/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CounterStaff;

/**
 *
 * @author PC
 */
public class Customer_CS {
    String customer_id, user_id;
    
    public Customer_CS(String customer_id, String user_id){
        this.customer_id = customer_id;
        this.user_id = user_id;
    }
    
    public String toFileString(){
        return customer_id + "," + user_id;
    }
}
