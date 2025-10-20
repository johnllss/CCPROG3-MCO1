package StoreApp;

public class Item {
    private Product product;
    private int quantity;

    /***
     *
     * @param product
     * @param quantity
     */
    public Item(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    /***
     *
     * @return
     */
    public double calculateItemSubtotal()
    {
        return this.product.getProductPrice() * this.quantity;
    }

    /***
     *
     * @return
     */
    public Product getProduct()
    {
        return product;
    }

    /***
     *
     * @return
     */
    public int getQuantity()
    {
        return quantity;
    }

    /***
     *
     * @param quantity
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
