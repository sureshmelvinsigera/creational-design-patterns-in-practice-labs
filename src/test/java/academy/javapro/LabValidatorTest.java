package academy.javapro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LabValidatorTest {

    @Test
    void fix1_castToElectronics() {
        ProductCatalog c = new ProductCatalog(2);
        c.add(new Electronics("E001", "Laptop", 1299.99, 24));
        assertDoesNotThrow(() -> {
            Electronics e = (Electronics) c.get(0);
        }, "Fix 1: Still adding wrong type - ClassCastException thrown");
    }

    @Test
    void fix1_nameAccessible() {
        ProductCatalog c = new ProductCatalog(2);
        c.add(new Electronics("E001", "Laptop", 1299.99, 24));
        Electronics e = (Electronics) c.get(0);
        assertNotNull(e.getName(), "Fix 1: getName() returned null");
    }

    @Test
    void fix1_warrantyAccessible() {
        ProductCatalog c = new ProductCatalog(2);
        c.add(new Electronics("E001", "Laptop", 1299.99, 24));
        Electronics e = (Electronics) c.get(0);
        assertTrue(e.getWarrantyMonths() > 0, "Fix 1: getWarrantyMonths() returned 0");
    }

    @Test
    void fix2_setUsesElectronics() {
        ProductCatalog c = new ProductCatalog(2);
        c.add(new Electronics("E001", "Keyboard", 89.99, 24));
        assertDoesNotThrow(() -> {
            c.set(0, new Electronics("E001", "Keyboard", 89.99, 24));
            Electronics k = (Electronics) c.get(0);
        }, "Fix 2: set() still stores wrong type");
    }

    @Test
    void fix2_warrantyReadable() {
        ProductCatalog c = new ProductCatalog(2);
        c.add(new Electronics("E001", "Keyboard", 89.99, 24));
        c.set(0, new Electronics("E001", "Keyboard", 89.99, 24));
        Electronics k = (Electronics) c.get(0);
        assertEquals(24, k.getWarrantyMonths(), "Fix 2: expected 24 warranty months");
    }

    @Test
    void fix3_allItemsAreElectronics() {
        ProductCatalog c = new ProductCatalog(3);
        c.add(new Electronics("E001", "Webcam",  79.99, 12));
        c.add(new Electronics("E002", "USB Hub", 39.99, 12));
        c.add(new Electronics("E003", "Mouse",   29.99, 6));
        assertDoesNotThrow(() -> {
            for (int i = 0; i < c.size(); i++) {
                Electronics e = (Electronics) c.get(i);
            }
        }, "Fix 3: non-Electronics still in catalog");
    }

    @Test
    void fix3_totalWarrantyCorrect() {
        ProductCatalog c = new ProductCatalog(3);
        c.add(new Electronics("E001", "Webcam",  79.99, 12));
        c.add(new Electronics("E002", "USB Hub", 39.99, 12));
        c.add(new Electronics("E003", "Mouse",   29.99, 6));
        double total = 0;
        for (int i = 0; i < c.size(); i++) {
            total += ((Electronics) c.get(i)).getWarrantyMonths();
        }
        assertTrue(total > 0, "Fix 3: total warranty was " + total);
    }
}
