package loadbalancer;

import workload.Task;

/**
 * Created by malithjayasinghe on 5/5/16.
 */
public class RoundRobinLoadBalancer extends AbstractLoadBalancer {


    @Override
    public void loadBalancerImpl() {
        int number = 1;
        while (true) {
            try {
                Task task = getTaskQueue().take();
                if (task.getSize() == 0) {
//                    workloadQueueFour.add(task);
//                    workloadQueueThree.add(task);
                    workloadQueueTwo.add(task);
                    workloadQueueOne.add(task);
                    break;
                }
                switch (number) {
                    case 1:
                        workloadQueueOne.add(task);
                        number++;
                        break;
                    case 2:
                        workloadQueueTwo.add(task);
                        number = 1;
                        break;
//                    case 3:
//                        workloadQueueThree.add(task);
//                        number++;
//                        break;
//                    case 4:
//                        workloadQueueFour.add(task);
//                        number=1;
//                        break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
