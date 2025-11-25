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

    private HBox QuantityBtn;
    private int quantity = 1;

    private Product_Model product;
    private AddToCartCallback addToCartCallback;

    /**
     * This method handles the action when the add to cart button is clicked.
     * @param e is the action event triggered by the button click.
     */
    @FXML
    private void onAddToCartBtnClicked(ActionEvent e) {

        // Build QuantityBtn only once
        if (QuantityBtn == null) {
            QuantityBtn = new HBox(10);
            QuantityBtn.setAlignment(Pos.CENTER_LEFT);

            Button minusBtn = new Button("-");
            Button plusBtn = new Button("+");
            Label qtyLabel = new Label(String.valueOf(quantity));

            minusBtn.getStyleClass().add("circular-btn");
            plusBtn.getStyleClass().add("circular-btn");

            minusBtn.setOnAction(ev -> {
                if (quantity > 1) {
                    quantity--;
                    qtyLabel.setText(String.valueOf(quantity));
                    notifyCartUpdate();
                }
            });

            plusBtn.setOnAction(ev -> {
                if (quantity < product.getProductQuantity()) {
                    quantity++;
                    qtyLabel.setText(String.valueOf(quantity));
                    notifyCartUpdate();
                }
            });

            QuantityBtn.getChildren().addAll(minusBtn, qtyLabel, plusBtn);
        }


        Label qtyLabel = (Label) QuantityBtn.getChildren().get(1);
        qtyLabel.setText(String.valueOf(quantity));


        buttonPane.getChildren().remove(addToCartBtn);
        buttonPane.getChildren().add(QuantityBtn);

        notifyCartUpdate();

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
        }
    }
    /**
     * This method displays the quantity adjustment buttons.
     */
    private void showQuantityButtons() {
        if (QuantityBtn != null) {
            buttonPane.getChildren().remove(addToCartBtn);
            buttonPane.getChildren().add(QuantityBtn);
            ((Label) QuantityBtn.getChildren().get(1)).setText(String.valueOf(quantity));
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
}