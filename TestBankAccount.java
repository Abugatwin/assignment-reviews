public class TestBankAccount {
  public static void main(String[] args) {
      // Test constructor with opening balance
      BankAccount savingsAccount = new BankAccount(BankAccount.AccountType.SAVINGS, "SA001", 100.0);
      BankAccount currentAccount = new BankAccount(BankAccount.AccountType.CURRENT, "CA001", 50.0);

      // Test deposit method
      savingsAccount.deposit(50.0);
      currentAccount.deposit(10.0);

      System.out.println("Savings account balance after deposit: " + savingsAccount.getBalance());
      System.out.println("Current account balance after deposit: " + currentAccount.getBalance());

      // Test withdrawal method
      savingsAccount.withdraw(30.0);
      currentAccount.withdraw(5.0);

      System.out.println("Savings account balance after withdrawal: " + savingsAccount.getBalance());
      System.out.println("Current account balance after withdrawal: " + currentAccount.getBalance());

      // Test monthly maintenance method
      savingsAccount.performMonthlyMaintenance();
      currentAccount.performMonthlyMaintenance();

      System.out.println("Savings account balance after monthly maintenance: " + savingsAccount.getBalance());
      System.out.println("Current account balance after monthly maintenance: " + currentAccount.getBalance());
  }
}
