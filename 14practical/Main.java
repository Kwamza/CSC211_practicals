import java.util.*;

public class Main {

    static int N = 1 << 20; // 1 million
    static Pair[] data = new Pair[N];

    static void generateData() {

        ArrayList<Integer> keys = new ArrayList<>();

        for (int i = 1; i <= N; i++)
            keys.add(i);

        Collections.shuffle(keys);

        for (int i = 0; i < N; i++) {

            String key = String.valueOf(keys.get(i));
            String value = String.valueOf(i + 1);

            data[i] = new Pair(key, value);

        }
    }

    public static void main(String[] args) {


        int m = 1000003; // prime number

    }

}
