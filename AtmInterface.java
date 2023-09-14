import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
            return false;
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayOptions() {
        System.out.println("ATM Options:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayOptions();
            System.out.print("Select an option (1-4): ");

            if (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine().trim();

                if (!inputLine.isEmpty()) {
                    try {
                        int choice = Integer.parseInt(inputLine);

                        switch (choice) {
                            case 1:
                                System.out.print("Enter the amount to withdraw: $");
                                if (scanner.hasNextDouble()) {
                                    double withdrawAmount = scanner.nextDouble();
                                    bankAccount.withdraw(withdrawAmount);
                                } else {
                                    System.out.println("Invalid input for withdrawal amount.");
                                    scanner.nextLine(); 
                                }
                                break;
                            case 2:
                                System.out.print("Enter the amount to deposit: $");
                                if (scanner.hasNextDouble()) {
                                    double depositAmount = scanner.nextDouble();
                                    bankAccount.deposit(depositAmount);
                                } else {
                                    System.out.println("Invalid input for deposit amount.");
                                    scanner.nextLine(); 
                                }
                                break;
                            case 3:
                                System.out.println("Current balance: $" + bankAccount.getBalance());
                                break;
                            case 4:
                                System.out.println("Exiting ATM. Thank you!");
                                scanner.close();
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid option (1-4).");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid option (1-4).");
                }
            }
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}