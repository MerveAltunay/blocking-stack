package org.kodluyoruz;

import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class PopStack implements Callable {

    private Stack<Integer> stack;
    private Semaphore semaphore;


    public PopStack(Stack<Integer> stack, Semaphore semaphore) {
        this.stack = stack;
        this.semaphore = semaphore;
    }

    @Override
    public Object call() throws Exception {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int number = stack.pop();
        semaphore.release();
        return number;

    }
}
