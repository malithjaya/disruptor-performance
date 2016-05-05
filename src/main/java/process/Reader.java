package process;

import loadbalancer.SizeBasedLoadBalancer;
import workload.Task;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by anoukh on 5/4/16.
 * Reader method
 */
public class Reader extends Thread
{
    private LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();

    public Reader() {
        super("Reader Thread");
    }

    public void run(){
        SizeBasedLoadBalancer loadBalancerThread = new SizeBasedLoadBalancer(taskQueue);
        loadBalancerThread.start();

        Random rn = new Random();
        for (int i = 0; i < 1000000; i++){
            int temp = rn.nextInt(100) + 1;
            taskQueue.add(new Task(temp));
        }
        taskQueue.add(new Task(0));
    }
}
