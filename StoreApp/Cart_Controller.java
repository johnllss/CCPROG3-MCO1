package StoreApp;

import java.util.ArrayList;

public class Cart_Controller {
    private Cart model;
    private Cart_View view;
    public Cart_Controller(Cart model, Cart_View view) {
        this.model = model;
        this.view = view;
    }


    public void updateDisplayView() {
        view.displayCart(model.getItems());
    }


}
