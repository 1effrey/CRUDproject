package desktopapps.demo4.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsStore {
    private final ObservableList<Product> products = FXCollections.observableArrayList();

    public ProductsStore() {
        // أمثلة مبدئية
        products.addAll(
                new Product("Laptop", 999.99, 5),
                new Product("Mouse", 19.99, 50),
                new Product("Keyboard", 49.99, 20)
        );
    }

    public ObservableList<Product> getProductsList() {
        return products;
    }

    public void addProduct(Product p) {
        if (p != null) products.add(p);
    }

    public void deleteProduct(Product p) {
        if (p != null) products.remove(p);
    }

    public void updateProduct(Product p, String name, double price, int qty) {
        if (p != null) {
            p.setName(name);
            p.setPrice(price);
            p.setQuantity(qty);
        }
    }
}
