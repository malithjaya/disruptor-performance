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

    public void run(){
        System.out.println("Started LoadBalancing");
        LinkedBlockingQueue<Task> workloadQueueOne = new LinkedBlockingQueue<Task>();
        LinkedBlockingQueue<Task> workloadQueueTwo = new LinkedBlockingQueue<Task>();
        LinkedBlockingQueue<Task> workloadQueueThree = new LinkedBlockingQueue<Task>();
        LinkedBlockingQueue<Task> workloadQueueFour = new LinkedBlockingQueue<Task>();

        for (Task task: workloadQueue) {
            if (task.getSize() <= 25){
                workloadQueueFour.add(task);
            } else if (task.getSize() <= 50){
                workloadQueueThree.add(task);
            } else if (task.getSize() <= 75){
                workloadQueueTwo.add(task);
            } else {
                workloadQueueOne.add(task);
            }
        }

        Consumer consumerThreadOne = new Consumer(workloadQueueOne, "Consumer One");
        Consumer consumerThreadTwo= new Consumer(workloadQueueTwo, "Consumer Two");
        Consumer consumerThreadThree = new Consumer(workloadQueueThree, "Consumer Three");
        Consumer consumerThreadFour = new Consumer(workloadQueueFour, "Consumer Four");

        consumerThreadOne.start();
        consumerThreadTwo.start();
        consumerThreadThree.start();
        consumerThreadFour.start();
    }
}
