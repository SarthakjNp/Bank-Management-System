import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class BankUI extends Frame {
    private Bank bank;
    private ATM atm;
    private TextArea displayArea;
    private Panel buttonPanel;
    private CardLayout cardLayout;
    private Panel mainPanel;

    public BankUI() {
        bank = new Bank();
        atm = new ATM(bank);
        setupUI();
    }

    private void setupUI() {
        setTitle("Bank Management System");
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        // Create main menu panel
        Panel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "MENU");

        // Create display area
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Add components to frame
        add(mainPanel, BorderLayout.CENTER);
        add(displayArea, BorderLayout.SOUTH);

        // Add window listener for closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Center the window
        setLocationRelativeTo(null);
    }

    private Panel createMenuPanel() {
        Panel panel = new Panel(new GridLayout(4, 2, 10, 10));

        // Create buttons
        Button createAccountBtn = new Button("Create Account");
        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button checkBalanceBtn = new Button("Check Balance");
        Button displayAccountsBtn = new Button("Display All Accounts");
        Button atmServicesBtn = new Button("ATM Services");
        Button exitBtn = new Button("Exit");

        // Add action listeners
        createAccountBtn.addActionListener(e -> showCreateAccountDialog());
        depositBtn.addActionListener(e -> showDepositDialog());
        withdrawBtn.addActionListener(e -> showWithdrawDialog());
        checkBalanceBtn.addActionListener(e -> showCheckBalanceDialog());
        displayAccountsBtn.addActionListener(e -> displayAllAccounts());
        atmServicesBtn.addActionListener(e -> showATMServices());
        exitBtn.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        panel.add(createAccountBtn);
        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(checkBalanceBtn);
        panel.add(displayAccountsBtn);
        panel.add(atmServicesBtn);
        panel.add(exitBtn);

        return panel;
    }

    private void showCreateAccountDialog() {
        Dialog dialog = new Dialog(this, "Create New Account", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        // Create components
        Label typeLabel = new Label("Account Type:");
        Choice typeChoice = new Choice();
        typeChoice.add("Savings");
        typeChoice.add("Checking");

        Label numberLabel = new Label("Account Number:");
        TextField numberField = new TextField();

        Label nameLabel = new Label("Account Holder Name:");
        TextField nameField = new TextField();

        Label balanceLabel = new Label("Initial Balance:");
        TextField balanceField = new TextField();

        Label pinLabel = new Label("PIN:");
        TextField pinField = new TextField();

        Button createBtn = new Button("Create");
        Button cancelBtn = new Button("Cancel");

        // Add components to dialog
        dialog.add(typeLabel);
        dialog.add(typeChoice);
        dialog.add(numberLabel);
        dialog.add(numberField);
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(balanceLabel);
        dialog.add(balanceField);
        dialog.add(pinLabel);
        dialog.add(pinField);
        dialog.add(createBtn);
        dialog.add(cancelBtn);

        // Add action listeners
        createBtn.addActionListener(e -> {
            try {
                String accountNumber = numberField.getText();
                String accountHolderName = nameField.getText();
                double initialBalance = Double.parseDouble(balanceField.getText());
                String pin = pinField.getText();

                Account account;
                if (typeChoice.getSelectedItem().equals("Savings")) {
                    account = new SavingsAccount(accountNumber, accountHolderName, initialBalance, pin);
                } else {
                    account = new CheckingAccount(accountNumber, accountHolderName, initialBalance, pin);
                }

                bank.addAccount(account);
                displayArea.setText("Account created successfully!\n" + account.toString());
                dialog.dispose();
            } catch (Exception ex) {
                displayArea.setText("Error creating account: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showDepositDialog() {
        Dialog dialog = new Dialog(this, "Deposit", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        Label accountLabel = new Label("Account Number:");
        TextField accountField = new TextField();

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();

        Button depositBtn = new Button("Deposit");
        Button cancelBtn = new Button("Cancel");

        dialog.add(accountLabel);
        dialog.add(accountField);
        dialog.add(amountLabel);
        dialog.add(amountField);
        dialog.add(depositBtn);
        dialog.add(cancelBtn);

        depositBtn.addActionListener(e -> {
            try {
                String accountNumber = accountField.getText();
                double amount = Double.parseDouble(amountField.getText());

                Account account = bank.findAccount(accountNumber);
                if (account != null) {
                    account.deposit(amount);
                    bank.saveAccounts();
                    displayArea.setText("Deposit successful!\nNew balance: $" + account.getBalance());
                    dialog.dispose();
                } else {
                    displayArea.setText("Account not found!");
                }
            } catch (Exception ex) {
                displayArea.setText("Error: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showWithdrawDialog() {
        Dialog dialog = new Dialog(this, "Withdraw", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        Label accountLabel = new Label("Account Number:");
        TextField accountField = new TextField();

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();

        Button withdrawBtn = new Button("Withdraw");
        Button cancelBtn = new Button("Cancel");

        dialog.add(accountLabel);
        dialog.add(accountField);
        dialog.add(amountLabel);
        dialog.add(amountField);
        dialog.add(withdrawBtn);
        dialog.add(cancelBtn);

        withdrawBtn.addActionListener(e -> {
            try {
                String accountNumber = accountField.getText();
                double amount = Double.parseDouble(amountField.getText());

                Account account = bank.findAccount(accountNumber);
                if (account != null) {
                    account.withdraw(amount);
                    bank.saveAccounts();
                    displayArea.setText("Withdrawal successful!\nNew balance: $" + account.getBalance());
                    dialog.dispose();
                } else {
                    displayArea.setText("Account not found!");
                }
            } catch (Exception ex) {
                displayArea.setText("Error: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showCheckBalanceDialog() {
        Dialog dialog = new Dialog(this, "Check Balance", true);
        dialog.setLayout(new GridLayout(2, 2, 10, 10));
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(this);

        Label accountLabel = new Label("Account Number:");
        TextField accountField = new TextField();

        Button checkBtn = new Button("Check");
        Button cancelBtn = new Button("Cancel");

        dialog.add(accountLabel);
        dialog.add(accountField);
        dialog.add(checkBtn);
        dialog.add(cancelBtn);

        checkBtn.addActionListener(e -> {
            String accountNumber = accountField.getText();
            Account account = bank.findAccount(accountNumber);
            if (account != null) {
                displayArea.setText(account.toString());
                dialog.dispose();
            } else {
                displayArea.setText("Account not found!");
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void displayAllAccounts() {
        StringBuilder sb = new StringBuilder();
        sb.append("All Accounts:\n\n");
        for (Account account : bank.getAccounts()) {
            sb.append(account.toString()).append("\n\n");
        }
        displayArea.setText(sb.toString());
    }

    private void showATMServices() {
        Dialog dialog = new Dialog(this, "ATM Services", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        Label accountLabel = new Label("Account Number:");
        TextField accountField = new TextField();

        Label pinLabel = new Label("PIN:");
        TextField pinField = new TextField();

        Button loginBtn = new Button("Login");
        Button cancelBtn = new Button("Cancel");

        dialog.add(accountLabel);
        dialog.add(accountField);
        dialog.add(pinLabel);
        dialog.add(pinField);
        dialog.add(loginBtn);
        dialog.add(cancelBtn);

        loginBtn.addActionListener(e -> {
            String accountNumber = accountField.getText();
            String pin = pinField.getText();

            Account account = bank.findAccount(accountNumber);
            if (account != null && account.getPin().equals(pin)) {
                showATMMenu(account);
                dialog.dispose();
            } else {
                displayArea.setText("Invalid account number or PIN!");
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showATMMenu(Account account) {
        Dialog dialog = new Dialog(this, "ATM Menu", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button balanceBtn = new Button("Check Balance");
        Button changePinBtn = new Button("Change PIN");
        Button logoutBtn = new Button("Logout");

        depositBtn.addActionListener(e -> {
            showATMDepositDialog(account);
            dialog.dispose();
        });

        withdrawBtn.addActionListener(e -> {
            showATMWithdrawDialog(account);
            dialog.dispose();
        });

        balanceBtn.addActionListener(e -> {
            displayArea.setText(account.toString());
            dialog.dispose();
        });

        changePinBtn.addActionListener(e -> {
            showChangePinDialog(account);
            dialog.dispose();
        });

        logoutBtn.addActionListener(e -> dialog.dispose());

        dialog.add(depositBtn);
        dialog.add(withdrawBtn);
        dialog.add(balanceBtn);
        dialog.add(changePinBtn);
        dialog.add(logoutBtn);

        dialog.setVisible(true);
    }

    private void showATMDepositDialog(Account account) {
        Dialog dialog = new Dialog(this, "ATM Deposit", true);
        dialog.setLayout(new GridLayout(2, 2, 10, 10));
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(this);

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();

        Button depositBtn = new Button("Deposit");
        Button cancelBtn = new Button("Cancel");

        dialog.add(amountLabel);
        dialog.add(amountField);
        dialog.add(depositBtn);
        dialog.add(cancelBtn);

        depositBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                bank.saveAccounts();
                displayArea.setText("Deposit successful!\nNew balance: $" + account.getBalance());
                dialog.dispose();
            } catch (Exception ex) {
                displayArea.setText("Error: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showATMWithdrawDialog(Account account) {
        Dialog dialog = new Dialog(this, "ATM Withdraw", true);
        dialog.setLayout(new GridLayout(2, 2, 10, 10));
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(this);

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();

        Button withdrawBtn = new Button("Withdraw");
        Button cancelBtn = new Button("Cancel");

        dialog.add(amountLabel);
        dialog.add(amountField);
        dialog.add(withdrawBtn);
        dialog.add(cancelBtn);

        withdrawBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                account.withdraw(amount);
                bank.saveAccounts();
                displayArea.setText("Withdrawal successful!\nNew balance: $" + account.getBalance());
                dialog.dispose();
            } catch (Exception ex) {
                displayArea.setText("Error: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showChangePinDialog(Account account) {
        Dialog dialog = new Dialog(this, "Change PIN", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        Label newPinLabel = new Label("New PIN:");
        TextField newPinField = new TextField();

        Button changeBtn = new Button("Change");
        Button cancelBtn = new Button("Cancel");

        dialog.add(newPinLabel);
        dialog.add(newPinField);
        dialog.add(changeBtn);
        dialog.add(cancelBtn);

        changeBtn.addActionListener(e -> {
            String newPin = newPinField.getText();
            account.setPin(newPin);
            bank.saveAccounts();
            displayArea.setText("PIN changed successfully!");
            dialog.dispose();
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        BankUI ui = new BankUI();
        ui.setVisible(true);
    }
} 