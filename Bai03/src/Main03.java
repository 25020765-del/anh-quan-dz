public class Main03{
    public static void main(String[] args){
        BankAccount bankAccount=new BankAccount(0);
        Thread A=new Thread(()->{
            for(int i=0; i<1000; i++){
                bankAccount.deposit(100);
            }
        });
        Thread B=new Thread(()->{
            for(int i=0; i<1000; i++){
                bankAccount.withdraw(100);
            }
        });
        A.start();
        B.start();

        try{
            A.join();
            B.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Balance: "+bankAccount.getBalance());
    }
}