package StoreApp.Controllers;

import StoreApp.Models.Product_Model;
import StoreApp.Models.Shelf_Model;
import StoreApp.Views.Shelf_View;

public class Shelf_Controller {
    private Shelf_Model model;
    private Shelf_View view;
    public Shelf_Controller(Shelf_Model model, Shelf_View view) {
        this.model = model;
        this.view = view;
    }

    public void addProduct(Product_Model product){
        if(model.isShelfFull())
        {
            view.displayFullShelf();
        }
        if(!product.getProductCategory().equals(model.getShelfCategory()))
        {
            view.displayProductCategoryMismatch();
        }
        else
        {
            view.displaySuccessAdding();
        }
    }

    public void updateDisplayView()
    {
        view.displayShelfCustomerView(model.getShelfCategory(), model.getProductsOnShelf());
    }


}
