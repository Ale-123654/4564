import java.util.;

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return id +   + name +  - $ + price;
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        return product.getPrice()  quantity;
    }

    @Override
    public String toString() {
        return product.getName() +  x  + quantity +  = $ + getTotalPrice();
    }
}

class ShoppingCart {
    private ListCartItem items = new ArrayList();

    public void addProduct(Product product, int quantity) {
        for (CartItem item  items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(int productId) {
        items.removeIf(item - item.getProduct().getId() == productId);
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item  items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println(Ваша корзина пуста.);
            return;
        }
        System.out.println(Ваша корзина);
        for (CartItem item  items) {
            System.out.println(item);
        }
        System.out.println(Итого $ + getTotal());
    }

    public void clear() {
        items.clear();
    }
}

public class OnlineStore {
    private static ListProduct products = new ArrayList();
    private static ShoppingCart cart = new ShoppingCart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initProducts();

        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case 1
                    showProducts();
                    break;
                case 2
                    addToCart();
                    break;
                case 3
                    cart.showCart();
                    break;
                case 4
                    checkout();
                    break;
                case 5
                    System.out.println(Спасибо за покупку! До свидания.);
                    return;
                default
                    System.out.println(Неверный выбор. Попробуйте снова.);
            }
        }
    }

    private static void initProducts() {
        products.add(new Product(1, Футболка, 15.99));
        products.add(new Product(2, Джинсы, 49.99));
        products.add(new Product(3, Кроссовки, 79.99));
        products.add(new Product(4, Кепка, 9.99));
    }

    private static void showMenu() {
        System.out.println(n=== Онлайн магазин ===);
        System.out.println(1. Показать каталог товаров);
        System.out.println(2. Добавить товар в корзину);
        System.out.println(3. Показать корзину);
        System.out.println(4. Оформить заказ);
        System.out.println(5. Выйти);
        System.out.print(Выберите действие );
    }

    private static void showProducts() {
        System.out.println(Каталог товаров);
        for (Product p  products) {
            System.out.println(p);
        }
    }

    private static void addToCart() {
        System.out.print(Введите ID товара для добавления );
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Ошибка ввода.);
            return;
        }
        Product product = findProductById(id);
        if (product == null) {
            System.out.println(Товар с таким ID не найден.);
            return;
        }

        System.out.print(Введите количество );
        int qty;
        try {
            qty = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Ошибка ввода.);
            return;
        }
        if (qty = 0) {
            System.out.println(Количество должно быть больше 0.);
            return;
        }

        cart.addProduct(product, qty);
        System.out.println(Товар добавлен в корзину.);
    }

    private static Product findProductById(int id) {
        for (Product p  products) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println(Ваша корзина пуста. Добавьте товары перед оформлением заказа.);
            return;
        }
        cart.showCart();
        System.out.print(Подтвердить заказ (данет) );
        String answer = scanner.nextLine().trim().toLowerCase();
        if (answer.equals(да)) {
            System.out.println(Заказ оформлен! Спасибо за покупку.);
            cart.clear();
        } else {
            System.out.println(Заказ отменен.);
        }
    }
}
