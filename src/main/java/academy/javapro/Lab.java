package academy.javapro;

public class Lab {
    public static void main(String[] args) {

        // ── Crash #1: Wrong cast ──────────────────────────────────────────
        System.out.println("=== Crash #1: Wrong cast ===");

        ProductCatalog catalog1 = new ProductCatalog(3);
        catalog1.add(new Product("P001", "Laptop", 1299.99));
        catalog1.add(new Product("P002", "Monitor", 399.99));

        // BUG: catalog1 holds Products but this casts to Electronics
        Electronics e = (Electronics) catalog1.get(0);
        System.out.println("Name: " + e.getName());
        System.out.println("Warranty: " + e.getWarrantyMonths());


        // ── Crash #2: Silent type corruption ────────────────────────────
        System.out.println("\n=== Crash #2: Silent type corruption ===");

        ProductCatalog catalog2 = new ProductCatalog(2);
        catalog2.add(new Electronics("E001", "Keyboard", 89.99, 24));

        // BUG: someone replaces an Electronics with a plain String
        catalog2.set(0, "This should not be here");

        // Later, this cast crashes because the value is now a String
        Electronics keyboard = (Electronics) catalog2.get(0);
        System.out.println("Keyboard warranty: " + keyboard.getWarrantyMonths());


        // ── Crash #3: Mixed types, wrong retrieval ──────────────────────
        System.out.println("\n=== Crash #3: Mixed types, wrong retrieval ===");

        ProductCatalog catalog3 = new ProductCatalog(3);
        catalog3.add(new Electronics("E001", "Webcam", 79.99, 12));
        catalog3.add(new Product("P003", "Desk", 249.99));            // plain Product slipped in
        catalog3.add(new Electronics("E002", "Mouse", 29.99, 6));

        double totalWarranty = 0;
        for (int i = 0; i < catalog3.size(); i++) {
            // BUG: assumes every item is Electronics, but index 1 is a plain Product
            Electronics item = (Electronics) catalog3.get(i);
            totalWarranty += item.getWarrantyMonths();
        }
        System.out.println("Total warranty months: " + totalWarranty);


        System.out.println("\n=== All crashes fixed! ===");
    }
}