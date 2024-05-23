package info.iut.sae2.graphs;

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
}
