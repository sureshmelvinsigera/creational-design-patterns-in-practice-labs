public class LabValidator {
    static int passed = 0;
    static int failed = 0;

    static void check(String test, boolean condition, String failMsg) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + test);
        } else {
            failed++;
            System.out.println("FAIL: " + test + " - " + failMsg);
        }
    }

    public static void main(String[] args) {

        // Fix #1: Wrong cast
        try {
            ProductCatalog c = Lab.buildCatalog1();
            Electronics e = (Electronics) c.get(0);
            check("Fix 1: Cast to Electronics", true, "");
            check("Fix 1: Name accessible", e.getName() != null, "getName() returned null");
            check("Fix 1: Warranty accessible", e.getWarrantyMonths() > 0, "getWarrantyMonths() returned 0");
        } catch (ClassCastException e) {
            failed++;
            System.out.println("FAIL: Fix 1 - still adding wrong type: " + e.getMessage());
        }

        // Fix #2: Silent type corruption
        try {
            ProductCatalog c = Lab.buildCatalog2();
            Electronics k = (Electronics) c.get(0);
            check("Fix 2: set() uses Electronics", true, "");
            check("Fix 2: Warranty readable", k.getWarrantyMonths() == 24,
                    "expected 24 got " + k.getWarrantyMonths());
        } catch (ClassCastException e) {
            failed++;
            System.out.println("FAIL: Fix 2 - set() still stores wrong type");
        }

        // Fix #3: Mixed types in loop
        try {
            ProductCatalog c = Lab.buildCatalog3();
            double total = 0;
            for (int i = 0; i < c.size(); i++) {
                total += ((Electronics) c.get(i)).getWarrantyMonths();
            }
            check("Fix 3: All items are Electronics", true, "");
            check("Fix 3: Total warranty > 0", total > 0, "total was " + total);
        } catch (ClassCastException e) {
            failed++;
            System.out.println("FAIL: Fix 3 - non-Electronics still in catalog");
        }

        System.out.println();
        System.out.println("Results: " + passed + "/" + (passed + failed) + " passed");
        if (failed == 0) System.out.println("ALL TESTS PASSED");
    }
}