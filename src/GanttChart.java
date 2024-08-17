public class GanttChart {
    private int id;
    private int start_time;
    private int end_time;

    public GanttChart(int id, int start_time, int end_time) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    public GanttChart() {}

    // ---------------------------- setter

    public void setEndTime(int end_time) {
        this.end_time = end_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartTime(int start_time) {
        this.start_time = start_time;
    }

    // ----------------------------- getter

    public int getId() {
        return id;
    }

    public int getEndTime() {
        return end_time;
    }

    public int getStartTime() {
        return start_time;
    }

    // ------------------------------------------ some important functions

    @Override
    public String toString() {
        return "[P"+id+"=> start:"+start_time+", end:"+end_time+"]";
    }
}
