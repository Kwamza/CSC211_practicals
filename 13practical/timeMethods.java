// Code is stored as 13template.java
import java.lang.Math.*;   import java.io.*;   import java.text.*; import java.util.Random;

public class timeMethods {

    public static int N = 32654;

    // Node class to hold each record: an integer key and its associated text
    static class Node {
        int key;
        String data;
        Node(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }

    static int linearsearch(Node[] arr, int n, int target) {
        for (int i = 0; i < n; i++) {
            if (arr[i].key == target) return i;
        }
        return -1;
    }

    static int binarysearch(Node[] arr, int n, int target) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if      (arr[mid].key == target) return mid;
            else if (arr[mid].key <  target) lo = mid + 1;
            else                             hi = mid - 1;
        }
        return -1;
    }

    public static void main(String args[])throws IOException {

        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        int n = N;

        //Load the data file into the array
        Node[] records = new Node[n];
        BufferedReader br = new BufferedReader(new FileReader("ulysses.numbered"));
        String line;
        int idx = 0;
        while ((line = br.readLine()) != null && idx < n) {
            // Each line: "00001 some text..."
            int spacePos = line.indexOf(' ');
            int key;
            String data;
            if (spacePos == -1) {
                key  = Integer.parseInt(line.trim());
                data = "";
            } else {
                key  = Integer.parseInt(line.substring(0, spacePos).trim());
                data = line.substring(spacePos + 1);
            }
            records[idx++] = new Node(key, data);
        }
        br.close();
        System.out.println("Loaded " + idx + " records from ulysses.numbered.");
        
        // generate random keys to search for
        int repetition, repetitions = 30;
        Random rng = new Random(42);
        int[] searchKeys = new int[repetitions];
        for (int i = 0; i < repetitions; i++) {
            searchKeys[i] = 1 + rng.nextInt(n); // keys in range [1, 32654]
        }
        

        // Timing the  linear search
        double runTimeL = 0;
        double runTimeL2 = 0;
        for (int rep = 0; rep < repetitions; rep++) {
            long start  = System.currentTimeMillis();

            linearsearch(records, n, searchKeys[rep]);

            long finish = System.currentTimeMillis();
            double time = (double)(finish - start);
            runTimeL  += time;
            runTimeL2 += (time * time);
        }

        double aveLinear    = runTimeL  / repetitions;
        double stdDevLinear =
            Math.sqrt(Math.abs(runTimeL2 - repetitions * aveLinear * aveLinear))
                / (repetitions - 1);


        // Timing the  binary search
        double runTimeB = 0, runTime2B = 0;

        for (int rep = 0; rep < repetitions; rep++) {
            long start  = System.currentTimeMillis();

            binarysearch(records, n, searchKeys[rep]);

            long finish = System.currentTimeMillis();
            double time = (double)(finish - start);
            runTimeB  += time;
            runTime2B += (time * time);
        }

        double aveBinary    = runTimeB  / repetitions;
        double stdDevBinary =
            Math.sqrt(Math.abs(runTime2B - repetitions * aveBinary * aveBinary))
            / (repetitions - 1);

        //Statistics display

        System.out.printf("\n\n\fStatistics\n");
            System.out.println("________________________________________________");

            System.out.println("\n--- Linear Search ---");
            System.out.println("Total time         = " + runTimeL / 1000 + "s.");
            System.out.println("Total time\u00b2        = " + runTimeL2);
            System.out.println("Average time       = " + fiveD.format(aveLinear / 1000)
                            + "s. \u00B1 " + fourD.format(stdDevLinear) + "ms.");
            System.out.println("Standard deviation = " + fourD.format(stdDevLinear));
            System.out.println("Average time / run = " + fiveD.format(aveLinear / n * 1000)
                            + "\u00B5s.");

            System.out.println("\n--- Binary Search ---");
            System.out.println("Total time         = " + runTimeB / 1000 + "s.");
            System.out.println("Total time\u00b2        = " + runTime2B);
            System.out.println("Average time       = " + fiveD.format(aveBinary / 1000)
                            + "s. \u00B1 " + fourD.format(stdDevBinary) + "ms.");
            System.out.println("Standard deviation = " + fourD.format(stdDevBinary));
            System.out.println("Average time / run = " + fiveD.format(aveBinary / n * 1000)
                            + "\u00B5s.");

            System.out.println("\n--- Summary (four key numbers) ---");
            System.out.println("n                  = " + n);
            System.out.println("Repetitions        = " + repetitions);
            System.out.println("Linear  avg (ms)   = " + fourD.format(aveLinear)
                            + " \u00B1 " + fourD.format(stdDevLinear));
            System.out.println("Binary  avg (ms)   = " + fourD.format(aveBinary)
                            + " \u00B1 " + fourD.format(stdDevBinary));
            System.out.println("________________________________________________");
            System.out.println();
            System.out.println();
        }
    }

