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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.myap.bo.ItemBO;
import lk.ijse.myap.bo.ItemBOImpl;
import lk.ijse.myap.dto.ItemDTO;
import lk.ijse.myap.entity.Item;


public class ItemController implements Initializable {

    @FXML
    private TextField idField; 
    @FXML
    private TextField nameField; 
    @FXML
    private TextField qtyField; 
    @FXML
    private TextField unitPriceField; 
    @FXML
    private TextField categoryIdField; 
    
    //     ================Table ========================
    @FXML
    private TableView<ItemDTO> tblItem;
    @FXML
    private TableColumn<ItemDTO, String> colId;
    @FXML
    private TableColumn<ItemDTO, String> colName;
    @FXML
    private TableColumn<ItemDTO, Integer> colQty;
    @FXML
    private TableColumn<ItemDTO, Double> colUnitPrice;
    @FXML
    private TableColumn<ItemDTO, Integer> colCategoryId;

    
    // Data validation 
    private final String ITEM_ID_REGEX = "^[A-Z]{1}[0-9]{3}$"; 
    private final String ITEM_NAME_REGEX = "^[A-Za-z0-9 ]{3,}$";
    private final String ITEM_QTY_REGEX = "^[0-9]+$"; 
    private final String ITEM_UNIT_PRICE_REGEX = "^[0-9]{1,5}(?:\\.[0-9]{1,2})?$"; 
    private final String CATEGORY_ID_REGEX = "^[0-9]+$";
    

    private final int LOW_STOCK_LIMIT = 5; 


    ItemBO itemBO = new ItemBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Item is loaded");

        
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); 
        colName.setCellValueFactory(new PropertyValueFactory<>("name")); 
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

        loadItemTable();
    }

    
    
    // Save button logic
    @FXML
    private void handleSaveItem() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String qtyText = qtyField.getText().trim();
        String unitPriceText = unitPriceField.getText().trim();
        String categoryIdText = categoryIdField.getText().trim();

        if (!id.matches(ITEM_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Item ID ").show();
        } else if (!name.matches(ITEM_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Item Name").show();
        } else if (!qtyText.matches(ITEM_QTY_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity ").show();
        } else if (!unitPriceText.matches(ITEM_UNIT_PRICE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Unit Price Format ").show();
        } else if (!categoryIdText.matches(CATEGORY_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Category ID ").show();
        } else {
            
            try {
                
                int qty = Integer.parseInt(qtyText);
                    double unitPrice = Double.parseDouble(unitPriceText);
                          int categoryId = Integer.parseInt(categoryIdText);
                
                   Item item = new Item(id, name, qty, unitPrice, categoryId);
                   boolean result = itemBO.saveItem(item);

                if (result) {
                       new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!").show();
                     cleanFields();
                    loadItemTable();
                    
                    
                   ///////////////////////////////////////// // low Stock ek  mek podi awl thinw==========//////////////////////////////////////
                    if (qty < LOW_STOCK_LIMIT) {
                        new Alert(Alert.AlertType.WARNING, "Low Stock : " + name + " quantity is " + qty + ". Please reorder.").show();
                           }
                    
                } else {
                      new Alert(Alert.AlertType.ERROR, "Item ID already exists or something went wrong!").show();
                    }
                
            } catch (Exception e) {
                 e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            }
        }
    }

    
    
    
    // Search logic
    @FXML
    private void handleSearchItem(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                String id = idField.getText().trim();

                if (!id.matches(ITEM_ID_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid ID Format!").show();
                    return;
                }

                Item item = itemBO.searchItem(id);

                if (item != null) {
                    nameField.setText(item.getName());
                    qtyField.setText(String.valueOf(item.getQty()));
                    unitPriceField.setText(String.format("%.2f", item.getUnitPrice()));
                    categoryIdField.setText(String.valueOf(item.getCategoryId()));
                    
                    // Low Stock 
                    if (item.getQty() <= LOW_STOCK_LIMIT) {
                           new Alert(Alert.AlertType.WARNING, "LOW STOCK: " + item.getName() + " quantity is " + item.getQty() + ". Please reorder.").show();
                    }
                    
                } else {
                    new Alert(Alert.AlertType.ERROR, "Item not found!").show();
                       cleanFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    



    
    
    // Update button logic (Modified with Low Stock Check)
    @FXML
    private void handleItemUpdate() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String qtyText = qtyField.getText().trim();
            String unitPriceText = unitPriceField.getText().trim();
            String categoryIdText = categoryIdField.getText().trim();

            if (!id.matches(ITEM_ID_REGEX)) {
                      new Alert(Alert.AlertType.ERROR, "Invalid Item ID").show();
            } else if (!name.matches(ITEM_NAME_REGEX)) {
                  new Alert(Alert.AlertType.ERROR, "Invalid Item Name").show();
            } else if (!qtyText.matches(ITEM_QTY_REGEX)) {
                     new Alert(Alert.AlertType.ERROR, "Invalid Quantity").show();
            } else if (!unitPriceText.matches(ITEM_UNIT_PRICE_REGEX)) {
                
                 new Alert(Alert.AlertType.ERROR, "Invalid Unit Price Format").show();
            } else if (!categoryIdText.matches(CATEGORY_ID_REGEX)) {
                
                 new Alert(Alert.AlertType.ERROR, "Invalid Category ID").show();
            } else {
                
                int qty = Integer.parseInt(qtyText);
                double unitPrice = Double.parseDouble(unitPriceText);
                int categoryId = Integer.parseInt(categoryIdText);
                
                Item item = new Item(id, name, qty, unitPrice, categoryId);
                boolean result = itemBO.updateItem(item);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();
                    cleanFields();
                    loadItemTable();
                    
                    
                    
                 /////////////////////////////////////////////////////////////////////////////
                                 //  quantity ek low unama update krnn kiyn mssg ek //
                    if (qty <= LOW_STOCK_LIMIT) {
                        new Alert(Alert.AlertType.WARNING, "LOW STOCK: " + name + " quantity is " + qty + ". Please reorder.").show();
                    }
                    /////////////////////////////////////////////////////////////////////////////
                   
                    
                    
                    
                } else {
                    new Alert(Alert.AlertType.ERROR, "Item ID not found or nothing updated!").show();
                }
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
               new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    
    
    

    // Delete button
    @FXML
    private void handleItemDelete() {
        
        
        try {
            
            
            String id = idField.getText().trim();

            if (!id.matches(ITEM_ID_REGEX)) {
                     new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
            } else {
                
                boolean result = itemBO.deleteItem(id);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Deleted successfully!").show();
                    cleanFields();
                    loadItemTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Item ID not found!").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    
    
    
         // Reset button
    @FXML
    private void handleReset() {
        cleanFields();
    }

    
    
    
    
    
    // Utility methods 
    private void cleanFields() {
        idField.clear();
        nameField.clear();
        qtyField.clear();
        unitPriceField.clear();
        categoryIdField.clear();
    }
    
    
    
    


    private void loadItemTable() {
        try {
            List<Item> itemList = itemBO.getAllItems();

            ObservableList<ItemDTO> obList = FXCollections.observableArrayList();

            for (Item item : itemList) {
                obList.add(new ItemDTO(
                   item.getId(),
                   item.getName(),
                   item.getQty(),
                   item.getUnitPrice(),
                   item.getCategoryId()
                ));

            }
            
            tblItem.setItems(obList);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading table!").show();
        }
    }
    
    @FXML
     void handlePrintItem(ActionEvent event){
        
        try {
           itemBO.printItemReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}