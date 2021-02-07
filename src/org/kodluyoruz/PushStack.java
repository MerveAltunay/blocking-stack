package org.kodluyoruz;

import java.util.Stack;
import java.util.concurrent.Semaphore;

public class PushStack implements Runnable{

    private Stack<Integer> stack;
    private int number;
    private Semaphore semaphore;

    public PushStack(Stack<Integer> stack, int number, Semaphore semaphore) {
        this.stack = stack;
        this.number = number;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stack.push(number);
        semaphore.release();

    }
}
