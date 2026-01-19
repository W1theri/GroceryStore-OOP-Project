# 🛒 Grocery Store Management System

A comprehensive Java-based grocery store management system with interactive console menu, implementing advanced OOP principles including **encapsulation**, **inheritance**, **polymorphism**, **abstract classes**, **interfaces**, and **exception handling**.

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

### Advanced Features
- **Interactive Console Menu**: 11+ operations with user-friendly interface
- **Auto-upgrade System**: Customer membership tiers adjust automatically based on purchases
- **Polymorphic Behavior**: Same method calls produce different behaviors based on object type
- **Type Filtering**: View products by specific types using `instanceof`
- **Downcasting**: Access child-specific methods when needed
- **Professional Exception Handling**: Try-catch blocks throughout with custom exceptions

---

## 📁 Project Structure

```
GroceryStore-OOP-Project/
├── src/
│   ├── model/
│   │   ├── Product.java (ABSTRACT CLASS ⭐)
│   │   ├── FreshProduct.java (extends Product, implements Perishable)
│   │   ├── PackagedProduct.java (extends Product)
│   │   ├── Perishable.java (INTERFACE ⭐)
│   │   ├── Customer.java
│   │   └── Sale.java
│   │
│   ├── menu/
│   │   ├── Menu.java (INTERFACE ⭐)
│   │   └── MenuManager.java (implements Menu)
│   │
│   ├── exception/
│   │   └── InvalidProductException.java (CUSTOM EXCEPTION ⭐)
│   │
│   └── Main.java (7 lines! ⭐)
```

---

## 🎯 Menu Options

```
╔══════════════════════════════════════╗
║     GROCERY STORE SYSTEM              ║
║     With Interfaces & Exceptions      ║
╚══════════════════════════════════════╝
┌────────────────────────────────────────┐
│  PRODUCT MANAGEMENT                    │
│  1. 📦 Add General Product             │
│  2. 🍎 Add Fresh Product               │
│  3. 📦 Add Packaged Product            │
│  4. 📋 View All Products (Polymorphic) │
│  5. ✨ Demonstrate Polymorphism        │
│  6. 🍎 View Fresh Products Only        │
│  7. 📦 View Packaged Products Only     │
├────────────────────────────────────────┤
│  CUSTOMER & SALES                      │
│  8. 👤 Add Customer                    │
│  9. 👥 View All Customers              │
│  10. 🛒 Add Sale                       │
│  11. 📊 View All Sales                 │
├────────────────────────────────────────┤
│  0. 🚪 Exit                            │
└────────────────────────────────────────┘
```



## 🎓 Week 6 Requirements Compliance

| Requirement | Implementation | Status |
|------------|----------------|--------|
| Menu Interface | `Menu.java` with `displayMenu()`, `run()` | ✅ |
| MenuManager implements Menu | `MenuManager implements Menu` | ✅ |
| One More Interface | `Perishable` interface | ✅ |
| Abstract Class | `Product` is abstract | ✅ |
| Abstract Methods | `getProductType()`, `displayProductDetails()` | ✅ |
| All child classes implement abstract methods | FreshProduct, PackagedProduct with @Override | ✅ |
| Custom Exception | `InvalidProductException` | ✅ |
| Setters throw exceptions | All setters throw InvalidProductException | ✅ |
| Try-catch blocks | Throughout MenuManager | ✅ |
| Clean Main.java | Only 7 lines! | ✅ |
| Package structure | menu/, exception/, model/ | ✅ |

**Compliance Score: 100%** ✅

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
```

---

## 💻 How to Run

### 1. Clone the repository:
```bash
git clone https://github.com/W1theri/GroceryStore-OOP-Project.git
cd GroceryStore-OOP-Project
```

### 2. Open in IntelliJ IDEA
- Open IntelliJ IDEA
- File → Open → Select project folder
- Wait for project to load

### 3. Run Main.java
- Navigate to `src/Main.java`
- Right-click → Run 'Main.main()'
- Or press `Shift + F10`

### 4. Follow the interactive menu prompts

---

## 📊 Example Usage

### Adding a Fresh Product
```
Enter your choice: 2
--- ADD FRESH PRODUCT 🍎 ---
Enter product ID: 106
Enter product name: Apple
Enter price (KZT): 500
Enter stock quantity: 150
Enter expiry date (YYYY-MM-DD): 2025-02-15
Is organic? (true/false): true

✅ Fresh product added successfully!
[Fresh Product] Product{productId=106, name='Apple', price=500.00 KZT, stockQuantity=150} | Expiry: 2025-02-15 | 🌿 ORGANIC
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

## 🔑 Key Concepts Demonstrated

### 1. Abstract Class vs Interface

**When to use Abstract Class (Product):**
- Have common code to share (concrete methods like `isInStock()`, `restock()`)
- Want to enforce certain methods in children (abstract methods)
- Related classes share fields and some behavior

**When to use Interface (Menu, Perishable):**
- Define "what" something can do (capability/contract)
- No shared implementation needed
- Want to support multiple implementations



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

#### PackagedProduct (extends Product)
- **Inherited & Overridden:** `getFormattedPrice()` shows price per kg
- **Unique Methods:** `getPricePerKg()`, `isLightweight()`, `isBulk()`
- **Special Features:** Bulk discount for packages > 2kg

---

## 📈 Project Statistics

- **Classes**: 10 (Product, FreshProduct, PackagedProduct, Customer, Sale, Menu, MenuManager, Perishable, InvalidProductException, Main)
- **Abstract Classes**: 1 (Product)
- **Interfaces**: 2 (Menu, Perishable)
- **Custom Exceptions**: 1 (InvalidProductException)
- **Inheritance Levels**: 2 (Parent → Child)
- **Polymorphic ArrayList**: 1 (ArrayList<Product>)
- **Menu Options**: 11+ interactive operations
- **Lines of Code**: ~1000+ (excluding comments)
- **Validation Points**: 20+ input validation checks with exceptions

---

## ✅ Learning Outcomes

### Week 6 Achievements
- ✅ Implemented abstract class with abstract methods
- ✅ Created and implemented multiple interfaces
- ✅ Built custom exception class
- ✅ Refactored all setters to throw exceptions
- ✅ Added comprehensive try-catch blocks
- ✅ Separated menu logic into MenuManager
- ✅ Achieved clean Main.java (7 lines!)
- ✅ Professional package structure (menu/, exception/, model/)
- ✅ Real date handling with LocalDate API
- ✅ Demonstrated difference between abstract class and interface

### Previous Weeks (Cumulative)
- ✅ Encapsulation with getters/setters
- ✅ Inheritance hierarchy
- ✅ Polymorphism with method overriding
- ✅ ArrayList for dynamic storage
- ✅ Interactive console application
- ✅ Input validation and error handling

---



## 👨‍💻 Author

**Abilmansur** - [W1theri](https://github.com/W1theri)

---

## 📚 Course Information

- **Course**: Object-Oriented Programming (OOP)
- **Institution**: AITU (Astana IT University)
- **Assignment**: Week 6 - Interfaces, Abstract Classes & Exception Handling
- **Date**: 20 January 2025


