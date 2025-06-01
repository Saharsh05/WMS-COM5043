package wms;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        WarehouseManager warehouseManager = new WarehouseManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("Warehouse Management Menu");
            System.out.println("1. Add product");
            System.out.println("2. Add supplier");
            System.out.println("3. Create purchase order");
            System.out.println("4. Receive purchase order");
            System.out.println("5. Create customer order");
            System.out.println("6. Process customer order");
            System.out.println("7. View products");
            System.out.println("8. View suppliers");
            System.out.println("9. View supplier order history");
            System.out.println("10. View Finance Report");
            System.out.println("11. Update supplier");
            System.out.println("12. Remove supplier");
            System.out.println("13. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter product name ");
                    String name = scanner.nextLine();

                    System.out.println("Enter the quantity");
                    int quantity = scanner.nextInt();

                    System.out.println("Enter the price");
                    double price = scanner.nextDouble();

                    System.out.println("Enter the low threshold amount");
                    int threshold = scanner.nextInt();

                    Product product = new Product(id, name, quantity, price, threshold);

                    warehouseManager.addProduct(product);
                    break;

                case 2:
                    System.out.println("Enter the name of the supplier");
                    String supplierName = scanner.nextLine();

                    System.out.println("Enter the Id of the supplier");
                    int supplierId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Add the contact information of the supplier");
                    String contact = scanner.nextLine();

                    Supplier supplier = new Supplier(supplierId, supplierName, contact);

                    warehouseManager.addSupplier(supplier);
                    break;
                case 3:
                    System.out.println("Enter Purchase order ID");
                    int poId = scanner.nextInt();

                    System.out.println("Enter supplier ID");
                    int sId = scanner.nextInt();

                    System.out.println("Enter the product ID");
                    int pid = scanner.nextInt();

                    System.out.println("Enter the quantity to order");
                    int poQ = scanner.nextInt();

                    Supplier foundSupplier = null;
                    Product foundProduct = null;

                    for (Supplier s : warehouseManager.getSuppliers()) {
                        if (s.getId() == sId) {
                            foundSupplier = s;
                            break;
                        }
                    }
                    for (Product p : warehouseManager.getProducts()) {
                        if (p.getId() == pid) {
                            foundProduct = p;
                            break;
                        }
                    }

                    if (foundSupplier != null && foundProduct != null) {
                        warehouseManager.createPurchaseOrder(poId, foundSupplier, foundProduct, poQ);
                    } else {
                        System.out.println("Invalid supplier or product ID");
                    }
                    break;
                case 4:
                    System.out.print("Enter purchase order ID to receive: ");
                    int receiveId = scanner.nextInt();
                    warehouseManager.receivePurchaseOrder(receiveId);
                    break;

                case 5:

                    System.out.println("Enter customer order ID");
                    int coID = scanner.nextInt();
                    scanner.nextLine();

                    CustomerOrder co = new CustomerOrder(coID);

                    boolean addingItems = true;

                    while (addingItems) {
                        System.out.println(
                                "Enter the ID of the product you are purchasing (or -1 if you want to exit the program)");
                        int pId = scanner.nextInt();

                        if (pId == -1) {
                            addingItems = false;
                            break;
                        }

                        System.out.println("How many units off " + pId + " do you need");
                        int pQuantity = scanner.nextInt();

                        Product match = null;
                        for (Product p : warehouseManager.getProducts()) {
                            if (p.getId() == pId) {
                                match = p;
                                break;
                            }
                        }

                        if (match != null) {
                            co.addProduct(match, pQuantity);
                        } else {
                            System.out.println("Product not found");
                        }

                    }
                    warehouseManager.addCustomerOrder(co);
                    System.out.println("Total order value: £" + co.calculateTotal());
                    break;

                case 6:
                    System.out.println("Enter customer ID to process");
                    int processId = scanner.nextInt();

                    CustomerOrder orderProcess = null;
                    for (CustomerOrder order : warehouseManager.getCustomerOrder()) {
                        if (order.getId() == processId) {
                            orderProcess = order;
                            break;
                        }
                    }
                    if (orderProcess != null) {
                        warehouseManager.processCustomerOrder(orderProcess);
                        System.out.println("Total order value: £" + orderProcess.calculateTotal());
                    } else {
                        System.out.println("Customer order not found");
                    }
                    break;
                case 7:
                    System.out.println("Products in Warehouse");
                    for (Product p : warehouseManager.getProducts()) {
                        System.out.println(p);
                        if (p.isLowStock()) {
                        System.out.println("LOW STOCK");
                            } else {
                            System.out.println();
                        }
                    }
                    break;
                case 8: 
                    System.out.println(" Suppliers:");
                     for (Supplier s : warehouseManager.getSuppliers()) {
                        System.out.println(
                        "ID: " + s.getId() +
                        ", Name: " + s.getName() +
                        ", Contact: " + s.getContactInfo() +
                        ", Orders: " + s.getOrderHistory().size()
                    );
                     }
                    break;
                case 9:
                     System.out.print("Enter supplier ID to view order history: ");
                    int sID = scanner.nextInt();
                    Supplier supplier1 = warehouseManager.getSupplierById(sID);

                    if (supplier1 != null) {
                    warehouseManager.printSupplierOrderHistory(supplier1);
                    } else {
                    System.out.println("Supplier not found.");
                    }

                    break;
                case 10:
                    System.out.println("Financial Report:");
                    System.out.println("Total revenue: £"
                            + String.format("%.2f", warehouseManager.getFinancialReport().getTotalRevenue()));
                    System.out.println("Total Expenses: £"
                            + String.format("%.2f", warehouseManager.getFinancialReport().getTotalExpenses()));
                    System.out.println("Net Income: £"
                            + String.format("%.2f", warehouseManager.getFinancialReport().getNetIncome()));
                    break;
                case 11:
                    System.out.print("Enter supplier ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); 

                    Supplier toUpdate = warehouseManager.getSupplierById(updateId);

                    if (toUpdate != null) {
                        System.out.print("Enter new name (or leave blank to keep current): ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter new contact info (or leave blank to keep current): ");
                        String newContact = scanner.nextLine();

                        warehouseManager.updateSupplier(updateId, newName, newContact);
                    } else {
                        System.out.println("Supplier not found.");
                    }
                    break;
                case 12:
                    System.out.print("Enter supplier ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine(); 

                    warehouseManager.removeSupplier(removeId); 
                    break;
                case 13:
                    System.out.println("Exiting system");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        scanner.close();
    }

}
