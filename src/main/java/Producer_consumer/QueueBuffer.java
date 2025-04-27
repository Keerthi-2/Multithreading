package Producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

public class QueueBuffer {
    int capacity;
    Queue<String> queue;
    public QueueBuffer(int cap){
        this.capacity=cap;
        this.queue=new LinkedList<>();
    }
    public  void produce(String msg) throws InterruptedException{
       while(queue.size()==capacity){
           System.out.println("Producer is in waiting state as ....Queue is full");
           wait();
       }
       queue.add(msg);
        System.out.println("produced: "+msg);
        System.out.println("current queue size: "+queue.size());
        notifyAll(); // will make waiting threads to awake and perform their operation
    }
    public  String consume() throws InterruptedException{
        while(queue.isEmpty()){
            System.out.println("consumer is waiting. Queue is empty");
            wait();
        }
        String msg=queue.poll();
        System.out.println("consumed: "+msg);
        System.out.println("Current queue size: " + queue.size());
        notifyAll();
        return msg;



    }
}
