package loadbalancer;

import consumer.Consumer;
import workload.Task;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by anoukh on 5/4/16.
 */
public class SizeBasedLoadBalancer extends AbstractLoadBalancer{


    @Override
    public void loadBalancerImpl() {

        int i = 1;
        while (true){
            try {
                Task task = getTaskQueue().take();
                if (task.getSize() == 0){
                    workloadQueueFour.add(task);
                    workloadQueueThree.add(task);
                    workloadQueueTwo.add(task);
                    workloadQueueOne.add(task);
                    break;
                }

                if (task.getSize() <= 25){
                    workloadQueueFour.add(task);
                } else if (task.getSize() <= 50){
                    workloadQueueThree.add(task);
                } else if (task.getSize() <= 75){
                    workloadQueueTwo.add(task);
                } else {
                    workloadQueueOne.add(task);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
