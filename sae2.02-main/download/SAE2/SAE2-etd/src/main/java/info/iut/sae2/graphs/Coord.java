package info.iut.sae2.graphs;
/**
 *
 * @author cbardot et ojfrancois
 */
public class Coord {
    private double x, y;

    public Coord() {
        x = y = 0.;
    }

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Cette méthode permet de calculer la distance euclidienne en deux sommets du
     * graph
     * 
     * @param c les coordonnées du sommet
     * @return un double: la distance entre deux sommets
     */
    public double dist(Coord c) {
        return Math.sqrt(Math.pow((c.getX() - this.getX()), 2) + Math.pow((c.getY() - this.getY()), 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coord other = (Coord) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }
}
