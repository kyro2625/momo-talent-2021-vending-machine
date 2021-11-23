package util;

import java.util.Scanner;

public class Validation {
    /**
     * This function is to show check if the Input String is valid or not
     */
    public static String checkInputString() {
        Scanner in = new Scanner(System.in);
        //loop until user input correct
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else return result;
        }
    }
    /**
     * This function is to check if the user input Y/N or not
     */
    public static boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y"))
                break;
            if (result.equalsIgnoreCase("N"))
                return false;
            System.err.println("Please input y/Y or n/N");
            System.out.print("Enter again: ");
        }
        return true;
    }

}
