package desktopapps.demo4.controllers;

import desktopapps.demo4.models.ProductsStore;

import desktopapps.demo4.models.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


import javafx.scene.image.ImageView;


public class
ProductsController {

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, Double> priceCol;

    @FXML
    private TableColumn<Product, Integer> quantityCol;

    @FXML
    private TextField nameFld;

    @FXML
    private TextField priceFld;

    @FXML
    private TextField quantityFld;

    @FXML
    private Label errorMsg;

    private final ProductsStore productStore = new ProductsStore();

    @FXML
    private ImageView productImage;


    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        priceCol.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()).asObject());
        quantityCol.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());

        ObservableList<Product> products = productStore.getProductsList();
        productsTable.setItems(products);

        productsTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Product selected = productsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                nameFld.setText(selected.getName());
                priceFld.setText(Double.toString(selected.getPrice()));
                quantityFld.setText(Integer.toString(selected.getQuantity()));
            }
        });
        Image img = new Image(getClass().getResourceAsStream("/desktopapps/demo4/images/products.png"));
        productImage.setImage(img);


    }

    @FXML
    void addProduct(ActionEvent event) {
        String error = "";
        boolean valid = true;

        String name = nameFld.getText();
        if (name.isEmpty()) {
            error += "Name is required\n";
            valid = false;
        }

        Double price = null;
        try {
            price = Double.parseDouble(priceFld.getText());
        } catch (Exception e) {
            error += "Invalid price\n";
            valid = false;
        }

        Integer qty = null;
        try {
            qty = Integer.parseInt(quantityFld.getText());
        } catch (Exception e) {
            error += "Invalid quantity\n";
            valid = false;
        }

        if (valid) {
            productStore.addProduct(new Product(name, price, qty));
            nameFld.clear();
            priceFld.clear();
            quantityFld.clear();
            errorMsg.setText("");
        } else {
            errorMsg.setText(error);
        }
    }

    @FXML
    void updateProduct(ActionEvent event) {
        Product selected = productsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String error = "";
            boolean valid = true;

            String name = nameFld.getText();
            if (name.isEmpty()) {
                error += "Name is required\n";
                valid = false;
            }

            Double price = null;
            try {
                price = Double.parseDouble(priceFld.getText());
            } catch (Exception e) {
                error += "Invalid price\n";
                valid = false;
            }

            Integer qty = null;
            try {
                qty = Integer.parseInt(quantityFld.getText());
            } catch (Exception e) {
                error += "Invalid quantity\n";
                valid = false;
            }

            if (valid) {
                productStore.updateProduct(selected, name, price, qty);
                errorMsg.setText("");
            } else {
                errorMsg.setText(error);
            }
        }
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        Product selected = productsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            productStore.deleteProduct(selected);
        }
    }

    @FXML
    void goHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desktopapps/demo4/views/home-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
