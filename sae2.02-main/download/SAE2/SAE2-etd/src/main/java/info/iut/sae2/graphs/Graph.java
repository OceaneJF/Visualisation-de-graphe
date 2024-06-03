package info.iut.sae2.graphs;

import java.util.ArrayList;

import info.iut.sae2.algorithms.Algos;

/**
 *
 * @author cbardot et ojfrancois
 */
public class Graph implements IGraph {

    /**
     * Tous les sommets du graph
     */
    private ArrayList<Node> nodes;

    /**
     * Toutes les arretes du graph
     */
    private ArrayList<Edge> edges;

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.nodes = new ArrayList<>(nodes);
        this.edges = new ArrayList<>(edges);
    }

    /**
     * Cette méthode crée et ajoute un nouveaux sommet à la liste des sommets du
     * graph
     */
    @Override
    public Node addNode() {
        Node n = new Node();
        return addNode(n);
    }

    /**
     * Cette méthode permet de récuperer la liste de tous les sommets du graph
     */
    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Cette méthode permet d'ajouter un sommet passé en paramètre dans la liste des
     * sommets du graph
     */
    @Override
    public Node addNode(Node n) {
        nodes.add(n);
        return n;
    }

    /*
     * Cette méthode permet d'ajouter une arrete au graph
     */
    @Override
    public Edge addEdge(Edge e) {
        edges.add(e);
        return e;
    }

    /**
     * Cette méthode permet d'ajouter une arrete dans la listes d'arretes du graph
     * en précisant son sommet source et de destination
     */
    @Override
    public Edge addEdge(Node src, Node tgt) {
        Edge e = new Edge(src, tgt);
        return addEdge(e);
    }

    /**
     * Cette méthode permet de supprimer un sommet de la liste des sommets du graph
     */
    @Override
    public void delNode(Node n) {
        nodes.remove(n);
    }

    /**
     * Cette méthode permet de supprimer une arrete de la liste des arretes du graph
     */
    @Override
    public void delEdge(Edge e) {
        edges.remove(e);
    }

    /**
     * Cette méthode permet de récuperer toutes les arrètes du graph
     */
    @Override
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Cette méthode permet de déterminer le nombre de sommets présent dans le graph
     */
    @Override
    public int numberOfNodes() {
        return nodes.size();
    }

    /**
     * Cette méthode permet de déterminer le nombre d'arrets présentes dans le graph
     */
    @Override
    public int numberOfEdges() {
        return edges.size();
    }

    /**
     * Cette méthode permet de récupérer les coordonnée d'un sommet passé en
     * paramètre
     * 
     * @param n le sommet dont on veut connaitre les coordonnées
     * @return les coordonnées de ce sommet
     */
    @Override
    public Coord getNodePosition(Node n) {
        return n.getPosition();
    }

    /**
     * Cette méthode permet de récuperer les coordonnées d'une arrete
     */
    @Override
    public ArrayList<Coord> getEdgePosition(Edge e) {
        return e.getBends();
    }

    /*
     * Cette méthode permet de modifier les coordonées des brisures d'une arrete
     */
    @Override
    public void setEdgePosition(Edge e, ArrayList<Coord> bends) {
        e.setBends(bends);
    }

    /**
     * Cette méthode permet de modifier les coordonnées d'un sommet passé en
     * parametre
     */
    @Override
    public void setNodePosition(Node n, Coord c) {
        n.setPosition(c);
    }

    /**
     * Cette méthode permet de modifier les coordonnées de tous les sommets
     */
    @Override
    public void setAllNodesPositions(Coord c) {
        for (Node n : nodes) {
            this.setNodePosition(n, c);
        }
    }

    /**
     * Cette méthode permet de modifier la position des brisures de toutes les
     * arretes
     */
    @Override
    public void setAllEdgesPositions(ArrayList<Coord> bends) {
        for (Edge e : edges) {
            this.setEdgePosition(e, bends);
        }
    }

    /**
     * Cette méthode permet de récuperer le sommet source de l'arrete passé en
     * parametre
     */
    @Override
    public Node source(Edge e) {
        return e.getSource();
    }

    /*
     * Cette méthode permet de récuperer le sommet de destination de l'arrete passée
     * en parametre
     */
    @Override
    public Node target(Edge e) {
        return e.getTarget();
    }

    /**
     * Cette méthode permet de récuperer tous les voisins d'un sommet
     */
    @Override
    public ArrayList<Node> getNeighbors(Node n) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!source(e).equals(n)) {
                neighbors.add(source(e));
            } else {
                neighbors.add(target(e));
            }
        }
        return neighbors;
    }

    /**
     * Cette méthode permet de récuperer tous les successeurs d'un sommet
     */
    @Override
    public ArrayList<Node> getSuccesors(Node n) {
        ArrayList<Node> succesors = new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!target(e).equals(n)) {
                succesors.add(target(e));
            }
        }
        return succesors;
    }

    /**
     * Cette méthode permet de récuperer tous les predecesseurs d'un sommet
     */
    @Override
    public ArrayList<Node> getPredecessors(Node n) {
        ArrayList<Node> predecessors = new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!source(e).equals(n)) {
                predecessors.add(source(e));
            }
        }
        return predecessors;
    }

    /**
     * Cette méthode permet de récuperer toutes les arretes qui sont rattaché à un
     * sommet
     */
    @Override
    public ArrayList<Edge> getInOutEdges(Node n) {
        return new ArrayList<>(n.getEdges());
    }

    /**
     * Cette méthode permet de récuperer toutes les arretes qui pointes vers le
     * sommet passé en parametre
     */
    @Override
    public ArrayList<Edge> getInEdges(Node n) {
        ArrayList<Edge> inEdges = new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!source(e).equals(n)) {
                inEdges.add(e);
            }
        }
        return inEdges;
    }

    /**
     * Cette méthode permet de récuperer toutes les arretes qui arrivent vers le
     * sommet passé en parametre
     */
    @Override
    public ArrayList<Edge> getOutEdges(Node n) {
        ArrayList<Edge> outEdges = new ArrayList<>();
        for (Edge e : n.getEdges()) {
            if (!target(e).equals(n)) {
                outEdges.add(e);
            }
        }
        return outEdges;
    }

    /**
     * Cette méthode permet de déterminer le nombre de predecesseurs d'un sommet
     * passé en parametre
     * 
     * @param n le sommet dont on veut connaitre le nombre de predecesseurs
     * @return int le nombre de predecesseurs du sommet
     */
    @Override
    public int inDegree(Node n) {
        return getPredecessors(n).size();
    }

    /**
     * Cette méthode permet de déterminer le nombre de successeurs d'un sommet
     * 
     * @param n le sommet dont on veut connaitre le nombre de successeurs
     * @return int le nombre de successeurs du sommet
     */
    @Override
    public int outDegree(Node n) {
        return getSuccesors(n).size();
    }

    /**
     * Cette méthode permet de déterminer le degres d'un sommet
     * 
     * @param n le sommet dont ont veut connaitre le degres
     * @return int le degres du sommet
     */
    @Override
    public int degree(Node n) {
        return getNeighbors(n).size();
    }

    /**
     * Cette méthode permet de savoir si il existe une arrete entre deux sommets
     * passés en parametre
     * en prenant en compte l'orientation de l'arrete
     * 
     * @param src      le sommet source
     * @param tgt      le sommet de destination
     * @param oriented un booléen précisant si le graph est orienté
     * @return boolean true si l'arrete existe, false sinon
     */
    @Override
    public boolean existEdge(Node src, Node tgt, boolean oriented) {
        return getEdge(src, tgt, oriented) != null;
    }

    /**
     * Cette méthode permet de récuperer l'arrete qui se trouve entre les deux
     * sommets passés en parametre, si aucune arrete n'existe entre ces deux sommet
     * elle retourne null
     * 
     * @param src      le sommet source
     * @param tgt      le sommet destination
     * @param oriented un booléen précisant si le graph est orienté ou pas
     * @return Edge l'arrete présente entre les deux sommets, null si il n'y en a
     *         pas
     */
    @Override
    public Edge getEdge(Node src, Node tgt, boolean oriented) {
        if (oriented) {
            ArrayList<Edge> srcOutEdges = getOutEdges(src);
            for (Edge e : srcOutEdges) {
                if (target(e).equals(tgt)) {
                    return e;
                }
            }
        } else {
            ArrayList<Edge> srcEdges = getInOutEdges(src);
            for (Edge e : srcEdges) {
                if (target(e).equals(tgt) || source(e).equals(tgt)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Cette méthode permet de déterminer les coins supérieur gauche et inférieur
     * droit du canvas
     */
    @Override
    public ArrayList<Coord> getBoundingBox() {
        ArrayList<Double> coordsX = new ArrayList<>();
        ArrayList<Double> coordsY = new ArrayList<>();
        for (Node n : nodes) {
            coordsX.add(this.getNodePosition(n).getX());
            coordsY.add(this.getNodePosition(n).getY());
        }
        double xMin = min(coordsX);
        double xMax = max(coordsX);
        double yMin = min(coordsY);
        double yMax = max(coordsY);
        ArrayList<Coord> rect = new ArrayList<>();
        rect.add(new Coord(xMin, yMin));
        rect.add(new Coord(xMax, yMax));
        return rect;
    }

    /**
     * Cette méthode retourne la valeur min de l'array list passé en parametre
     * 
     * @param coords la liste des coordonnées
     * @return la plus petite valeur de la liste de copordonnées
     */
    private double min(ArrayList<Double> coords) {
        double min = coords.get(0);
        for (int i = 1; i < coords.size(); i++) {
            if (coords.get(i) < min) {
                min = coords.get(i);
            }
        }
        return min;
    }

    /**
     * Cette méthode retourne la valeur max de la liste de coordonnées passées en
     * parametres
     * 
     * @param coords la listes des coordonnées
     * @return la valeur max de la liste de coordonnées
     */
    private double max(ArrayList<Double> coords) {
        double max = coords.get(0);
        for (int i = 1; i < coords.size(); i++) {
            if (coords.get(i) > max) {
                max = coords.get(i);
            }
        }
        return max;
    }
    
    /**
     * // * Cette méthode retourne un arbre couvrant de cout minimum en utilisant
     * // * l'algorithm de Prim
     * //
     */
    @Override
    public Graph getMinimumSpanningTree() {
        Algos a = new Algos(this);
        return a.getMinimumSpanningTree();
    }

    @Override
    public void bundle() {
        Algos a = new Algos(this);
        a.bundle();
    }
}
