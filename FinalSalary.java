import java.io.*;
import java.util.Scanner;
public class FinalSalary {

    public static double basicSalary;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        double perDayPayment = 0;
        int noOfDays = 0;

        //check invalid input
        while (true) {
            System.out.println("Enter Payment Per Day: ");
            String input = sc.nextLine();
            if (input.isEmpty() || input == null) {
                System.out.println("Invalid input. Please enter a valid per day payment: ");
                continue;
            }
            try {
                perDayPayment = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid per day payment: ");
            }
        }

        while (true) {
            System.out.println("Enter No of Days: ");
            String input = sc.nextLine();
            if (input.isEmpty() || input == null) {
                System.out.println("Invalid input. Please enter a valid number of days: ");
                continue;
            }
            try {
                noOfDays = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number of days: ");
            }
        }
        sc.close();

        
        // creating three threads
        Thread t1 = new Thread(new Task1(), "Mainthread");
        Thread t2 = new Thread(new Task2(), "thread2");
        Thread t3 = new Thread(new Task3(), "thread3");

        t1.start();
        t1.join();
        t2.start();
        t3.start();
        t2.join();
        t3.join();

        basicSalary = perDayPayment * noOfDays;
        System.out.println("Basic Salary = " + basicSalary); 

        double finalSalary = basicSalary + Task2.allowances + Task3.epf;
        System.out.println("Final Salary: " + finalSalary);
    }

}

class Task2 implements Runnable {
    public static double allowances;
    public void run() {
        allowances = Task1.basicSalary * 0.05;
        System.out.println("Allowances = " + allowances); 
    }
}

class Task3 implements Runnable {
    public static double epf;
    public void run() {
        double totalMonthlyEarnings = Task1.basicSalary + Task2.allowances;
        double epfDeduction = totalMonthlyEarnings * 0.08;
        double employerContribution = totalMonthlyEarnings * 0.12;
        epf = epfDeduction + employerContribution;
        System.out.println("EPF = " + epf);
    }
}
