import java.util.Arrays;

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

    public static void main(String[] args) {
        int N = 9; 
        int[] result = shuffleBiased(N);
        System.out.println("Shuffled array: " + Arrays.toString(result));
    }
}