import java.util.ArrayList;
import java.util.Scanner;

// Base class
class Account {
    protected String accountHolder;
    protected int accountNumber;
    protected double balance;
    protected ArrayList<String> transactions;

    public Account(String accountHolder, int accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: ₹" + amount);
        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            transactions.add("Failed Withdrawal Attempt: ₹" + amount);
        } else {
            balance -= amount;
            transactions.add("Withdrawn: ₹" + amount);
            System.out.println("₹" + amount + " withdrawn successfully.");
        }
    }

    public void showBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("---------------------------\n");
    }
}

// Inherited class with method overriding
class SavingsAccount extends Account {
    public SavingsAccount(String accountHolder, int accountNumber) {
        super(accountHolder, accountNumber);
    }

    // Overriding withdraw to add minimum balance check
    @Override
    public void withdraw(double amount) {
        if (balance - amount < 500) {
            System.out.println("Cannot withdraw. Minimum ₹500 must be maintained.");
            transactions.add("Failed Withdrawal Attempt (Minimum Balance Violation): ₹" + amount);
        } else {
            super.withdraw(amount);
        }
    }
}

// Main class to simulate
public class BankSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        SavingsAccount acc = new SavingsAccount(name, accNo);

        int choice;
        do {
            System.out.println("\n----- BANK MENU -----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double dep = sc.nextDouble();
                    acc.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double with = sc.nextDouble();
                    acc.withdraw(with);
                    break;
                case 3:
                    acc.showBalance();
                    break;
                case 4:
                    acc.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using our Bank System.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
