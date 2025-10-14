public class BankAccount {
    private double balance;

    public void deposit(double amt){
        System.out.println("Depositing " + amt);
        balance += amt;
    }

    public void withdraw(double amt) throws InsufficientFundExecption{
        if(amt <= balance){
            System.out.println("Withdrawing " + amt);
            balance -= amt;
        }else{
            throw new InsufficientFundExecption("Insufficient balance");
        }
    }

    public double getBalance() {return this.balance;}

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount();

        acc1.deposit(1000);

        try {
            acc1.withdraw(1200);
        } catch (InsufficientFundExecption e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }finally{
            System.out.println("Final balance is :- " + acc1.getBalance());
        }

    }
}


