package info.iut.sae2.graphs;

import java.util.ArrayList;

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

}
