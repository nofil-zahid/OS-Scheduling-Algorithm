import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int idCount = 1;

        // ========================= FCFS ========================

//        Process[] p = {
//          new Process(0, 7, 8),
//          new Process(1, 10, 13),
//          new Process(2, 18, 25),
//          new Process(3, 27, 28),
//        };

//        Process[] p = {
//            new Process(1,2,8),
//            new Process(2,4,2),
//            new Process(3,1,1),
//            new Process(4,3,4),
//            new Process(5,6,3)
//        };

//        Process[] p = {
//                new Process(1, 0, 1),
//                new Process(2, 3, 2),
//                new Process(3, 4, 3)
//        };

//        FCFS algo = new FCFS(p);

        // ======================== SJF ========================

        Process[] p = {
          new Process(idCount++, 2,8),
          new Process(idCount++, 4,2),
          new Process(idCount++, 1,5),
          new Process(idCount++, 3,4),
          new Process(idCount++, 6,3),
        };

        SJF algo = new SJF(p);

        System.out.println("QUESTION :_");
        algo.printArrayInTable();
        System.out.println("\nGANTT CHART :_");
        algo.printGanttChart();
        System.out.println("\nSOLUTION :_");
        algo.printSolutionTable();

        System.out.println("\nAverage Turnaround Time = "+ algo.getAvgTurnaroundTime());
        System.out.println("Average Waiting Time = "+ algo.getAvgWaitingTime());

        sc.close();
    }
}
