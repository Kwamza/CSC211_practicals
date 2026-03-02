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
        generateData();
        int m = 1000003; // prime number

        int repetitions = 30;

        int[] sizes = {
                750000,
                800000,
                850000,
                900000,
                950000
        };

        System.out.println("Average time in seconds");
        System.out.println("N\tOpenHash\tChainedHash");


        for(int size : sizes){

            double openAvg = 0;
            double chainAvg = 0;


            // Repeat 30 times
            for(int r=0;r<repetitions;r++){

                openAvg += timeOpenHash(size,m);

                chainAvg += timeChainedHash(size,m);

            }

            openAvg /= repetitions;
            chainAvg /= repetitions;

            System.out.println(size + "\t" + openAvg + "\t" + chainAvg);

    }

    static double timeOpenHash(int size,int m){

        openHash table = new openHash(m);


        // Insert first size pairs

        for(int i=0;i<size;i++){

            table.insert(
                    data[i].key,
                    data[i].value
            );

        }
        long start = System.currentTimeMillis();


        // Lookup

        for(int i=0;i<size;i++){

            table.lookup(data[i].key);

        }


        long end = System.currentTimeMillis();


        return (end-start)/1000.0;

    }

    static double timeChainedHash(int size,int m){

        chainedHash table = new chainedHash(m);


        // Insert first size pairs

        for(int i=0;i<size;i++){

            table.insert(
                    data[i].key,
                    data[i].value
            );

        }
        long start = System.currentTimeMillis();


        // Lookup

        for(int i=0;i<size;i++){

            table.lookup(data[i].key);

        }


        long end = System.currentTimeMillis();


        return (end-start)/1000.0;

    }

}
