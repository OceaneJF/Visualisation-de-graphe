package info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import info.iut.sae2.viewer.GraphCanvas;

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
            n.setPosition(c);
        }
    }

    /**
     * Cette méthode permet de modifier la position des brisures de toutes les
     * arretes
     */
    @Override
    public void setAllEdgesPositions(ArrayList<Coord> bends) {
        for (Edge e : edges) {
            e.setBends(bends);
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
            if (!e.getSource().equals(n)) {
                neighbors.add(e.getSource());
            } else {
                neighbors.add(e.getTarget());
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
            if (!e.getTarget().equals(n)) {
                succesors.add(e.getTarget());
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
            if (!e.getSource().equals(n)) {
                predecessors.add(e.getSource());
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
            if (!e.getSource().equals(n)) {
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
            if (!e.getTarget().equals(n)) {
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
                if (e.getTarget().equals(tgt)) {
                    return e;
                }
            }
        } else {
            ArrayList<Edge> srcEdges = getInOutEdges(src);
            for (Edge e : srcEdges) {
                if (e.getTarget().equals(tgt) || e.getSource().equals(tgt)) {
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
        ArrayList<Double> coordsX = new ArrayList<Double>();
        ArrayList<Double> coordsY = new ArrayList<Double>();
        for (Node n : nodes) {
            coordsX.add(n.getPosition().getX());
            coordsY.add(n.getPosition().getY());
        }
        double xMin = min(coordsX);
        double xMax = max(coordsX);
        double yMin = min(coordsY);
        double yMax = max(coordsY);
        ArrayList<Coord> rect = new ArrayList<Coord>();
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
     * Cette méthode retourne un arbre couvrant de cout minimum en utilisant
     * l'algorithm de Prim
     */
    @Override
    public Graph getMinimumSpanningTree() {
        // Les arretes de l'ACM
        ArrayList<Edge> ACM = new ArrayList<>();
        // Les sommets de l'ACM
        ArrayList<Node> S = new ArrayList<>();
        // Toutes les arretes voisines des sommets visités
        ArrayList<Edge> neighbors = new ArrayList<>();
        // Les arretes voisines des sommets visités sans les arretes deja visités
        ArrayList<Edge> neighborsWithoutVisited;
        // Initialisation
        Edge edgeMin = null;
        Node U = nodes.get(0);
        S.add(U);
        // Tant qu'on a pas parcouru tous les sommets
        while (S.size() < nodes.size()) {
            // Pour chaque sommet qu'on a deja parcouru on récupere tous ses voisins et on
            // les mets dans neighbors
            for (Node node : S) {
                neighbors.addAll(node.getEdges());
            }
            // On supprime les arretes voisines deja visités et on les mets dans
            // neighborsWithoutVisited
            neighborsWithoutVisited = removeVisitedNeighbors(neighbors, S);
            // On récupere l'arrete qui a le cout le plus faibles parmis les arretes
            // voisines non visités et on la met dans edgeMin
            edgeMin = chooseEdge(neighborsWithoutVisited);
            // On ajoute cette arrete dans l'ACM
            ACM.add(edgeMin);
            // On ajoute le sommet de l'arrete qui n'a pas déja été visité et on l'ajoute
            // dans la liste des sommets visités
            S.add(notVisitedNode(edgeMin, S));
            // On éfface le contenu de neighbors
            neighbors.clear();

        }
        // On créer l'ACM et on le retourne
        Graph graph = new Graph(S, ACM);
        return graph;
    }

    /**
     * Cette méthode retourne toutes les arretes voisines des sommets visités sauf
     * les arretes deja visités
     * 
     * @param neighbors toutes les arretes voisines des sommets visités
     * @param S         l'ensemble des sommets visités
     * @return toutes les arretes voisines des sommets visités sauf les arretes deja
     *         visités
     */
    private ArrayList<Edge> removeVisitedNeighbors(ArrayList<Edge> neighbors, ArrayList<Node> S) {
        ArrayList<Edge> newNeighbors = new ArrayList<>(neighbors);
        // Si le sommet source et destination de l'arrete sont contenu dans la liste des
        // sommets deja visité alors on retire cette arrete de neighbors
        for (Edge e : neighbors) {
            if (S.contains(e.getSource()) && S.contains(e.getTarget())) {
                newNeighbors.remove(e);
            }
        }
        return newNeighbors;
    }

    /**
     * Cette méthode retourne l'arrete qui a le cout le plus petit parmis la liste
     * d'arretes passé en parametre
     * 
     * @param edges la listes des arretes
     * @return l'arrete qui a le cout le plus petit
     */
    private Edge chooseEdge(ArrayList<Edge> edges) {
        // Initialisation de min
        Edge edgeMin = edges.get(0);
        Coord srcCoord = edgeMin.getSource().getPosition();
        Coord tgtCoord = edgeMin.getTarget().getPosition();
        double min = srcCoord.dist(tgtCoord);
        double dist;
        // Pour chaque arretes si la distance entre ses deux extrémités est plus petite
        // que la distance minimale alors la distance minimale prend cette distance
        for (int i = 1; i < edges.size(); i++) {
            srcCoord = edges.get(i).getSource().getPosition();
            tgtCoord = edges.get(i).getTarget().getPosition();
            dist = srcCoord.dist(tgtCoord);
            if (dist < min) {
                min = dist;
                edgeMin = edges.get(i);
            }
        }
        return edgeMin;

    }

    /**
     * Cette méthode retourne l'extrémité de l'arrete qui n'est pas deja visité
     * 
     * @param edge l'arrete dont on veut connaitre l'extrémité qui n'est pas deja
     *             visité
     * @param S    la liste des sommets deja visité
     * @return le sommet de l'arrete qui n'a pas deja été visité
     */
    private Node notVisitedNode(Edge edge, ArrayList<Node> S) {
        Node src = edge.getSource();
        Node tgt = edge.getTarget();
        if (S.contains(src)) {
            return tgt;
        } else {
            return src;
        }
    }

    @Override
    public void bundle() {
        // L'arbre couvrant de cout minimum du graph
        Graph ACM = getMinimumSpanningTree();
        // La liste des sommets du chemin
        ArrayList<Node> path;
// On crée le graphe simplifié
        Graph simpleGraph = simpleGraph();
        // Pour chaque arrete du graphe
        for (Edge edge : simpleGraph.getEdges()) {
            // Si cette arrete n'est pas dans l'arbre couvrant minimum
            System.out.println("test");
            if (!ACM.getEdges().contains(edge)) {
                System.out.println("y'a pas edge dans ACM");
                // On cherche les brisures de cette arrete
                path = findBends(ACM, edge.getSource(), edge.getTarget());

                if (path != null) {
                    // On retire le sommet source et le sommet destination de la listes des brisures
System.out.println("Size of path : "+path.size()+ " node1 "+ path.get(0).getNum()+ " node2 "+ path.get(1).getNum());
                    path.remove(edge.getSource());
                    path.remove(edge.getTarget());
System.out.println("Size of path : "+path.size());
                    // On les met dans bends

                    ArrayList<Coord> newBends = new ArrayList<>();
                    for (Node node : path) {
                        System.out.println(node.getNum() + " bends");
                        System.out.println(edge.getSource().getNum() + " src edge");
                        System.out.println(edge.getTarget().getNum() + " tgt edge");
                        System.out.println(" ");
                        newBends.add(node.getPosition());

                    }
                    System.out.println("fin bends");
                    simpleGraph.setEdgePosition(edge, newBends);
                }

}
            else{
                System.out.println("IL EST PAS PASSE !!!");
            }
        }
this.setEdges(simpleGraph.getEdges());
    }

    private ArrayList<Node> findBends(Graph ACM, Node src, Node tgt) {
        // On créer une liste des sommets qui ont été visités
        ArrayList<Node> visited = new ArrayList<>();
        // On créer une liste des sommets par lequelles ont passe pour aller du sommet
        // source au sommet destination
        ArrayList<Node> path = new ArrayList<>();

        // si on a atteint la destination
        if (DFS(ACM, src, tgt, path, visited)) {
            // on retourne les sommets du chemin parcouru
            return path;
        }
System.out.println("Path est null, on n'a pas trouvé le sommet target :(");
        return null;
    }

    private boolean DFS(Graph ACM, Node src, Node tgt, ArrayList<Node> path, ArrayList<Node> visited) {
        // On ajoute le sommet dans le chemin
        path.add(src);
        // On ajoute le sommet dans la liste des sommets visités
        visited.add(src);
        // Si le sommet que l'on est entrain de visiter est la destination alors on
        // retourne true
        if (src.equals(tgt)) {
            return true;
        }

        // Pour chaque sommet voisin du sommet passé en parametre
        for (Node n : ACM.getNeighbors(src)) {
            // Si il a pas déja été visité
            if (!visited.contains(n)) {
                // et qu'il s'agit de la destination, on retourne true
                if (DFS(ACM, n, tgt, path, visited)) {
                    return true;
                }
            }
        }

        // Si le sommet n'a pas de voisins ou qu'ils ont tous déja été visité et qu'il
        // ne s'agit pas de la destination on remonte en arriere et on l'enleve de la
        // liste des sommet du chemin et on retourne false
        path.remove(src);
        return false;
    }

    /**
     * Permet de construire un graphe dont les arrêtes ne sont pas en double.
     * @return un graphe simplifié
     */
    private Graph simpleGraph(){
        ArrayList<Edge> edgesSimple = new ArrayList<Edge>();
        for (Edge e : this.edges) {
            if(!edgesSimple.contains(e)){
                edgesSimple.add(e);
                System.out.println("Source : " +e.getSource().getNum());
                System.out.println("Target : "+ e.getTarget().getNum());
            }
        }
        
        return new Graph(nodes, edgesSimple);
    }

}
