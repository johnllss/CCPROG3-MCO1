package StoreApp.Models;

public class Product_Model {
    private int productID;
    private static int productIDCounter = 0;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productCategory;
    private String brand;
    private String variant;
    private String expirationDate;

    /**
     * Class Product parameterized constructor
     * @param productName is the product name.
     * @param productPrice is the product price.
     * @param productQuantity is the product quantity.
     * @param productCategory is the product category.
     * @param brand is product brand.
     * @param variant is the product variant
     * @param expirationDate is the product's expiration date.
     */
    public Product_Model(String productName, double productPrice, int productQuantity, String productCategory, String brand, String variant, String expirationDate)
    {
        this.productID = ++productIDCounter;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.brand = brand;
        this.variant = variant;
        this.expirationDate = expirationDate;
    }

    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public int getProductIDCounter() {
        return productIDCounter;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getVariant() {
        return variant;
    }
    public void setVariant(String variant) {
        this.variant = variant;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
