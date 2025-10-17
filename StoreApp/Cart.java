package StoreApp;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Items> items ;

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
            items.add(new Items(product, quantity));
            return true;
        }
    }

    public boolean removeItem(Product product)
    {
       for(Items i : items)
       {
           if(i.getProduct().equals(product)){
               items.remove(i);
               return true;
           }
       }
       return false;
    }
    public Items findItem(String id)
    {
        for(Items i : items) {
            if (i.equals(id)) {
                return i;
            }
        }
    }
    public ArrayList<Items> getItems()
    {
        return items;
    }
    


}
