package academy.javapro;

public class ProductCatalog {
    private final Object[] items;
    private int size;

    public ProductCatalog(int capacity) {
        this.items = new Object[capacity];
        this.size = 0;
    }

    public void add(Object item) {
        if (size < items.length) {
            items[size++] = item;
        }
    }

    public Object get(int index) {
        return items[index];
    }

    public void set(int index, Object item) {
        items[index] = item;
    }

    public int size() {
        return size;
    }
}