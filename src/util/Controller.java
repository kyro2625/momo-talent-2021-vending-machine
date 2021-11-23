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

    static int budget = budgetLimit, day = 1, receiveMoney = 0, amountOfCoke = 0, amountOfPepsi = 0, amountOfSoda = 0, consecutiveSelection = 0, consecutiveProduct = 0;
    static double upperRate = defaultRate;
    static ArrayList<Product> orderedProducts = new ArrayList<>();

    public static void startMachine() {
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
                default -> System.out.println("No supported. Enter choice again");
            }
        } while (userChoice >= 0 && userChoice != menu.size());
    }

    public static void initializeMenu(Menu menu) {
        menu.add("1. Choose the notes (Top up)");
        menu.add("2. Choose products");
        menu.add("3. Go to day 2");
        menu.add("4. Cancel the transaction");
        menu.add("5. Quit program");
    }

    public static void displayInformation() {
        System.out.println("\nPlease choose the note:");
        System.out.println("Day" + day);
        System.out.println("UpperRate" + upperRate);
        System.out.println("Budget" + budget);
        System.out.println("Remain money in the machine: " + receiveMoney);
        System.out.println("Selected Product: " + amountOfCoke + " Coke, " + amountOfPepsi + " Pepsi, " + amountOfSoda + " Soda");
    }

    public static void subMenuCash(Menu menu1) {
        menu1.add("     1 - 10.000VND");
        menu1.add("     2 - 20.000VND");
        menu1.add("     3 - 50.000VND");
        menu1.add("     4 - 100.000VND");
        menu1.add("     5 - 200.000VND");
        menu1.add("     6 - Return to main menu");
    }

    public static void insertCash() {
        Menu menu1 = new Menu();
        subMenuCash(menu1);
        int userChoice1;
        do {
            System.out.println("     --Choose the note--");
            System.out.println("     Remain money in the machine: " + receiveMoney);
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

    public static void subMenuProduct(Menu menu2) {
        menu2.add("     1 - Coke (10.000VND)");
        menu2.add("     2 - Pepsi (10.000VND)");
        menu2.add("     3 - Soda (20.000VND)");
        menu2.add("     4 - Return to main menu");
    }

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
                        orderedProducts.add(new Product("Coke"));
                        if (receiveMoney < cokePrice) {
                            System.err.println("Please top up " + (cokePrice - receiveMoney) + " VND before buying");
                        } else {
                            receiveMoney -= cokePrice;
                            amountOfCoke++;
                        }
                        System.out.println();
                    }
                    case 2 -> {
                        orderedProducts.add(new Product("Pepsi"));
                        if (receiveMoney < pepsiPrice) {
                            System.err.println("Please top up " + (pepsiPrice - receiveMoney) + " VND before buying");
                        } else {
                            receiveMoney -= pepsiPrice;
                            amountOfPepsi++;
                        }
                        System.out.println();
                    }
                    case 3 -> {
                        orderedProducts.add(new Product("Soda"));
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

    public static void checkConsecutiveBuying() {
        Product currentProduct = null;
        for (Product product : orderedProducts) {
            if (currentProduct == null) {
                currentProduct = product;
                consecutiveSelection = 1;
            } else if (!currentProduct.getName().equalsIgnoreCase(product.getName())) {
                currentProduct = product;
                consecutiveSelection = 1;
            } else consecutiveSelection++;
            if (consecutiveSelection == 3) {
                checkAndAward(currentProduct);
                currentProduct = null;
                consecutiveSelection = 0;
            }
        }
    }

    public static void checkAndAward(Product currentProduct) {
        if (budget > 0) {
            if (Gacha.getRandomNumber(0, 1) <= upperRate) {
                System.out.println("You have won 1 free " + currentProduct.getName());
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

    public static void changeDay() {
        if (budget > 0) {
            if (upperRate < 1) {
                upperRate = upperRate + upperRate * 0.5;
            }
            if (upperRate >= 1) upperRate = 1;
        } else {
            budget = budgetLimit;
            upperRate = defaultRate;
        }
        day++;
    }

    public static void cancelRequest() {
        //                    String [] productNames = new String[products.size()];
//                    if (!products.isEmpty()) {
//                        Validation.checkRewardCondition(products);
//                    }
//                    System.out.println(productNames[2]);
        checkConsecutiveBuying();

//        System.out.print("Do you want end the transaction? (y/n): ");
//        if (Validation.checkInputYN()) {
//            System.out.println("Thank you for using our service!");
//            if (receiveMoney > 0) {
        System.out.println("Please receive the refund: " + receiveMoney + " VND");
        receiveMoney = 0;
        amountOfCoke = 0;
        amountOfPepsi = 0;
        amountOfSoda = 0;
//            }
//        }
    }


}
