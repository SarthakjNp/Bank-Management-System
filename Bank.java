import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private List<Account> accounts;
    private static final String DATA_FILE = "bank_data.ser";

    public Bank() {
        accounts = new ArrayList<>();
        loadAccounts();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        saveAccounts();
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            accounts = (List<Account>) ois.readObject();
        } catch (FileNotFoundException e) {
            // First run, no data file exists yet
            accounts = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
            accounts = new ArrayList<>();
        }
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println("-------------------");
        }
    }
} 