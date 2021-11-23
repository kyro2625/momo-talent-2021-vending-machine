package gui;

import java.util.Scanner;
import java.util.Vector;

public class Menu extends Vector<String> {
    public Menu() {
        super();
    }
    /**
     * This function is to get the user choice input in the main menu
     */
    public int getUserChoice() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        boolean valid;
        do {
            System.out.print("Choose: ");
            valid = true;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                valid = false;
            }
            if (choice < 0) valid = false;
            if (!valid) System.out.print("Choose again! ");
        } while (!valid);
        return choice;
    }
    /**
     * This function is to get the user choice in the sub menu
     */
    public int getUserSubChoice() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        boolean valid;
        do {
            System.out.print("      Choose: ");
            valid = true;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                valid = false;
            }
            if (choice < 0) valid = false;
            if (!valid) System.out.print("       Choose again! ");
        } while (!valid);
        return choice;
    }
}