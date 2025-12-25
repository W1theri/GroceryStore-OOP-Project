#  Grocery Store Management System

A Java-based grocery store management system with interactive console menu, implementing core OOP principles: classes, objects, encapsulation, data validation, and collection management.


##  Features

### Data Validation & Interactive Menu
- **Input Validation**: Prevents negative prices, empty names, invalid quantities
- **Console Menu System**: User-friendly interface with 6 operations
- **ArrayList Integration**: Dynamic storage for multiple objects
- **Real-time Feedback**: Warnings and success messages
- **Auto-upgrade System**: Customer membership levels adjust based on purchases

## Menu Options

```
========================================
    🛒 GROCERY STORE SYSTEM 🛒
========================================
1. Add Product       - Register new products
2. View All Products - Display inventory
3. Add Customer      - Register new customers
4. View All Customers- View customer database
5. Add Sale          - Record transactions
6. View All Sales    - Sales history & analytics
0. Exit              - Close application
========================================
```

##  Validation Rules

### Product Validation
-  Product ID must be positive
-  Name cannot be empty
-  Price cannot be negative (set to 0 if invalid)
-  Stock quantity cannot be negative

### Customer Validation
-  Customer ID must be positive
-  Name cannot be empty
-  Total purchases cannot be negative
-  Auto-upgrade membership based on spending:
    - Standard: < 20,000 KZT
    - Silver: 20,000 - 49,999 KZT
    - Gold: 50,000 - 99,999 KZT
    - Platinum: ≥ 100,000 KZT

### Sale Validation
-  Sale ID must be positive
-  Customer name cannot be empty
-  Total amount cannot be negative
-  Date must be provided

##  How to Run

1. **Clone the repository:**
git clone https://github.com/W1theri/GroceryStore-OOP-Project.git
cd GroceryStore-OOP-Project


2. **Open in IntelliJ IDEA**

3. **Run Main.java**

4. **Follow the interactive menu prompts**

##  Example Usage

### Adding a Product
```
Enter your choice: 1
--- ADD PRODUCT ---
Enter product ID: 104
Enter product name: Cheese
Enter price (KZT): 1500
Enter stock quantity: 25

✅ Product added successfully!
```

### Viewing Products
```
Enter your choice: 2
========================================
          ALL PRODUCTS
========================================
Total products: 4

1. Milk - 650.00 KZT
   ID: 101
   Stock: 50 units
   In Stock: ✅ Yes

2. Bread - 200.00 KZT
   ID: 102
   Stock: 100 units
   In Stock: ✅ Yes
...
```

### Validation Example
```
Enter price (KZT): -1400
⚠️ Warning: Price cannot be negative! Setting to 0.
```

## Technical Implementation

### Core OOP Principles
- **Encapsulation**: Private fields with controlled access via getters/setters
- **Data Validation**: All setters include input validation logic
- **Method Overriding**: Custom toString() methods for readable output
- **Collection Management**: ArrayList for dynamic data storage

### Key Methods by Class

**Product:**
- `isInStock()` - Check availability
- `restock(int)` - Add inventory
- `sell(int)` - Process sale with validation
- `applyDiscount(double)` - Apply percentage discount
- `isExpensive()` - Check premium status

**Customer:**
- `isVIP()` - Check VIP status (>50k purchases)
- `addPurchase(double)` - Add to purchase history
- `getDiscountPercentage()` - Get membership discount
- `updateMembershipLevel()` - Auto-upgrade tier

**Sale:**
- `addItem(double)` - Add item to transaction
- `applyDiscount(double)` - Apply sale discount
- `calculateTax()` - Compute 12% tax
- `getTotalWithTax()` - Final amount
- `isLargeSale()` - Check if >20k KZT

## Learning Outcomes


### Achievements 
- Enhanced encapsulation with comprehensive validation
- Built interactive console application using Scanner
- Implemented ArrayList for dynamic data management
- Created user-friendly interface with error handling
- Added real-time feedback and data integrity checks


## Author

Abilmansur - [W1theri](https://github.com/W1theri)

## Course Information

**Institution**: AITU (Astana IT University)  
**Course**: Object-Oriented Programming  

---


## Notes

- Initial test data is loaded on startup for demonstration
- All monetary values in Kazakhstan Tenge (KZT)
- Tax rate: 12% (Kazakhstan standard)
- Premium product threshold: 5,000 KZT
- VIP customer threshold: 50,000 KZT total purchases

---
