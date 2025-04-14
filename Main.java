import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    bank.displayAllAccounts();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using our Bank Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nBank Management System");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Display All Accounts");
        System.out.println("6. Exit");
    }

    private static void createAccount() {
        System.out.println("\nCreate New Account");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        int type = getIntInput("Select account type: ");
        
        if (type != 1 && type != 2) {
            System.out.println("Invalid account type.");
            return;
        }
        
        String accountNumber = getStringInput("Enter account number: ");
        String accountHolderName = getStringInput("Enter account holder name: ");
        double initialBalance = getDoubleInput("Enter initial balance: ");
        
        try {
            Account account;
            if (type == 1) {
                account = new SavingsAccount(accountNumber, accountHolderName, initialBalance);
            } else {
                account = new CheckingAccount(accountNumber, accountHolderName, initialBalance);
            }
            bank.addAccount(account);
            System.out.println("Account created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    private static void deposit() {
        String accountNumber = getStringInput("Enter account number: ");
        Account account = bank.findAccount(accountNumber);
        
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        double amount = getDoubleInput("Enter amount to deposit: ");
        try {
            account.deposit(amount);
            bank.saveAccounts();
            System.out.println("Deposit successful. New balance: $" + account.getBalance());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw() {
        String accountNumber = getStringInput("Enter account number: ");
        Account account = bank.findAccount(accountNumber);
        
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        double amount = getDoubleInput("Enter amount to withdraw: ");
        try {
            account.withdraw(amount);
            bank.saveAccounts();
            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
        } catch (InsufficientFundsException | InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance() {
        String accountNumber = getStringInput("Enter account number: ");
        Account account = bank.findAccount(accountNumber);
        
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        System.out.println("\nAccount Details:");
        System.out.println(account);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
} 