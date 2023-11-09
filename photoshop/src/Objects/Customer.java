package Objects;

public class Customer {

    private String name;
    private String street;
    private String postcode;
    private String city;
    private String email;
    private String phoneNumber;


    public Customer() {
        
    }
    //create customer with above attributes
    public Customer(String name, String street, String postcode, String city, String email, String phoneNumber) {

        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    //get elements from the customer
    
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.street + "\n" + this.postcode + "\n" + this.city;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    //set elements from the customer

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String street, String postcode, String city) {
        this.street = street;
        this.postcode = postcode;
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    

}