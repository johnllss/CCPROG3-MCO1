package StoreApp;
/**
 *
 */
public class Product {
    private int productID;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productCategory;
    private String brand;
    private String variant;

    /**
     *
     * @param productID
     * @param productName
     * @param productPrice
     * @param productQuantity
     * @param productCategory
     * @param brand
     * @param variant
     */
    public Product(int productID, String productName, double productPrice, int productQuantity, String productCategory, String brand, String variant)
    {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.brand = brand;
        this.variant = variant;
    }

    /**
     *
     * @return productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     *
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @return productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     *
     * @return productQuantity
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     *
     * @return productCategory
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     *
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @return variant
     */
    public String getVariant() {
        return variant;
    }

    /**
     *
     * @param restockQuantity
     */
    public void updateStock(int restockQuantity)
    {
        this.productQuantity = productQuantity + restockQuantity;
    }
}
