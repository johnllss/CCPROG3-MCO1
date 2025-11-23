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

    /**
     * This method reduces the stock of this Product.
     * @param amount is the amount to reduce the stock of the Product.
     * @return boolean for success/failure.
     */
    public boolean reduceStock(int amount)
    {
        if (amount <= productQuantity)
        {
            productQuantity -= amount;
            return true;
        }

        return false;
    }

    /**
     * This method updates the stock of this Product.
     * @param restockQuantity is the amount of stock to update the stock of this Product.
     *
     */
    public void updateStock(int restockQuantity)
    {
        this.productQuantity = productQuantity + restockQuantity;
    }

    /**
     * This is a getter method to get the productID attribute.
     * @return int for productID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This method sets the product's productID.
     * @param productID is the new productID.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * This methods returns the product ID of the Product_Model
     * @return int is the ID number of the Product_Model.
     */
    public int getProductIDCounter() {
        return productIDCounter;
    }

    /**
     * This is a getter method to get the productName attribute.
     * @return String for productName.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method sets the product's productName.
     * @param productName is the new productName.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This is a getter method to get the productPrice attribute.
     * @return double for productPrice.
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * This method sets the product's productPrice.
     * @param productPrice is the new productPrice.
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * This is a getter method to get the productQuantity attribute.
     * @return int for productQuantity.
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * This method sets the product's productQuantity.
     * @param productQuantity is the new productQuantity.
     *
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * This is a getter method to get the productCategory attribute.
     * @return String for productCategory.
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * This method sets the product's productCategory.
     * @param productCategory is the new productCategory.
     */
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    /**
     * This is a getter method to get the brand attribute.
     * @return String for brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method sets the product's brand.
     * @param brand is the new brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * This is a getter method to get the variant attribute.
     * @return String for variant.
     */
    public String getVariant() {
        return variant;
    }

    /**
     * This method sets the product's variant.
     * @param variant is the new variant.
     */
    public void setVariant(String variant) {
        this.variant = variant;
    }

    /**
     * This is a getter method to get the expirationDate attribute.
     * @return String for expirationDate.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * This method sets the product's expirationDate.
     * @param expirationDate is the new expirationDate.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
