package StoreApp;

public class Receipt_Controller {
    private  Receipt_View view;
    private Receipt model;
    public Receipt_Controller(Receipt_View view, Receipt model) {
        this.view = view;
        this.model = model;
    }
    public void updateReceiptView(){
        view.displayReceiptDetails(model.getTransaction(), model.generateReceiptNumber());
    }
}
