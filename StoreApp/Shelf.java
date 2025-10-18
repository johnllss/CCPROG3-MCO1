package StoreApp;

public class Shelf {
    private String shelfID:
    private String category;
    private int currentCapacity;
    private int maxCapacity;
    private ArrayList<Product> products;

    public Shelf(String id, String category)
    {
        this.shelfID = id;
        this.category = category;
        this.products = new ArrayList<Product>();
    }

    public boolean addProductToShelf(Product product)
    {
        for (Product productOnShelf: products)
        {
            if (productOnSheld.getProductID() != product.getProductID() && this.currentCapacity < this.maxCapacity)
            {
                products = product;
            }
        }
    }

    public boolean removeProductFromShelf(String ShelfID)
    {
        for (Product productOnShelf: products)
        {
            
        }
    }

    public boolean findProductOnShelf()
    {

    }

    public void displayShelf()
    {

    }

    public String getShelfID()
    {
        return this.shelfID;
    }

    public String getShelfCategory(String id)
    {
        return this.category;
    }

    public ArrayList<Product> getProductsOnShelf()
    {

    }

    public void setShelfCategory(String newCategory)
    {
        this.category = newCategory;
    }
}