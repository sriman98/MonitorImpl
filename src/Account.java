import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Account
{
    Lock lock= new ReentrantLock();

    //two conditions, 1st one for when deposit occurs and second one for giving preference to preferred withdrawals
    Condition condition=lock.newCondition();
    Condition condition2=lock.newCondition();

    int balance;

    //Getting a count of preferred withdrawals in the queue
    int preferredWithdraws=0;

    public Account()
    {balance=0;}

    public Account(int balance)
    {this.balance=balance;}


    //Deposit and signal when deposit is done
    public void deposit(int k)
    {
        lock.lock();

        balance+=k;
        System.out.println("Deposit Successfull, New Balance ="+balance);
        condition.signal();

        lock.unlock();
    }

    //withdrawal method to withdraw based on preferred or ordinary transaction
    public void withdraw(int k,boolean isPreferred)
    {
        if(isPreferred)
        {preferredWithdraws++;}

        lock.lock();


        if(!isPreferred)
        {
            while(balance<k || preferredWithdraws>0) {
                while (balance < k) {
                    try {
                        System.out.println("Waiting");
                        condition.await();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }
                while (preferredWithdraws > 0) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }

            }
        }
        else
        {
            while (balance < k) {
                try {
                    System.out.println("Waiting");
                    condition.await();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }

        balance-=k;

        System.out.println("Withdraw Successfull, New Balance ="+balance);

        if(isPreferred)
        {
            preferredWithdraws--;
            condition2.signalAll();
         }


        lock.unlock();
    }


    //transfer function as defined in 3rd part
    void transfer(int k, Account reserve) {
        lock.lock();
        try {
            reserve.withdraw(k,false);
            deposit(k);
        } finally {
            lock.unlock();
        }

        System.out.println("Transaction Successfull");
    }



}
