package workload;

import util.Constants;

/**
 * Created by anoukh on 5/4/16.
 * Task class
 */
public class Task {
    private double size;

    /**
     * The constructor
     *
     * @param size the size of the task
     */
    public Task(double size) {
        this.size = size;
    }


    /**
     * Gets the size of the task to be excecuted
     *
     * @return the size
     */
    public double getSize() {
        return size;
    }

    /**
     * Execute the task
     *
     * @return status of the method call
     */
    public String executeTask()  {

        double count = 0;
        for (double i = 0; i < this.size * Constants.TASK_COMPLEXITY; i++){
            count++;
        }
            return "Done " + count;
    }
}
