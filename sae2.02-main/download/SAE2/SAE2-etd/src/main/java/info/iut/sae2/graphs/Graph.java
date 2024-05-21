package info.iut.sae2.graphs;

import java.util.ArrayList;

import info.iut.sae2.viewer.GraphCanvas;

public abstract class Graph implements IGraph{

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph(){
        nodes=new ArrayList<>();
        edges=new ArrayList<>();
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

}
