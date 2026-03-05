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
import lk.ijse.myap.bo.EmployeeBO;
import lk.ijse.myap.bo.EmployeeBOImpl;
import lk.ijse.myap.dto.CustomerDTO;
import lk.ijse.myap.dto.EmployeeDTO;
import lk.ijse.myap.entity.Customer;
import lk.ijse.myap.entity.Employee;


public class EmployeeController implements Initializable {

    @FXML
    private TextField idField; // emId
    @FXML
    private TextField nameField; // emName
    @FXML
    private TextField roleField; // role
    @FXML
    private TextField salaryField; // salary
    @FXML
    private TextField teleField; // tele
    
    
    
    //  ======================Table=========================== 
    @FXML
    private TableView<EmployeeDTO> tblEmployee;
    @FXML
    private TableColumn<EmployeeDTO, String> colId;
    @FXML
    private TableColumn<EmployeeDTO, String> colName;
    @FXML
    private TableColumn<EmployeeDTO, String> colRole;
    @FXML
    private TableColumn<EmployeeDTO, Double> colSalary;
    @FXML
    private TableColumn<EmployeeDTO, String> colTele;

  
    //   Data validation
    private final String EMPLOYEE_ID_REGEX = "^[A-Z]{1}[0-9]{3}$"; 
    private final String EMPLOYEE_NAME_REGEX = "^[A-Za-z ]{3,}$";
    private final String EMPLOYEE_ROLE_REGEX = "^[A-Za-z ]{3,}$";
    private final String EMPLOYEE_SALARY_REGEX = "^[0-9]{1,6}(?:\\.[0-9]{1,2})?$"; 
    private final String EMPLOYEE_TELE_REGEX = "^[0-9]{9,10}$"; 


    EmployeeBO employeeBO = new EmployeeBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            System.out.println("Employee is loaded");

           
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); 
        colName.setCellValueFactory(new PropertyValueFactory<>("name")); 
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colTele.setCellValueFactory(new PropertyValueFactory<>("tele"));

        loadEmployeeTable();
    }
    
   

           // Save button 
    @FXML
    private void handleSaveEmployee() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String role = roleField.getText().trim();
        String salaryText = salaryField.getText().trim();
        String tele = teleField.getText().trim();

        if (!id.matches(EMPLOYEE_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID ").show();
         } else if (!name.matches(EMPLOYEE_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Employee Name").show();
        } else if (!role.matches(EMPLOYEE_ROLE_REGEX)) {
                 new Alert(Alert.AlertType.ERROR, "Invalid Employee Role").show();
        } else if (!salaryText.matches(EMPLOYEE_SALARY_REGEX)) { 
                    new Alert(Alert.AlertType.ERROR, "Invalid Salary Format ").show();
        } else if (!tele.matches(EMPLOYEE_TELE_REGEX)) {
                  new Alert(Alert.AlertType.ERROR, "Invalid Telephone Number ").show();
        } else {
            
            
            try {
                
                
                double salary = Double.parseDouble(salaryText);
                   Employee employee = new Employee(id, name, role, salary, tele);

                    boolean result = employeeBO.saveEmployee(employee);

                if (result) {
                       new Alert(Alert.AlertType.INFORMATION, "Employee saved successfully!").show();
                        cleanFields();
                        loadEmployeeTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee ID already exists or something went wrong!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            }
        }
    }

    // Search
    @FXML
    private void handleSearchEmployee(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                String id = idField.getText().trim();

                if (!id.matches(EMPLOYEE_ID_REGEX)) {
                        new Alert(Alert.AlertType.ERROR, "Invalid ID Format!").show();
                     return;
                }

                Employee employee = employeeBO.searchEmployee(id);

                if (employee != null) {
                    nameField.setText(employee.getName());
                    roleField.setText(employee.getRole());
                   
                    salaryField.setText(String.format("%.2f", employee.getSalary()));
                    teleField.setText(employee.getTele());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee not found!").show();
                    nameField.clear(); 
                    roleField.clear();
                    salaryField.clear();
                    teleField.clear();
                }

            } catch (Exception e) {
                    e.printStackTrace();
                 new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    // Update button
    @FXML
    private void handleEmployeeUpdate() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String role = roleField.getText().trim();
            String salaryText = salaryField.getText().trim();
            String tele = teleField.getText().trim();

            if (!id.matches(EMPLOYEE_ID_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
            } else if (!name.matches(EMPLOYEE_NAME_REGEX)) {
                      new Alert(Alert.AlertType.ERROR, "Invalid Employee Name").show();
            } else if (!role.matches(EMPLOYEE_ROLE_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Employee Role").show();
            } else if (!salaryText.matches(EMPLOYEE_SALARY_REGEX)) { 
                   new Alert(Alert.AlertType.ERROR, "Invalid Salary Format ").show();
            } else if (!tele.matches(EMPLOYEE_TELE_REGEX)) {
                  new Alert(Alert.AlertType.ERROR, "Invalid Telephone Number").show();
            } else {
                double salary = Double.parseDouble(salaryText);
                Employee employee = new Employee(id, name, role, salary, tele);
                boolean result = employeeBO.updateEmployee(employee);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Employee updated successfully!").show();
                    cleanFields();
                    loadEmployeeTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee ID not found or nothing updated!").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    // Delete button 
    @FXML
    private void handleEmployeeDelete() {
        
        try {
            
            String id = idField.getText().trim();

            if (!id.matches(EMPLOYEE_ID_REGEX)) {
                           new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
            } else {
                
                boolean result = employeeBO.deleteEmployee(id);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Employee Deleted successfully!").show();
                        cleanFields();
                       loadEmployeeTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee ID not found!").show();
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
        roleField.clear();
        salaryField.clear();
        teleField.clear();
    }

    private void loadEmployeeTable() {
        
        
        try {
            
            
            List<Employee> employeeList = employeeBO.getAllEmployees();

                ObservableList<EmployeeDTO> obList = FXCollections.observableArrayList();

            for (Employee entity : employeeList) {

                obList.add(new EmployeeDTO(
                        entity.getId(),
                        entity.getName(),
                       entity.getRole(),
                        entity.getSalary(),
                        entity.getTele()
                ));
            }
            
               tblEmployee.setItems(obList);
        } catch (Exception e) {
               e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading table!").show();
        }
    }
}