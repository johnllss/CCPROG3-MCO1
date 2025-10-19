package StoreApp;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Product product, int quantity)
    {
        if(product.getProductQuantity() < quantity)
        {
            return false;
        }
        else
        {
            items.add(new Item(product, quantity));
            return true;
        }
    }

    public boolean removeItem(Product product)
    {
       for(Item i : items)
       {
           if(i.getProduct().equals(product)){
               items.remove(i);
               return true;
           }
       }
       return false;
    }
    public Item findItem(String id)
    {
        for(Item i : items) {
            if (i.getProduct().getProductName().equals(id)) {
                return i;
            }
        }
        return null;
    }
    public ArrayList<Item> getItems()
    {
        return items;
    }
    public boolean updateQuantity(String id, int amount){
        for(Item i : items){
            if(i.getProduct().getProductName().equals(id) && i.getProduct().getProductQuantity() <= amount){
                i.setQuantity(amount);
                return true;
            }
        }
        return false;
    }

    public double calculateSubTotal(){
        double subTotal = 0;
        for(Item i : items){
            subTotal += i.calculateItemSubtotal();

        }
        return subTotal;
    }

    public boolean isEmpty()
    {
        if(items.isEmpty())
        {
            return true;
        }
        return false;
    }

    public void clearCart()
    {
        items.clear();
    }
    public void displayCart()
    {
        for(Item i : items)
        {
            System.out.println(i.getProduct().getProductName());
            System.out.println(i.getProduct().getProductPrice());
            System.out.println(i.getProduct().getProductQuantity());
        }
    }

}

