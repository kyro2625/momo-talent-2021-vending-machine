package gui;

import entity.Product;
import util.Gacha;
import util.Validation;

import java.util.ArrayList;

public class VendingMachine {
    private final static int budgetLimit = 50000;
    private final static int cokePrice = 10000;
    private final static int pepsiPrice = 10000;
    private final static int sodaPrice = 20000;
    private final static double defaultRate = 0.1;

    public static void main(String[] args) {
        int budget = budgetLimit, day = 1, receiveMoney = 0, amountOfCoke = 0, amountOfPepsi = 0, amountOfSoda = 0, consecutiveSelection = 0, consecutiveProduct = 0;
        double upperRate = defaultRate;
        ArrayList<Product> products = new ArrayList<>();
        Menu menu = new Menu();
        menu.add("1. Choose the notes (Top up)");
        menu.add("2. Choose products");
        menu.add("3. Go to day 2");
        menu.add("4. Cancel the transaction");
        menu.add("5. Quit program");
        int userChoice;
        do {
            System.out.println("\nPlease choose the note:");
            System.out.println("Day" + day);
            System.out.println("UpperRate" + upperRate);
            System.out.println("Budget" + budget);
            System.out.println("Remain money in the machine: " + receiveMoney);
            System.out.println("Selected Product: " + amountOfCoke + " Coke, " + amountOfPepsi + " Pepsi, " + amountOfSoda + " Soda");
            for (Object str : menu) {
                System.out.println(str);
            }
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1 -> {
                    Menu menu1 = new Menu();
                    int userChoice1;
                    menu1.add("     1 - 10.000VND");
                    menu1.add("     2 - 20.000VND");
                    menu1.add("     3 - 50.000VND");
                    menu1.add("     4 - 100.000VND");
                    menu1.add("     5 - 200.000VND");
                    menu1.add("     6 - Return to main menu");
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
                case 2 -> {
                    if (receiveMoney > 0) {
                        Menu menu2 = new Menu();
                        int userChoice2;
                        menu2.add("     1 - Coke (10.000VND)");
                        menu2.add("     2 - Pepsi (10.000VND)");
                        menu2.add("     3 - Soda (20.000VND)");
                        menu2.add("     4 - Return to main menu");
                        do {
                            System.out.println("     --Choose the product--");
                            System.out.println("     Remain money in the machine: " + receiveMoney);
                            System.out.println("     Product has bought: " + amountOfCoke + " Coke, " + amountOfPepsi + " Pepsi, " + amountOfSoda + " Soda");
                            for (Object sub : menu2) System.out.println(sub);
                            userChoice2 = menu2.getUserSubChoice();
                            switch (userChoice2) {
                                case 1 -> {
                                    products.add(new Product("Coke"));
                                    if (receiveMoney < cokePrice) {
                                        System.err.println("Please top up " + (cokePrice - receiveMoney) + " VND before buying");
                                    } else {
                                        receiveMoney -= cokePrice;
                                        if (consecutiveProduct != userChoice2) {
                                            consecutiveProduct = userChoice2;
                                            consecutiveSelection = 1;
                                        } else {
                                            consecutiveSelection++;
                                        }
                                        if (consecutiveSelection == 3) {
                                            consecutiveProduct = 0;
                                            consecutiveSelection = 0;
                                            if (budget > 0) {
                                                if (Gacha.getRandomNumber(0, 1) <= upperRate) {
                                                    System.out.println("You have won 1 free Coke");
                                                    amountOfCoke++;
                                                    budget -= cokePrice;
                                                }
                                            }
                                        }
                                        amountOfCoke++;
                                    }
                                    System.out.println();
                                }
                                case 2 -> {
                                    products.add(new Product("Pepsi"));
                                    if (receiveMoney < pepsiPrice) {
                                        System.err.println("Please top up " + (pepsiPrice - receiveMoney) + " VND before buying");
                                    } else {
                                        receiveMoney -= pepsiPrice;
                                        if (consecutiveProduct != userChoice2) {
                                            consecutiveProduct = userChoice2;
                                            consecutiveSelection = 1;
                                        } else {
                                            consecutiveSelection++;
                                        }
                                        if (consecutiveSelection == 3) {
                                            consecutiveProduct = 0;
                                            consecutiveSelection = 0;
                                            if (budget > 0) {
                                                if (Gacha.getRandomNumber(0, 1) <= upperRate) {
                                                    System.out.println("You have won 1 free Pepsi");
                                                    amountOfPepsi++;
                                                    budget -= pepsiPrice;
                                                }
                                            }
                                        }
                                        amountOfPepsi++;
                                    }
                                    System.out.println();
                                }
                                case 3 -> {
                                    products.add(new Product("Soda"));
                                    if (receiveMoney < sodaPrice) {
                                        System.err.println("Please top up " + (sodaPrice - receiveMoney) + " VND before buying");
                                    } else {
                                        receiveMoney -= sodaPrice;
                                        if (consecutiveProduct != userChoice2) {
                                            consecutiveProduct = userChoice2;
                                            consecutiveSelection = 1;
                                        } else {
                                            consecutiveSelection++;
                                        }
                                        if (consecutiveSelection == 3) {
                                            consecutiveProduct = 0;
                                            consecutiveSelection = 0;
                                            if (budget > 0) {
                                                if (Gacha.getRandomNumber(0, 1) <= upperRate) {
                                                    System.out.println("You have won 1 free Soda");
                                                    amountOfSoda++;
                                                    budget -= sodaPrice;
                                                }
                                            }
                                        }
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
                case 3 -> {
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

                case 4 -> {
                    String [] productNames = new String[products.size()];
                    if (!products.isEmpty()) {
                        Validation.checkRewardCondition(products);
                    }
                    System.out.println(productNames[2]);
                    System.out.print("Do you want cancel the transaction? (y/n): ");
                    if (Validation.checkInputYN()) {
                        System.out.println("Thank you for using our service!");
                        if (receiveMoney > 0) {
                            System.out.println("Please receive the refund: " + receiveMoney + " VND");
                            receiveMoney = 0;
                        }
                    }
                }
                default -> System.out.println("No supported. Enter choice again");
            }
        } while (userChoice >= 0 && userChoice != menu.size());
    }
}


