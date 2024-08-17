import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FCFS {
    private Process[] processes;
    private ArrayList<GanttChart> chart;
    private int no_of_processses;
    FCFS(Process[] p){
        processes = p;
        no_of_processses = processes.length;

        chart = new ArrayList<GanttChart>();
    }

    // Question
    public void printArrayInTable() {
        System.out.println("-------------------------------------------");
        System.out.println("Process\t|\tArrival Time\t|\tBurst Time");
        System.out.println("-------------------------------------------");
        for(int i=0; i<no_of_processses; ++i) {
            System.out.println("P"+processes[i].getId()+"\t\t|\t"+processes[i].getArrivalTime()+"\t\t\t\t|\t"+processes[i].getBurstTime());
        }
        System.out.println("-------------------------------------------");
    }

    // Answer
    public void printSolutionTable() {
        processingFunc();

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Process\t|\tArrival Time\t|\tBurst Time\t|\tWaiting Time\t|\tTurnaround Time");
        System.out.println("------------------------------------------------------------------------------------");
        for(int i=0; i<no_of_processses; ++i) {
            System.out.println(
                    "P"+processes[i].getId()+
                    "\t\t|\t"+processes[i].getArrivalTime()+
                    "\t\t\t\t|\t"+processes[i].getBurstTime()+
                    "\t\t\t|\t"+processes[i].getWaitingTime()+
                    "\t\t\t\t|\t"+processes[i].getTurnaroundTime()
            );
        }
        System.out.println("------------------------------------------------------------------------------------");
    }

    public void printGanttChart() {
        processingGanttChart();
        for (GanttChart g : chart) {
            System.out.println(g);
        }
    }

    public double getAvgTurnaroundTime() {
        double sum = 0;
        for (Process p : processes) {
            sum += p.getTurnaroundTime();
        }
        return sum / no_of_processses;
    }

    public double getAvgWaitingTime() {
        double sum = 0;
        for (Process p : processes) {
            sum += p.getWaitingTime();
        }
        return sum / no_of_processses;
    }

    // IMPORTANT FUNCTION
    private void processingFunc() {
        processingGanttChart();
        processingSolution(); // waiting time, turnaround time
    }
    private void processingGanttChart() {
        sort(processes, "arrival_time");
        int start = 0, end = 0;
        for (int i = 0; i < no_of_processses; ++i) {
            if (i == 0) {
                start = processes[i].getArrivalTime();
                end = start + processes[i].getBurstTime();
            } else {
                if (chart.get(chart.size()-1).getEndTime() < processes[i].getArrivalTime()) {
                    start = processes[i].getArrivalTime();
                } else {
                    start = chart.get(chart.size() - 1).getEndTime();
                }
                end = start + processes[i].getBurstTime();
            }
            chart.add(new GanttChart(processes[i].getId(), start, end));
        }
    }

    private void processingSolution() {
        for (int i=0; i<no_of_processses; ++i) {
            processes[i].setWaitingTime(chart.get(i).getStartTime() - processes[i].getArrivalTime());
            processes[i].setTurnaroundTime(processes[i].getBurstTime() + processes[i].getWaitingTime());
        }
        sort(processes, "id");
    }

    private void sort(Process[] p, String reference) {
        if (reference.equals("arrival_time"))
            Arrays.sort(p, Comparator.comparingInt(Process::getArrivalTime));
        else if (reference.equals("id"))
            Arrays.sort(p, Comparator.comparingInt(Process::getId));
        else {
            System.out.println("INVALID INPUT");
        }
    }
}
