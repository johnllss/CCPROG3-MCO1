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
            if (i.equals(id)) {
                return i;
            }
        }
    }
    public ArrayList<Item> getItems()
    {
        return items;
    }
}
