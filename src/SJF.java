import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SJF {
    private ArrayList<Process> processes;
    private ArrayList<GanttChart> chart;
    private int no_of_processes;

    SJF(Process[] p) {
        processes = new ArrayList<>();
        chart = new ArrayList<>();
        arrange(p);
        no_of_processes = processes.size();
    }

    void arrange(Process[] process) {
        // Sort by arrival time first, then by burst time
        Arrays.sort(process, Comparator.comparingInt(Process::getArrivalTime)
                .thenComparingInt(Process::getBurstTime));
        processes.addAll(Arrays.asList(process));
    }

    // Print processes in a table format
    public void printArrayInTable() {
        System.out.println("-------------------------------------------");
        System.out.println("Process\t|\tArrival Time\t|\tBurst Time");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < no_of_processes; ++i) {
            System.out.println("P" + processes.get(i).getId() + "\t\t|\t" + processes.get(i).getArrivalTime() + "\t\t\t\t|\t" + processes.get(i).getBurstTime());
        }
        System.out.println("-------------------------------------------");
    }

    // Print the solution table with waiting time and turnaround time
    public void printSolutionTable() {
        processingFunc();

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Process\t|\tArrival Time\t|\tBurst Time\t|\tWaiting Time\t|\tTurnaround Time");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0; i < no_of_processes; ++i) {
            System.out.println(
                    "P" + processes.get(i).getId() +
                            "\t\t|\t" + processes.get(i).getArrivalTime() +
                            "\t\t\t\t|\t" + processes.get(i).getBurstTime() +
                            "\t\t\t|\t" + processes.get(i).getWaitingTime() +
                            "\t\t\t\t|\t" + processes.get(i).getTurnaroundTime()
            );
        }
        System.out.println("------------------------------------------------------------------------------------");
    }

    // Print the Gantt chart
    public void printGanttChart() {
        processingGanttChart();
        for (GanttChart g : chart) {
            System.out.println(g);
        }
    }

    // Calculate and return the average turnaround time
    public double getAvgTurnaroundTime() {
        double sum = 0;
        for (Process p : processes) {
            sum += p.getTurnaroundTime();
        }
        return sum / no_of_processes;
    }

    // Calculate and return the average waiting time
    public double getAvgWaitingTime() {
        double sum = 0;
        for (Process p : processes) {
            sum += p.getWaitingTime();
        }
        return sum / no_of_processes;
    }

    // Main processing function to handle Gantt chart and solution table
    private void processingFunc() {
        processingGanttChart();
        processingSolution(); // waiting time, turnaround time
    }

    // Process the Gantt chart
    private void processingGanttChart() {
        int start, end = 0;
        for (int i = 0; i < no_of_processes; ++i) {
            if (i == 0) {
                start = processes.get(i).getArrivalTime();
            } else {
                start = Math.max(chart.get(chart.size() - 1).getEndTime(), processes.get(i).getArrivalTime());
            }
            end = start + processes.get(i).getBurstTime();
            chart.add(new GanttChart(processes.get(i).getId(), start, end));
        }
    }

    // Process the solution (waiting time and turnaround time)
    private void processingSolution() {
        for (int i = 0; i < no_of_processes; ++i) {
            processes.get(i).setWaitingTime(chart.get(i).getStartTime() - processes.get(i).getArrivalTime());
            processes.get(i).setTurnaroundTime(processes.get(i).getBurstTime() + processes.get(i).getWaitingTime());
        }
        sort(processes, "id");
    }

    // Sort processes based on arrival time or ID
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
