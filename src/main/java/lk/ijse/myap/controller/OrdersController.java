package lk.ijse.myap.controller;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.myap.bo.ItemBO;
import lk.ijse.myap.bo.ItemBOImpl;
import lk.ijse.myap.bo.PlacedOrderBO;
import lk.ijse.myap.bo.PlacedOrderBOImpl;
import lk.ijse.myap.db.DBConnection;
import lk.ijse.myap.dto.OrdersDTO;
import lk.ijse.myap.dto.OrderDetailsDTO;
import lk.ijse.myap.dto.ItemDTO;


import lk.ijse.myap.entity.Item;
import lk.ijse.myap.entity.OrderDetails;
import lk.ijse.myap.entity.Orders;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class OrdersController implements Initializable {

    // Order Fields
    @FXML
    private TextField orderIdField;
    @FXML
    private DatePicker orderDateField;
    @FXML
    private TextField totalPriceDisplayField;

    // Order details Fields
    @FXML
    private TextField itemIdField;
    @FXML
    private TextField qtyField;
    @FXML
    private Label unitPriceLabel;
    @FXML
    private Label itemNameLabel;



    // =======table  order details======


    @FXML
    private TableView<OrderDetailsDTO> tblOrder_Items;
    @FXML
    private TableColumn<OrderDetailsDTO, String> colItemId;
    @FXML
    private TableColumn<OrderDetailsDTO, Integer> colOrderQty;
    @FXML
    private TableColumn<OrderDetailsDTO, Double> colUnitPrice;
    @FXML
    private TableColumn<OrderDetailsDTO, Double> colLineTotal;



    private final String ORDER_ID_REGEX = "^O[0-9]{3}$"; // data validation


    PlacedOrderBO placedOrderBO=new PlacedOrderBOImpl();
    ItemBO itemBO=new ItemBOImpl();

    private final ObservableList<OrderDetailsDTO> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderDateField.setValue(LocalDate.now());


        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colLineTotal.setCellValueFactory(new PropertyValueFactory<>("lineTotal"));

        tblOrder_Items.setItems(cartList);
        loadNextOrderId();
    }



    private void loadNextOrderId() {

        try {
            orderIdField.setText(placedOrderBO.getNextOrderId());
        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, "Error loading next Order ID!").show();
        }
    }






    @FXML
    private void handleItemSearch() {
        String itemId = itemIdField.getText().trim();
        if (itemId.isEmpty()) return;

        try {

            Item item = itemBO.searchItem(itemId);

            if (item != null) {
                itemNameLabel.setText(item.getName());
                unitPriceLabel.setText(String.format("%.2f", item.getUnitPrice()));
            } else {
                itemNameLabel.setText("Item Not Found");
                unitPriceLabel.setText("");
            }


        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "Error searching item!").show();
        }
    }






    @FXML
    private void handleAddItemToCart() {
        String itemId = itemIdField.getText().trim();
        String qtyText = qtyField.getText().trim();

        if (itemNameLabel.getText().isEmpty() || itemNameLabel.getText().equals("Item Not Found") || itemId.isEmpty() || qtyText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Select a valid Item and enter Quantity!").show();
            return;
        }

        try {
            int orderQty = Integer.parseInt(qtyText);
            Item item = itemBO.searchItem(itemId);

            if (item == null || orderQty <= 0) {
                new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
                return;
            }

            // Stock Check
            if (orderQty > item.getQty()) {
                new Alert(Alert.AlertType.ERROR, "Insufficient Stock! Available: " + item.getQty()).show();
                return;
            }


            double unitPrice = item.getUnitPrice();
            double lineTotal = orderQty * unitPrice;




            OrderDetailsDTO detail = new OrderDetailsDTO(
                    orderIdField.getText().trim(),
                    itemId,
                    orderQty,
                    unitPrice,
                    lineTotal
            );

            cartList.add(detail);

            calculateUiTotal(); //  total display




            itemIdField.clear();
            qtyField.clear();
            itemNameLabel.setText("");
            unitPriceLabel.setText("");

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Quantity must be a valid number!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error adding item: " + e.getMessage()).show();
        }
    }



    // calculate total
    private void calculateUiTotal() {
        double total = 0.0;
        for (OrderDetailsDTO item : cartList) {
            total += item.getLineTotal();
        }
        totalPriceDisplayField.setText(String.format("%.2f", total));
    }





    // Place order button
    @FXML
    private void handlePlaceOrder() {
        String orderId = orderIdField.getText().trim();
        LocalDate orderDate = orderDateField.getValue();



        /// transaction///
        List<OrderDetails> detailsList = new ArrayList<>();
        for (OrderDetailsDTO orderDetailsDTO : (cartList)) {

        }


        if (!orderId.matches(ORDER_ID_REGEX)) {

            new Alert(Alert.AlertType.ERROR, "Invalid Order ID ").show();
            return;
        }


        if (detailsList.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot place an order without items!").show();
            return;
        }

        try {


            Orders order = new Orders(orderId, orderDate);



            boolean result = placedOrderBO.placeOrder( order, detailsList);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, " Order Placed Successfully! Total: " + String.format("%.2f", order.getTotalPrice())).show();

                totalPriceDisplayField.setText(String.format("%.2f", order.getTotalPrice()));
                clearOrderFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Placement Failed! A database rollback occurred.").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred.").show();
        }
    }

    private void clearOrderFields() {
        loadNextOrderId();
        orderDateField.setValue(LocalDate.now());
        cartList.clear();
        totalPriceDisplayField.clear();
    }



    // remove button from cart

    @FXML
    private void handleRemoveItemFromCart(ActionEvent event) {


        OrderDetailsDTO selectedDetail = tblOrder_Items.getSelectionModel().getSelectedItem();




        if (selectedDetail != null) {
            // remove item from cart
            cartList.remove(selectedDetail);

            // call calculate method
            calculateUiTotal();

            new Alert(Alert.AlertType.INFORMATION, "Item removed from the cart!").show();
        } else {

            new Alert(Alert.AlertType.WARNING, "Please select an item to remove from the cart!").show();
        }
    }



    @FXML
    public void handlePrint() {
        String orderId = orderIdField.getText().trim();

        if (orderId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter or select an Order ID!").show();
            return;
        }

        try {

            placedOrderBO.printOrderReport(orderId);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Jasper Report Error: " + e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Unexpected Error: " + e.getMessage()).show();
        }
    }


}