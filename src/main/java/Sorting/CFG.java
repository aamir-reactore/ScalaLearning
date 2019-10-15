package Sorting;

public class CFG {
        // Java recursive function to solve tower of hanoi puzzle
        static void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod)
        {
            System.out.println("n:" + n);
            System.out.println("from rod:" + from_rod);
            System.out.println("to rod:" + to_rod);
            System.out.println("temp rod:" + aux_rod);
            if (n == 1)
            {
                System.out.println("Move disk 1 from rod " +  from_rod + " to rod " + to_rod);
                return;
            }
            System.out.println("=========================");
            towerOfHanoi(n-1, from_rod, aux_rod, to_rod);
            System.out.println("Move disk " + n + " from rod " +  from_rod + " to rod " + to_rod);
            towerOfHanoi(n-1, aux_rod, to_rod, from_rod);
        }

        //  Driver method
        public static void main(String args[])
        {
            int n = 3; // Number of disks
            towerOfHanoi(n, 'A', 'B', 'C');  // A, B and C are names of rods
        }
    }
