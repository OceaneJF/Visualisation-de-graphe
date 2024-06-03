package info.iut.sae2.graphs;

import java.util.ArrayList;
/**
 *
 * @author cbardot et ojfrancois
 */
public class Edge {

    /*
     * Le sommet source de l'arrete
     */
    private Node source;

    /*
     * Le sommet destination de l'arrete
     */
    private Node target;

    /*
     * Les coordonées des brisures de l'arrete
     */
    private ArrayList<Coord> bends;

    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
        bends = new ArrayList<>();
        source.getEdges().add(this);
        target.getEdges().add(this);
    }

    /**
     * Cette méthode permet de récuperer le sommet source de l'arrete
     * 
     * @return le sommet source de l'arrete
     */
    public Node getSource() {
        return source;
    }

    /**
     * Cette méthode permet de récuperer le sommet destination de l'arrete
     * 
     * @return le sommet destination de l'arrete
     */
    public Node getTarget() {
        return target;
    }

    /**
     * 
     * Cette méthode permet de récuperer les coordonées des brisures de l'arrete
     * 
     * @return les coordonées des brisures de l'arrete
     */
    public ArrayList<Coord> getBends() {
        return bends;
    }

    /**
     * Cette méthode permet de modifier les coordonnées des brisures de l'arrete
     * 
     * @param les nouvelles coordonnées des brisures de l'arrete
     */
    public void setBends(ArrayList<Coord> positions) {
        bends = new ArrayList<>(positions);

    }

    /**
     * Deux arrêtes sont les mêmes si elles ont la même source et la même destination
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }

    /**
     * Deux arrêtes sont les mêmes si elles ont la même source et la même destination
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }

}
