package loadbalancer;

import consumer.Consumer;
import workload.Task;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by anoukh on 5/4/16.
 */
public class LoadBalancer extends Thread{

    private LinkedBlockingQueue<Task> workloadQueue;

    public LoadBalancer(LinkedBlockingQueue<Task> taskQueue) {
        super("LoadBalancer Thread");
        this.workloadQueue = taskQueue;
    }

    public static float latency;
    public static float throughput;
    public static float count;
    public static volatile boolean queueOneFinished;
    public static volatile boolean queueTwoFinished;
    public static volatile boolean queueThreeFinished;
    public static volatile boolean queueFourFinished;

    public void run(){
        System.out.println("Started LoadBalancing");
        LinkedBlockingQueue<Task> workloadQueueOne = new LinkedBlockingQueue<Task>();
        LinkedBlockingQueue<Task> workloadQueueTwo = new LinkedBlockingQueue<Task>();
        LinkedBlockingQueue<Task> workloadQueueThree = new LinkedBlockingQueue<Task>();
        LinkedBlockingQueue<Task> workloadQueueFour = new LinkedBlockingQueue<Task>();
        Consumer consumerThreadOne = new Consumer(workloadQueueOne, "Consumer One");
        Consumer consumerThreadTwo= new Consumer(workloadQueueTwo, "Consumer Two");
        Consumer consumerThreadThree = new Consumer(workloadQueueThree, "Consumer Three");
        Consumer consumerThreadFour = new Consumer(workloadQueueFour, "Consumer Four");

        consumerThreadOne.start();
        consumerThreadTwo.start();
        consumerThreadThree.start();
        consumerThreadFour.start();

        int i = 1;
        while (true){
            try {
                Task task = workloadQueue.take();
                if (task.getSize() == 0){
                    workloadQueueFour.add(task);
                    workloadQueueThree.add(task);
                    workloadQueueTwo.add(task);
                    workloadQueueOne.add(task);
                    break;
                }

//            if (i == 1){
//                workloadQueueFour.add(task);
//                i++;
//            } else if (i == 2){
//                workloadQueueThree.add(task);
//                i++;
//            } else if (i == 3){
//                workloadQueueTwo.add(task);
//                i++;
//            } else {
//                workloadQueueOne.add(task);
//                i = 1;
//            }


            if (task.getSize() <= 25){
                workloadQueueFour.add(task);
            } else if (task.getSize() <= 50){
                workloadQueueThree.add(task);
            } else if (task.getSize() <= 75){
                workloadQueueTwo.add(task);
            } else {
                workloadQueueOne.add(task);
            }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        while(true){
            if (queueOneFinished && queueTwoFinished && queueThreeFinished && queueFourFinished){
                System.out.println("Total Throughput = " + throughput + " tasks/sec");
                System.out.println("Total Latency = " + latency + " sec");
                System.out.println("Mean Latency = " + latency/count + " sec/task");
                System.out.println("Count = " + count + " tasks");
                System.exit(0);
            }
        }
    }
}
