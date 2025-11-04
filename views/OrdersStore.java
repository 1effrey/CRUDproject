package desktopapps.demo4.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrdersStore {
    private static OrdersStore instance;
    private ObservableList<Order> orders;

    private OrdersStore() {
        orders = FXCollections.observableArrayList();
        orders.add(new Order(1, "Laptop", 2, 1200.00));
        orders.add(new Order(2, "Mouse", 5, 25.50));
        orders.add(new Order(3, "Keyboard", 3, 75.00));
    }

    public static OrdersStore getInstance() {
        if (instance == null) {
            instance = new OrdersStore();
        }
        return instance;
    }

    public ObservableList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void updateOrder(Order oldOrder, Order newOrder) {
        int index = orders.indexOf(oldOrder);
        if (index != -1) {
            orders.set(index, newOrder);
        }
    }

    public void deleteOrder(Order order) {
        orders.remove(order);
    }

    public int getNextId() {
        return orders.isEmpty() ? 1 : orders.stream()
                .mapToInt(Order::getId)
                .max()
                .getAsInt() + 1;
    }
}
