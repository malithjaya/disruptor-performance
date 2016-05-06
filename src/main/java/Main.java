import loadbalancer.SizeBasedLoadBalancer;
import process.Simulation;
import workload.OutOfRangeException;

/**
 * Created by anoukh on 5/4/16.
 */
public class Main {
    public static void main(String[] args) throws OutOfRangeException {
        Simulation simulation = new Simulation(new SizeBasedLoadBalancer());
        simulation.start();
    }
}
