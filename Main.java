import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static int[] slowshuffle(int N) {
        int[] shuffled = new int[N]; 
        boolean[] isNotPresent = new boolean[N + 2]; 
        Arrays.fill(isNotPresent, true); 

        int i = 0;
        while (i < N - 1) { 
            int r = (int) (Math.random() * N) + 1; 
            if (isNotPresent[r] && r != 0) {
                shuffled[i] = r; 
                isNotPresent[r] = false; 
                i++; 
            }
        }
        for (int j = 1; j <= N; j++) {
            if (isNotPresent[j]) {
                shuffled[N - 1] = j; 
                break;
            }
        }
        return shuffled;
    }

    public static int[] shuffleBiased(int N){
        int[] shuffled = new int[N];
        for (int i = 0; i < N; i++){
            shuffled[i] = i+1;
        }
        for (int i = 0; i < N; i++) {
            int r = (int) (Math.random() * N);
            int temp = shuffled[i];
            shuffled[i] = shuffled[r];
            shuffled[r] = temp;
        }

        return shuffled;
    }

    public static int[] shuffleUnbiased(int N) {
        int[] B = new int[N];
        for (int i = 0; i < N; i++) B[i] = i + 1; 

        for (int b = 0; b < N; b++) { 
            // Generate r in the range
            int r = b + (int) (Math.random() * (N - b));
            // Swap B[b] with B[r] 
            int temp = B[b];
            B[b] = B[r];
            B[r] = temp;
        }
        return B;
    }

    public static void runTest(String methodName) {
        Map<String, Integer> D = new HashMap<>();
        int N = 3;
        int iterations = 60000;

        for (int i = 0; i < iterations; i++) {
            int[] result = methodName.equals("biased") ? shuffleBiased(N) : shuffleUnbiased(N);
            
            // Create string key (e.g., "132")
            StringBuilder keyBuilder = new StringBuilder();
            for (int num : result) keyBuilder.append(num);
            String key = keyBuilder.toString(); 

            // Update dictionary counts 
            D.put(key, D.getOrDefault(key, 0) + 1); 
        }

        // List the keys and counts
        System.out.println("Results for " + methodName + ":");
        for (String key : D.keySet()) { 
            System.out.println(key + " " + D.get(key)); 
        }
    }


    public static void main(String[] args) {
        int N = 9; 
        int[] result = shuffleUnbiased(N);
        System.out.println("Shuffled array: " + Arrays.toString(result));

        runTest("biased");
        runTest("unbiased");
    }
}