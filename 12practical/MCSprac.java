import java.util.Random;

public class MCSprac {
    private static long count;

    // 0n^3 algorithm
    public static int mcsOn3(int[] X){
        count = 0;
        int n = X.length;
        int maxSoFar = 0;

        for(int low = 0; low < n; low++){
            for(int high = low; high < n; high++){
                int sum = 0;
                for(int i = low; i <= high; i++){
                    sum += X[i];
                    count++;
                }
                if(sum > maxSoFar){
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;
    }

    // first 0n^2 algorithm
    public static int mcsOn2A(int[] X){
        count = 0;
        int n = X.length;
        int maxSoFar = 0;

        for(int low = 0; low < n; low++){
            int sum = 0;
            for(int i = low; i < n; i++){
                sum += X[i];
                count++;
                if(sum > maxSoFar){
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;
    }

    // second 0n^2 algorithm
    public static int mcsOn2B(int[] X){
        count = 0;
        int n = X.length;
        int[] sumTo = new int[n + 1];
        // prefix sum array
        sumTo[0] = 0;
        for (int i = 1; i < n; i++){
            sumTo[i+1] = sumTo[i] + X[i];
        }

        int maxSoFar = 0;
        for (int low = 0; low < n; low++){
            for(int high = low; high < n; high++){
                int sum = sumTo[high + 1] - sumTo[low];
                count++;
                if(sum > maxSoFar){
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;
    }

    // fourth algorithm
    public static int mcsOn(int[] X){
        count = 0;
        int n = X.length;
        int maxSoFar = 0;
        int maxToHere = 0;

        for(int i = 0; i < n; i++){
            maxToHere = Math.max(X[i], maxToHere + X[i]);
            maxSoFar = Math.max(maxSoFar, maxToHere);
            count++;  
        }
        return maxSoFar;
    }

    public static int[] generateRandomArray(int n){
        Random rand = new Random();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            int value = rand.nextInt(n) + 1 ;
            int sign = rand.nextInt(2,5); 
            arr[i] = value * (int)Math.pow(-1, sign); // randomly assign positive or negative sign
        }
        return arr;
    }

        public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000, 1000000};
        
        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", 
            "n", "O(n³)", "O(n²)A", "O(n²)B", "O(n)");
        System.out.println("-".repeat(70));
        
        for (int n : sizes) {
            int[] X = generateRandomArray(n);

            // O(n³)
            mcsOn3(X);
            long count3 = count;
            
            // O(n²)A
            mcsOn2A(X);
            long count2A = count;
            
            // O(n²)B
            mcsOn2B(X);
            long count2B = count;
            
            // O(n)
            mcsOn(X);
            long countN = count;
            
            if (n <= 10000) {
                System.out.printf("%-10d %-15d %-15d %-15d %-15d%n", 
                    n, count3, count2A, count2B, countN);
            } else {
                System.out.printf("%-10d %-15s %-15d %-15d %-15d%n", 
                    n, "-", count2A, count2B, countN);
            }
        }
    }

}
