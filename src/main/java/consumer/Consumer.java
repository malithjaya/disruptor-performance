package consumer;

import loadbalancer.AbstractLoadBalancer;
import workload.Task;

import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by anoukh on 5/4/16.
 */
public class Consumer extends Thread{

    private LinkedBlockingQueue<Task> workloadQueue;
    private int threadIdentifier;
    private int count = 0;

    public Consumer(LinkedBlockingQueue<Task> workloadQueue, String name) {
        super(name);

        if (name.equals("Consumer One")){
            threadIdentifier = 1;
        } else if (name.equals("Consumer Two")){
            threadIdentifier = 2;
//        } else if (name.equals("Consumer Three")){
//            threadIdentifier = 3;
//        } else if (name.equals("Consumer Four")){
//            threadIdentifier = 4;
        }

        this.workloadQueue = workloadQueue;
    }

    public void run(){

        long startTime = System.currentTimeMillis();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss-a-zzz");
        System.out.println("Started Thread : " + super.getName() + " at " + ft.format(startTime));


        while(true){
            try {
                Task task = workloadQueue.poll(100, TimeUnit.MILLISECONDS);

            if (task.getSize() == 0){
                break;
            } else {
                count++;
                task.executeTask();
            }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        long endTime = System.currentTimeMillis();
        float runTime = (endTime - startTime)/1000.0f;
        float throughput = count/runTime;
        System.out.println(super.getName()/* + " at " + ft.format(endTime) */+ " : Size: " + count + ", Runtime: " + runTime + ", Mean Latency " + runTime/count + ", Throughput: " + throughput + " tasks/sec");
//        SizeBasedLoadBalancer.throughput += throughput;
//        SizeBasedLoadBalancer.latency += runTime;
//        SizeBasedLoadBalancer.count += count;
        AbstractLoadBalancer.throughput += throughput;
        AbstractLoadBalancer.latency += runTime;
        AbstractLoadBalancer.count += count;
        switch (threadIdentifier){
            case 1:
                AbstractLoadBalancer.queueOneFinished = true;
                break;
            case 2:
                AbstractLoadBalancer.queueTwoFinished = true;
                break;
//            case 3:
//                SizeBasedLoadBalancer.queueThreeFinished = true;
//                break;
//            case 4:
//                SizeBasedLoadBalancer.queueFourFinished = true;
//                break;
        }
    }

}
