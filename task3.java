
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Details {

    String holderName;
    long accountNumber;
    String password;
    double balance;

    Details(String holderName, String password, long accountNumber, double balance) {
        this.holderName = holderName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}

public class task3 {

    static long accNumber = 10000000;
    static List<Details> accountInformation = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void createAccount() {
        System.out.print("Enter holder's name: ");
        scanner.nextLine();
        String holderName = scanner.nextLine();
        System.out.print("Enter password : ");

        String password = scanner.nextLine();
        long accountNumber = ++accNumber;
        double balance = 0;

        Details newHolder = new Details(holderName, password, accountNumber, balance);
        accountInformation.add(newHolder);

        System.out.println("Account created successfully. Account Number: " + newHolder.accountNumber + " Password : " + newHolder.password);
    }

    public static void depositMoney() {
        if (accountInformation.isEmpty()) {
            System.out.println("YOU DON'T HAVE AN ACCOUNT, CREATE ONE!");
            System.out.println();
            createAccount();
            return;
        }

        System.out.print("Enter your account number: ");
        long accNum = scanner.nextLong();
        scanner.nextLine();

        Details account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter password : ");
            String pass = scanner.nextLine();

            if (pass.equals(account.password)) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                account.balance += amount;
                System.out.println("Deposit successful. New balance: " + account.balance);
            } else {
                System.out.println("Incorrect Password.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void withdrawMoney() {
        if (accountInformation.isEmpty()) {
            System.out.println("YOU DON'T HAVE AN ACCOUNT, CREATE ONE!");
            createAccount();
            return;
        }

        System.out.print("Enter your account number: ");
        long accNum = scanner.nextLong();
        scanner.nextLine();

        Details account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter password : ");
            String pass = scanner.nextLine();

            if (pass.equals(account.password)) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();

                if (account.balance >= amount) {
                    account.balance -= amount;
                    System.out.println("Withdrawal successful. New balance: " + account.balance);
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Incorrect Password.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void checkBalance() {
        if (accountInformation.isEmpty()) {
            System.out.println("YOU DON'T HAVE AN ACCOUNT, CREATE ONE!");
            createAccount();
            return;
        }

        System.out.print("Enter your account number: ");
        long accNum = scanner.nextLong();
        scanner.nextLine();

        Details account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter password : ");
            String pass = scanner.nextLine();

            if (pass.equals(account.password)) {
                System.out.println("Account Balance: " + account.balance);
            } else {
                System.out.println("Incorrect Password.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static Details findAccount(long accNum) {
        for (Details account : accountInformation) {
            if (account.accountNumber == accNum) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String[] facility = {"Create Account", "Deposit Money", "Withdraw Money", "Check Balance"};
        int numOfFacility = facility.length;

        while (true) {
            System.out.println();
            for (int i = 1; i <= numOfFacility; i++) {
                System.out.println(i + ". " + facility[i - 1]);
            }
            System.out.println("5. To exit the menu");

            System.out.print("Enter the facility you want to access: ");
            int input = scanner.nextInt();

            if (input < 1 || input > numOfFacility + 1) {
                System.out.println("INVALID CHOICE");
                return;
            }

            switch (input) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }
}
