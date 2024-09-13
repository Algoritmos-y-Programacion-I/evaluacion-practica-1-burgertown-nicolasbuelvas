package ui;

import java.util.Scanner;

/**
 * 
 * @author Nicolas santiago buelvas gonzalez
 * @version 1.0
 * 
 */

public class BurgerTown {

    public static Scanner reader;
    public static double[] prices;
    public static int[] quantities;

    public static void main(String[] args) {

        initializeGlobals();
        menu();
    }

    /**
     * Description: This method initializes the global variables.
     * pre: The Scanner reader must be declared.
     * post: The Scanner reader is initialized.
    */
    public static void initializeGlobals() {

        reader = new Scanner(System.in);

    }

    /**
     * Description: This method displays the menu to the user and shows result messages for each functionality.
     * pre: The Scanner reader must be initialized.
     * pre: The prices array must be initialized.
    */
    public static void menu() {

        System.out.println("Welcome to BurgerTown!");

        setQuantitySold();

        boolean exit = false;

        do {

            System.out.println("\nMain Menu:");
            System.out.println("1. Request prices ($) and quantities of each dish sold in the day");
            System.out.println("2. Calculate the total number of dishes sold in the day");
            System.out.println("3. Calculate the average price of the dishes sold in the day");
            System.out.println("4. Calculate the total sales (money collected) during the day");
            System.out.println("5. Query the number of dishes that have exceeded a minimum sales limit");
            System.out.println("6. Exit");
            System.out.println("\nEnter the option to execute");
            int option = reader.nextInt();

            switch (option) {
                case 1:
                    requestData();
                    break;
                case 2:
                    System.out.println("\nThe total number of dishes sold in the day was: " + calculateTotalDishesSold());
                    break;
                case 3:
                    System.out.println("\nThe average price of the dishes sold in the day was: " + calculateAveragePrice());
                    break;
                case 4:
                    System.out.println("\nThe total sales (money collected) during the day were: " + calculateTotalSales());
                    break;
                case 5:
                    System.out.println("\nEnter the minimum sales limit to analyze");
                    double limit = reader.nextDouble();
                    System.out.println("\nOut of the " + prices.length + " references sold in the day, " + queryDishesAboveLimit(limit) + " exceeded the minimum sales limit of " + limit);
                    break;
                case 6:
                    System.out.println("\nThank you for using our services!");
                    exit = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nInvalid option, please try again.");
                    break;
            }

        } while (!exit);

    }

    /**
     * Description: This method asks the user for the number of different dishes sold in the day and initializes the arrays 
     * for storing prices and quantities with that value.
     * pre: The Scanner reader must be initialized.
     * pre: The arrays prices and quantities must be declared.
     * post: The arrays prices and quantities are initialized.
     */
    public static void setQuantitySold() {

        System.out.println("\nEnter the number of different dishes sold in the day");
        int dishes = reader.nextInt();

        prices = new double[dishes];
        quantities = new int[dishes];

    }

    /**
     * Description: This method requests the price and quantity of each dish sold and stores the data in arrays.
     * pre: The arrays prices and quantities must be initialized.
     * post: The arrays prices and quantities are populated with user input.
     */
    public static void requestData() {
        for (int i = 0; i < prices.length; i++) {
            System.out.println("\nEnter the price of dish " + (i + 1) + ":");
            prices[i] = reader.nextDouble();
            System.out.println("Enter the quantity of dish " + (i + 1) + " sold:");
            quantities[i] = reader.nextInt();
        }
    }

    /**
     * Description: This method calculates the total number of dishes sold in the day.
     * pre: The arrays quantities must be initialized.
     * post: Returns the total number of dishes sold.
     * @return The total number of dishes sold.
     */
    public static int calculateTotalDishesSold() {
        int total = 0;
        for (int quantity : quantities) {
            total += quantity;
        }
        return total;
    }

    /**
     * Description: This method calculates the average price of the dishes sold in the day.
     * pre: The arrays prices and quantities must be initialized.
     * post: Returns the average price of the dishes sold.
     * @return The average price of the dishes sold.
     */
    public static double calculateAveragePrice() {
        double totalRevenue = 0;
        int totalDishes = 0;
        for (int i = 0; i < prices.length; i++) {
            totalRevenue += prices[i] * quantities[i];
            totalDishes += quantities[i];
        }
        return totalDishes == 0 ? 0 : totalRevenue / totalDishes;
    }

    /**
     * Description: This method calculates the total sales (money collected) during the day.
     * pre: The arrays prices and quantities must be initialized.
     * post: Returns the total sales amount.
     * @return The total sales amount.
     */
    public static double calculateTotalSales() {
        double totalSales = 0;
        for (int i = 0; i < prices.length; i++) {
            totalSales += prices[i] * quantities[i];
        }
        return totalSales;
    }

    /**
     * Description: This method queries the number of dishes that have exceeded a minimum sales limit.
     * pre: The arrays prices and quantities must be initialized.
     * post: Returns the number of dishes exceeding the sales limit.
     * @param limit The minimum sales limit.
     * @return The number of dishes exceeding the sales limit.
     */
    public static int queryDishesAboveLimit(double limit) {
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] * quantities[i] > limit) {
                count++;
            }
        }
        return count;
    }
}