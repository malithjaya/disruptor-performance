package loadbalancer;

import consumer.Consumer;
import workload.Task;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by malithjayasinghe on 5/5/16.
 */
public class AbstractLoadBalancer {

    LinkedBlockingQueue<Task> workloadQueueOne = new LinkedBlockingQueue<Task>();
    LinkedBlockingQueue<Task> workloadQueueTwo = new LinkedBlockingQueue<Task>();
    LinkedBlockingQueue<Task> workloadQueueThree = new LinkedBlockingQueue<Task>();
    LinkedBlockingQueue<Task> workloadQueueFour = new LinkedBlockingQueue<Task>();
    Consumer consumerThreadOne = new Consumer(workloadQueueOne, "Consumer One");
    Consumer consumerThreadTwo= new Consumer(workloadQueueTwo, "Consumer Two");
    Consumer consumerThreadThree = new Consumer(workloadQueueThree, "Consumer Three");
    Consumer consumerThreadFour = new Consumer(workloadQueueFour, "Consumer Four");

  
}
