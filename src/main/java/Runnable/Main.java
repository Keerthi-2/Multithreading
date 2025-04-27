package Runnable;

public class Main {
    public static void main(String[] args){
        ThreadClass thread1=new ThreadClass();
        ThreadClass thread2=new ThreadClass();
        Thread t1=new Thread(thread1);

        Thread t2=new Thread(thread2);
        System.out.println("first thread is going to start");
        t1.start();
        System.out.println("second thread is going to start");
        t2.start();
        for(int i=1;i<=1000;i++) {
            System.out.println("running main thread"+i);
        }





    }
}
