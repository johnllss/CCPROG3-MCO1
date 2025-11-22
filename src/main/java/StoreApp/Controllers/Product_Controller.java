package StoreApp.Controllers;

import StoreApp.Models.Product_Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class Product_Controller {
    @FXML private ImageView productImage;
    @FXML private Button addToCartBtn;
    @FXML private Text productName;
    @FXML private Text productPrice;

    public void setProduct(Product_Model product, Consumer<Product_Model> onAddToCart)
    {
        try
        {
            Image image = new Image(getClass().getResourceAsStream("/ConvenienceStore UI copy.png"));
            productImage.setImage(image);
        }
        catch (Exception e)
        {
            System.out.println("Image failed to load.");
        }

        productName.setText(product.getProductName());
        productPrice.setText(String.format("₱%.2f", product.getProductPrice()));

        addToCartBtn.setOnAction(e -> onAddToCart.accept(product));
    }
}
