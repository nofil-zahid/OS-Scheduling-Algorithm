public class Process {
    private int id; // => P , id => P1, P2
    private int arrival_time;
    private int burst_time;
    private int waiting_time;
    private int turnaround_time;

    Process(int id, int arrival_time, int burst_time) {
        this.id = id;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
    }
    Process(int id, int arrival_time, int burst_time, int waiting_time, int turnaround_time) {
        this.id = id;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
        this.turnaround_time = turnaround_time;
        this.waiting_time = waiting_time;
    }

    // setter functions
    public void setWaitingTime(int waiting_time) {
        this.waiting_time = waiting_time;
    }
    public void setTurnaroundTime(int turnaround_time) {
        this.turnaround_time = turnaround_time;
    }

    // getter functions
    public int getArrivalTime() {
        return arrival_time;
    }
    public int getBurstTime() {
        return burst_time;
    }
    public int getTurnaroundTime() {
        return turnaround_time;
    }
    public int getWaitingTime() {
        return waiting_time;
    }
    public int getId() {
        return id;
    }

    // some important functions to use
    @Override
    public String toString() {
        return "[P"+id+" => {ArrivalTime:"+arrival_time+"},{BurstTime:"+burst_time+"}]";
    }
}
