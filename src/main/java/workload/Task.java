package workload;

/**
 * Created by anoukh on 5/4/16.
 * Task class
 */
public class Task {
    private int size;

    public Task(int size) {
        this.size = size;
    }


    public int getSize() {
        return size;
    }

    /**
     *
     * @return status of the method call
     */
    public String executeTask() throws OutOfRangeException {
        if (this.size < 1 || this.size > 100){
            throw new OutOfRangeException("Range: 1 <= size <= 100");
        }
        double count = 0;
        for (double i = 0; i < this.size*100000000d; i++){
            count++;
        }
            return "Done " + count;
    }
}
