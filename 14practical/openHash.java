public class openHash {

    private Pair[] table;
    private int m;
    private int size;

    public openHash(int m) {
        this.m = m;
        table = new Pair[m + 1];
        size = 0;
    }

    private int hash(String key) {
        int h = Math.abs(key.hashCode());
        return (h % m) + 1;
    }

    public boolean isFull() {
        return size == m;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isInTable(String key) {
        return lookup(key) != null;
    }

    public void insert(String key, String value) {

        if (isFull())
            return;

        int i = hash(key);

        while (table[i] != null && !table[i].key.equals(key)) {

            i = (i % m) + 1; // linear probing

        }

        if (table[i] == null) {
            table[i] = new Pair(key, value);
            size++;
        }
        else {
            table[i].value = value;
        }
    }

    public String lookup(String key) {

        int i = hash(key);

        while (table[i] != null) {

            if (table[i].key.equals(key))
                return table[i].value;

            i = (i % m) + 1;
        }

        return null;
    }

    public String remove(String key) {

        int i = hash(key);

        while (table[i] != null) {

            if (table[i].key.equals(key)) {

                String val = table[i].value;
                table[i] = null;
                size--;

                return val;
            }

            i = (i % m) + 1;
        }

        return null;
    }
}

