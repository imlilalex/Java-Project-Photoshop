package Objects;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import CSV.ReadCSV;

public class Invoice{

    private List<CartItem> items;
    private int orderNumber;
    private Customer customer;
    private LocalDateTime dateTime;
    private int productionTime;
    private LocalDateTime pickupTime;
    private BigDecimal totalCost;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Invoice(List<CartItem> cart, Customer customer) {
        this.items = cart;
        this.customer = customer;
        this.orderNumber = (int)(Math.random() * 1000000);
        this.dateTime = LocalDateTime.now();
        this.calculateProductionTime();
        this.calculatePickupTime();
        this.calculateTotalCost();
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    //Calculate the order's total cost
    private void calculateTotalCost() {
        
        BigDecimal price = new BigDecimal(0);
        for (CartItem item : this.items) {
            price = price.add(item.item.getPrice().multiply(BigDecimal.valueOf(item.amount)));
        }
        this.totalCost = price;
    }

    //Calculate the pick up time for the order
    private void calculatePickupTime() {
        // Get Open times per day
        List<OpenTimes> openTimes = ReadCSV.getOpenTimes();

        // Calculate time print finishes
        double productionTime = this.productionTime;
        LocalDateTime movingDate = LocalDateTime.now(); 
        LocalDateTime finishedDate = LocalDateTime.now();

        while(productionTime > 0){
            for (OpenTimes openTime : openTimes) {
                if(openTime.day.toLowerCase().equals(movingDate.getDayOfWeek().name().toLowerCase())){
                    if(movingDate.toLocalTime().isBefore(openTime.close)){
                        // If Store is not yet closed calculate hours left in the day
                        float productionHours = movingDate.toLocalTime().until(openTime.close, ChronoUnit.MINUTES) / ((float)60);
                        float dayOpenHours = openTime.open.until(openTime.close, ChronoUnit.MINUTES) / ((float)60);
                        // Take lowest of working hours in the day and time left until closing time
                        float workHours = Math.min(productionHours, dayOpenHours);
                        
                        if(productionTime - workHours < 0){
                            // calculate end time in the day
                            if(movingDate.toLocalDate().compareTo(LocalDateTime.now().toLocalDate()) == 0){
                                finishedDate = movingDate.plusMinutes((long) (productionTime * (float)60));
                            }else{
                                finishedDate = movingDate.with(openTime.open).plusMinutes((long) (productionTime * (float)60));
                            }
                            productionTime = -1;
                            break;
                        }
                        productionTime -= workHours;
                    }

                    //Set time of day to midnight next day
                    movingDate = movingDate.with(LocalTime.of(0,1));
                    movingDate = movingDate.plusDays(1);
                    
                }
            }
        }
        // save end time.
        this.pickupTime = finishedDate;
    }

    //Calculate total processing time for order
    private void calculateProductionTime() {
        int time = 0;
        for (CartItem item : this.items) {
            time += item.item.getProcessingTime() * item.amount;
        }
        this.productionTime = time;
    }

    //Print invoice for order
    public void print() {
        System.out.println("Customer:");
        System.out.println(this.customer.getName());
        System.out.println(this.customer.getAddress());
        System.out.println(this.customer.getEmail());
        System.out.println(this.customer.getPhoneNumber());
        
        System.out.println("\n");
        System.out.println("Order specification:");
        System.out.printf("%-35.35s  %-32.32s%n", "Order number", this.orderNumber);
        System.out.printf("%-35.35s  %-32.32s%n", "Order date", this.dtf.format(this.dateTime));
        System.out.printf("%-35.35s  %-32.32s%n", "Production time in working hours", this.productionTime);
        System.out.printf("%-35.35s  %-32.32s%n", "You can pickup at ", this.dtf.format(this.pickupTime));
       
        System.out.println("\n"); 
        System.out.printf("%-30.30s  %6.6s  %6.6s  %7.7s%n", "Photo type", "Price", "Amount", "Cost");
        for (CartItem item : this.items) {
            System.out.printf("%-30.30s  %6.6s  %6.6s  %7.7s%n", item.item.getName(), item.item.getPrice(), item.amount, item.item.getPrice().multiply(BigDecimal.valueOf(item.amount)));
        }
        System.out.println("\n"); 
        System.out.printf("%-30.30s  %6.6s  %6.6s  %7.7s%n","Total Cost", "", "", this.totalCost);

    }

    public String toJson(){

        JSONObject invoiceJson = new JSONObject();
        
        JSONArray cartArray = new JSONArray();
        for (CartItem cartItem : this.items) {
            JSONObject cartItemJson = new JSONObject();
            cartItemJson.put("amount",cartItem.amount);

            JSONObject cartItemItemJson = new JSONObject();
            cartItemItemJson.put("id",cartItem.item.getid());
            cartItemItemJson.put("name",cartItem.item.getName());
            cartItemItemJson.put("price",cartItem.item.getPrice());
            cartItemItemJson.put("processingTime",cartItem.item.getProcessingTime());

            cartItemJson.put("item", cartItemItemJson);

            cartArray.put(cartItemJson);
        }
        invoiceJson.put("items", cartArray);


        invoiceJson.put("orderNumber", this.orderNumber);
        invoiceJson.put("dateTime", this.dtf.format(this.dateTime));
        invoiceJson.put("productionTime", this.productionTime);
        invoiceJson.put("pickupTime", this.dtf.format(this.pickupTime));
        invoiceJson.put("totalCost", this.totalCost);

        JSONObject customerJson = new JSONObject();
        customerJson.put("name", this.customer.getName());
        customerJson.put("Address", this.customer.getAddress()); 
        customerJson.put("email", this.customer.getEmail()); 
        customerJson.put("phoneNumber", this.customer.getPhoneNumber());
        
        invoiceJson.put("customer", customerJson);

        return invoiceJson.toString();
    }
}