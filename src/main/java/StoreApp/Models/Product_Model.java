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
    private String imagePath;

    /**
     * Class Product parameterized constructor
     * @param productName is the product name.
     * @param productPrice is the product price.
     * @param productQuantity is the product quantity.
     * @param productCategory is the product category.
     * @param brand is product brand.
     * @param variant is the product variant
     * @param expirationDate is the product's expiration date.
     * @param imagePath is the path to the product's image file.
     */
    public Product_Model(String productName, double productPrice, int productQuantity, String productCategory, String brand, String variant, String expirationDate, String imagePath)
    {
        this.productID = ++productIDCounter;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.brand = brand;
        this.variant = variant;
        this.expirationDate = expirationDate;
        this.imagePath = imagePath;
    }

    /**
     * Class Product_Model parameterized constructor
     * @param name is the product name.
     * @param price is the product price.
     * @param qty is the product quantity.
     * @param category is the product category.
     * @param brand is the product brand.
     */
    public Product_Model(String name, double price, int qty, String category, String brand) {
        this.productID = ++productIDCounter;
        this.productName = name;
        this.productPrice = price;
        this.productQuantity = qty;
        this.productCategory = category;
        this.brand = brand;
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

    /**
     * This method checks if the requested quantity is available in stock.
     * @param quantity is the quantity to check against stock.
     * @return boolean for success/failure.
     */
    public boolean ProductQuantity(int quantity){
        if (quantity > productQuantity)
        {
            return false;
        }
        else
        {
            return true;
        }
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
    public String getProductBrand() {
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
     * This is a getter method to get the expirationDate attribute.
     * @param expirationDate is the new expiration date.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * This is a getter method to get the imagePath attribute.
     * @return String for imagePath.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * This method sets the product's imagePath.
     * @param imagePath is the new imagePath.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
