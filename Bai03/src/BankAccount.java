public class BankAccount{
    private int balance;
    public BankAccount(int balance){
        this.balance=balance;
    }
    public synchronized void deposit(int amount){
        balance+=amount;
    }
    public synchronized void withdraw(int amount){
        balance-=amount;
    }
    public int getBalance(){
        return balance;
    }
}