package question3;

import java.util.Scanner;

public class CheckPassFail {
    public static void main(String[] args) {
        // Create a Scanner object
        Scanner input = new Scanner(System.in);
        try {
            // Prompt the user to enter a mark
            System.out.print("Enter mark: ");
            int mark = input.nextInt();
            // Check if the mark is greater than or equal to 50
            if (mark >= 50) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            }
        } finally {
            // Close the Scanner object
            System.out.println("DONE");
            // Close the Scanner object
            input.close();
        }
    }
}
