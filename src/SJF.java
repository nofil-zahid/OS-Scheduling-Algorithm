import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SJF {
    private ArrayList<Process> processes;
    private ArrayList<GanttChart> chart;
    private int no_of_processses;
    SJF(Process[] p){
        processes = new ArrayList<>();
        chart = new ArrayList<GanttChart>();
        arrange(p);
        no_of_processses = processes.size();
    }

    void arrange(Process[] process) {
        int smallestArrivalTime=process[0].getArrivalTime(), burstTimeOfMinArrival=process[0].getArrivalTime();

        for (Process p : process) {
            if ((p.getArrivalTime() < smallestArrivalTime) || ((p.getArrivalTime() == smallestArrivalTime) && (p.getBurstTime() < burstTimeOfMinArrival))) {
                smallestArrivalTime = p.getArrivalTime();
                burstTimeOfMinArrival = p.getBurstTime();

                if (processes.isEmpty()) {
                    processes.add(p);
                }
                else {
                    processes.set(0, p);
                }
            }
        }

        Arrays.sort(process, Comparator.comparingInt(Process::getBurstTime));
        for (Process p : process) {
            if (p.getId() == processes.get(0).getId()) {
                continue;
            }
            processes.add(p);
        }
    }

    // Question
    public void printArrayInTable() {
        System.out.println("-------------------------------------------");
        System.out.println("Process\t|\tArrival Time\t|\tBurst Time");
        System.out.println("-------------------------------------------");
        for(int i=0; i<no_of_processses; ++i) {
            System.out.println("P"+processes.get(i).getId()+"\t\t|\t"+processes.get(i).getArrivalTime()+"\t\t\t\t|\t"+processes.get(i).getBurstTime());
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
                    "P"+processes.get(i).getId()+
                            "\t\t|\t"+processes.get(i).getArrivalTime()+
                            "\t\t\t\t|\t"+processes.get(i).getBurstTime()+
                            "\t\t\t|\t"+processes.get(i).getWaitingTime()+
                            "\t\t\t\t|\t"+processes.get(i).getTurnaroundTime()
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
//        sort(processes, "arrival_time");
        int start=0, end=0;
        for (int i=0; i<no_of_processses; ++i) {
            if (i == 0) {
                start = processes.get(i).getArrivalTime();
                end = processes.get(i).getArrivalTime()+processes.get(i).getBurstTime();
            }
            else {
                if (chart.getLast().getEndTime() >= processes.get(i).getArrivalTime()) {
                    start = chart.getLast().getEndTime();
                    end = start+processes.get(i).getBurstTime();
                }
                else {
                    System.out.println("\n\nCant' process this input");
                    System.exit(0);
                }
            }
            chart.add(new GanttChart(processes.get(i).getId(), start, end));
        }
    }
    private void processingSolution() {
        for (int i=0; i<no_of_processses; ++i) {
            processes.get(i).setWaitingTime(chart.get(i).getStartTime() - processes.get(i).getArrivalTime());
            processes.get(i).setTurnaroundTime(processes.get(i).getBurstTime() + processes.get(i).getWaitingTime());
        }
        sort(processes, "id");
    }

    private void sort(ArrayList<Process> p, String reference) {
        if (reference.equals("arrival_time"))
            p.sort(Comparator.comparingInt(Process::getArrivalTime));
        else if (reference.equals("id"))
            p.sort(Comparator.comparingInt(Process::getId));
        else {
            System.out.println("INVALID INPUT");
        }
    }
}
