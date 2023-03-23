public class BankAccount {
  public enum AccountType { CURRENT, SAVINGS }
   // Defined constants
   public static final int MONTHS_PER_YEAR = 12;
   public static final double SAVINGS_ACCT_MIN_BALANCE = 30.0;
   public static final double CURRENT_ACCT_MIN_BALANCE = 6.00;
   public static final double SAVINGS_ACCT_INTEREST_RATE = 0.06;
   public static final double CURRENT_ACCT_MAINTENANCE_FEE = 2.00;
   public static final int SAVINGS_WITHDRAWAL_LIMIT = 2;
   
  private AccountType acctType;
  private String acctID;
  private double balance;
  private double minBalance;
  private double interestRate;
  private double maintenanceFee;
  private int withdrawalLimit;
  private int numWithdrawals;
  private boolean inTheRed;
//   private int yourCurrentAccount;
//   private int mySavingsAccount;
//   private int myCurrentAccount;


  // Constructor with opening balance
  public BankAccount(AccountType type, String id, double openingBalance) {
      this(type, id);
      this.balance = openingBalance;
      this.inTheRed = (this.balance < this.minBalance);
  }
  public void deposit(double amount) {
    balance += amount;



  }

  // Default constructor
  public BankAccount(AccountType type, String id) {
      this.acctType = type;
      this.acctID = id;
      this.numWithdrawals = 0;
      this.inTheRed = false;

      if (type == AccountType.CURRENT) {
          this.minBalance = CURRENT_ACCT_MIN_BALANCE;
          this.interestRate = 0.0;
          this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
          this.withdrawalLimit = -1;
      } else {
          this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
          this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
          this.maintenanceFee = 0.0;
          this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
      }
  }

  // Getter methods
  public double getBalance() {
      return this.balance;
  }

  public AccountType getAccountType() {
      return this.acctType;
  }

  public String getAccountID() {
      return this.acctID;
  }

  public double getMinBalance() {
      return this.minBalance;
  }

  // method to check if a withdrawal is allowed
  private boolean withdrawals(double amount) {
      if (this.withdrawalLimit != -1 && this.numWithdrawals >= this.withdrawalLimit) {
          System.out.println("Withdrawal limit reached for this month.");
          return false;
      } else if (this.balance - amount < this.minBalance) {
          System.out.println("Insufficient balance to make the withdrawal.");
          return false;
      } else if (this.inTheRed) {
          System.out.println("Account is in the red. Please deposit funds before withdrawing.");
          return false;
      } else {
          return true;
      }
    
  }

  // // Perform monthly maintenance method
    public void performMonthlyMaintenance() {
        double interestEarned = 0;

        // Compute interest earned for the month
        if (interestRate > 0) {
            double monthlyInterestRate = interestRate / 12;
            interestEarned = balance * monthlyInterestRate;
            balance += interestEarned;
        }

        // Deduct monthly maintenance fee
        balance -= maintenanceFee;

        // Reset withdrawals for the month
        numWithdrawals = 0;

        // Check if account is in the red
        if (balance < 0) {
            System.out.println("WARNING: This account is in the red!");
        }

        // Print summary of monthly maintenance
        System.out.println("Earned interest: " + interestEarned);
        System.out.println("Maintenance fee: " + maintenanceFee);
        System.out.println("Updated balance: " + balance);
    }

    // Withdrawal method
    public void withdraw(double amount) {
        if (SAVINGS_WITHDRAWAL_LIMIT == -1 || numWithdrawals < SAVINGS_WITHDRAWAL_LIMIT) {
            balance -= amount;
            numWithdrawals++;
        } else {
            System.out.println("Withdrawal limit exceeded for the month!");
        }
    }
    public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount) {
        boolean success = false;
        if (transferTo) {
            success = withdrawals(amount);
            if (success) {
                otherAccount.deposit(amount);
            }
        } else {
            success = otherAccount.withdrawals(amount);
            if (success) {
                deposit(amount);
            }
        }
        return success;
    }
}
  
