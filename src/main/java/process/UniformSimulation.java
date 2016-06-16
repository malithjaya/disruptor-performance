package process;

import loadbalancer.AbstractLoadBalancer;
import loadbalancer.RandomLoadBalancer;
import workload.Task;

import java.util.Random;

/**
 * Created by anoukh on 5/4/16.
 * UniformSimulation method
 */
public class UniformSimulation extends Thread
{

    private AbstractLoadBalancer loadBalancer;
    public final static long NUM_EVENTS = 1000000;

    /**
     * The constructor
     *
     * @param loadBalancer the load balancer to use
     */
    public UniformSimulation(AbstractLoadBalancer loadBalancer) {
        super("UniformSimulation Thread");
        this.loadBalancer = loadBalancer;
    }

    public void run(){
//        AbstractLoadBalancer loadBalancerThread = new SizeBasedLoadBalancer();
        AbstractLoadBalancer loadBalancerThread=new RandomLoadBalancer();
        loadBalancerThread.start();
        Random rn = new Random();
        for (int i = 0; i < NUM_EVENTS; i++){
            int temp = rn.nextInt(100) + 1;
            loadBalancerThread.addItemToQueue(new Task(temp));
        }
        loadBalancerThread.addItemToQueue(new Task(0));
    }

}
