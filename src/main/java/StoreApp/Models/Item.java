package StoreApp.Models;

/***
 * Class that represents item which interacts with cart
 */
public class Item {
    private Product product;
    private int quantity;

    /**
     * Class Item parameterized constructor
     * @param product is the product being referenced
     * @param quantity is the quantity placed by the Customer
     */
    public Item(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * This method calculates for the Item's subtotal price in the Cart. 
     * @return double for the subtotal price of the Item in the Cart.
     */
    public double calculateItemSubtotal()
    {
        return this.product.getProductPrice() * this.quantity;
    }

    /**
     * This is a getter method to get the referenced product.
     * @return Product is the product and its details.
     */
    public Product getProduct()
    {
        return product;
    }

    /**
     * This is a getter method to get the quantity of the Item placed by the Customer.
     * @return int is the quantity in this class.
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * This is a setter method to set the new quantity as desired by the Customer.
     * @param quantity is the new quantity.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
