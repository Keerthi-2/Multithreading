package Producer_consumer;



public class Main {

    public static void main(String[] args) {
        QueueBuffer queuebuffer=new QueueBuffer(3);
        Producer producer=new Producer(queuebuffer);
        Consumer consumer=new Consumer(queuebuffer);
        Thread producerthread=new Thread(producer);
        Thread consumerthread=new Thread(consumer);

        producerthread.start();
        consumerthread.start();



    }
}
