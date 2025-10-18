package StoreApp;

public class Items {
    private Product product;
    private int quantity;

    public Items(Product product, int quantity)
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
}
