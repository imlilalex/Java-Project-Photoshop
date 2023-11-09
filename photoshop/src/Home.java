import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Objects.CartItem;
import Objects.Customer;

public class Home {
    public void showHomepage(){
        ItemListing itemListing = new ItemListing();
        Scanner scan = new Scanner(System.in);

        Customer customer = null;
        List<CartItem> cart = new ArrayList<CartItem>();
        boolean exit = false;
        System.out.println("Welcome to PhotoShop");
        while(exit == false){
            
            System.out.println("Which page would you like to view? (Enter page number)");
            System.out.println("0. Exit");
            System.out.println("1. Opening Times");
            System.out.println("2. Items");
            System.out.println("3. Customer Information");
            System.out.println("4. Your Order");
            System.out.println("5. Finalize order");
            
            int page = scan.nextInt();

            while(page<0 || page>5){
                System.out.println("That is an invalid option. Enter a page number.");
                page = scan.nextInt();
            }

            switch (page) {
                case 1: new OpeningTimesPage().showOpeningTimes();
                    break;
                case 2: CartItem cartItem = itemListing.showItemListings();
                    if(cartItem != null){
                        cart.add(cartItem);
                    } 
                    break;
                case 3: customer = CustomerInformation.showCustomer(customer);
                    break;
                case 4: cart = Order.showOrder(cart);
                    break;
                case 5: 
                    if(customer == null || cart.size()==0){
                        System.out.println("Please enter your customer information and add at least one item to the cart before viewing your invoice.");
                    }else {
                        Order.finalize(cart, customer);
                    }
                    break;
                default: exit = true;
                    System.out.println("Thank you for visiting PhotoShop.");
            }

        }
    
        // For some reason it bugs out when the scanner is open and close each time, so we just close it here once.
        scan.close();
    }
}
