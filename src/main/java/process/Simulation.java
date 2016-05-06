package process;

import loadbalancer.AbstractLoadBalancer;
import loadbalancer.SizeBasedLoadBalancer;
import workload.Task;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by anoukh on 5/4/16.
 * Simulation method
 */
public class Simulation extends Thread
{

    private AbstractLoadBalancer loadBalancer;
    public final static long NUM_EVENTS = 1000000;

    /**
     * The constructor
     *
     * @param loadBalancer the load balancer to use
     */
    public Simulation(AbstractLoadBalancer loadBalancer) {
        super("Simulation Thread");
        this.loadBalancer = loadBalancer;
    }

    public void run(){
        SizeBasedLoadBalancer loadBalancerThread = new SizeBasedLoadBalancer();;
        loadBalancerThread.start();
        Random rn = new Random();
        for (int i = 0; i < NUM_EVENTS; i++){
            int temp = rn.nextInt(100) + 1;
            loadBalancerThread.addItemToQueue(new Task(temp));
        }
        loadBalancerThread.addItemToQueue(new Task(0));
    }

}
