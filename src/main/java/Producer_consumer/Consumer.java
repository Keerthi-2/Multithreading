package Producer_consumer;

public class Consumer implements Runnable{
    QueueBuffer queueBuffer;
    public Consumer(QueueBuffer queuebuffer){
        this.queueBuffer=queuebuffer;
    }
    @Override
    public void run() {
        try{

            while(true) {
                System.out.println("consumer trying to cosume ");
                queueBuffer.consume();
                Thread.sleep(1500);
            }

        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }
}
