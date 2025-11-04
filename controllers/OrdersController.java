package desktopapps.demo4.controllers;

import desktopapps.demo4.models.Order;
import desktopapps.demo4.models.OrdersStore;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdersController {

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Integer> idColumn;

    @FXML
    private TableColumn<Order, String> productColumn;

    @FXML
    private TableColumn<Order, Integer> quantityColumn;

    @FXML
    private TableColumn<Order, Double> priceColumn;

    @FXML
    private TextField productField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button clearButton;

    private OrdersStore ordersStore;
    private Order selectedOrder;

    @FXML
    public void initialize() {

        ordersStore = OrdersStore.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ordersTable.setItems(ordersStore.getOrders());

        ordersTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedOrder = newValue;
                        populateFields(newValue);
                    }
                }
        );
    }

    @FXML
    private void handleAdd() {
        try {
            String product = productField.getText().trim();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (product.isEmpty()) {
                showAlert("Validation Error", "Product name cannot be empty!");
                return;
            }

            int newId = ordersStore.getNextId();
            Order newOrder = new Order(newId, product, quantity, price);
            ordersStore.addOrder(newOrder);

            clearFields();
            showAlert("Success", "Order added successfully!");

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for quantity and price!");
        }
    }

    @FXML
    private void handleUpdate() {
        if (selectedOrder == null) {
            showAlert("Selection Error", "Please select an order to update!");
            return;
        }

        try {
            String product = productField.getText().trim();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (product.isEmpty()) {
                showAlert("Validation Error", "Product name cannot be empty!");
                return;
            }

            Order updatedOrder = new Order(selectedOrder.getId(), product, quantity, price);
            ordersStore.updateOrder(selectedOrder, updatedOrder);

            clearFields();
            selectedOrder = null;
            showAlert("Success", "Order updated successfully!");

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for quantity and price!");
        }
    }

    @FXML
    private void handleDelete() {
        if (selectedOrder == null) {
            showAlert("Selection Error", "Please select an order to delete!");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText("Delete Order");
        confirmation.setContentText("Are you sure you want to delete this order?");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ordersStore.deleteOrder(selectedOrder);
                clearFields();
                selectedOrder = null;
                showAlert("Success", "Order deleted successfully!");
            }
        });
    }

    @FXML
    private void handleClear() {
        clearFields();
        selectedOrder = null;
        ordersTable.getSelectionModel().clearSelection();
    }

    private void populateFields(Order order) {
        productField.setText(order.getProduct());
        quantityField.setText(String.valueOf(order.getQuantity()));
        priceField.setText(String.valueOf(order.getPrice()));
    }

    private void clearFields() {
        productField.clear();
        quantityField.clear();
        priceField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
