/*
 * @author jquinlan
 * Class to be used with Homework4.java and Homework4UI.java. 
 * Provides the arrays and methods necessary to run Homework4.java properly.
 */

public class Product {
        final private int MAX = 10; // Maximum number of items allowed.
        final private int MIN = 3; // Minimum number of items allowed.
        private int index = 2; // Set by default to account for default entries.
        final public double TAX = 0.06; // Tax rate
        final public double ORDER_10 = 0.1; // Discount for ordering 10 or more items
        final public double FULLPAY = 0.05; // Discount for paying in full
        private String userName = "Default";
        public String firstName = "Default";
        public String lastName = "Default";
        public String customerID = "000000";
        // Each datafield of Product is allocated one additional entry.
        // This is strictly used due to the design of the array parsing algorithm.
        // The parser methods stop reading when the next entry is empty.
        private String[] productName = new String[MAX + 1];
        private String[] productCode = new String[MAX + 1];
        private String[] productDescript = new String[MAX + 1];
        private double[] productPrice = new double[MAX + 1];
        private int[] productQuantity = new int[MAX + 1];
      
        // Default values.
        public Product() {
            // Create file object for reading "output.txt"
            ReadFile file = new ReadFile();
            productName[0] = file.getName(0);
            productName[1] = file.getName(1);
            productName[2] = file.getName(2);
            productName[3] = file.getName(3);
            productName[4] = file.getName(4);
            productName[10] = "";
            productCode[0] = file.getCode(0);
            productCode[1] = file.getCode(1);
            productCode[2] = file.getCode(2);
            productCode[3] = file.getCode(3);
            productCode[4] = file.getCode(4);
            productDescript[0] = file.getDescript(0);
            productDescript[1] = file.getDescript(1);
            productDescript[2] = file.getDescript(2);
            productDescript[3] = file.getDescript(3);
            productDescript[4] = file.getDescript(4);
            productPrice[0] = file.getPrice(0);
            productPrice[1] = file.getPrice(1);
            productPrice[2] = file.getPrice(2);
            productPrice[3] = file.getPrice(3);
            productPrice[4] = file.getPrice(4);
            productQuantity[0] = 1;
            productQuantity[1] = 2;
            productQuantity[2] = 1;
            productQuantity[3] = 1;
            productQuantity[4] = 1;
        }
        
        public int getMAX() {
            return MAX;
        } 
        
        public int getMIN() {
            return MIN;
        }

        public void setName(String input) {
            productName[index] = input;
        }
        
        public String getName() {
            return productName[index];
        }
        
        public void setCode(String input) {
            productCode[index] = input;
        }
        
        public String getCode() {
            return productCode[index];
        }
        
        public void setDescript(String input) {
            productDescript[index] = input;
        }
        
        public String getDescript() {
            return productDescript[index];
        }
        
        public void setPrice(double input) {
            productPrice[index] = input;
        }
        
        public double getPrice() {
            return productPrice[index];
        }
        
        public void setQuantity(int input) {
            productQuantity[index] = input;
        }
        
        public int getQuantity() {
            return productQuantity[index];
        }
        
        public void setUserName(String input) {
            userName = input;
        }
        
        public String getUserName() {
            return userName;
        }
        
        public void incIndex() {
            index++;
        }
        
        public void setIndex(int index) {
            this.index = index;
        }
        
        public int getIndex() {
            return index;
        }    
}
