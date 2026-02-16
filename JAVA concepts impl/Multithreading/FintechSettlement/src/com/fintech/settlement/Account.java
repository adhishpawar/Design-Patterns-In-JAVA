/*
Shared mutable state
Explicit locking
FinTech mandatory
*/
package com.fintech.settlement;

import java.util.concurrent.locks.ReentrantLock;
public class Account {

    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int balance)
    {
        this.balance = balance;
    }

    public boolean debit(int amount)
    {
        lock.lock();
        try{
            if(balance >= amount)
            {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public int getBalance()
    {
        return balance;
    }

}