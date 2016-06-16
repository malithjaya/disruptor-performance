package loadbalancer;

import workload.Task;

/**
 * Created by malithjayasinghe on 5/5/16.
 */
public class RandomLoadBalancer extends AbstractLoadBalancer{


    @Override
    public void loadBalancerImpl() {

        while (true){
            try {
                Task task = getTaskQueue().take();
                int random = 1 + (int)(Math.random() * ((2 - 1) + 1));
                if (task.getSize() == 0) {
//                    workloadQueueFour.add(task);
//                    workloadQueueThree.add(task);
                    workloadQueueTwo.add(task);
                    workloadQueueOne.add(task);
                    break;
                }

                switch (random){
                    case 1:
                        workloadQueueOne.add(task);
                        break;
                    case 2:
                        workloadQueueTwo.add(task);
                        break;
//                    case 3:
//                        workloadQueueThree.add(task);
//                        break;
//                    case 4:
//                        workloadQueueFour.add(task);
//                        break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
