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
            System.out.println("8. Exit");
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
                        warehouseManager.createPurchaseOrder(pid, foundSupplier, foundProduct, poQ);
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

                    break;
                default:
                    break;
            }
        }

    }

}
