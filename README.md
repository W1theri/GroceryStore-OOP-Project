# 🛒 Grocery Store Management System

A comprehensive Java-based grocery store management system with **PostgreSQL database integration**, implementing advanced OOP principles including **encapsulation**, **inheritance**, **polymorphism**, **abstract classes**, **interfaces**, **exception handling**, and **JDBC connectivity**.

---

## ✨ Features

### Core OOP Implementation (Week 6 Enhanced)
- **Encapsulation**: Private/protected fields with controlled access via getters/setters
- **Inheritance**: Parent-child class hierarchy (`Product` → `FreshProduct`, `PackagedProduct`)
- **Polymorphism**: Single ArrayList stores multiple product types with dynamic behavior
- **Abstract Classes**: `Product` is abstract with abstract methods `getProductType()` and `displayProductDetails()`
- **Interfaces**:
  - `Menu` interface for menu system
  - `Perishable` interface for fresh products
- **Exception Handling**: Custom `InvalidProductException` for robust error handling
- **Data Validation**: Comprehensive input validation with exception throwing
- **ArrayList Integration**: Dynamic storage for multiple objects
- **Real-time Feedback**: Warnings and success messages with emoji indicators

### Database Integration (Week 7 NEW)
- **PostgreSQL Database**: Persistent data storage with relational database
- **JDBC Connectivity**: Direct connection from Java to PostgreSQL
- **DAO Pattern**: Data Access Objects for clean separation of concerns
- **CRUD Operations**:
  - ✅ CREATE (INSERT) - Add products and customers to database
  - ✅ READ (SELECT) - Retrieve data from database
  - 🔜 UPDATE - Modify existing records (Week 8)
  - 🔜 DELETE - Remove records (Week 8)
- **PreparedStatement**: Protection against SQL injection attacks
- **Transaction Management**: Atomic operations for related tables
- **Database Schema**: 5 normalized tables with foreign key relationships
- **Connection Pooling**: Efficient database connection management

### Advanced Features
- **Interactive Console Menu**: 11+ operations with user-friendly interface
- **Auto-upgrade System**: Customer membership tiers adjust automatically based on purchases
- **Polymorphic Behavior**: Same method calls produce different behaviors based on object type
- **Type Filtering**: View products by specific types using `instanceof`
- **Downcasting**: Access child-specific methods when needed
- **Professional Exception Handling**: Try-catch blocks throughout with custom exceptions
- **Real Date Handling**: LocalDate API for expiry date calculations

---

## 📁 Project Structure

```
GroceryStore-OOP-Project/
├── src/
│   ├── model/
│   │   ├── Product.java (ABSTRACT CLASS )
│   │   ├── FreshProduct.java (extends Product, implements Perishable)
│   │   ├── PackagedProduct.java (extends Product)
│   │   ├── Perishable.java (INTERFACE )
│   │   ├── Customer.java
│   │   └── Sale.java
│   │
│   ├── menu/
│   │   ├── Menu.java (INTERFACE )
│   │   └── MenuManager.java (implements Menu)
│   │
│   ├── exception/
│   │   └── InvalidProductException.java (CUSTOM EXCEPTION )
│   │
│   ├── database/ (NEW Week 7 )
│   │   ├── DatabaseConnection.java (Connection Management)
│   │   ├── ProductDAO.java (Product CRUD Operations)
│   │   ├── CustomerDAO.java (Customer CRUD Operations)
│   │   ├── TestConnection.java (Test DB Connection)
│   │   ├── TestInsert.java (Test INSERT Operations)
│   │   └── TestSelect.java (Test SELECT Operations)
│   │
│   └── Main.java (7 lines)
│
└── database/
    └── database_setup.sql (PostgreSQL Schema & Test Data)
```

---

## 🗄️ Database Schema

### Tables Overview:

```sql
-- Main product table
product (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INTEGER NOT NULL,
    product_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

-- Fresh products (inherits from product)
fresh_product (
    product_id INTEGER PRIMARY KEY REFERENCES product(product_id),
    expiry_date DATE NOT NULL,
    is_organic BOOLEAN DEFAULT FALSE
)

-- Packaged products (inherits from product)
packaged_product (
    product_id INTEGER PRIMARY KEY REFERENCES product(product_id),
    manufacturer VARCHAR(100) NOT NULL,
    weight DECIMAL(10,2) NOT NULL
)

-- Customer management
customer (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    membership_level VARCHAR(50) NOT NULL,
    total_purchases DECIMAL(12,2) DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

-- Sales tracking
sale (
    sale_id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL,
    sale_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
```

### Database Features:
- ✅ **Foreign Keys**: Referential integrity between tables
- ✅ **Constraints**: CHECK constraints for data validation
- ✅ **Auto-increment**: SERIAL primary keys
- ✅ **Timestamps**: Automatic creation time tracking
- ✅ **Indexes**: Optimized queries for better performance

---

## 🎨 Class Hierarchy

```
Product (Abstract Class)
├── FreshProduct (implements Perishable)
└── PackagedProduct

Menu (Interface)
└── MenuManager (implements Menu)

Perishable (Interface)
└── FreshProduct (implements Perishable)

Exception
└── InvalidProductException (Custom Exception)

Database (DAO Pattern)
├── DatabaseConnection (Connection Management)
├── ProductDAO (Product CRUD)
└── CustomerDAO (Customer CRUD)
```

---

## 💻 How to Run

### Prerequisites:
1. **Java JDK 11+** installed
2. **PostgreSQL 16** installed and running
3. **IntelliJ IDEA** (or any Java IDE)
4. **PostgreSQL JDBC Driver** (postgresql-42.7.1.jar)

### Step 1: Clone the repository
```bash
git clone https://github.com/W1theri/GroceryStore-OOP-Project.git
cd GroceryStore-OOP-Project
```

### Step 2: Setup PostgreSQL Database

**Option A: Using pgAdmin (Recommended)**
1. Open pgAdmin 4
2. Connect to PostgreSQL server
3. Create database: Right-click `Databases` → `Create` → `Database`
4. Name: `grocery_db`
5. Right-click `grocery_db` → `Query Tool`
6. Open `database/database_setup.sql`
7. Execute (F5)

**Option B: Using psql**
```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE grocery_db;

# Connect to database
\c grocery_db

# Execute setup script
\i /path/to/database_setup.sql
```

### Step 3: Configure Database Connection
Open `src/database/DatabaseConnection.java` and update:
```java
private static final String PASSWORD = "your_postgres_password";
```

### Step 4: Add JDBC Driver to Project

**In IntelliJ IDEA:**
1. `File` → `Project Structure` (Ctrl+Alt+Shift+S)
2. `Libraries` → `+` → `Java`
3. Select `postgresql-42.7.1.jar`
4. `Apply` → `OK`

### Step 5: Test Database Connection
```bash
# Run TestConnection.java
# Should output: ✅ Connected to database successfully!
```

### Step 6: Run the Application
1. Navigate to `src/Main.java`
2. Right-click → `Run 'Main.main()'`
3. Or press `Shift + F10`
4. Follow the interactive menu prompts

---

## 📊 Example Usage

### Adding a Fresh Product (Saved to Database)
```
Enter your choice: 2
--- ADD FRESH PRODUCT 🍎 ---
Enter product ID: 106
Enter product name: Apple
Enter price (KZT): 500
Enter stock quantity: 150
Enter expiry date (YYYY-MM-DD): 2025-02-15
Is organic? (true/false): true

✅ Connected to database successfully!
✅ Fresh product inserted successfully! ID: 6
🔒 Connection closed.

✅ Fresh product added successfully!
[Fresh Product] Product{productId=106, name='Apple', price=500.00 KZT, stockQuantity=150} | Expiry: 2025-02-15 | 🌿 ORGANIC
```

### Viewing Products from Database
```
Enter your choice: 4

✅ Connected to database successfully!

╔══════════════════════════════════════╗
║   📦 ALL PRODUCTS FROM DATABASE 📦   ║
╚══════════════════════════════════════╝

1. Product ID: 1
   Name: Rice
   Price: 1200.00 KZT
   Stock: 200
   Type: General
   ─────────────────────────────────
2. Product ID: 2
   Name: Apple
   Price: 500.00 KZT
   Stock: 150
   Type: Fresh
   ─────────────────────────────────

📊 Total products: 5
🔒 Connection closed.
```

### Exception Handling in Action
```
Enter price (KZT): -500
❌ Failed to add product: Price cannot be negative: -500.0
```

### Demonstrating Polymorphism
```
Enter your choice: 5
╔══════════════════════════════════════╗
║   ✨ POLYMORPHISM DEMONSTRATION ✨    ║
╚══════════════════════════════════════╝

Calling displayProductDetails() on all products:
(Same method name, different behavior!)

📦 General Product: Rice
   Stock: 200
   Price: 1200.00 KZT

🍎 Fresh Product: Apple
   Expiry Date: 2025-02-15
   Days Until Expiry: 26
   Organic: Yes 🌿
   Status: ✅ FRESH

📦 Packaged Product: Flour
   Manufacturer: Kazakhstan
   Weight: 2000.0g
   Price per kg: 1250.00 KZT

✨ This is POLYMORPHISM in action! 🎭
```

---


## 🛠️ Technical Implementation

### Model Classes Features

#### Product (Abstract Parent)
- Abstract methods: `getProductType()`, `displayProductDetails()`
- Concrete methods: `isInStock()`, `restock()`, `sell()`, `applyDiscount()`
- All setters throw `InvalidProductException`
- Protected fields accessible to children

#### FreshProduct (extends Product, implements Perishable)
- **Inherited & Overridden:** `isInStock()` checks expiry + stock
- **Perishable Methods:** `isExpired()`, `getDaysUntilExpiry()`, `markAsExpired()`
- **Real Date Handling:** Uses `LocalDate` to calculate actual days until expiry
- **Unique Features:** Organic certification, freshness status
- **Database:** Stored in `product` + `fresh_product` tables

#### PackagedProduct (extends Product)
- **Inherited & Overridden:** `getFormattedPrice()` shows price per kg
- **Unique Methods:** `getPricePerKg()`, `isLightweight()`, `isBulk()`
- **Special Features:** Bulk discount for packages > 2kg
- **Database:** Stored in `product` + `packaged_product` tables

### Database Classes

#### DatabaseConnection
- **Singleton Pattern**: Single connection instance
- **Connection Management**: Open and close connections safely
- **Error Handling**: Comprehensive exception handling
- **Configuration**: Easy to configure URL, user, password

#### ProductDAO
- **CREATE**: Insert products with transaction support
- **READ**: Retrieve all products, filter by type
- **PreparedStatement**: All queries use PreparedStatement
- **Transaction Management**: Atomic operations for related tables

#### CustomerDAO
- **CREATE**: Insert customers
- **READ**: Retrieve all customers, get by ID
- **Data Validation**: Ensures data integrity

---

## 📈 Project Statistics

- **Classes**: 13 (Product, FreshProduct, PackagedProduct, Customer, Sale, Menu, MenuManager, Perishable, InvalidProductException, DatabaseConnection, ProductDAO, CustomerDAO, Main)
- **Abstract Classes**: 1 (Product)
- **Interfaces**: 2 (Menu, Perishable)
- **Custom Exceptions**: 1 (InvalidProductException)
- **DAO Classes**: 2 (ProductDAO, CustomerDAO)
- **Inheritance Levels**: 2 (Parent → Child)
- **Polymorphic ArrayList**: 1 (ArrayList<Product>)
- **Database Tables**: 5 (product, fresh_product, packaged_product, customer, sale)
- **Menu Options**: 11+ interactive operations
- **Lines of Code**: ~1500+ (excluding comments)
- **Validation Points**: 20+ input validation checks with exceptions

---

## ✅ Learning Outcomes

### Week 7 Achievements (NEW! 🎉)
- ✅ PostgreSQL database setup and configuration
- ✅ JDBC driver integration
- ✅ Database connection management
- ✅ DAO pattern implementation
- ✅ CREATE (INSERT) operations with PreparedStatement
- ✅ READ (SELECT) operations with ResultSet
- ✅ Transaction management for multi-table operations
- ✅ SQL injection prevention with PreparedStatement
- ✅ Exception handling for database operations
- ✅ Connection pooling best practices

### Week 6 Achievements
- ✅ Implemented abstract class with abstract methods
- ✅ Created and implemented multiple interfaces
- ✅ Built custom exception class
- ✅ Refactored all setters to throw exceptions
- ✅ Added comprehensive try-catch blocks
- ✅ Separated menu logic into MenuManager
- ✅ Achieved clean Main.java (7 lines!)
- ✅ Professional package structure (menu/, exception/, model/, database/)
- ✅ Real date handling with LocalDate API
- ✅ Demonstrated difference between abstract class and interface

### Previous Weeks (Cumulative)
- ✅ Encapsulation with getters/setters
- ✅ Inheritance hierarchy
- ✅ Polymorphism with method overriding
- ✅ ArrayList for dynamic storage
- ✅ Interactive console application
- ✅ Input validation and error handling




## 👨‍💻 Author

**Abilmansur** - [W1theri](https://github.com/W1theri)

---

## 📚 Course Information

- **Course**: Object-Oriented Programming (OOP)
- **Institution**: AITU (Astana IT University)


---
