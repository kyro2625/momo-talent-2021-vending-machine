package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validation {
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
    public static boolean checkInputYN() {
        //System.out.print("Do you want to continue? (Y/N): ");
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
    public static boolean checkRewardCondition(List<String> productNames) {
        String [] names = productNames.toArray(new String[0]);
        int count=0;
        for (int i = 0; i < names.length-1 ; i++) {
            System.out.println(names[i]);
            if (names[i+1].equals(names[i])) {
                count++;
            } else {
                count = 0;
            }
            if (count==3) {
                return true;
            }
        }
        return false;
    }

}
