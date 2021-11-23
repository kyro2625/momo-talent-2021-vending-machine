package util;

import entity.Product;
import gui.Menu;

import java.util.ArrayList;

public class Controller {
    final static int budgetLimit = 50000;
    final static int cokePrice = 10000;
    final static int pepsiPrice = 10000;
    final static int sodaPrice = 20000;
    final static double defaultRate = 0.1;

    static int budget = budgetLimit, day = 1, receiveMoney = 0, amountOfCoke = 0, amountOfPepsi = 0, amountOfSoda = 0;
    static double upperRate = defaultRate;
    static boolean isReceiveReward = false, participateGacha = false;
    static ArrayList<Product> orderedProducts = new ArrayList<>();

    /**
     * This function is to show main menu and option to process
     */
    public static void startMachine() throws InterruptedException {
        Menu menu = new Menu();
        initializeMenu(menu);
        int userChoice;
        do {
            displayInformation();
            for (Object str : menu) {
                System.out.println(str);
            }
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1 -> insertCash();
                case 2 -> buyProduct();
                case 3 -> changeDay();
                case 4 -> cancelRequest();
                case 5 -> {}
                default -> System.out.println("No supported. Enter choice again");
            }
        } while (userChoice >= 0 && userChoice != menu.size());
    }

    /**
     * This function is to show menu
     */
    public static void initializeMenu(Menu menu) {
        menu.add("1. Choose the notes (Top up)");
        menu.add("2. Choose products");
        menu.add("3. Go to next day");
        menu.add("4. Finish the transaction");
        menu.add("5. Quit program");
    }

    /**
     * This function is to display the information about day, transaction balance, products have bought
     */
    public static void displayInformation() {
//        System.out.println("\nPlease choose the note:");
        System.out.println("Day" + day);
//        System.out.println("UpperRate" + upperRate);
//        System.out.println("Budget" + budget);
        System.out.println("Transaction balance: " + receiveMoney + " VND");
        System.out.println("Purchased products: " + amountOfCoke + " Coke, " + amountOfPepsi + " Pepsi, " + amountOfSoda + " Soda");
    }

    /**
     * This function is to show Top up Menu
     */
    public static void subMenuCash(Menu menu1) {
        menu1.add("     1 - 10.000VND");
        menu1.add("     2 - 20.000VND");
        menu1.add("     3 - 50.000VND");
        menu1.add("     4 - 100.000VND");
        menu1.add("     5 - 200.000VND");
        menu1.add("     6 - Return to main menu");
    }

    /**
     * This function is to show Top up option
     */
    public static void insertCash() {
        Menu menu1 = new Menu();
        subMenuCash(menu1);
        int userChoice1;
        do {
            System.out.println("     --Choose the note--");
            System.out.println("     Transaction balance: " + receiveMoney + " VND");
            for (Object sub : menu1) System.out.println(sub);
            userChoice1 = menu1.getUserSubChoice();
            switch (userChoice1) {
                case 1 -> {
                    receiveMoney += 10000;
                    System.out.println();
                }
                case 2 -> {
                    receiveMoney += 20000;
                    System.out.println();
                }
                case 3 -> {
                    receiveMoney += 50000;
                    System.out.println();
                }
                case 4 -> {
                    receiveMoney += 100000;
                    System.out.println();
                }
                case 5 -> {
                    receiveMoney += 200000;
                    System.out.println();
                }
                case 6 -> {

                }
                default -> System.out.println("     No supported function. Enter choice again");
            }
        } while (userChoice1 >= 0 && userChoice1 != menu1.size());
        System.out.println();
    }

    /**
     * This function is to show Product menu
     */
    public static void subMenuProduct(Menu menu2) {
        menu2.add("     1 - Coke (10.000VND)");
        menu2.add("     2 - Pepsi (10.000VND)");
        menu2.add("     3 - Soda (20.000VND)");
        menu2.add("     4 - Return to main menu");
    }

    /**
     * This function is to show product option
     */
    public static void buyProduct() {
        if (receiveMoney > 0) {
            int userChoice2;
            Menu menu2 = new Menu();
            subMenuProduct(menu2);
            do {
                System.out.println("     --Choose the product--");
                displayInformation();
                for (Object sub : menu2) System.out.println(sub);
                userChoice2 = menu2.getUserSubChoice();
                switch (userChoice2) {
                    case 1 -> {
                        orderedProducts.add(new Product("Coke",cokePrice));
                        if (receiveMoney < cokePrice) {
                            System.err.println("Please top up " + (cokePrice - receiveMoney) + " VND before buying");
                        } else {
                            receiveMoney -= cokePrice;
                            amountOfCoke++;
                        }
                        System.out.println();
                    }
                    case 2 -> {
                        orderedProducts.add(new Product("Pepsi", pepsiPrice));
                        if (receiveMoney < pepsiPrice) {
                            System.err.println("Please top up " + (pepsiPrice - receiveMoney) + " VND before buying");
                        } else {
                            receiveMoney -= pepsiPrice;
                            amountOfPepsi++;
                        }
                        System.out.println();
                    }
                    case 3 -> {
                        orderedProducts.add(new Product("Soda", sodaPrice));
                        if (receiveMoney < sodaPrice) {
                            System.err.println("Please top up " + (sodaPrice - receiveMoney) + " VND before buying");
                        } else {
                            receiveMoney -= sodaPrice;
                            amountOfSoda++;
                        }
                        System.out.println();
                    }
                    case 4 -> {
                    }
                    default -> System.out.println("     No supported function. Enter choice again");
                }
            } while (userChoice2 >= 0 && userChoice2 != menu2.size());
            System.out.println();
        } else {
            System.out.println("\nPlease top up before buying!!!");
        }
    }

    /**
     * This function is to change to the next day and check if the budget is not met the limit, so it will rate up the win rate, if it is then restart the rate up and budget limit
     */
    public static void changeDay() {
        if (receiveMoney==0 && amountOfPepsi==0 && amountOfCoke==0 && amountOfSoda==0) {
            if (budget > 0) {
                budget = budgetLimit;
                if (upperRate < 1) {
                    upperRate = upperRate + upperRate * 0.5;
                }
                if (upperRate >= 1) upperRate = 1;
            } else {
                budget = budgetLimit;
                upperRate = defaultRate;
            }
            day++;
        } else {
            System.out.println("\nYou can not change day when in the current transaction!");
        }
    }

    /**
     * This function is to check if the user want to finish the transaction and then receive the refund
     */
    public static void cancelRequest() throws InterruptedException {
        System.out.print("Do you want end the transaction? (y/n): ");
        if (Validation.checkInputYN()) {
            Validation.checkConsecutiveBuying(orderedProducts);
            System.out.println("Thank you for using our service!");
            if (receiveMoney > 0) {
                System.out.println("Please receive the refund: " + receiveMoney + " VND");
            }
            receiveMoney = 0;
            amountOfCoke = 0;
            amountOfPepsi = 0;
            amountOfSoda = 0;
            participateGacha=false;
            isReceiveReward=false;
        }
    }
}
