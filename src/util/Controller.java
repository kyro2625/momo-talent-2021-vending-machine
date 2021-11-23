package util;

import entity.Product;

import java.util.ArrayList;

public class Controller {
    final static int budgetLimit = 50000;
    final static int cokePrice = 10000;
    final static int pepsiPrice = 10000;
    final static int sodaPrice = 20000;
    final static double defaultRate = 0.1;

    int budget = budgetLimit, day = 1, receiveMoney = 0, amountOfCoke = 0, amountOfPepsi = 0, amountOfSoda = 0, consecutiveSelection = 0, consecutiveProduct = 0;
    double upperRate = defaultRate;
    ArrayList<Product> products = new ArrayList<>();

    public static void startMachine() {

    }
}
