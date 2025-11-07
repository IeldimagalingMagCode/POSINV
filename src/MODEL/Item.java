package MODEL;  // Or your package

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHelper {
    // Update these with your MySQL details
    private static final String URL = "jdbc:mysql://localhost:3306/pos_inventory";  // Your DB name
    private static final String USER = "root";  // Your username
    private static final String PASSWORD = "sicilian7opening";  // Your password

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Get all items for inventory
    public static ResultSet getAllItems() throws Exception {
        String sql = "SELECT * FROM items";
        Connection conn = getConnection();
        return conn.createStatement().executeQuery(sql);
    }

    // Add a new item
    public static void addItem(String name, double price, int stock) throws Exception {
        String sql = "INSERT INTO items (name, price, stock) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, stock);
            stmt.executeUpdate();
        }
    }

    // Delete an item by id (updated for reliability)
    public static void deleteItem(int id) throws Exception {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // New: Update an item by id
    public static void updateItem(int id, String name, double price, int stock) throws Exception {
        String sql = "UPDATE items SET name = ?, price = ?, stock = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, stock);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    // Ingredients methods (unchanged)
    public static ResultSet getAllIngredients() throws Exception {
        String sql = "SELECT * FROM ingredients";
        Connection conn = getConnection();
        return conn.createStatement().executeQuery(sql);
    }

    public static void addIngredient(String name, double quantity, String unit) throws Exception {
        String sql = "INSERT INTO ingredients (name, quantity, unit) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, quantity);
            stmt.setString(3, unit);
            stmt.executeUpdate();
        }
    }

    public static void deleteIngredient(String name) throws Exception {
        String sql = "DELETE FROM ingredients WHERE name = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
}