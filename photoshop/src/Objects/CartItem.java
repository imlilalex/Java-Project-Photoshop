package Objects;

//Object for items in the cart to set amount
public class CartItem {
    public Item item;
    public int amount;

    public CartItem(Item item, int amount){
        this.item = item;
        this.amount = amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

}
