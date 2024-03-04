import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userId = 1234;
        int pin = 5678;
        int attempts = 0;
        int choice;
        double balance = 10000.0;
        ArrayList<Transaction> transactionHistory = new ArrayList<>();
        while (attempts < 3) {
            System.out.print("Enter your user ID: ");
            int inputUserId = sc.nextInt();
            System.out.print("Enter your PIN: ");
            int inputPin = sc.nextInt();
            if (inputUserId == userId && inputPin == pin) {
                System.out.println("Welcome to the ATM!");
                while (true) {
                    System.out.println("Please select an option:");
                    System.out.println("1. Transaction History");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    System.out.println("5. Change User ID");
                    System.out.println("6. Change PIN");
                    System.out.println("7. Quit");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Transaction history:");
                            for (Transaction t : transactionHistory) {
                                System.out.println(t);
                            }
                            break;
                        case 2:
                            System.out.print("Enter amount to withdraw: ");
                            double withdrawAmount = sc.nextDouble();
                            if (withdrawAmount > balance) {
                                System.out.println("Insufficient funds.");
                            } else {
                                balance -= withdrawAmount;
                                System.out.println("Withdrawal successful. New balance: " + balance);
                                transactionHistory.add(new Transaction("Withdrawal", withdrawAmount));
                            }
                            break;
                        case 3:
                            System.out.print("Enter amount to deposit: ");
                            double depositAmount = sc.nextDouble();
                            balance += depositAmount;
                            System.out.println("Deposit successful. New balance: " + balance);
                            transactionHistory.add(new Transaction("Deposit", depositAmount));
                            break;
                        case 4:
                            System.out.print("Enter recipient's account number: ");
                            int recipientAccountNumber = sc.nextInt();
                            System.out.print("Enter transfer amount: ");
                            double transferAmount = sc.nextDouble();
                            if (transferAmount > balance) {
                                System.out.println("Insufficient funds.");
                            } else {
                                balance -= transferAmount;
                                System.out.println("Transfer successful. New balance: " + balance);
                                transactionHistory.add(new Transaction("Transfer", transferAmount));
                            }
                            break;
                        case 5:
                            System.out.print("Enter new user ID: ");
                            userId = sc.nextInt();
                            System.out.println("User ID changed successfully.");
                            break;
                        case 6:
                            System.out.print("Enter new PIN: ");
                            pin = sc.nextInt();
                            System.out.println("PIN changed successfully.");
                            break;
                        case 7:
                            System.out.println("Goodbye!");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                }
            } else {
                attempts++;
                System.out.println("Invalid user ID or PIN. Please try again.");
            }
        }
        sc.close();
        System.out.println("Too many attempts. Exiting.");
        System.exit(0);
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String toString() {
        return type + ": " + amount;
    }
}
