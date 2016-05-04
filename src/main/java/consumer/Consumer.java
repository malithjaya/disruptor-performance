package consumer;

import workload.OutOfRangeException;
import workload.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by anoukh on 5/4/16.
 */
public class Consumer extends Thread{

    private LinkedBlockingQueue<Task> workloadQueue;

    public Consumer(LinkedBlockingQueue<Task> workloadQueue, String name) {
        super(name);
        this.workloadQueue = workloadQueue;
    }

    public void run(){
        if (workloadQueue.size() < 1){
            System.out.println("Ended Thread: " + super.getName() + "No Elements");
            return;
        }
        Date startDateTime = new Date();
        long startTime = startDateTime.getTime();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss-a-zzz");
        System.out.println("Started Thread : " + super.getName() + " at " + ft.format(startTime));
        for (Task task: workloadQueue) {
            try {
                task.executeTask();
            } catch (OutOfRangeException e) {
                e.printStackTrace();
            }
        }
        Date endDateTime = new Date();
        long endTime = endDateTime.getTime();
//        System.out.println("Ended Thread : " + super.getName() + " at " + ft.format(endTime) + " Size: " + workloadQueue.size());
        System.out.println("Ended Thread : " + super.getName() + " at " + ft.format(endTime) + " Size: " + workloadQueue.size() + " Throughput: " + workloadQueue.size()/(float)(endTime-startTime)*1000);
    }

}
