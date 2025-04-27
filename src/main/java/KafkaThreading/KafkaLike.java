package KafkaThreading;

import java.util.LinkedList;
import java.util.Queue;

public class KafkaLike {

    // QueueBuffer class to manage the queue for producer-consumer
    static class QueueBuffer {
        private final Queue<String> queue;
        private final int MAX_CAPACITY;

        public QueueBuffer(int capacity) {
            this.queue = new LinkedList<>();
            this.MAX_CAPACITY = capacity;
        }

        // Producer adds data to the queue
        public synchronized void produce(String message) throws InterruptedException {
            while (queue.size() == MAX_CAPACITY) {
                System.out.println("Producer is waiting. Queue is full.");
                wait();  // Wait if the queue is full
            }
            queue.offer(message);  // Add the message to the queue
            System.out.println("Produced: " + message);
            System.out.println("Current queue size: " + queue.size());
            notifyAll();  // Notify the consumer that a message is available
        }

        // Consumer consumes data from the queue
        public synchronized String consume() throws InterruptedException {
            while (queue.isEmpty()) {
                System.out.println("Consumer is waiting. Queue is empty.");
                wait();  // Wait if the queue is empty
            }
            String message = queue.poll();  // Remove the message from the queue
            System.out.println("Consumed: " + message);
            System.out.println("Current queue size: " + queue.size());
            notifyAll();  // Notify the producer that space is available in the queue
            return message;
        }
    }

    // Producer thread implementing Runnable
    static class Producer implements Runnable {
        private final QueueBuffer queueBuffer;

        public Producer(QueueBuffer queueBuffer) {
            this.queueBuffer = queueBuffer;
        }

        @Override
        public void run() {
            try {
                String[] messages = {"Message 1", "Message 2", "Message 3", "Message 4", "Message 5"};
                for (String message : messages) {
                    System.out.println("Producer trying to produce: " + message);
                    queueBuffer.produce(message);
                    Thread.sleep(1000);  // Simulate time taken to produce the message
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Consumer thread implementing Runnable
    static class Consumer implements Runnable {
        private final QueueBuffer queueBuffer;

        public Consumer(QueueBuffer queueBuffer) {
            this.queueBuffer = queueBuffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Consumer trying to consume...");
                    queueBuffer.consume();
                    Thread.sleep(1500);  // Simulate time taken to consume the message
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        QueueBuffer queueBuffer = new QueueBuffer(3);  // Set a maximum capacity of 3

        // Create producer and consumer threads with Runnable interface
        Producer producer = new Producer(queueBuffer);
        Consumer consumer = new Consumer(queueBuffer);

        // Start the threads using Thread class
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();  // Start the producer thread
        consumerThread.start();  // Start the consumer thread
    }
}
