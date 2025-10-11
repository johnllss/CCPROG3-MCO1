package StoreApp;

public class Shelf {
    private String shelfID:
    private String category;
    private ArrayList<Product> products;

    public Shelf(String id, String category)
    {
        this.shelfID = id;
        this.category = category;
        this.products = new ArrayList<Product>();
    }

    public boolean addProductToShelf()
    {
        
    }

    public String getShelfCategory(String id)
    {
        return this.category;
    }
}