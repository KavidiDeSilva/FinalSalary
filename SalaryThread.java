import java.io.*;
import java.util.Scanner;

public class SalaryThread {

    public static double basicSalary;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());
        Thread t3 = new Thread(new Task3());

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
    }

    public void calculateSalary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Payment Per Day: ");
        String perDayPaymentStr = scanner.nextLine();
        if(perDayPaymentStr == null || perDayPaymentStr.isEmpty()) {
            System.out.println("Empty input. Please enter valid input.");
            return;
        }
        double perDayPayment = Double.parseDouble(perDayPaymentStr);

        System.out.println("Enter No of Days: ");
        String noOfDaysStr = scanner.nextLine();
        if(noOfDaysStr == null || noOfDaysStr.isEmpty()) {
            System.out.println("Empty input. Please enter valid input.");
            return;
        }
        int noOfDays = Integer.parseInt(noOfDaysStr);


        basicSalary = perDayPayment * noOfDays;
       

        double finalSalary = basicSalary + Task2.allowances + Task3.epf;
        System.out.println("Final Salary: " + finalSalary);
    }


}


class Task2 implements Runnable {
    public static double allowances;
    public void run() {
        allowances = SalaryThread.basicSalary * 0.05;
    }
}

class Task3 implements Runnable {
    public static double epf;
    public void run() {
        double totalMonthlyEarnings = SalaryThread.basicSalary + Task2.allowances;
        double epfDeduction = totalMonthlyEarnings * 0.08;
        double employerContribution = totalMonthlyEarnings * 0.12;
        epf = epfDeduction + employerContribution;
    }
}