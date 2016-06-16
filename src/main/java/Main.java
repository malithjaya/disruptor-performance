import loadbalancer.SizeBasedLoadBalancer;
import process.ExponentialSimulation;
import workload.OutOfRangeException;

/**
 * Created by anoukh on 5/4/16.
 */
public class Main {
    public static void main(String[] args) throws OutOfRangeException {
//        UniformSimulation simulation = new UniformSimulation(new SizeBasedLoadBalancer());
//        simulation.start();
//        UniformSimulation simulation = new UniformSimulation(new RandomLoadBalancer());
//        simulation.start();
//        UniformSimulation simulation = new UniformSimulation(new RoundRobinLoadBalancer());
//        simulation.start();

        ExponentialSimulation exponentialSimulation = new ExponentialSimulation(new SizeBasedLoadBalancer());
        exponentialSimulation.start();
//        ExponentialSimulation exponentialSimulation=new ExponentialSimulation(new RoundRobinLoadBalancer());
//        exponentialSimulation.start();
//        ExponentialSimulation exponentialSimulation=new ExponentialSimulation(new RandomLoadBalancer());
//        exponentialSimulation.start();
    }
}
