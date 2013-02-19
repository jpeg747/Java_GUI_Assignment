/*
 * @author jquinlan
 * Class to be used with Homework4.java and Homework4UI.java
 * Provides String arrays read from external files.
 */

import java.io.*;

public class ReadFile {
    public String[] inputLines = new String[100];
    public String[] userLines = new String[100];
    // Object for input/user files.
    public ReadFile() {
        try {
            FileInputStream fstream = new FileInputStream("input.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int i = 0;
            // Read file and insert into string
            while((inputLines[i] = br.readLine()) != null) {
                inputLines[i] = inputLines[i].trim();
                i++;
            }
            // Close file when done
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        try {
            FileInputStream fstream = new FileInputStream("users.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int i = 0;
            while((userLines[i] = br.readLine()) != null) {
                userLines[i] = userLines[i].trim();
                i++;
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } 
    }
    
    public String getName(int input) {
        return inputLines[(input) * 4];
    }
    
    public String getCode(int input) {
        return inputLines[(input) * 4 + 1];
    }
    
    public String getDescript(int input) {
        return inputLines[(input) * 4 + 2];
    }
    
    public double getPrice(int input) {
        return Double.parseDouble(inputLines[(input) * 4 + 3]);
    }
    
}
    

