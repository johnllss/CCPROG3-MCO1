package StoreApp;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;
    private int quantity;
    private ArrayList<Shelf> shelves;

    public Inventory()
    {
        this.products = new ArrayList<>(Product);
        
    }
}
