/*
 * @author jquinlan
 * Program that displays products with name, code, description, price and 
 * quantity and finds the total price. LIMIT 10 ITEMS
 */
public class Homework4 {
    public static void main(String[] args) {
        Product product = new Product();
        
        // Ask the user if they want to use the GUI
        if(userResponseGUI() == 'y') {
            Homework4UI gui = new Homework4UI();
            gui.setVisible(true);
        }
        else {
            inputUserName(product);
            inputName(product);
            inputCode(product);
            inputDescript(product);
            inputQuantity(product);
            inputPrice(product);
            printScreen(product);
        }
    }
    
    // Method to input username.
    public static void inputUserName(Product product) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        ReadFile file = new ReadFile();
        
        
        System.out.println("Enter customer ID: ");
        String inputID = scanner.nextLine();
        product.customerID = inputID;
        // Parse "users.txt" to find matching ID.
        for(int i = 0; (file.userLines[i] != null); i++) {
            if(file.userLines[i].matches(inputID) == true) {
                product.firstName = file.userLines[i - 2];
                product.lastName = file.userLines[i - 1];
                product.setUserName(product.firstName + product.lastName);
                continue;
            }
        }
    }
    
    // Method to input product names. The user can enter an empty entry when finished.
    // There are three products already added by deault.
    public static void inputName(Product product) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        product.setIndex(5); // Set index to 5 to account for the deafult items.
        
        // Loop until the item limit is reached or when the user is done.
        while(product.getIndex() < product.getMAX()) {
            System.out.println("Enter product " + (product.getIndex()+1) 
                    + " name  (or insert blank when finished): ");
            product.setName(scanner.nextLine());
            
            if(product.getName().isEmpty() == true)
                break;
            product.incIndex(); // Increment product.index by one.
        }
    }
    
    // Method to input product codes.
    public static void inputCode(Product product) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        product.setIndex(5);
        
        while(product.getName().isEmpty() == false) {
            System.out.println("Enter product " + (product.getIndex()+1) 
                    + " code (" + product.getName() + "): ");
            product.setCode(scanner.nextLine());
            product.incIndex();
        }
            
    }
    
    // Method to input item descriptions.
    public static void inputDescript(Product product) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        product.setIndex(5);
        
        while(product.getName().isEmpty() == false) {
            System.out.println("Enter product " + (product.getIndex()+1) 
                    + " description (" + product.getName() + "): ");
            product.setDescript(scanner.nextLine());
            product.incIndex();
        }
    }
    
    // Method to input item prices.
    public static void inputPrice(Product product) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        product.setIndex(5);
        
        while(product.getName().isEmpty() == false) {
            System.out.println("Enter product " + (product.getIndex()+1) 
                    + " (" + product.getName() + ") price: ");
            String productPriceCheck = scanner.nextLine();
            // Check to see if the user entered a valid price.
            // If the string input is a double, true. If not, false and return error.
            if(productPriceCheck.matches("[0-9.]+") == false) {
                System.out.println("Invalid input!");
                continue;
            }
            else {
                double value = Double.parseDouble(productPriceCheck);
                product.setPrice(value);
            }
            product.incIndex();
        }
    }
    
    // Method to input product quantity.
    public static void inputQuantity(Product product) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        product.setIndex(0); // Set index to 0 so the user can change the default quantities.
        
        while(product.getName().isEmpty() == false) {
            System.out.println("Enter product " + (product.getIndex()+1) 
                    + " (" + product.getName() + ") quantity: ");
            String productQuantityCheck = scanner.nextLine();
            // Check to see if the user entered a valid quantity.
            // If the string input is an integer, true. If not, false and return error.
            if(productQuantityCheck.matches("[0-9]+") == false) {
                System.out.println("Invalid input!");
                continue;
            }
            // Set the quantity by parsing a double and casting it to an integer.
            // Since it is now know that the double is really an integer, this shouldn't be a problem.
            else {
                double value = Double.parseDouble(productQuantityCheck);
                product.setQuantity(((int) value));
            }
            product.incIndex();
        }
    }
    
    // Method to display results.
    public static void printScreen(Product product) {
        product.setIndex(0);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        char yn = 'a'; // Single Y(yes) or N(no) character.
        String ynString; // Y/N character check.
        // Loop until the user enters a valid answer.
        while((Character.toUpperCase(yn) != 'Y') && (Character.toUpperCase(yn) != 'N')) {
            System.out.println("Do you want to pay in full? (y/n): ");
            ynString = scanner.nextLine();
            yn = ynString.charAt(0); // If the first letter is Y/y or N/n, loop will terminate.
        }
        System.out.println(product.getUserName() 
                + "\n  |  Product  | Code |      Description      | Price | Quantity | Subtotal |");
        // Loop and print arrays.
        while(product.getName().isEmpty() == false) {
            System.out.printf("   %11s", product.getName());
            System.out.printf(" %6s", product.getCode());
            System.out.printf(" %23s", product.getDescript());
            System.out.printf(" $%6.2f", product.getPrice());
            System.out.printf(" %10d", product.getQuantity());
            // If the quantity is greater than 10, add a discount.
            // A new price is assigned with a discount modifier.
            if(product.getQuantity() >= 10) {
                product.setPrice(product.getPrice() * (1.0 - product.ORDER_10));
                System.out.printf("  $%8.2f",(product.getQuantity() * product.getPrice()));
                System.out.print(" (%" + (100.0*product.ORDER_10 + " DISCOUNT)\n"));
            }
            else
                System.out.printf("  $%8.2f\n",(product.getQuantity() * product.getPrice()));
            product.incIndex();
        }
                
        System.out.println("\t\t\t\t\t\t\t\t      Total");
        System.out.println("\t\t\t\t\t\t\t\t   --------");
        // Find total sum by passing arrays and applying taxes and full pay discounts.
        if(Character.toUpperCase(yn) == 'Y') {
            System.out.print("\t\t\t\t     (PAY IN FULL, %" 
                    + (100.0*product.FULLPAY) + " DISCOUNT) ");
            System.out.printf("$%8.2f",((1.0+product.TAX) 
                    * (total(product) * (1.0 - product.FULLPAY))));
        }
        else
            System.out.printf("\t\t\t\t\t\t\t\t  $%8.2f",
                    ((1.0+product.TAX) * (total(product))));
        System.out.println(" (%" + (100.0*product.TAX) + " SALES TAX)");
    }
    
    // Method takes the sum of the price times the quantity of each product.
    public static double total(Product product) {
        double total = 0;
        product.setIndex(0);
        while(product.getName() != "") {
            total = total + (product.getPrice() * product.getQuantity());
            product.incIndex();
        }
        return total;
    }
    
    // Special response method for the GUI to use
    public static char userResponseGUI() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        char yn = 'a';
        String ynString;
        while((Character.toUpperCase(yn) != 'Y') && (Character.toUpperCase(yn) != 'N')) {
            System.out.println("Do you want to use the GUI? (y/n): ");
            ynString = scanner.nextLine();
            yn = ynString.charAt(0); // If the first letter is Y/y or N/n, loop will terminate.
        }
        
        return yn;
    }
    
    
}