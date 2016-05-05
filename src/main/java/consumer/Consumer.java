package consumer;

import loadbalancer.SizeBasedLoadBalancer;
import workload.OutOfRangeException;
import workload.Task;

import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by anoukh on 5/4/16.
 */
public class Consumer extends Thread{

    private LinkedBlockingQueue<Task> workloadQueue;
    private int threadIdentifier;
    private int count;

    public Consumer(LinkedBlockingQueue<Task> workloadQueue, String name) {
        super(name);

        if (name.equals("Consumer One")){
            threadIdentifier = 1;
        } else if (name.equals("Consumer Two")){
            threadIdentifier = 2;
        } else if (name.equals("Consumer Three")){
            threadIdentifier = 3;
        } else if (name.equals("Consumer Four")){
            threadIdentifier = 4;
        }

        this.workloadQueue = workloadQueue;
    }

    public void run(){

        long startTime = System.currentTimeMillis();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss-a-zzz");
        System.out.println("Started Thread : " + super.getName() + " at " + ft.format(startTime));


        while(true){
            try {
                Task task = workloadQueue.take();

            if (task.getSize() == 0){
                break;
            } else {
                task.executeTask();
                count++;
            }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
        }


        long endTime = System.currentTimeMillis();
        float runTime = (endTime - startTime)/1000.0f;
        float throughput = count/runTime;
        System.out.println(super.getName()/* + " at " + ft.format(endTime) */+ " : Size: " + count + ", Runtime: " + runTime + ", Mean Latency " + runTime/count + ", Throughput: " + throughput + " tasks/sec");
        SizeBasedLoadBalancer.throughput += throughput;
        SizeBasedLoadBalancer.latency += runTime;
        SizeBasedLoadBalancer.count += count;
        switch (threadIdentifier){
            case 1:
                SizeBasedLoadBalancer.queueOneFinished = true;
                break;
            case 2:
                SizeBasedLoadBalancer.queueTwoFinished = true;
                break;
            case 3:
                SizeBasedLoadBalancer.queueThreeFinished = true;
                break;
            case 4:
                SizeBasedLoadBalancer.queueFourFinished = true;
                break;
        }
    }

}
