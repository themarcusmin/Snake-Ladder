/*
 * Coordinate stores each instance of starting or ending postion for snake / ladder
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Coordinate))
            return false;
        Coordinate other = (Coordinate) obj;
        return other.getX() == this.getX()
                && other.getY() == this.getY();
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (int) (x ^ (x >>> 32 ));
        hash = 31 * hash + (int) (y ^ (y >>> 32));
        return hash;
    }

    public String toString() {
        String str = String.format("Y: %d, X: %d\n", y, x);
        return str;
    }
}