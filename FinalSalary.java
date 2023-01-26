import java.io.*;
import java.util.Scanner;
public class FinalSalary {

    public static void main(String[] args) throws InterruptedException {
        // creating three threads
        Thread t1 = new Thread(new Task1(), "Mainthread");
        Thread t2 = new Thread(new Task2(), "thread2");
        Thread t3 = new Thread(new Task3(), "thread3");

        // call run() method
        t1.start();
        t2.start();
        t3.start();


        t1.join();
        t2.join();
        t3.join();
    }
}

class Task1 implements Runnable {
    public static double basicSalary;
    public void run() {
        double perDayPayment = 500;
        int noOfDays = 20;
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
