package process;

import loadbalancer.AbstractLoadBalancer;
import loadbalancer.SizeBasedLoadBalancer;
import workload.Task;

/**
 * Created by bhagya on 6/6/16.
 */
public class ExponentialSimulation extends Thread {
    private AbstractLoadBalancer loadBalancer;
    public final static long NUM_EVENTS = 1000000;

    /**
     * The constructor
     *
     * @param loadBalancer the load balancer to use
     */
    public ExponentialSimulation(AbstractLoadBalancer loadBalancer) {
        super("ExponentialSimulation Thread");
        this.loadBalancer = loadBalancer;
    }

    public void run() {
        AbstractLoadBalancer loadBalancerThread = new SizeBasedLoadBalancer();
//        AbstractLoadBalancer loadBalancerThread = new RoundRobinLoadBalancer();
//        AbstractLoadBalancer loadBalancerThread = new RandomLoadBalancer();
        loadBalancerThread.start();
        double lambda = 1;
        int min = 1;
        int max = 100;
        for (int i = 0; i < NUM_EVENTS; i++) {
            double temp = (Math.log(-(Math.random()) * ((Math.exp(-lambda * min)) - (Math.exp(-lambda * max))) + Math.exp((-lambda) * min))) / -(lambda);
            loadBalancerThread.addItemToQueue(new Task(temp));
        }
        loadBalancerThread.addItemToQueue(new Task(0));

    }


}
