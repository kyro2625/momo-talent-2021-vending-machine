package util;

import entity.Product;

import java.util.ArrayList;
import java.util.Scanner;

import static util.Controller.*;

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
            System.err.println("___Please input y/Y or n/N___");
            System.out.print("      Enter again: ");
        }
        return true;
    }

    /**
     * This function is to check the Consecutive purchased products and then process the gacha if the condition is met
     */
    public static void checkConsecutiveBuying(ArrayList<Product> orderedProducts) {
        Product currentProduct = null;
        int consecutiveSelection = 0;
        for (Product product : orderedProducts) {
            if (currentProduct == null) {
                currentProduct = product;
                consecutiveSelection = 1;
            } else if (!currentProduct.getName().equalsIgnoreCase(product.getName())) {
                currentProduct = product;
                consecutiveSelection = 1;
            } else if (currentProduct.getName().equalsIgnoreCase(product.getName())) consecutiveSelection++;
            if (consecutiveSelection == 3) {
                checkAndAward(currentProduct, upperRate);
                currentProduct = null;
                consecutiveSelection = 0;
            }
        }
    }

    /**
     * This function is to check the budget of the machine and then process the gacha
     *
     * @param currentProduct purchased products met the condition
     */
    public static void checkAndAward(Product currentProduct, double upperRate) {
        if (budget > 0) {
            if (Gacha.getRandomNumber(0, 1) <= upperRate) {
                if (budget < currentProduct.getPrice()) {
                    System.out.println("            ------You have won 1 free Coke------");
                } else {
                    System.out.println("            ------You have won 1 free " + currentProduct.getName() + "------");
                    if (currentProduct.getName().equalsIgnoreCase("Coke")) {
                        amountOfCoke++;
                        budget -= cokePrice;
                    } else if (currentProduct.getName().equalsIgnoreCase("Pepsi")) {
                        amountOfPepsi++;
                        budget -= pepsiPrice;
                    } else if (currentProduct.getName().equalsIgnoreCase("Soda")) {
                        amountOfSoda++;
                        budget -= sodaPrice;
                    }
                }
            }
        }
    }
}
