
package lk.ijse.myap.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.myap.App;

public class LayoutController implements Initializable {
    
@FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Layout FXML is loaded....");
    }    
    @FXML
    private void clickCustomerNav()throws  IOException{
        Parent customerFXML=App.loadFXML("Customer");
        mainContent.getChildren().setAll(customerFXML);
    }
    @FXML
    private void clickItemNav()throws  IOException{
        Parent customerFXML=App.loadFXML("Item");
        mainContent.getChildren().setAll(customerFXML);
    }
    @FXML
    private void clickOrdersNav()throws  IOException{
        Parent customerFXML=App.loadFXML("Orders");
        mainContent.getChildren().setAll(customerFXML);
    }
    @FXML
    private void clickEmployeeNav()throws  IOException{
        Parent customerFXML=App.loadFXML("Employee");
        mainContent.getChildren().setAll(customerFXML);
    }
    @FXML
    private void clickSupplierNav()throws  IOException{
        Parent customerFXML=App.loadFXML("Supplier");
        mainContent.getChildren().setAll(customerFXML);
    }
    @FXML
    private void clickPaymentNav()throws  IOException{
        Parent customerFXML=App.loadFXML("Payment");
        mainContent.getChildren().setAll(customerFXML);
    }
    
    @FXML
    private void clickCategoryNav()throws  IOException{
        Parent customerFXML=App.loadFXML("Category");
        mainContent.getChildren().setAll(customerFXML);
    }
   

    
}
