package StoreApp.Views;

import StoreApp.Models.Product_Model;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class Product_View extends AnchorPane {

    private ImageView productImage;
    private Button addToCartBtn;
    private Text productName;
    private Text productPrice;

    public Product_View(Product_Model product, Consumer<Product_Model> onAddToCart) 
    {
        setupUI();
        setProduct(product, onAddToCart);
    }

    private void setupUI() 
    {
        setPrefHeight(130);
        setPrefWidth(140);
        setStyle("-fx-background-color: #ffff; -fx-background-radius: 8; -fx-border-radius: 8;");
        setPadding(new Insets(10, 10, 10, 10));

        productImage = new ImageView();
        productImage.setFitHeight(80);
        productImage.setFitWidth(120);
        productImage.setLayoutX(20);
        productImage.setLayoutY(-10);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);

        addToCartBtn = new Button("+");
        addToCartBtn.setLayoutX(100);
        addToCartBtn.setLayoutY(85);
        addToCartBtn.setPrefHeight(26);
        addToCartBtn.setPrefWidth(29);
        addToCartBtn.setStyle("-fx-border-radius:50%; -fx-background-radius:50%; -fx-background-color:#006937;");
        addToCartBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        addToCartBtn.setFont(Font.font("Helvetica Neue Bold", 13));

        productName = new Text();
        productName.setLayoutX(8);
        productName.setLayoutY(93);
        productName.setFont(Font.font("Helvetica Neue Bold", 13));

        productPrice = new Text();
        productPrice.setLayoutX(8);
        productPrice.setLayoutY(110);
        productPrice.setFont(Font.font("Helvetica Neue Italic", 10));

        getChildren().addAll(productImage, addToCartBtn, productName, productPrice);
    }

    private void setProduct(Product_Model product, Consumer<Product_Model> onAddToCart) 
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
