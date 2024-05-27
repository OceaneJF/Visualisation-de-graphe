package info.iut.sae2.graphs;

import java.util.HashSet;

import info.iut.sae2.viewer.GraphCanvas;

public class Node {

    /**
     * Les coordonnées du sommet
     */
    private Coord position;

    /**
     * Le numéro du sommet
     */
    private int num;

    /**
     * La liste des arrètes qui sont reliées au point
     */
    private HashSet<Edge> edges;

    public Node() {
        edges = new HashSet<>();
    }

    public Node(Coord position, int num) {
        this.position = position;
        this.num = num;
        edges = new HashSet<>();
    }

    /**
     * Récuperer les coordonnées d'un sommet
     * 
     * @return les coordonées du sommet
     */
    public Coord getPosition() {
        return position;
    }

    /**
     * Cette méthode permet de modifier les coordonnées d'un sommet
     * 
     * @param coord les nouvelles coordonées du sommet
     */
    public void setPosition(Coord coord) {
        this.position = coord;
    }

    /**
     * Méthode permettant de récuperer le numéro du sommet
     */
    public int getNum() {
        return num;
    }

    /**
     * Cette méthode permet de récuperer toutes les arretes reliés à ce sommet
     * 
     * @return toutes les arretes reliés à ce sommet
     */
    public HashSet<Edge> getEdges() {
        return edges;
    }

    /**
     * Cette méthode permet de modifier le numero du sommet
     * 
     * @param num le nouveau numero du sommet
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Cette méthode permet de modifier la liste contenant toutes les arretes reliés
     * à ce sommet
     * 
     * @param edges les nouvelles arretes reliés à ce sommet
     */
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Cette méthode permet d'ajouter une arrete dans la liste des arretes reliés à
     * ce sommet
     * 
     * @param e l'arrete à ajouter
     */
    public void addEdge(Edge e) {
        this.edges.add(e);
    }

    /**
     * Deux sommets sont les memes si leur numero sont égaux
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (num != other.num)
            return false;
        return true;
    }

}
