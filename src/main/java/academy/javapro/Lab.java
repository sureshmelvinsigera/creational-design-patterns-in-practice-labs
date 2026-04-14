package academy.javapro;

public class Lab {

    // Fix #1: Wrong cast — change new Product(...) to new Electronics(...)
    public static ProductCatalog buildCatalog1() {
        ProductCatalog catalog = new ProductCatalog(2);
        catalog.add(new Product("P001", "Laptop", 1299.99));   // BUG
        catalog.add(new Product("P002", "Monitor", 399.99));   // BUG
        return catalog;
    }

    // Fix #2: Silent type corruption — change the set() argument to Electronics
    public static ProductCatalog buildCatalog2() {
        ProductCatalog catalog = new ProductCatalog(2);
        catalog.add(new Electronics("E001", "Keyboard", 89.99, 24));
        catalog.set(0, "This should not be here");             // BUG
        return catalog;
    }

    // Fix #3: Mixed types — change new Product(...) to new Electronics(...)
    public static ProductCatalog buildCatalog3() {
        ProductCatalog catalog = new ProductCatalog(3);
        catalog.add(new Electronics("E001", "Webcam",  79.99, 12));
        catalog.add(new Product("P003", "Desk", 249.99));      // BUG
        catalog.add(new Electronics("E002", "Mouse",  29.99,  6));
        return catalog;
    }

    public static void main(String[] args) {
        System.out.println("Run Tests to validate your fixes.");
    }
}