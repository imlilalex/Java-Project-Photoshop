import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import Objects.CartItem;
import Objects.Customer;
import Objects.Invoice;


public class Order {

    //Shows items in the cart
    public static List<CartItem> showOrder(List<CartItem> cart) {
        System.out.println("Currently your cart contains the following items.");
        int i = 1;
        for (CartItem cartItem : cart) {
            System.out.println(i +". "+ cartItem.amount +" "+ cartItem.item.getName());
            i++;
        }

        Scanner scan = new Scanner(System.in);
        int item = -1;
        
        //Allow editting of items in the cart
        do{
            System.out.println("Enter the Id of the item you want to update or enter 0 to return to the home page.");
            item = scan.nextInt();
        }while(item <0 || item > cart.size());
        
        if(item > 0 && item <= cart.size()){
            System.out.println("You are changing '"+ cart.get(item-1).item.getName() +"' How many would you like? Enter 0 if you want to remove it from the order.");
            int amount = scan.nextInt();
            if(amount == 0){
                cart.remove(item-1);
            }else if(amount > 0){
                System.out.println("=========Updating Cart");
                cart.get(item -1).setAmount(amount);
            }
            System.out.println("Order updated.");
        }

        // scan.close(); Cant close scanner yet because it closes the global scanner and causes issues. Scanner is closed in the home class.
        return cart;
    }

    //Confirm the order is complete
    public static void finalize(List<CartItem> cart, Customer customer) {

        Invoice invoice = new Invoice(cart, customer);

        invoice.print();

        //cannot close this scan without breaking code
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            System.out.println("Type 'Confirm' to finalize. Type 0 to return home.");
            input = scan.nextLine();
        } while (!input.equals("0") && !input.toLowerCase().equals("confirm"));

        if(input.toLowerCase().equals("confirm")){
            // Todo Save order
            System.out.println("Your order has been saved");
            saveInvoiceAsJson(invoice);
            System.exit(0);
        }

    }

    //Save the created Invoice to JSON file in orders folder
    private static void saveInvoiceAsJson(Invoice invoice) {
        try {
            String path = "./orders/"+ invoice.getOrderNumber() +".json";

            FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            String json = invoice.toJson();
            
            System.out.println(invoice);
            System.out.println(json);

			objectOut.writeObject(json);
			fileOut.getFD().sync();
            objectOut.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
