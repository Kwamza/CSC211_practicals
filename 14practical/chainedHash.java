import java.util.LinkedList;

public class chainedHash {

    private LinkedList<Pair>[] table;
    private int m;

    public chainedHash(int m) {

        this.m = m;

        table = new LinkedList[m + 1];

        for (int i = 1; i <= m; i++)
            table[i] = new LinkedList<>();
    }

    private int hash(String key) {

        int h = Math.abs(key.hashCode());
        return (h % m) + 1;

    }

    public boolean isEmpty() {

        for (int i = 1; i <= m; i++)
            if (!table[i].isEmpty())
                return false;

        return true;
    }

    public boolean isInTable(String key) {

        return lookup(key) != null;

    }

    public void insert(String key, String value) {

        int i = hash(key);

        for (Pair p : table[i]) {

            if (p.key.equals(key)) {

                p.value = value;
                return;
            }
        }

        table[i].add(new Pair(key, value));

    }

    public String lookup(String key) {

        int i = hash(key);

        for (Pair p : table[i]) {

            if (p.key.equals(key))
                return p.value;
        }

        return null;
    }

    public String remove(String key) {

        int i = hash(key);

        for (Pair p : table[i]) {

            if (p.key.equals(key)) {

                table[i].remove(p);
                return p.value;
            }
        }

        return null;
    }
}
