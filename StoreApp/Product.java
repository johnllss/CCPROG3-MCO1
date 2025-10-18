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
    private String expirationDate;

    /**
     *
     * @param productID
     * @param productName
     * @param productPrice
     * @param productQuantity
     * @param productCategory
     * @param brand
     * @param variant
     * @param expirationDate
     */
    public Product(int productID, String productName, double productPrice, int productQuantity, String productCategory, String brand, String variant, String expirationDate)
    {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.brand = brand;
        this.variant = variant;
        this.expirationDate = expirationDate;
    }

    public boolean isExpired()
    {
        if (isPerishable())
        {
            // TODO: implement date comparison
            // might need Date class if there is one for dates
        }

        return false;
    }

    public boolean isPerishable()
    {
        return !expirationDate.equalsIgnoreCase("N/A");
    }

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
     *
     * @param restockQuantity
     */
    public void updateStock(int restockQuantity)
    {
        this.productQuantity = productQuantity + restockQuantity;
    }

    public boolean isProductLowStock(String productID)
    {
        if (productQuantity < 3)
        {
            return true;
        }

        return false;
    }



    // GETTERS AND SETTERS
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

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity)
    {
        this.productQuantity= productQuantity;
    }

    public void setProductCategory(String productCategory)
    {
        this.productCategory = productCategory;
    }

    public void setbrand(String brand)
    {
        this.brand = brand;
    }

    public void setVariant(String variant)
    {
        this.variant = variant;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }
}
