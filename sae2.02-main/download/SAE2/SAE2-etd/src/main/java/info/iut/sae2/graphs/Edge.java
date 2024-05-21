package info.iut.sae2.graphs;

import java.util.ArrayList;

public class Edge extends Graph{
    /*
     * Le sommet source de l'arrete
     */
    private Node source;

    /*
     * Le sommet destination de l'arrete 
     */
    private Node target;

/*
 * Les coordonées du sommets source et du sommet destination d'une arrete 
 */
    private ArrayList<Coord> bends;

    public Edge(Node source, Node target){
        this.source=source;
        this.target=target;
        bends=new ArrayList<>();
        bends.add(source.getPosition());
        bends.add(target.getPosition());
    }

    /**
     * Cette méthode permet de récuperer le sommet source de l'arrete 
     * @return
     */
    public Node getSource(){
        return source;
    }

    /**
     * Cette méthode permet de récuperer le sommet destination de l'arrete 
     * @return
     */
    public Node getTarget(){
        return target;
    }
    /*
     * Cette méthode permet de récuperer les coordonées du sommets source et du sommet destination de l'arrete 
     */
    public ArrayList<Coord> getBends(){
        return bends;
    }

    public void setBends(ArrayList<Coord> positions){
        bends= new ArrayList<>(positions);

    }

}
