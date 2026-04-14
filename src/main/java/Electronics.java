public class Electronics extends Product {
    private final int warrantyMonths;

    public Electronics(String id, String name, double price, int warrantyMonths) {
        super(id, name, price);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() { return warrantyMonths; }

    @Override
    public String toString() {
        return super.toString() + " [" + warrantyMonths + "mo warranty]";
    }
}