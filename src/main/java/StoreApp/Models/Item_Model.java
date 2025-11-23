package StoreApp.Models;

public class Item_Model {
    private Product product;
    private int quantity;

    /**
     * Class Item parameterized constructor
     * @param product is the product being referenced
     * @param quantity is the quantity placed by the Customer
     */
    public Item_Model(Product_Model product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

