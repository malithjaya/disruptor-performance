package loadbalancer;

import consumer.Consumer;
import workload.Task;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by malithjayasinghe on 5/5/16.
 */
public abstract class AbstractLoadBalancer extends Thread {


    public static float latency;
    public static float throughput;
    public static float count;
    public static volatile boolean queueOneFinished;
    public static volatile boolean queueTwoFinished;
    public static volatile boolean queueThreeFinished;
    public static volatile boolean queueFourFinished;

    LinkedBlockingQueue<Task> workloadQueueOne = new LinkedBlockingQueue<Task>();
    LinkedBlockingQueue<Task> workloadQueueTwo = new LinkedBlockingQueue<Task>();
    LinkedBlockingQueue<Task> workloadQueueThree = new LinkedBlockingQueue<Task>();
    LinkedBlockingQueue<Task> workloadQueueFour = new LinkedBlockingQueue<Task>();
    Consumer consumerThreadOne = new Consumer(workloadQueueOne, "Consumer One");
    Consumer consumerThreadTwo= new Consumer(workloadQueueTwo, "Consumer Two");
    Consumer consumerThreadThree = new Consumer(workloadQueueThree, "Consumer Three");
    Consumer consumerThreadFour = new Consumer(workloadQueueFour, "Consumer Four");

    private LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();


    /**
     *
     * Adds an item to the task queue
     *
     */
    public void addItemToQueue(Task task)
    {
        taskQueue.add(task);
    }

    /**
     *
     * The implementation of the load balancer
     *
     */
    public  abstract void loadBalancerImpl();



    /**
     * Gets the task queue which stores the tasks
     *
     * @return the task queue
     */
    public LinkedBlockingQueue<Task> getTaskQueue()
    {
        return taskQueue;
    }


    public void run() {
        loadBalancerImpl();
        consumerThreadOne.start();
        consumerThreadTwo.start();
        consumerThreadThree.start();
        consumerThreadFour.start();

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
