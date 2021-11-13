public class TransactMain {


    public static void main(String[] args)
    {
        Account account= new Account(0);
       Runnable r1=  new Runnable() {
            @Override
            public void run() {
                account.withdraw(100,true);
                account.deposit(100);
                account.withdraw(400,true);
            }
        };
        Runnable r2= new Runnable() {
            @Override
            public void run() {
                account.deposit(200);
                account.withdraw(100,true);
                account.deposit(300);
            }
        };
        Runnable r3=new Runnable() {
            @Override
            public void run() {
                account.deposit(100);
                account.withdraw(200,false);
                account.deposit(200);
                account.withdraw(100,false);
            }
        };

        Thread t1=new Thread(r1);
        Thread t2= new Thread(r2);
        Thread t3= new Thread(r3);

        t1.start();
        t2.start();
        t3.start();



    }
}
