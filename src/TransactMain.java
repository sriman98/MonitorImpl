public class TransactMain {


    public static void main(String[] args)
    {
        Account account= new Account(0);
       Runnable r1=  new Runnable() {
            @Override
            public void run() {
                account.withdraw(100,true);
            }
        };

        Runnable r2= new Runnable() {
            @Override
            public void run() {
                account.deposit(200);
            }
        };
        Runnable r3=new Runnable() {
            @Override
            public void run() {
                account.deposit(100);
            }
        };
        Runnable r4=  new Runnable() {
            @Override
            public void run() {
                account.withdraw(200,false);
            }
        };
        Runnable r5=  new Runnable() {
            @Override
            public void run() {
                account.deposit(200);
            }
        };
        Runnable r6=  new Runnable() {
            @Override
            public void run() {
                account.withdraw(100,false);
            }
        };
        Runnable r7=  new Runnable() {
            @Override
            public void run() {
                account.deposit(300);
            }
        };
        Runnable r8=  new Runnable() {
            @Override
            public void run() {
                account.withdraw(100,true);
            }
        };
        Runnable r9=  new Runnable() {
            @Override
            public void run() {
                account.deposit(100);
            }
        };
        Runnable r10=  new Runnable() {
            @Override
            public void run() {
                account.withdraw(400,true);
            }
        };

        Thread t1=new Thread(r1);
        Thread t2= new Thread(r2);
        Thread t3= new Thread(r3);
        Thread t4=new Thread(r4);
        Thread t5=new Thread(r5);
        Thread t6=new Thread(r6);
        Thread t7=new Thread(r7);
        Thread t8=new Thread(r8);
        Thread t9=new Thread(r9);
        Thread t10=new Thread(r10);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }
}
