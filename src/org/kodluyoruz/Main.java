package org.kodluyoruz;

import java.util.Stack;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Semaphore semaphore = new Semaphore(1);

        Stack<Integer> integerStack = new Stack<>();

        PushStack pushStack1 = new PushStack(integerStack,1,semaphore);
        PushStack pushStack2 = new PushStack(integerStack,2,semaphore);
        PushStack pushStack3 = new PushStack(integerStack,3,semaphore);

        Thread thread1 = new Thread(pushStack1);
        Thread thread2 = new Thread(pushStack2);
        Thread thread3 = new Thread(pushStack3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(integerStack + " are pushed" );

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> popStack = executorService.submit(new PopStack(integerStack,semaphore));
        Future<Integer> popStack2 = executorService.submit(new PopStack(integerStack,semaphore));
        Future<Integer> popStack3 = executorService.submit(new PopStack(integerStack,semaphore));

        Thread thread4 = new Thread(String.valueOf(popStack));
        Thread thread5 = new Thread(String.valueOf(popStack2));
        Thread thread6 = new Thread(String.valueOf(popStack3));

        thread4.start();
        thread5.start();
        thread6.start();

        thread4.join();
        thread5.join();
        thread6.join();

        System.out.println(popStack.get() + " is popped" );
        System.out.println(popStack2.get() + " is popped" );
        System.out.println(popStack3.get() + " is popped" );


        executorService.shutdown();

    }
}
