import java.io.*;
import java.util.Scanner;
public class FinalSalary {

    public static double basicSalary;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter per day payment: ");
        double perDayPayment = sc.nextDouble();
        System.out.print("Enter number of days: ");
        int noOfDays = sc.nextInt();
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
