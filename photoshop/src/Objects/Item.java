package Objects;

import java.math.BigDecimal;

public class Item {
    
    private int id;
    private String name;
    private BigDecimal price;
    private int processingTime;


    public Item(int id, String name, BigDecimal price, int processingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.processingTime = processingTime;
    }

    public int getid() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    //set elements from the customer
    
    public void setid(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }  

    public void setEmail(BigDecimal price) {
        this.price = price;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    @Override
    public String toString() {
        
        if (this.name.length() > 23) {
            return "\n" + this.id + "\t" + this.name + "\t" + this.price + "\t" + this.processingTime;
        } else {
            return "\n" + this.id + "\t" + this.name + "\t\t" + this.price + "\t" + this.processingTime;
        }
    }

}

