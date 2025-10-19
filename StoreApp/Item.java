package StoreApp;

public class Item {
    private Product product;
    private int quantity;

    public Item(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public double calculateItemSubtotal()
    {
        return this.product.getProductPrice() * this.quantity;
    }

    public Product getProduct()
    {
        return product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
