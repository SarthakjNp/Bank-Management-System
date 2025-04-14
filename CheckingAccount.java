public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 500.0;

    public CheckingAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
        this.accountType = "Checking";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        
        if (balance - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException("Cannot withdraw. Overdraft limit of $" + OVERDRAFT_LIMIT + " exceeded");
        }
        
        balance -= amount;
    }
} 