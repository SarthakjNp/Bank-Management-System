import java.io.Serializable;

public abstract class Account implements Serializable {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected String accountType;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException;

    @Override
    public String toString() {
        return String.format("Account Number: %s\nAccount Holder: %s\nAccount Type: %s\nBalance: $%.2f",
                accountNumber, accountHolderName, accountType, balance);
    }
} 