package database;

import model.Product;
import model.FreshProduct;
import model.PackagedProduct;
import exception.InvalidProductException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {

    // ========================================
    // CREATE - Insert Product
    // ========================================

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

    public void displayAllProducts() {
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
                System.out.println("   ─────────────────────────────────────");
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

    public List<FreshProduct> getAllFreshProducts() {
        List<FreshProduct> products = new ArrayList<>();
        String sql = "SELECT p.product_id, p.name, p.price, p.stock_quantity, " +
                "fp.expiry_date, fp.is_organic " +
                "FROM product p " +
                "JOIN fresh_product fp ON p.product_id = fp.product_id " +
                "ORDER BY p.product_id";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FreshProduct product = new FreshProduct(
                        resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock_quantity"),
                        resultSet.getString("expiry_date"),
                        resultSet.getBoolean("is_organic")
                );
                products.add(product);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException | InvalidProductException e) {
            System.out.println(" Select fresh products failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    public List<PackagedProduct> getAllPackagedProducts() {
        List<PackagedProduct> products = new ArrayList<>();
        String sql = "SELECT p.product_id, p.name, p.price, p.stock_quantity, " +
                "pp.manufacturer, pp.weight " +
                "FROM product p " +
                "JOIN packaged_product pp ON p.product_id = pp.product_id " +
                "ORDER BY p.product_id";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PackagedProduct product = new PackagedProduct(
                        resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock_quantity"),
                        resultSet.getString("manufacturer"),
                        resultSet.getDouble("weight")
                );
                products.add(product);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException | InvalidProductException e) {
            System.out.println(" Select packaged products failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String type = resultSet.getString("product_type");

                if (type.equals("Fresh")) {
                    return getFreshProductById(productId);
                } else if (type.equals("Packaged")) {
                    return getPackagedProductById(productId);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    private FreshProduct getFreshProductById(int productId) {
        String sql = "SELECT p.*, fp.expiry_date, fp.is_organic " +
                "FROM product p " +
                "JOIN fresh_product fp ON p.product_id = fp.product_id " +
                "WHERE p.product_id = ?";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                FreshProduct product = new FreshProduct(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("expiry_date"),
                        rs.getBoolean("is_organic")
                );
                rs.close();
                statement.close();
                DatabaseConnection.closeConnection(connection);
                return product;
            }

        } catch (SQLException | InvalidProductException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
        return null;
    }

    private PackagedProduct getPackagedProductById(int productId) {
        String sql = "SELECT p.*, pp.manufacturer, pp.weight " +
                "FROM product p " +
                "JOIN packaged_product pp ON p.product_id = pp.product_id " +
                "WHERE p.product_id = ?";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                PackagedProduct product = new PackagedProduct(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("manufacturer"),
                        rs.getDouble("weight")
                );
                rs.close();
                statement.close();
                DatabaseConnection.closeConnection(connection);
                return product;
            }

        } catch (SQLException | InvalidProductException e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeConnection(connection);
        return null;
    }

    // ========================================
    // UPDATE - Week 8
    // ========================================

    public boolean updateFreshProduct(FreshProduct product) {
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String sqlProduct = "UPDATE product SET name = ?, price = ?, stock_quantity = ? " +
                    "WHERE product_id = ? AND product_type = 'Fresh'";

            PreparedStatement stmtProduct = connection.prepareStatement(sqlProduct);
            stmtProduct.setString(1, product.getName());
            stmtProduct.setDouble(2, product.getPrice());
            stmtProduct.setInt(3, product.getStockQuantity());
            stmtProduct.setInt(4, product.getProductId());

            int rowsUpdated = stmtProduct.executeUpdate();
            stmtProduct.close();

            if (rowsUpdated == 0) {
                connection.rollback();
                return false;
            }

            String sqlFresh = "UPDATE fresh_product SET expiry_date = ?::date, is_organic = ? " +
                    "WHERE product_id = ?";

            PreparedStatement stmtFresh = connection.prepareStatement(sqlFresh);
            stmtFresh.setString(1, product.getExpiryDate());
            stmtFresh.setBoolean(2, product.isOrganic());
            stmtFresh.setInt(3, product.getProductId());

            stmtFresh.executeUpdate();
            stmtFresh.close();

            connection.commit();
            System.out.println(" Fresh product updated: " + product.getName());
            return true;

        } catch (SQLException e) {
            System.out.println(" Update failed!");
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;

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

    public boolean updatePackagedProduct(PackagedProduct product) {
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String sqlProduct = "UPDATE product SET name = ?, price = ?, stock_quantity = ? " +
                    "WHERE product_id = ? AND product_type = 'Packaged'";

            PreparedStatement stmtProduct = connection.prepareStatement(sqlProduct);
            stmtProduct.setString(1, product.getName());
            stmtProduct.setDouble(2, product.getPrice());
            stmtProduct.setInt(3, product.getStockQuantity());
            stmtProduct.setInt(4, product.getProductId());

            int rowsUpdated = stmtProduct.executeUpdate();
            stmtProduct.close();

            if (rowsUpdated == 0) {
                connection.rollback();
                return false;
            }

            String sqlPackaged = "UPDATE packaged_product SET manufacturer = ?, weight = ? " +
                    "WHERE product_id = ?";

            PreparedStatement stmtPackaged = connection.prepareStatement(sqlPackaged);
            stmtPackaged.setString(1, product.getManufacturer());
            stmtPackaged.setDouble(2, product.getWeight());
            stmtPackaged.setInt(3, product.getProductId());

            stmtPackaged.executeUpdate();
            stmtPackaged.close();

            connection.commit();
            System.out.println(" Packaged product updated: " + product.getName());
            return true;

        } catch (SQLException e) {
            System.out.println(" Update failed!");
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;

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
    // DELETE - Week 8
    // ========================================

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println(" Product deleted (ID: " + productId + ")");
                return true;
            } else {
                System.out.println(" No product found with ID: " + productId);
            }

        } catch (SQLException e) {
            System.out.println(" Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    // ========================================
    // SEARCH - Week 8
    // ========================================

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String type = resultSet.getString("product_type");

                Product product = null;
                if (type.equals("Fresh")) {
                    product = getFreshProductById(id);
                } else if (type.equals("Packaged")) {
                    product = getPackagedProductById(id);
                }

                if (product != null) {
                    products.add(product);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println(" Found " + products.size() + " product(s)");

        } catch (SQLException e) {
            System.out.println(" Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product " +
                "WHERE price BETWEEN ? AND ? " +
                "ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String type = resultSet.getString("product_type");

                Product product = null;
                if (type.equals("Fresh")) {
                    product = getFreshProductById(id);
                } else if (type.equals("Packaged")) {
                    product = getPackagedProductById(id);
                }

                if (product != null) {
                    products.add(product);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println(" Found " + products.size() + " product(s)");

        } catch (SQLException e) {
            System.out.println(" Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    public List<Product> searchByMinPrice(double minPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product " +
                "WHERE price >= ? " +
                "ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String type = resultSet.getString("product_type");

                Product product = null;
                if (type.equals("Fresh")) {
                    product = getFreshProductById(id);
                } else if (type.equals("Packaged")) {
                    product = getPackagedProductById(id);
                }

                if (product != null) {
                    products.add(product);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println(" Found " + products.size() + " product(s)");

        } catch (SQLException e) {
            System.out.println(" Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }
}