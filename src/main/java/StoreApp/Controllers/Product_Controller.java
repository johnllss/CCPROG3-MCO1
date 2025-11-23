package StoreApp.Controllers;

import StoreApp.Models.Product_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Product_Controller {

    @FXML
    private ImageView productImage;
    @FXML private Button addToCartBtn;
    @FXML private Text productName;
    @FXML private Text productPrice;
    @FXML private VBox cardRoot;
    @FXML private AnchorPane buttonPane;

    private HBox QuantityBtn;
    private int quantity = 1;

    private Product_Model productModel;

    @FXML
    private void onAddToCartBtnClicked(ActionEvent e){
        if(QuantityBtn == null){
            QuantityBtn = new HBox(10);
            QuantityBtn.setAlignment(Pos.CENTER_LEFT);
            QuantityBtn.setMaxWidth(Double.MAX_VALUE);
            Button Minus = new Button("-");
            Button Plus = new Button("+");
            Label quantityLabel = new Label(String.valueOf(quantity));

            Minus.getStyleClass().add("circular-btn");
            Plus.getStyleClass().add("circular-btn");
            Minus.setOnAction(e1 -> {
                if(quantity > 1){
                    quantity--;
                    quantityLabel.setText(String.valueOf(quantity));
                }
            });
            Plus.setOnAction(e1 -> {
                if(quantity != 0) {
                    quantity++;
                    quantityLabel.setText(String.valueOf(quantity));
                }
            });

            QuantityBtn.getChildren().addAll(Minus, quantityLabel, Plus);

        }
        buttonPane.getChildren().remove(addToCartBtn);
        buttonPane.getChildren().add(QuantityBtn);
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
        return !productModel.getExpirationDate().equalsIgnoreCase("N/A");
    }

    /**
     * This method checks if the product is low on stock.
     * @return boolean if success/failure.
     */
    public boolean isProductLowStock()
    {
        if (productModel.getProductQuantity() < 3)
        {
            return true;
        }

        return false;
    }
}


