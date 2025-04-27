package Producer_consumer;
import java.lang.*;
public class Producer implements Runnable{
    QueueBuffer queuebuffer;

    public Producer(QueueBuffer queueBuffer){
        this.queuebuffer=queueBuffer;

    }
    @Override
    public void run() {
        try{
            String[] msgs={"msg1","msg2","msg3","msg4"};
            for(String message:msgs){
                System.out.println("Producer trying to produce: " + message);
                queuebuffer.produce(message);
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


    }
}
