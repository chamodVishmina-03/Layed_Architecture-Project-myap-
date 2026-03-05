package lk.ijse.myap.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.myap.bo.CustomerBO;
import lk.ijse.myap.bo.CustomerBOImpl;
import lk.ijse.myap.dto.CustomerDTO;
import lk.ijse.myap.entity.Customer;


public class CustomerController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea addressField;
    @FXML
    private TextField telField;
    
    
    
    //Table id
    @FXML
    private TableView<CustomerDTO> tblCustomer;
    @FXML
    private TableColumn<CustomerDTO,Integer>  colId;
    @FXML
     private TableColumn<CustomerDTO,String> colName;
    @FXML
      private TableColumn<CustomerDTO,String>  colAddress;
    @FXML
       private TableColumn<CustomerDTO,String>  colTele;
    

    private final String CUSTOMER_ID_REGEX = "^[0-9]+$";
    private final String CUSTOMER_NAME_REGEX = "^[A-Za-z ]{3,}$";
    private final String CUSTOMER_ADDRESS_REGEX = "^[A-Za-z0-9 ]{5,}$";
    private final String CUSTOMER_TELE_REGEX = "^[0-9]{9,}$";
    


    CustomerBO customerBO = new CustomerBOImpl();

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Customer is loaded");
        
        // dto and column mapping table
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
         colTele.setCellValueFactory(new PropertyValueFactory<>("tele"));
         
         loadCustomerTable(); // detalis load to table
    }

    
    
    
    //save button
    @FXML
    private void handleSaveCustomer() {

        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String tele = telField.getText().trim();

        if (!name.matches(CUSTOMER_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer name").show();
        
        }else  if (!address.matches(CUSTOMER_ADDRESS_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer address").show();
         
        }else  if (!tele.matches(CUSTOMER_TELE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer tele").show();
           
        }else{

        try {
         
            
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          CustomerDTO customerDTO=new CustomerDTO(name, address, tele);
          
         boolean result= customerBO.saveCustomer(customerDTO);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully!").show();
                cleanFields();
                loadCustomerTable();
                } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
              }

            } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
         }
      }
    }

    
  
    
 @FXML
private void handleSearchCustomer(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
        try {
            String id = idField.getText();

            if (!id.matches(CUSTOMER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
                return;
            }
            

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Customer customer = customerBO.searchCustomer(id);

            if (customer != null) {
                nameField.setText(customer.getName());
                addressField.setText(customer.getAddress());
                telField.setText(customer.getTele());
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
}


    
    
    
    
    
    //Update button 
  @FXML
private void handleCustomerUpdate() {
    
    try {

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String tele = telField.getText().trim();

        if (!id.matches(CUSTOMER_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID").show();
        } else if (!name.matches(CUSTOMER_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Name").show();
        } else if (!address.matches(CUSTOMER_ADDRESS_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Address").show();
        } else if (!tele.matches(CUSTOMER_TELE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Telephone").show();
        } else {

          ///////////////////////////////////////////////////////////////////////////////////////////////////
           CustomerDTO customerDTO=new CustomerDTO(Integer.parseInt(id), name, address, tele);
           boolean result=customerBO.updateCustomer(customerDTO);

            if (result ) {
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
                cleanFields();
                loadCustomerTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer ID not found!").show();
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
    }
}




// DElete button
@FXML
private void handleCustomerDelete(){
    
    try {
        
                   String id = idField.getText();

                if (!id.matches(CUSTOMER_ID_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
                   
                }else{
                    
                
                  ////////////////////////////////////////////////////////////////////////////////////////////////////////  
                     boolean result=customerBO.deleteCustomer(id);
                     
                    
                       if (result ) {
                             new Alert(Alert.AlertType.INFORMATION, "Customer Deleted successfully!").show();
                              cleanFields();
                              loadCustomerTable();
                         } else {
                           new Alert(Alert.AlertType.ERROR, "Customer ID not found!").show();
                              }
                      
                
                }
        
        
      } catch (Exception e) {
          e.printStackTrace();
             new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
      }
}
    
    //reset button
    @FXML
    private void handleReset() {
        cleanFields();
    }

    
    
    private void cleanFields() {
        idField.clear();
        nameField.clear();
        addressField.clear();
        telField.clear();
    }

    private void loadCustomerTable() {
        try {
            // 1. Get List<Customer> (Entity) from your BO
            List<Customer> customerList = customerBO.getCustomer();

            // 2. Create an ObservableList of CustomerDTO
            ObservableList<CustomerDTO> dtoList = FXCollections.observableArrayList();

            // 3. Map Entity -> DTO
            for (Customer entity : customerList) {
                dtoList.add(new CustomerDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getAddress(),
                        entity.getTele()
                ));
            }

            // 4. Set the list to the table
            tblCustomer.setItems(dtoList);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading table!").show();
        }
    }
    
    // report print button
    
    @FXML
    void handlePrint(ActionEvent event){
        
        try {
            customerBO.printCustomerReport();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }
    
}
