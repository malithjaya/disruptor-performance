package process;

import loadbalancer.LoadBalancer;
import workload.OutOfRangeException;
import workload.Task;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by anoukh on 5/4/16.
 * Process method
 */
public class Process {

    private LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>(10);

    public void process() throws OutOfRangeException{
//        System.out.println(new Task().createLoad(101));
        for (int i = 0; i < 10; i++){
            Random rn = new Random();
            int temp = rn.nextInt(100) + 1;
            getTaskQueue().add(new Task(temp));
        }

        LoadBalancer loadBalancerThread = new LoadBalancer(taskQueue);
        loadBalancerThread.start();

    }

    public LinkedBlockingQueue<Task> getTaskQueue() {
        return taskQueue;
    }

}
