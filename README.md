# Bank Account Management System

A Java-based console application that demonstrates object-oriented programming concepts while providing a complete bank account management system.

## Features

- **Account Management**
  - Create new accounts (Savings or Checking)
  - Deposit funds
  - Withdraw funds with validation
  - Check account balance
  - Display all accounts

- **Account Types**
  - Savings Account (with minimum balance requirement)
  - Checking Account (with overdraft protection)

- **Data Persistence**
  - Automatic saving of account data
  - Loading of previous account data on startup

## Object-Oriented Programming Concepts Demonstrated

- **Classes and Objects**
  - Base `Account` class
  - Specialized account types
  - `Bank` class for account management

- **Inheritance**
  - `SavingsAccount` and `CheckingAccount` extend base `Account` class
  - Shared functionality in base class
  - Specialized behavior in derived classes

- **Polymorphism**
  - Method overriding for customized withdraw behavior
  - Different withdrawal rules for different account types

- **File I/O**
  - Object serialization for data persistence
  - Automatic loading and saving of account data

- **Exception Handling**
  - Custom exceptions for invalid amounts
  - Custom exceptions for insufficient funds
  - Graceful error handling throughout the application

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or above
- Basic understanding of Java programming

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/bank-management-system.git
```

2. Navigate to the project directory:
```bash
cd bank-management-system
```

3. Compile the Java files:
```bash
javac *.java
```

4. Run the application:
```bash
java Main
```

## Usage

The application provides a menu-driven interface with the following options:

1. Create New Account
2. Deposit
3. Withdraw
4. Check Balance
5. Display All Accounts
6. Exit

### Example Usage

1. Create a new account:
   - Select account type (Savings or Checking)
   - Enter account number
   - Enter account holder name
   - Enter initial balance

2. Perform transactions:
   - Deposit funds
   - Withdraw funds
   - Check balance
   - View all accounts

## Sample Data

The repository includes sample data with Indian names:

- Nisha Patel (Savings Account)
- Arjun Singh (Checking Account)
- Kavita Desai (Savings Account)
- Rahul Mehta (Checking Account)
- Deepika Reddy (Savings Account)
- Vikram Joshi (Checking Account)

## File Structure

```
bank-management-system/
├── Account.java              # Base account class
├── SavingsAccount.java       # Savings account implementation
├── CheckingAccount.java      # Checking account implementation
├── Bank.java                 # Bank management class
├── Main.java                 # Main application class
├── InvalidAmountException.java # Custom exception
├── InsufficientFundsException.java # Custom exception
└── bank_data.ser            # Serialized account data
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Java Documentation
- Object-Oriented Programming Principles
- Bank Management System Concepts 
