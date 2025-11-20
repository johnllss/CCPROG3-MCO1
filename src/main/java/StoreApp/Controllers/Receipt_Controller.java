package StoreApp.Controllers;

import StoreApp.Models.Receipt_Model;
import StoreApp.Views.Receipt_View;

public class Receipt_Controller {
    private  Receipt_View view;
    private Receipt_Model model;
    public Receipt_Controller(Receipt_View view, Receipt_Model model) {
        this.view = view;
        this.model = model;
    }
    public void updateReceiptView(){
        view.displayReceiptDetails(model.getTransaction(), model.generateReceiptNumber());
    }
}
