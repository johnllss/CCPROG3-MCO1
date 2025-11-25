package StoreApp.Controllers;

import StoreApp.Models.Product_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Product_Controller {
    @FXML private ImageView productImage;
    @FXML private Button addToCartBtn;
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private VBox cardRoot;
    @FXML private AnchorPane buttonPane;
    @FXML private HBox badgeContainer;

    private HBox quantityControls;
    private Button confirmAddBtn;
    private int quantity = 1;

    private Product_Model product;
    private AddToCartCallback addToCartCallback;

    /**
     * This method handles the action when the initial add to cart button is clicked. It shows the quantity selector controls.
     * @param e is the action event triggered by the button click.
     */
    @FXML
    private void onAddToCartBtnClicked(ActionEvent e) {
        // show quantity controls instead of the initial add button
        showQuantityControls();
    }

    /**
     * This method creates and displays the quantity selector controls with an "Add to Cart" button.
     */
    private void showQuantityControls() {
        // build quantity controls only once
        if (quantityControls == null) {
            quantityControls = new HBox(5);
            quantityControls.setAlignment(Pos.CENTER);

            Button minusBtn = new Button("-");
            Button plusBtn = new Button("+");
            Label qtyLabel = new Label(String.valueOf(quantity));
            confirmAddBtn = new Button("Add");

            minusBtn.getStyleClass().add("circular-btn");
            plusBtn.getStyleClass().add("circular-btn");
            confirmAddBtn.getStyleClass().add("primary-btn");

            // style the quantity label
            qtyLabel.setStyle("-fx-min-width: 20px; -fx-alignment: center;");

            // decrease quantity (minimum 1)
            minusBtn.setOnAction(ev -> {
                if (quantity > 1) {
                    quantity--;
                    qtyLabel.setText(String.valueOf(quantity));
                }
            });

            // increase quantity (up to available stock)
            plusBtn.setOnAction(ev -> {
                if (quantity < product.getProductQuantity()) {
                    quantity++;
                    qtyLabel.setText(String.valueOf(quantity));
                }
            });

            // add to cart when confirm button is clicked
            confirmAddBtn.setOnAction(ev -> {
                notifyCartUpdate();
                
                // reset UI after adding to cart
                resetAddToCartButton();
            });

            quantityControls.getChildren().addAll(minusBtn, qtyLabel, plusBtn, confirmAddBtn);
        }

        // update the quantity label to current value
        Label qtyLabel = (Label) quantityControls.getChildren().get(1);
        qtyLabel.setText(String.valueOf(quantity));

        // replace the initial add button with quantity controls
        buttonPane.getChildren().remove(addToCartBtn);
        buttonPane.getChildren().add(quantityControls);
    }


    /**
     * This method sets the product and initializes the controller with product details.
     * @param product is the Product_Model to be displayed.
     * @param existingQty is the existing quantity in the cart.
     * @param callback is the callback for handling cart updates.
     */
    public void setProduct(Product_Model product, int existingQty,AddToCartCallback callback)
    {
        this.product = product;
        this.addToCartCallback = callback;

        if(existingQty > 0){
            this.quantity = existingQty;
            showQuantityButtons();
        }
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
     * This method displays the quantity adjustment buttons.
     */
    private void showQuantityButtons() {
        showQuantityControls();
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
     * This method resets the UI back to the initial Add to Cart button after a successful add.
     */
    private void resetAddToCartButton() {
        if (quantityControls != null && buttonPane != null) {
            buttonPane.getChildren().remove(quantityControls);
            buttonPane.getChildren().add(addToCartBtn);
        }
        // Reset quantity to 1 for the next time the customer adds
        quantity = 1;
    }
}