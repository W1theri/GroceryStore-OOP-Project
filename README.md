# 🛒 Grocery Store Management System

A comprehensive Java-based grocery store management system with interactive console menu, implementing advanced OOP principles including **encapsulation**, **inheritance**, **polymorphism**, and **data validation**.


##  Features

### Core OOP Implementation
- **Encapsulation**: Private/protected fields with controlled access via getters/setters
- **Inheritance**: Parent-child class hierarchy (`Product` → `FreshProduct`, `PackagedProduct`)
- **Polymorphism**: Single ArrayList stores multiple product types with dynamic behavior
- **Data Validation**: Comprehensive input validation across all classes
- **ArrayList Integration**: Dynamic storage for multiple objects
- **Real-time Feedback**: Warnings and success messages with emoji indicators

### Advanced Features
- **Interactive Console Menu**: 11+ operations with user-friendly interface
- **Auto-upgrade System**: Customer membership tiers adjust automatically based on purchases
- **Polymorphic Behavior**: Same method calls produce different behaviors based on object type
- **Type Filtering**: View products by specific types using `instanceof`
- **Downcasting**: Access child-specific methods when needed

---

##  Menu Options

```
╔════════════════════════════════════════╗
║     GROCERY STORE SYSTEM               ║
║      With Inheritance                  ║
╚════════════════════════════════════════╝
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
│  10. 🛍️  Add Sale                      │
│  11. 📊 View All Sales                 │
├────────────────────────────────────────┤
│  0. 🚪 Exit                            │
└────────────────────────────────────────┘
```

---

## Class Hierarchy

### Inheritance Structure

Product (Parent Class)
├── Protected fields: productId, name, price, stockQuantity
├── Methods: isInStock(), restock(), sell(), applyDiscount()
│
├─── FreshProduct (Child Class)
│    ├── Additional fields: expiryDate, isOrganic
│    ├── Overridden methods: isInStock(), toString()
│    └── Unique methods: isExpired(), markAsExpired(), displayFreshnessInfo()
│
└─── PackagedProduct (Child Class)
     ├── Additional fields: manufacturer, weight
     ├── Overridden methods: getFormattedPrice(), toString()
     └── Unique methods: getPricePerKg(), isBulk(), displayPackageInfo()
`

##  Validation Rules

### Product Validation
- ✅ Product ID must be positive
- ✅ Name cannot be empty
- ✅ Price cannot be negative (set to 0 if invalid)
- ✅ Stock quantity cannot be negative

### FreshProduct Additional Validation
- ✅ Expiry date must be provided
- ✅ Organic status (boolean)
- ✅ Automatic stock removal when expired

### PackagedProduct Additional Validation
- ✅ Manufacturer name cannot be empty
- ✅ Weight must be positive
- ✅ Automatic price per kg calculation

### Customer Validation
- ✅ Customer ID must be positive
- ✅ Name cannot be empty
- ✅ Total purchases cannot be negative
- ✅ Auto-upgrade membership based on spending:
   - **Standard**: < 20,000 KZT
   - **Silver**: 20,000 - 49,999 KZT
   - **Gold**: 50,000 - 99,999 KZT
   - **Platinum**: ≥ 100,000 KZT

### Sale Validation
- ✅ Sale ID must be positive
- ✅ Customer name cannot be empty
- ✅ Total amount cannot be negative
- ✅ Date must be provided

---

##  How to Run

### 1. Clone the repository:

git clone https://github.com/W1theri/GroceryStore-OOP-Project.git
cd GroceryStore-OOP-Project


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

##  Example Usage

### Adding a Fresh Product
```
Enter your choice: 2
--- ADD FRESH PRODUCT 🍎 ---
Enter product ID: 106
Enter product name: Apple
Enter price (KZT): 500
Enter stock quantity: 150
Enter expiry date (YYYY-MM-DD): 2025-01-20
Is organic? (true/false): true

✅ Fresh product added successfully!
[Fresh Product] Product{productId=106, name='Apple', price=500.00 KZT, stockQuantity=150} | Expiry: 2025-01-20 | 🌿 ORGANIC
```

### Adding a Packaged Product
```
Enter your choice: 3
--- ADD PACKAGED PRODUCT 📦 ---
Enter product ID: 107
Enter product name: Flour
Enter price (KZT): 2500
Enter stock quantity: 50
Enter manufacturer: Kazakhstan
Enter weight (grams): 2000

✅ Packaged product added successfully!
[Packaged Product] Product{productId=107, name='Flour', price=2500.00 KZT, stockQuantity=50} | Manufacturer: Kazakhstan | Weight: 2000.0g
```

### Viewing All Products (Polymorphic)
```
Enter your choice: 4
╔════════════════════════════════════════╗
║     📋 ALL PRODUCTS (POLYMORPHIC)      ║
╚════════════════════════════════════════╝
Total products: 5

1. [General Product] Rice...
   
2. [Fresh Product] Apple...
   🍎 Fresh Product Details:
   Expiry: 2025-01-20
   Days Until Expiry: 7
   🌿 ORGANIC CERTIFIED

3. [Packaged Product] Flour...
   📦 Packaged Product Details:
   Manufacturer: Kazakhstan
   Weight: 2000.0g
   Price per kg: 1250.00 KZT
   📦 BULK PACKAGE
```

### Demonstrating Polymorphism
```
Enter your choice: 5
╔════════════════════════════════════════╗
║   ✨ POLYMORPHISM DEMONSTRATION ✨     ║
╚════════════════════════════════════════╝

Calling isInStock() on all products:
(Same method name, different behavior!)

Rice - ✅ In Stock
   Type: General Product
Apple - ✅ In Stock
   Type: Fresh Product
Flour - ✅ In Stock
   Type: Packaged Product

✨ Notice: Same method call (isInStock())
   but different logic for FreshProduct!
   This is POLYMORPHISM in action! 🎭
```

### Viewing Fresh Products Only
```
Enter your choice: 6
╔════════════════════════════════════════╗
║       🍎 FRESH PRODUCTS ONLY 🍎        ║
╚════════════════════════════════════════╝

1. Apple
🍎 Fresh Product: Apple
   Expiry Date: 2025-01-20
   Days Until Expiry: 7
   Organic: Yes 🌿
   Status: ✅ FRESH

📊 Total fresh products: 2
```

### Validation Example
```
Enter price (KZT): -1400
⚠️ Warning: Price cannot be negative! Setting to 0.
```

---

## 🛠️ Technical Implementation

### Core OOP Principles

#### 1. Encapsulation
- **Private fields** with controlled access via getters/setters
- **Data validation** in all setters
- **Information hiding** to protect object state

#### 2. Inheritance
- **Parent class**: `Product` with protected fields
- **Child classes**: `FreshProduct`, `PackagedProduct`
- **`extends` keyword** for class hierarchy
- **`super()` keyword** in child constructors
- **Code reusability** through inheritance

#### 3. Polymorphism
- **Single ArrayList<Product>** stores all product types
- **Method overriding** with `@Override` annotation
- **Dynamic method dispatch** based on actual object type
- **Same method name, different behavior**

#### 4. instanceof and Downcasting
- **Type checking** with `instanceof` operator
- **Safe downcasting** to access child-specific methods
- **Type filtering** for viewing specific product types

---

## Key Methods by Class

### Product (Parent Class)
- `isInStock()` - Check availability
- `getProductType()` - Return product type (overridden in children)
- `restock(int)` - Add inventory with validation
- `sell(int)` - Process sale with validation
- `applyDiscount(double)` - Apply percentage discount
- `isExpensive()` - Check premium status (>5,000 KZT)
- `getFormattedPrice()` - Format price display

### FreshProduct (Child Class)
**Inherited + Overridden:**
- `isInStock()` - **OVERRIDE**: Checks both quantity AND expiry status
- `toString()` - **OVERRIDE**: Includes expiry date and organic status

**Unique Methods:**
- `isExpired()` - Check if product has expired
- `markAsExpired()` - Mark product as expired and remove from stock
- `getDaysUntilExpiry()` - Calculate days until expiration
- `displayFreshnessInfo()` - Show detailed freshness information

### PackagedProduct (Child Class)
**Inherited + Overridden:**
- `getFormattedPrice()` - **OVERRIDE**: Shows price per kg
- `toString()` - **OVERRIDE**: Includes manufacturer and weight

**Unique Methods:**
- `getPricePerKg()` - Calculate price per kilogram
- `isLightweight()` - Check if package < 500g
- `isBulk()` - Check if package > 2kg
- `displayPackageInfo()` - Show detailed package information
- `applyBulkDiscount()` - Apply 10% discount for bulk packages

### Customer
- `isVIP()` - Check VIP status (>50k purchases)
- `addPurchase(double)` - Add to purchase history
- `getDiscountPercentage()` - Get membership discount (0-15%)
- `updateMembershipLevel()` - Auto-upgrade tier based on purchases
- `getCustomerLevel()` - Get customer tier with emoji

### Sale
- `addItem(double)` - Add item to transaction
- `applyDiscount(double)` - Apply sale discount
- `calculateTax()` - Compute 12% tax
- `getTotalWithTax()` - Calculate final amount with tax
- `isLargeSale()` - Check if transaction >20k KZT

---

## Learning Outcomes

### Week 3 Achievements ✅
-  Enhanced encapsulation with comprehensive validation
-  Built interactive console application using Scanner
-  Implemented ArrayList for dynamic data management
-  Created user-friendly interface with error handling
-  Added real-time feedback and data integrity checks

### Week 4 Achievements ✅
-  Implemented inheritance with parent-child hierarchy
-  Created polymorphic behavior using method overriding
-  Used protected fields for child class access
-  Demonstrated dynamic method dispatch
-  Applied instanceof operator for type checking
-  Performed safe downcasting for child-specific operations
-  Built comprehensive console menu demonstrating OOP principles

---

## Author

Abilmansur - [W1theri](https://github.com/W1theri)



##  Project Statistics

- **Classes**: 6 (Product, FreshProduct, PackagedProduct, Customer, Sale, Main)
- **Inheritance Levels**: 2 (Parent → Child)
- **Polymorphic ArrayList**: 1 (ArrayList<Product>)
- **Menu Options**: 11+ interactive operations
- **Lines of Code**: ~800+ (excluding comments)
- **Validation Points**: 15+ input validation checks

---

##  Notes

### System Configuration
- Initial test data loaded on startup for demonstration
- All monetary values in **Kazakhstan Tenge (KZT)**
- Tax rate: **12%** (Kazakhstan standard)
- UTF-8 encoding for emoji support

### Thresholds
- Premium product: **5,000 KZT**
- VIP customer: **50,000 KZT** total purchases
- Large sale: **20,000 KZT**
- Bulk package: **2,000g** (2kg)
- Lightweight package: **500g**

### Membership Tiers
| Tier | Spending Range | Discount |
|------|---------------|----------|
| Standard | < 20,000 KZT | 0% |
| Silver | 20,000 - 49,999 KZT | 5% |
| Gold | 50,000 - 99,999 KZT | 10% |
| Platinum | ≥ 100,000 KZT | 15% |



##  License

This project is created for educational purposes as part of OOP course at AITU

