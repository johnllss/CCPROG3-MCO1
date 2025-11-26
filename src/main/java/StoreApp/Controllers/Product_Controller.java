package StoreApp.Controllers;

import StoreApp.Models.Product_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Product_Controller {
    @FXML private ImageView productImage;
    @FXML private Button initialAddBtn;
    @FXML private Button addToCartBtn;
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private VBox cardRoot;
    @FXML private HBox quantityControls;
    @FXML private HBox badgeContainer;
    @FXML private Button decrementBtn;
    @FXML private Button incrementBtn;
    @FXML private Label quantityLabel;

    private int quantity = 1;

    private Product_Model product;
    private AddToCartCallback addToCartCallback;

    /**
     * Initializes the controller and sets up button handlers.
     */
    @FXML
    private void initialize() {
        if (initialAddBtn != null) {
            initialAddBtn.setOnAction(this::onInitialAddBtnClicked);
        }

        if (decrementBtn != null) {
            decrementBtn.setOnAction(e -> decrementQuantity());
        }

        if (incrementBtn != null) {
            incrementBtn.setOnAction(e -> incrementQuantity());
        }

        if (addToCartBtn != null) {
            addToCartBtn.setOnAction(this::onAddToCartBtnClicked);
        }
    }

    /**
     * This method handles the action when the initial add to cart button is clicked. It shows the quantity selector controls.
     * @param e is the action event triggered by the button click.
     */
    private void onInitialAddBtnClicked(ActionEvent e) {
        // show quantity controls instead of the initial add button
        showQuantityControls();
    }

    /**
     * This method handles the action when the confirm add to cart button is clicked.
     * @param e is the action event triggered by the button click.
     */
    @FXML
    private void onAddToCartBtnClicked(ActionEvent e) {
        notifyCartUpdate();
        
        // reset UI after adding to cart
        resetAddToCartButton();
    }

    /**
     * This method creates and displays the quantity selector controls with an "Add to Cart" check mark button.
     */
    private void showQuantityControls() {
        if (quantityControls != null && initialAddBtn != null) {
            // hide the initial add button
            initialAddBtn.setVisible(false);
            initialAddBtn.setManaged(false);
            
            // show the quantity controls
            quantityControls.setVisible(true);
            quantityControls.setManaged(true);
            
            // update the quantity label to current value
            if (quantityLabel != null) {
                quantityLabel.setText("1");
            }
        }
    }


    /**
     * This method sets the product and initializes the controller with product details.
     * @param product is the Product_Model to be displayed.
     * @param existingQty is the existing quantity in the cart.
     * @param callback is the callback for handling cart updates.
     */
    public void setProduct(Product_Model product, int existingQty, AddToCartCallback callback)
    {
        this.product = product;
        this.addToCartCallback = callback;

        // always start with quantity = 1
        this.quantity = 1;

        productName.setText(product.getProductName());
        productPrice.setText(String.format("₱ %.2f", product.getProductPrice()));

        // update UI with product information
        if (product != null)
        {
            productName.setText(product.getProductName());
            productPrice.setText(String.format("₱ %.2f", product.getProductPrice()));

            // load and set product image
            if (product.getImagePath() != null && !product.getImagePath().isEmpty())
            {
                try
                {
                    String imagePath = product.getImagePath();
                    Image image = new Image(getClass().getResourceAsStream(imagePath));
                    productImage.setImage(image);
                }
                catch (Exception e)
                {
                    System.err.println("Failed to load image for " + product.getProductName() + ": " + e.getMessage());
                }
            }

            // display status badges based on product state
            displayStatusBadges();
        }
    }

    /**
     * This method decrements the quantity in the quantity controls by 1 (minimum is 1).
     */
    private void decrementQuantity() {
        if (quantity > 1) {
            quantity--;

            if (quantityLabel != null) {
                quantityLabel.setText(String.valueOf(quantity));
            }
        }
    }

    /**
     * This method increments the quantity in the quantity controls by 1 (up to available stock).
     */
    private void incrementQuantity() {
        if (product != null && quantity < product.getProductQuantity()) {
            quantity++;

            if (quantityLabel != null) {
                quantityLabel.setText(String.valueOf(quantity));
            }
        }
    }

    /**
     * This method notifies the Cart_Controller about quantity updates.
     */
    private void notifyCartUpdate()
    {
        if (addToCartCallback != null && product != null)
        {
            // calls the method assigned to addToCartCallback and sends the product and quantity data
            addToCartCallback.onAddToCart(product, quantity);
        }
    }

    /**
     * This method is delegated the task of checking if product is expired to the product model.
     * @return boolean for success/failure
     */
    public boolean isExpired()
    {
        return product.isExpired();
    }

    /**
     * This method is delegated the task of checking if product is perishable to the product model.
     * @return boolean for success/failure.
     */
    public boolean isPerishable()
    {
        return product.isPerishable();
    }

    /**
     * This method is delegated the task of checking if product is low on stock to the product model.
     * @return boolean if success/failure.
     */
    public boolean isProductLowStock()
    {
        return product.isProductLowStock();
    }

    /**
     * This method creates a status badge label with the specified text and style class to display the status of the Product in the Product_View.
     * @param text is the text to display on the badge.
     * @param styleClass is the CSS style class for the badge.
     * @return Label configured as a status badge.
     */
    private Label createBadge(String text, String styleClass) {
        Label badge = new Label(text);
        badge.getStyleClass().addAll("status-badge", styleClass);

        return badge;
    }

    /**
     * This method displays status badges on the product card based on product state. This only shows LOW STOCK and PERISHABLE badges.
     */
    private void displayStatusBadges() {
        if (badgeContainer == null || product == null) {
            return;
        }

        // clear any existing badges
        badgeContainer.getChildren().clear();

        // show LOW STOCK badge
        if (isProductLowStock()) {
            Label lowStockBadge = createBadge("LOW STOCK", "badge-low-stock");
            badgeContainer.getChildren().add(lowStockBadge);
        }
        // show PERISHABLE badge only if not low stock
        else if (isPerishable()) {
            Label perishableBadge = createBadge("PERISHABLE", "badge-perishable");
            badgeContainer.getChildren().add(perishableBadge);
        }
    }

    /**
     * This method resets the UI back to the initial Add to Cart (+) button after a successful add.
     */
    private void resetAddToCartButton() {
        if (quantityControls != null && initialAddBtn != null) {
            // hide quantity controls
            quantityControls.setVisible(false);
            quantityControls.setManaged(false);
            
            // then... show the initial add button
            initialAddBtn.setVisible(true);
            initialAddBtn.setManaged(true);
        }

        // reset quantity to 1 for the next time the customer adds
        quantity = 1;
    }
}