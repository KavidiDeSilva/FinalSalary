import java.io.*;
import java.util.Scanner;
public class FinalSalary {

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

        double finalSalary = Task1.basicSalary + Task2.allowances + Task3.epf;
        System.out.println("Final Salary: " + finalSalary);       
    }

}

class Task1 implements Runnable {
    Scanner scanner = new Scanner(System.in);
    public static double basicSalary;
    public void run() {
        double perDayPayment = 1000;
        int noOfDays = 30;
        basicSalary = perDayPayment * noOfDays;
        System.out.println("Basic Salary = " + basicSalary); 
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
