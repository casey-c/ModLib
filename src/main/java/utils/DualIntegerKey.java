package utils;

public class DualIntegerKey {
    public final int x;
    public final int y;

    public DualIntegerKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DualIntegerKey)) return false;
        DualIntegerKey key = (DualIntegerKey) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Key: (" + x + ", " + y + ")";
    }
}
