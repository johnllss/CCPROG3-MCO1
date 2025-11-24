package StoreApp.Controllers;

import StoreApp.Models.Product_Model;

/**
 * This is a callback interface for handling add-to-cart events.
 * This interface allows Product_Controller to notify Shopping_Controller when a product's quantity is updated. Removes dependencies between the two controllers.
 */
public interface AddToCartCallback {
    /**
     * This method is called when a product is added to Cart or its quantity is updated.
     * @param product is the product being added or updated.
     * @param quantity is the new quantity for this product.
     */
    void onAddToCart(Product_Model product, int quantity);
}