# WMS-COM5043
# Warehouse Management System - COM5043

A Java-based, menu-driven Warehouse Management System built as part of the COM5043 Software Engineering coursework. This application allows you to manage suppliers, products, inventory, customer orders, purchase orders, and financial tracking — all via a text-based interface.

---

## 📦 Features

- **Product Management**: Add and view products with low stock alerts
- **Supplier Management**: Add, view, and track supplier details and their order history
- **Purchase Orders**: Create and receive supplier purchase orders, automatically updating inventory and recording expenses
- **Customer Orders**: Create multi-item customer orders, process them against inventory, and track revenue
- **Financial Reporting**: Track revenue, expenses, and calculate net income
- **Receipt & History Viewing**: View receipts for individual customer orders and supplier purchase history
- **Automated Testing**: JUnit 5 tests follow the Arrange–Act–Assert pattern to ensure reliability

---

## Technologies Used

- Java 11
- Maven (for build and dependency management)
- JUnit 5 (for testing)

---

## Project Structure

```
WMS-COM5043/
├── src/
│   ├── main/java/wms/         # All main application classes
│   └── test/java/wms/         # All JUnit test classes
├── pom.xml                    # Maven configuration
└── README.md                  # This file
```

---

## How to Run the Application

1. Clone or download the repository
2. Open a terminal in the project root
3. Compile and run the program:

```
mvn compile
mvn exec:java -Dexec.mainClass="wms.Main"
```

> You may need to configure the exec plugin in `pom.xml` if not already done.

---

## How to Run Tests

Tests are written using JUnit 5 and follow the AAA (Arrange–Act–Assert) pattern.

```
mvn test
```

Expected output:
```
Tests run: 46, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Testing Summary

- `ProductTest` — Tests restocking, price, and low-stock logic
- `CustomerOrderTest` — Tests product addition and total calculation
- `PurchaseOrderTest` — Tests price calculation and status updates
- `WarehouseManagerTest` — Tests core workflow like processing and receiving orders
- `FinancialReportTest` — Tests revenue/expense tracking and net income calculation

---

## Reflection

This system demonstrates core Object-Oriented Programming principles:
- **Encapsulation** via private fields and accessors
- **Inheritance & Polymorphism** with the abstract `Order` superclass
- **Responsibility Separation** between manager, domain, and data classes

All functionality was designed based on assignment requirements and tested for accuracy.

---

## Author

Saharsh Didigam — BSc Software Engineering (Year 2)

---

## Feedback

Feel free to report bugs or suggest improvements via GitHub Issues or email!
