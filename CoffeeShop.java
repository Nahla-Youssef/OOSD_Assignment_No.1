import java.util.*;
public class CoffeeShop {
    private String name;
    private MenuItem[] menu;
    private String[] orders;
    
    public CoffeeShop(String name, MenuItem[] menu) {
        this.name = name;
        this.menu = menu;
        this.orders = new String[0];
    }
    
    public String addOrder(String item) {
        for (MenuItem menuItem : menu) {
            if (menuItem.getItem().equalsIgnoreCase(item)) {
                String[] newOrders = new String[orders.length + 1];
                System.arraycopy(orders, 0, newOrders, 0, orders.length);
                newOrders[orders.length] = item;
                orders = newOrders;
                return "Order added!";
            }
        }
        return "This item is currently unavailable!";
    }
    
    public String fulfillOrder() {
        if (orders.length == 0) {
            return "All orders have been fulfilled!";
        }
        String item = orders[0];
        String[] newOrders = new String[orders.length - 1];
        System.arraycopy(orders, 1, newOrders, 0, orders.length - 1);
        orders = newOrders;
        return "The " + item + " is ready!";
    }
    
    public String[] listOrders() {
        return orders;
    }
    
    public double dueAmount() {
        double total = 0;
        for(MenuItem menuItem : menu) {
            for (String order : orders) {
                if (menuItem.getItem().equalsIgnoreCase(order)) {
                    total += menuItem.getPrice();
                }
            }
        }
        return total;
    }
    
    public String cheapestItem() {
        double minPrice = Double.MAX_VALUE;
        String cheapest = "";
        for (MenuItem menuItem : menu) {
            if (menuItem.getType().equalsIgnoreCase("food") && menuItem.getPrice() < minPrice) {
                minPrice = menuItem.getPrice();
                cheapest = menuItem.getItem();
            }
        }
        return cheapest;
    }
    
    public String[] drinksOnly() {
        List<String> drinkItems = new ArrayList<>();
        for (MenuItem menuItem : menu) {
            if (menuItem.getType().equalsIgnoreCase("drink")) {
                drinkItems.add(menuItem.getItem());
            }
        }
        return drinkItems.toArray(new String[0]);
    }
    
    public String[] foodOnly() {
        List<String> foodItems = new ArrayList<>();
        for (MenuItem menuItem : menu) {
            if (menuItem.getType().equalsIgnoreCase("food")) {
                foodItems.add(menuItem.getItem());
            }
        }
        return foodItems.toArray(new String[0]);
    }
}


//The Main
public static void main(String[] args) {
    MenuItem[] menu = {
        new MenuItem("tuna sandwich", "food", 4.5),
        new MenuItem("ham and cheesesandwich", "food", 5.0),
        new MenuItem("cinnamon roll", "food", 2.0),
        new MenuItem("iced coffee", "drink", 2.0),
        new MenuItem("orange juice", "drink", 2.5),
        new MenuItem("lemonade", "drink", 1.5),
        new MenuItem("cranberry juice", "drink", 3.0),
        new MenuItem("pineapple juice", "drink", 2.75),
        new MenuItem("bacon and egg sandwich", "food", 4.5)
    };   
    CoffeeShop tes = new CoffeeShop("Tesha's Coffee Shop", menu);    
    System.out.println(tes.addOrder("hot cocoa")); // This item is currently unavailable!
    System.out.println(tes.addOrder("iced tea")); // This item is currently unavailable!
    System.out.println(tes.addOrder("cinnamon roll")); // Order added!
    System.out.println(tes.addOrder("iced coffee")); // Order added!
    System.out.println(Arrays.toString(tes.listOrders())); // [cinnamon roll, iced coffee]
    System.out.println(tes.dueAmount()); // 4.0
    System.out.println(tes.fulfillOrder()); // The cinnamon roll is ready!
    System.out.println(tes.fulfillOrder()); // The iced coffee is ready!
    System.out.println(tes.fulfillOrder()); // All ordershave been fulfilled!
    System.out.println(Arrays.toString(tes.listOrders())); // []
    System.out.println(tes.dueAmount()); // 0.0
    System.out.println(tes.cheapestItem()); // lemonade
    System.out.println(Arrays.toString(tes.drinksOnly())); // [iced coffee, orange juice, lemonade, cranberry juice, pineapple juice]
    System.out.println(Arrays.toString(tes.foodOnly())); // [tuna sandwich, ham and cheesesandwich, cinnamon roll, bacon and egg sandwich]
}