package lab01.example.model;

public class SimpleBankAccountWithAtm extends SimpleBankAccount {

    public static final double WITHDRAW_FEE = 1;

    public SimpleBankAccountWithAtm(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        if (checkUser(usrID) && isWithdrawAllowed(amount)) {
            this.balance -= amount + WITHDRAW_FEE;
        }
    }

    @Override
    protected boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount + WITHDRAW_FEE;
    }

}
