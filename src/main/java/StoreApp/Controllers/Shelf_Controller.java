package StoreApp.Controllers;

import StoreApp.Views.Shelf_View;

public class Shelf_Controller {
    private Shelf model;
    private Shelf_View view;
    public Shelf_Controller(Shelf model, Shelf_View view) {
        this.model = model;
        this.view = view;
    }

    public void addProduct(Product product){
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
