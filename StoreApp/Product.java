package StoreApp;

public class Product {
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
     * @param productName
     * @param productPrice
     * @param productQuantity
     * @param productCategory
     * @param brand
     * @param variant
     * @param expirationDate
     */
    public Product(String productName, double productPrice, int productQuantity, String productCategory, String brand, String variant, String expirationDate)
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
     * This method checks if the product is expired or not.
     * @return boolean for success/failure
     */
    public boolean isExpired()
    {
        if (isPerishable())
        {
            // TODO: implement date comparison
            // might need Date class if there is one for dates
        }

        return false;
    }

    /**
     * This method checks if the product is perishable or not.
     * @return boolean for success/failure.
     */
    public boolean isPerishable()
    {
        return !expirationDate.equalsIgnoreCase("N/A");
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
     * @return void
     */
    public void updateStock(int restockQuantity)
    {
        this.productQuantity = productQuantity + restockQuantity;
    }

    /**
     * This method checks if the product is low on stock.
     * @return boolean if success/failure.
     */
    public boolean isProductLowStock()
    {
        if (productQuantity < 3)
        {
            return true;
        }

        return false;
    }



    // GETTERS AND SETTERS
    /**
     * This is a getter method to get the productID attribute.
     * @return int for productID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This is a getter method to get the productName attribute.
     * @return String for productName.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This is a getter method to get the productPrice attribute.
     * @return double for productPrice.
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * This is a getter method to get the productQuantity attribute.
     * @return int for productQuantity.
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * This is a getter method to get the productCategory attribute.
     * @return String for productCategory.
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * This is a getter method to get the brand attribute.
     * @return String for brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This is a getter method to get the variant attribute.
     * @return String for variant.
     */
    public String getVariant() {
        return variant;
    }

    /**
     * This is a getter method to get the expirationDate attribute.
     * @return String for expirationDate.
     */
    public String getExpirationDate()
    {
        return expirationDate;
    }

    /**
     * This method sets the product's productID.
     * @param productID is the new productID.
     * @return void
     */
    public void setProductID(int productID)
    {
        this.productID = productID;
    }

    /**
     * This method sets the product's productName.
     * @param productName is the new productName.
     * @return void
     */
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    /**
     * This method sets the product's productPrice.
     * @param productPrice is the new productPrice.
     * @return void
     */
    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    /**
     * This method sets the product's productQuantity.
     * @param productQuantity is the new productQuantity.
     * @return void
     */
    public void setProductQuantity(int productQuantity)
    {
        this.productQuantity= productQuantity;
    }

    /**
     * This method sets the product's productCategory.
     * @param productCategory is the new productCategory.
     * @return void
     */
    public void setProductCategory(String productCategory)
    {
        this.productCategory = productCategory;
    }

    /**
     * This method sets the product's brand.
     * @param brand is the new brand.
     * @return void
     */
    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    /**
     * This method sets the product's variant.
     * @param variant is the new variant.
     * @return void
     */
    public void setVariant(String variant)
    {
        this.variant = variant;
    }

    /**
     * This method sets the product's expirationDate.
     * @param expirationDate is the new expirationDate.
     * @return void
     */
    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }
}
