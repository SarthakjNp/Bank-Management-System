public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 100.0;

    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
        this.accountType = "Savings";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        
        if (balance - amount < MINIMUM_BALANCE) {
            throw new InsufficientFundsException("Cannot withdraw. Minimum balance of $" + MINIMUM_BALANCE + " must be maintained");
        }
        
        balance -= amount;
    }
} 