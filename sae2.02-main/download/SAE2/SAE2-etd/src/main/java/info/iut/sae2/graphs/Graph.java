package info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.HashSet;

import info.iut.sae2.viewer.GraphCanvas;

public class Graph implements IGraph{

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph(){
        nodes=new ArrayList<>();
        edges=new ArrayList<>();
    }

/**
 * Cette méthode crée et ajoute un nouveaux sommet à la liste des sommets du graph
 */
    public Node addNode(){
        Node n= new Node();
        return addNode(n);
    }
/**
 * Cette méthode permet de récuperer la liste de tous les sommets du graph
 */
    public ArrayList<Node> getNodes(){
        return nodes;
    }

/**
 * Cette méthode permet d'ajouter un sommet passé en paramètre dans la liste des sommets du graph
 */
    public Node addNode(Node n){
        nodes.add(n);
        return n;
    }

    /*
     * Cette méthode permet d'ajouter une arrete au graph
     */
    public Edge addEdge(Edge e){
        edges.add(e);
        return e;
    }

    /**
     * Cette méthode permet d'ajouter une arrete dans la listes d'arretes du graph en précisant son sommet source et de destination 
     */
    public Edge addEdge(Node src, Node tgt){
        Edge e= new Edge(src, tgt);
        return addEdge(e);
    }

    /**
     * Cette méthode permet de supprimer un sommet de la liste des sommets du graph
     */
    public void delNode(Node n){
        nodes.remove(n);
    }

      /**
     * Cette méthode permet de supprimer une arrete de la liste des arretes du graph
     */
    public void delEdge(Edge e){
        edges.remove(e);
    }


    /**
     * Cette méthode permet de récuperer toutes les arrètes du graph 
     */
    public ArrayList<Edge> getEdges(){
        return edges;
    }

    /**
     * Cette méthode permet de déterminer le nombre de sommets présent dans le graph
     */
    public int numberOfNodes(){
        return nodes.size();
    }


    /**
     * Cette méthode permet de déterminer le nombre d'arrets présentes dans le graph
     */
    public int numberOfEdges(){
        return edges.size();
    }

    /**
     * Cette méthode permet de récupérer les coordonnée d'un sommet passé en paramètre
     * @param n le sommet dont on veut connaitre les coordonnées
     * @return les coordonnées de ce sommet
     */
    public Coord getNodePosition(Node n){
        return n.getPosition();
    }

    /**
     * Cette méthode permet de récuperer les coordonnées d'une arrete 
     */
    public ArrayList<Coord> getEdgePosition(Edge e){
        return e.getBends();
    }

 /*
  * Cette méthode permet de modifier les coordonées d'une arrete
  */
    public void setEdgePosition(Edge e, ArrayList<Coord> bends){
        e.setBends(bends);
    }


/**
 * Cette méthode permet de modifier les coordonnées d'un sommet passé en parametre
 */
    public void setNodePosition(Node n, Coord c){
        n.setPosition(c);
    }

    /**
     * Cette méthode permet de modifier les coordonnées de tous les sommets 
     */
    public void setAllNodesPositions(Coord c){
        for (Node n : nodes) {
            n.setPosition(c);
        }
    }

    /**
     * Cette méthode permet de modifier la position de toutes les arretes 
     */
    public void setAllEdgesPositions(ArrayList<Coord> bends){
        for(Edge e: edges) {
            e.setBends(bends);
        }
    }

    /**
     * Cette méthode permet de récuperer le sommet source de l'arrete passé en parametre 
     */
    public Node source(Edge e){
        return e.getSource();
    }

    /*
     * Cette méthode permet de récuperer le sommet de destination de l'arrete passée en parametre 
     */
    public Node target(Edge e){
        return e.getTarget();
    }

    /**
     * Cette méthode permet de récuperer tous les voisins d'un sommet
     */
    public ArrayList<Node> getNeighbors(Node n){
        ArrayList<Node> neighbors= new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!e.getSource().equals(n)) {
                neighbors.add(e.getSource());
            }else{
                neighbors.add(e.getTarget());
            }
        }
        return neighbors;
    }

    /**
     * Cette méthode permet de récuperer tous les successeurs d'un sommet
     */
    public ArrayList<Node> getSuccesors(Node n){
        ArrayList<Node> succesors= new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!e.getTarget().equals(n)) {
                succesors.add(e.getTarget());
            }
        }
        return succesors;
    }

    /**
     * Cette méthode permet de récuperer tous les predecesseurs d'un sommet
     */
    public ArrayList<Node> getPredecessors(Node n){
        ArrayList<Node> predecessors= new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!e.getSource().equals(n)) {
                predecessors.add(e.getSource());
            }
        }
        return predecessors;
    }

    /**
     * Cette méthode permet de récuperer toutes les arretes qui sont rattaché à un sommet
     */
    public ArrayList<Edge> getInOutEdges(Node n){
        return new ArrayList<>(n.getEdges());
    }

    /**
     * Cette méthode permet de récuperer toutes les arretes qui pointes vers le sommet passé en parametre
     */
    public ArrayList<Edge> getInEdges(Node n){
        ArrayList<Edge> inEdges= new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!e.getSource().equals(n)) {
                inEdges.add(e);
            }
        }
        return inEdges;
    }

    /**
     * Cette méthode permet de récuperer toutes les arretes qui arrivent vers le sommet passé en parametre 
     */
    public ArrayList<Edge> getOutEdges(Node n){
        ArrayList<Edge> outEdges= new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!e.getTarget().equals(n)) {
                outEdges.add(e);
            }
        }
        return outEdges;
    }

    public int inDegree(Node n){
        return getPredecessors(n).size();
    }

    public int outDegree(Node n){
        return getSuccesors(n).size();
    }

    public int degree(Node n){
        return getNeighbors(n).size();
    }

    public boolean existEdge(Node src, Node tgt, boolean oriented){

        if (oriented) {
        ArrayList<Edge> srcOutEdges= getOutEdges(src);
        for (Edge e : srcOutEdges) {
            if (e.getTarget().equals(tgt)) {
                return true;
            } 
        }
        }else{
            ArrayList<Edge> srcEdges = getInOutEdges(src);
            for (Edge e: srcEdges) {
                if(e.getTarget().equals(tgt) || e.getSource().equals(tgt)){
                    return true;
                } 
            }
        }

        return false;
    }

    public Edge getEdge(Node src, Node tgt, boolean oriented){
        if (existEdge(src, tgt, oriented)) {
            if (oriented) {
                ArrayList<Edge> srcOutEdges= getOutEdges(src);
                for (Edge e : srcOutEdges) {
                    if (e.getTarget().equals(tgt)) {
                        return e;
                    } 
                }
                }else{
                    ArrayList<Edge> srcEdges = getInOutEdges(src);
                    for (Edge e: srcEdges) {
                        if(e.getTarget().equals(tgt) || e.getSource().equals(tgt)){
                            return e;
                        } 
                    }
                }
        }
        return null;
    }
}
