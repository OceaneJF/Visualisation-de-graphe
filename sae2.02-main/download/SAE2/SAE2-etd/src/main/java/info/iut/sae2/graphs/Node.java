package info.iut.sae2.graphs;

import java.util.HashSet;

import info.iut.sae2.viewer.GraphCanvas;

public class Node extends Graph{

    /**
     * Le graph dans lequel se trouve le sommet
     */
    private Graph graph;
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

    public Node(Coord position,int num,Graph graph){
        this.position=position;
        this.num=num;
        this.graph=graph;
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

    /**
     * Cette méthode permet d'ajouter le sommet à la listes des sommets du graph
     * @return
     */
    public Node addNode(){
        graph.getNodes().add(this);
        return this;

    }

}
