package database;

import model.Product;
import model.FreshProduct;
import model.PackagedProduct;
import exception.InvalidProductException;

import java.sql.*;
import java.util.ArrayList;


public class ProductDAO {

    // ========================================
    // CREATE - Insert Product
    // ========================================


    public int insertProduct(Product product) {
        String sql = "INSERT INTO product (name, price, stock_quantity, product_type) " +
                "VALUES (?, ?, ?, ?) RETURNING product_id";

        Connection connection = DatabaseConnection.getConnection();
        int productId = -1;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStockQuantity());
            statement.setString(4, product.getProductType());

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                productId = rs.getInt("product_id");
                System.out.println(" Product inserted successfully ID: " + productId);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Insert failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return productId;
    }


    public int insertFreshProduct(FreshProduct freshProduct) {
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String sqlProduct = "INSERT INTO product (name, price, stock_quantity, product_type) " +
                    "VALUES (?, ?, ?, 'Fresh') RETURNING product_id";

            PreparedStatement stmtProduct = connection.prepareStatement(sqlProduct);
            stmtProduct.setString(1, freshProduct.getName());
            stmtProduct.setDouble(2, freshProduct.getPrice());
            stmtProduct.setInt(3, freshProduct.getStockQuantity());

            ResultSet rs = stmtProduct.executeQuery();
            int productId = -1;
            if (rs.next()) {
                productId = rs.getInt("product_id");
            }
            rs.close();
            stmtProduct.close();

            if (productId == -1) {
                connection.rollback();
                return -1;
            }

            String sqlFresh = "INSERT INTO fresh_product (product_id, expiry_date, is_organic) " +
                    "VALUES (?, ?::date, ?)";

            PreparedStatement stmtFresh = connection.prepareStatement(sqlFresh);
            stmtFresh.setInt(1, productId);
            stmtFresh.setString(2, freshProduct.getExpiryDate());
            stmtFresh.setBoolean(3, freshProduct.isOrganic());

            stmtFresh.executeUpdate();
            stmtFresh.close();

            connection.commit();
            System.out.println(" Fresh product inserted successfully ID: " + productId);

            return productId;

        } catch (SQLException e) {
            System.out.println(" Insert fresh product failed");
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return -1;

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConnection(connection);
        }
    }


    public int insertPackagedProduct(PackagedProduct packagedProduct) {
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String sqlProduct = "INSERT INTO product (name, price, stock_quantity, product_type) " +
                    "VALUES (?, ?, ?, 'Packaged') RETURNING product_id";

            PreparedStatement stmtProduct = connection.prepareStatement(sqlProduct);
            stmtProduct.setString(1, packagedProduct.getName());
            stmtProduct.setDouble(2, packagedProduct.getPrice());
            stmtProduct.setInt(3, packagedProduct.getStockQuantity());

            ResultSet rs = stmtProduct.executeQuery();
            int productId = -1;
            if (rs.next()) {
                productId = rs.getInt("product_id");
            }
            rs.close();
            stmtProduct.close();

            if (productId == -1) {
                connection.rollback();
                return -1;
            }

            String sqlPackaged = "INSERT INTO packaged_product (product_id, manufacturer, weight) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement stmtPackaged = connection.prepareStatement(sqlPackaged);
            stmtPackaged.setInt(1, productId);
            stmtPackaged.setString(2, packagedProduct.getManufacturer());
            stmtPackaged.setDouble(3, packagedProduct.getWeight());

            stmtPackaged.executeUpdate();
            stmtPackaged.close();

            connection.commit();
            System.out.println(" Packaged product inserted successfully ID: " + productId);

            return productId;

        } catch (SQLException e) {
            System.out.println(" Insert packaged product failed");
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return -1;

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    // ========================================
    // READ - Select Products
    // ========================================


    public void getAllProducts() {
        String sql = "SELECT * FROM product ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║   📦 ALL PRODUCTS FROM DATABASE 📦   ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println();

            int count = 0;
            while (resultSet.next()) {
                count++;
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock_quantity");
                String type = resultSet.getString("product_type");

                System.out.println(count + ". Product ID: " + id);
                System.out.println("   Name: " + name);
                System.out.println("   Price: " + String.format("%.2f KZT", price));
                System.out.println("   Stock: " + stock);
                System.out.println("   Type: " + type);
                System.out.println("   ─────────────────────────────────");
            }

            if (count == 0) {
                System.out.println(" No products found in database");
            } else {
                System.out.println("\n Total products: " + count);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Select failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }


    public void getAllFreshProducts() {
        String sql = "SELECT p.product_id, p.name, p.price, p.stock_quantity, " +
                "fp.expiry_date, fp.is_organic " +
                "FROM product p " +
                "JOIN fresh_product fp ON p.product_id = fp.product_id " +
                "ORDER BY p.product_id";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║     🍎 FRESH PRODUCTS ONLY 🍎        ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println();

            int count = 0;
            while (resultSet.next()) {
                count++;
                System.out.println(count + ". " + resultSet.getString("name"));
                System.out.println("   ID: " + resultSet.getInt("product_id"));
                System.out.println("   Price: " + resultSet.getDouble("price") + " KZT");
                System.out.println("   Stock: " + resultSet.getInt("stock_quantity"));
                System.out.println("   Expiry: " + resultSet.getDate("expiry_date"));
                System.out.println("   Organic: " + (resultSet.getBoolean("is_organic") ? "Yes 🌿" : "No"));
                System.out.println("   ─────────────────────────────────");
            }

            if (count == 0) {
                System.out.println(" No fresh products found");
            } else {
                System.out.println("\n Total fresh products: " + count);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Select fresh products failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }
}