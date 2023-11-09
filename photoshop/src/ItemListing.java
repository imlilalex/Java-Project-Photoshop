import java.util.List;
import java.util.Scanner;

import Objects.CartItem;
import Objects.Item;
import CSV.*;

public class ItemListing {

    List<Item> items;

    public ItemListing(){
        try {
            ReadCSV readCSV = new ReadCSV();
            items = readCSV.readItemsFile("src\\CSV\\PhotoShop_PriceList.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Show full item list 
    public CartItem showItemListings() {
        CartItem cartItem;
        System.out.print("Id \tItem \t\t\t\tPrice \tProcessing Time");
        for (Item item : items) {
            System.out.print(item.toString());   
        }
        System.out.println("\n");
        
        //Allow Customer to add items to cart by item ID
        System.out.println("Enter the Id of the item you want to buy or 0 to return to home.");
        Scanner scan = new Scanner(System.in);
        int item = scan.nextInt();
        if(item >=1 && item <= items.size()){
            System.out.println("How many would you like to buy?");
            int amount = scan.nextInt();
            cartItem = new CartItem(items.get(item-1), amount);
            return cartItem;
        }

        return null;


        // scan.close(); Cant close scanner het because it closes the global scanner and causes issues. Scanner is closed in the home class.
    }

}
