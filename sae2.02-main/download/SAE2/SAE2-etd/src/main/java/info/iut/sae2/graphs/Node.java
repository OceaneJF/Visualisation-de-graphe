package info.iut.sae2.graphs;

import java.util.HashSet;

import info.iut.sae2.viewer.GraphCanvas;

public class Node {

    /**
     * Le graph dans lequel se trouve le sommet
     */
    //private Graph graph;
    
    /**
     * Les coordonnées du points 
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


    public HashSet<Edge> getEdges() {
        return edges;
    }

    public Node() {
        edges=new HashSet<>();
    }

    public Node(Coord position,int num){
        this.position=position;
        this.num=num;
        //this.graph=graph;
        edges=new HashSet<>();
    }

    /**
     * Récuperer les coordonnées d'un sommet
     * @return
     */
    public Coord getPosition(){
        return position;
    }

    /**
     * Cette méthode permet de modifier les coordonnées d'un sommet 
     * @param coord
     */
    public void setPosition(Coord coord){
        this.position=coord;
    }

    /**
     * Méthode permettant de récuperer le numéro du sommet 
     */
    public int getNum(){
        return num;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + num;
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
        Node other = (Node) obj;
        if (num != other.num)
            return false;
        return true;
    }

    

}
