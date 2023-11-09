import java.util.Scanner;
import Objects.Customer;


public class CustomerInformation {
 
    public static Customer showCustomer(Customer customer){
        Scanner scan = new Scanner(System.in);
        if (customer == null) {
            System.out.println("Your customer information is empty. Please enter your information.");
            
            customer = enterCustomerInformation();
            
        } else {
            //show the customer information and ask if they want to change it
            
            System.out.println("\nHere is your information:");    
            System.out.println("Name: \t" + customer.getName());
            System.out.println("Address: \t" + customer.getAddress());
            System.out.println("Email: \t" + customer.getEmail());
            System.out.println("Phone Number: \t" + customer.getPhoneNumber());

            System.out.println("\n"+"Enter 1 to update your information. Enter anything else to return to home.");
            String update = scan.next();
            if(update.equals("1")){
                System.out.println("Please enter your information.");
                customer = enterCustomerInformation();
            }

        }   
        return customer;
    }

    //Enter customer information and save it to current customer
    private static Customer enterCustomerInformation(){
        Scanner scan = new Scanner(System.in);
        // Collect information from user
        System.out.println("Name:");
        String name = scan.nextLine();
        System.out.println("Street & House Number:");
        String street = scan.nextLine();
        System.out.println("Postcode:");
        String postcode = scan.nextLine();
        System.out.println("City:");
        String city = scan.nextLine();
        System.out.println("Email:");
        String email = scan.nextLine();
        System.out.println("Phone Number:");
        String phoneNumber = scan.nextLine();
        
        return new Customer(name, street, postcode, city, email, phoneNumber);
        
    }


}