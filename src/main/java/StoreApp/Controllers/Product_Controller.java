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
     * This method is delegated the task of checking if product is expired to the product model.
     * @return boolean for success/failure
     */
    public boolean isExpired()
    {
        return productModel.isExpired();
    }

    /**
     * This method is delegated the task of checking if product is perishable to the product model.
     * @return boolean for success/failure.
     */
    public boolean isPerishable()
    {
        return productModel.isPerishable();
    }

    /**
     * This method is delegated the task of checking if product is low on stock to the product model.
     * @return boolean if success/failure.
     */
    public boolean isProductLowStock()
    {
        return productModel.isProductLowStock();
    }
}


