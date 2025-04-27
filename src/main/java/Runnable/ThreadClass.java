package Runnable;

public class ThreadClass implements Runnable {


    @Override
    public void run() {
        for(int i=1;i<=1000;i++)
            System.out.println("welcome to thread"+i);
    }
}
