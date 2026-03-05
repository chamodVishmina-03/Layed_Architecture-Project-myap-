package lk.ijse.myap.controller;

import java.net.URL;
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
import lk.ijse.myap.bo.CategoryBO;
import lk.ijse.myap.bo.CategoryBOImpl;
import lk.ijse.myap.dto.CategoryDTO;
import lk.ijse.myap.entity.Category;


public class CategoryController implements Initializable {

   CategoryBO categoryBO=new CategoryBOImpl();


    @FXML
    private TextField idField; //  ctId
    @FXML
    private TextField nameField; // ctname



    // ==============table elements===============
    @FXML
    private TableView<CategoryDTO> tblCategory;
    @FXML
    private TableColumn<CategoryDTO, Integer> colId;
    @FXML
    private TableColumn<CategoryDTO, String> colName;




    
    // data validations
    private final String CATEGORY_ID_REGEX = "^[0-9]+$";
    private final String CATEGORY_NAME_REGEX = "^[A-Za-z ]{3,}$"; 



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Category is loaded");

        
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); 
        colName.setCellValueFactory(new PropertyValueFactory<>("name")); 

        loadCategoryTable();
    }





    // Save button 
    @FXML
    private void handleSaveCategory() {



        String name = nameField.getText().trim();

        if (!name.matches(CATEGORY_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Category name").show();
        } else {
            try {
                   CategoryDTO categoryDTO = new CategoryDTO(name); 
                      boolean result = categoryBO.save(categoryDTO);

                if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "Category saved successfully!").show();
                             cleanFields();
                        loadCategoryTable();
                } else {
                           new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            } catch (Exception e) {
                         e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }




                  // Search
    @FXML
    private void handleSearchCategory(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            
            
            try {
                       String id = idField.getText().trim();

                if (!id.matches(CATEGORY_ID_REGEX)) {
                            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
                              return;
                }

               Category category=categoryBO.search(id);

                if ( category!= null) {
                          nameField.setText(category.getName());
             
                } else {
                    new Alert(Alert.AlertType.ERROR, "Category not found!").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }



            // Update button
    @FXML
    private void handleCategoryUpdate() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();

            if (!id.matches(CATEGORY_ID_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Category ID").show();
               } else if (!name.matches(CATEGORY_NAME_REGEX)) {
                        new Alert(Alert.AlertType.ERROR, "Invalid Category Name").show();
            } else {
                     CategoryDTO categoryDTO = new CategoryDTO(Integer.parseInt(id), name);
                       boolean result = categoryBO.update(categoryDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Category updated successfully!").show();
                    cleanFields();
                    loadCategoryTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Category ID not found or nothing updated!").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }




                // Delete button
    @FXML
    private void handleCategoryDelete() {
        try {
            String id = idField.getText().trim();

            if (!id.matches(CATEGORY_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
            } else {
                boolean result = categoryBO.delete(id);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Category Deleted successfully!").show();
                    cleanFields();
                    loadCategoryTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Category ID not found!").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }




    // reset button
    @FXML
    private void handleReset() {
        cleanFields();
    }




    private void cleanFields() {
        idField.clear();
        nameField.clear();
    }






    private void loadCategoryTable() {
        try {

            List<Category> categoryList = categoryBO.getAll();


            ObservableList<CategoryDTO> obList = FXCollections.observableArrayList();


            for (Category entity : categoryList) {
                CategoryDTO dto = new CategoryDTO(entity.getId(), entity.getName());
                obList.add(dto);
            }


            tblCategory.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading table!").show();
        }
    }
}