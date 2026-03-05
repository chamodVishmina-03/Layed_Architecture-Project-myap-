package lk.ijse.myap.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.myap.bo.SupplierBO;
import lk.ijse.myap.bo.SupplierBOImpl;
import lk.ijse.myap.dto.SupplierDTO;
import lk.ijse.myap.entity.Supplier;

public class SupplierController implements Initializable {

    @FXML 
    private TextField idField; 
    @FXML 
    private TextField nameField; 
    @FXML 
    private TextField teleField; 
    @FXML 
    private TextField ctIdField; 
    
    @FXML 
    private TableView<SupplierDTO> tblSupplier;
    @FXML 
    private TableColumn<SupplierDTO, Integer> colId;
    @FXML 
    private TableColumn<SupplierDTO, String> colName;
    @FXML 
    private TableColumn<SupplierDTO, String> colTele;
    @FXML 
    private TableColumn<SupplierDTO, Integer> colCtId;

    
    // Validation
    private final String SUPPLIER_ID_REGEX = "^[0-9]+$";
    private final String SUPPLIER_NAME_REGEX = "^[A-Za-z ]{3,}$";
    private final String SUPPLIER_TELE_REGEX = "^[0-9]{10}$";
    

    SupplierBO supplierBO= new SupplierBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); 
        colName.setCellValueFactory(new PropertyValueFactory<>("name")); 
        colTele.setCellValueFactory(new PropertyValueFactory<>("tele"));
        colCtId.setCellValueFactory(new PropertyValueFactory<>("ctId"));
        
        loadSupplierTable();
    }

   
    
    
    private void loadSupplierTable() {
        try {
            List<Supplier> supplierList = supplierBO.getAllSuppliers();
               ObservableList<SupplierDTO> obList = FXCollections.observableArrayList();
               for (Supplier supplier : supplierList) {
                   obList.add(new SupplierDTO(
                           supplier.getId(),
                           supplier.getName(),
                           supplier.getTele(),
                           supplier.getCtId()
                   ));
               }
            tblSupplier.setItems(obList);
            
            
        } catch (SQLException e) {
            
            new Alert(Alert.AlertType.ERROR, "Error loading supplier table!").show();
            
        }
    }
    
    
    
    //SAVE
    @FXML
    private void handleSaveSupplier() {
        String idText = idField.getText().trim();
        String name = nameField.getText().trim();
        String tele = teleField.getText().trim();
        String ctIdText = ctIdField.getText().trim();

        if (!idText.matches(SUPPLIER_ID_REGEX)) {
            
                     new Alert(Alert.AlertType.ERROR, "Invalid ID format!").show();
                     
        } else if (!name.matches(SUPPLIER_NAME_REGEX)) {
            
               new Alert(Alert.AlertType.ERROR, "Invalid Supplier Name!").show();
               
        } else if (!tele.matches(SUPPLIER_TELE_REGEX)) {
            
               new Alert(Alert.AlertType.ERROR, "Invalid Telephone Number!").show();
               
        } else if (!ctIdText.matches(SUPPLIER_ID_REGEX)) { 
            
                new Alert(Alert.AlertType.ERROR, "Invalid Contact Type ID!").show();
                
        } else {
            
            
            try {
                
                
                
                int id = Integer.parseInt(idText);
                int ctId = Integer.parseInt(ctIdText);
                
                    Supplier supplier = new Supplier(id, name, tele, ctId);
                       boolean result = supplierBO.saveSupplier(supplier);

                if (result) {
                    
                    
                    new Alert(Alert.AlertType.INFORMATION, "Supplier saved successfully!").show();
                    cleanFields();
                    loadSupplierTable();
                    
                } else {
                    
                    new Alert(Alert.AlertType.ERROR, "Supplier ID already exists or save failed!").show();
                }
            } catch (SQLException e) {
                 new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            }
        }
    }

  
    
    
    
    @FXML
    private void handleSearchSupplier(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                String idText = idField.getText().trim();

                if (!idText.matches(SUPPLIER_ID_REGEX)) {
                        new Alert(Alert.AlertType.ERROR, "Invalid ID Format!").show();
                       return;
                }
                
                int id = Integer.parseInt(idText);
                       Supplier supplier = supplierBO.searchSupplier(id);

                if (supplier != null) {
                    
                     nameField.setText(supplier.getName());
                      teleField.setText(supplier.getTele());
                     ctIdField.setText(String.valueOf(supplier.getCtId()));
                     
                } else {
                    
                    new Alert(Alert.AlertType.ERROR, "Supplier not found!").show();
                    cleanFieldsExceptId();
                }
                
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            }
        }
    }

    
    
    
    
    // update Button
    @FXML
    private void handleSupplierUpdate() {
        
        String idText = idField.getText().trim();
        String name = nameField.getText().trim();
        String tele = teleField.getText().trim();
        String ctIdText = ctIdField.getText().trim();
        
        
        try {
            
            
            int id = Integer.parseInt(idText);
            int ctId = Integer.parseInt(ctIdText);
            
            Supplier supplier = new Supplier(id, name, tele, ctId);
            boolean result = supplierBO.updateSupplier(supplier);

            if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier updated successfully!").show();
                   cleanFields();
                loadSupplierTable();
            } else {
                
                new Alert(Alert.AlertType.ERROR, "Supplier ID not found or nothing updated!").show();
            }
            
        } catch (SQLException e) {
             new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
     
    }

    
    
    
    
      //Delete Button
    
    @FXML
    private void handleSupplierDelete() {
        
        try {
            
            
            String idText = idField.getText().trim();

            if (!idText.matches(SUPPLIER_ID_REGEX)) {
                
                new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
                
            } else {
                
                
                int id = Integer.parseInt(idText);
                boolean result = supplierBO.deleteSupplier(id);

                if (result) {
                    
                     new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted successfully!").show();
                    cleanFields();
                    loadSupplierTable();
                } else {
                    
                    new Alert(Alert.AlertType.ERROR, "Supplier ID not found!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
    }
    
    
    
    
    
    // rest
    @FXML
    private void handleReset() {
        cleanFields();
    }

    
    // clear all fields
    private void cleanFields() {
        idField.clear();
        nameField.clear();
        teleField.clear();
        ctIdField.clear();
    }
    
    
    
// clear all field without id field
    private void cleanFieldsExceptId(){ 
        nameField.clear();
        teleField.clear();
        ctIdField.clear();
    }
}