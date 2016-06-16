package loadbalancer;

import workload.Task;

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
//                    workloadQueueFour.add(task);
//                    workloadQueueThree.add(task);
                    workloadQueueTwo.add(task);
                    workloadQueueOne.add(task);
                    break;
                }

                if (task.getSize() <= 1.1) {
                    workloadQueueTwo.add(task);
                } else if (task.getSize() <= 100) {
                    workloadQueueOne.add(task);
//                } else if (task.getSize() <= 75){
//                    workloadQueueFour.add(task);
//                } else {
//                    workloadQueueThree.add(task);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
