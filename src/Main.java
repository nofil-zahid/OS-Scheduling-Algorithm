import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int idCount = 1;

        // ========================= FCFS ========================

//        Process[] p1 = {
//          new Process(idCount++, 7, 8),
//          new Process(idCount++, 10, 13),
//          new Process(idCount++, 18, 25),
//          new Process(idCount++, 27, 28),
//        };

//        Process[] p2 = {
//            new Process(idCount++,2,8),
//            new Process(idCount++,4,2),
//            new Process(idCount++,1,1),
//            new Process(idCount++,3,4),
//            new Process(idCount++,6,3)
//        };

//        Process[] p3 = {
//                new Process(idCount++, 0, 1),
//                new Process(idCount++, 3, 2),
//                new Process(idCount++, 4, 3)
//        };
//
//        FCFS algo = new FCFS(p);

        // ======================== SJF ========================

        Process[] p1 = {
                new Process(idCount++, 0, 7),
                new Process(idCount++, 2, 4),
                new Process(idCount++, 4, 1),
                new Process(idCount++, 5, 4)
        };

//        Process[] p2 = {
//                new Process(idCount++, 0, 5),
//                new Process(idCount++, 8, 3),
//                new Process(idCount++, 12, 6),
//                new Process(idCount++, 15, 4)
//        };

        SJF algo = new SJF(p1);

        // ======================== SRTF ========================
        // ======================== PRIORITY SCHDULING ========================
        // ======================== ROUND ROBIN ========================

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
