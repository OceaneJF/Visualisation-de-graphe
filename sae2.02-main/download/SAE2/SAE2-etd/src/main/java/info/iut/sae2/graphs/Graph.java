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
        ArrayList<Double> coordsX = new ArrayList<Double>();
        ArrayList<Double> coordsY = new ArrayList<Double>();
        for (Node n : nodes) {
            coordsX.add(this.getNodePosition(n).getX());
            coordsY.add(this.getNodePosition(n).getY());
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
     * // * Cette méthode retourne un arbre couvrant de cout minimum en utilisant
     * // * l'algorithm de Prim
     * //
     */
    @Override
    public Graph getMinimumSpanningTree() {
        // Les arretes de l'ACM
        ArrayList<Edge> ACM = new ArrayList<>();
        // Les sommets de l'ACM
        ArrayList<Node> S = new ArrayList<>();
        // Toutes les arretes voisines des sommets visités
        ArrayList<Edge> neighbors = new ArrayList<>();

        // On cherche toutes les composantes connexes et on les met dans un tableau de
        // tableau
        ArrayList<ArrayList<Node>> connectedComponents = findConnectedComponents();
        // On crée une liste de Graphs qui correspondent à chacuunes des composantes
        // connexes
        ArrayList<Graph> graphs = new ArrayList<>();

        // On fait l'algorithm de Prim pour chacune des composantes connexes
        for (ArrayList<Node> component : connectedComponents) {
            // On efface le contenu de toutes les listes
            S.clear();
            ACM.clear();

            // Initialisation
            Node U = component.get(0);
            S.add(U);

            // Tant qu'on a pas parcouru tous les sommets de la composante connexe
            while (S.size() < component.size()) {
                // On récupère tous les voisins pas deja visités de chaque sommets visité
                for (Node node : S) {
                    for (Edge e : node.getEdges()) {
                        if (!(S.contains(source(e)) && S.contains(target(e)))) {
                            neighbors.add(e);
                        }
                    }
                }

                // Parmis ces arretes voisines on choisit celles dont la distance entre sa
                // source et sa destination est la plus petite
                Edge edgeMin = chooseEdge(neighbors);
                // On ajoute cette arrete dans l'ACM
                ACM.add(edgeMin);
                // On éfface le contenu de la liste des arretes voisines
                neighbors.clear();

                // On récupère le sommet suivant à parcourir qui n'a pas déja été visité
                Node nextNode;
                if (!S.contains(source(edgeMin))) {
                    nextNode = source(edgeMin);
                } else {
                    nextNode = target(edgeMin);
                }
                // On ajoute ce sommet dans la liste des sommets visité
                S.add(nextNode);
            }
            // On créer un graph pour chaque composante connexe sur lequel on a appliqué
            // l'algorithm de Prim
            Graph mstGraph = new Graph(this.getNodes(), ACM);
            // On l'ajoute à la liste des graphs qui correspond aux composantes connexe d'un
            // graph
            graphs.add(mstGraph);
        }

        // Un créer un nouveau graph qui correspond au regroupement des composantes
        // connexes sur lequels on a appliqué l'algorithm de Prim
        Graph mergeGraph = new Graph();

        for (Graph g : graphs) {
            for (Node nodeG : g.getNodes()) {
                mergeGraph.addNode(nodeG);
            }

            for (Edge edgeG : g.getEdges()) {
                mergeGraph.addEdge(edgeG);
            }
        }

        return mergeGraph;
    }
    /**
    * Cette méthode permet de récuperer les sommets de chaque composantes connexes du graph
    * @return la liste des sommets de chaque composantes connexes du graph
    */
    private ArrayList<ArrayList<Node>> findConnectedComponents() {
        //On crée un liste qui va contenir les sommets de chaques composantes connexes
        ArrayList<ArrayList<Node>> connectedComponents = new ArrayList<>();
        //On crée la liste des sommets visités
        HashSet<Node> visited = new HashSet<>();

        //On détermine la listes des sommets d'une composante connexe du graph et on l'ajoute à la liste de toutes les composantes connexes
        for (Node node : this.getNodes()) {
            if (!visited.contains(node)) {
                ArrayList<Node> component = new ArrayList<>();
                dfsForPrim(node, visited, component);
                connectedComponents.add(component);
            }
        }

        return connectedComponents;
    }

    /**
     * Cette méthode permet de déterminer les sommets d'une composante connexes du graph 
     * @param node le sommet de départ de l'algorithme
     * @param visited la liste des sommets visités
     * @param component la liste des sommets d'une composante connexe
     */
    private void dfsForPrim(Node node, HashSet<Node> visited, ArrayList<Node> component) {
        //On ajoute le sommet courant dans la liste des sommets visités et dans la liste de sommets de la composante connexe 
        visited.add(node);
        component.add(node);
        //On parcours par récurrence les sommets de la composante connexe 
        for (Edge edge : node.getEdges()) {
            Node neighbor;
            if (target(edge).equals(node)) {
                neighbor = source(edge);
            } else {
                neighbor = target(edge);
            }
            if (!visited.contains(neighbor)) {
                dfsForPrim(neighbor, visited, component);
            }
        }
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
        Coord srcCoord = this.getNodePosition(source(edgeMin));
        Coord tgtCoord = this.getNodePosition(target(edgeMin));
        double min = srcCoord.dist(tgtCoord);
        double dist;
        // Pour chaque arretes si la distance entre ses deux extrémités est plus petite
        // que la distance minimale alors la distance minimale prend cette distance
        for (int i = 1; i < edges.size(); i++) {
            srcCoord = this.getNodePosition(source(edges.get(i)));
            tgtCoord = this.getNodePosition(target(edges.get(i)));
            dist = srcCoord.dist(tgtCoord);
            if (dist < min) {
                min = dist;
                edgeMin = edges.get(i);
            }
        }
        return edgeMin;

    }


    @Override
    public void bundle() {
        // La liste des sommets du chemin
        ArrayList<Node> path;
        // On crée le graphe simplifié
        Graph simpleGraph = simpleGraph();

        // L'arbre couvrant de cout minimum du graph
        Graph ACM = getMinimumSpanningTree();

        //On enlève les arretes voisines des sommets de l'ACM qui sont présentes dans le graph simple mais pas l'ACM
        for (Edge edge : simpleGraph.getEdges()) {
            for (Node node : ACM.getNodes()) {
                if (node.getEdges().contains(edge) && !ACM.getEdges().contains(edge)) {
                    node.getEdges().remove(edge);
                }
            }
        }

        // Pour chaque arrete du graphe
        for (Edge edge : simpleGraph.getEdges()) {
            // Si cette arrete n'est pas dans l'arbre couvrant minimum
            if (!ACM.getEdges().contains(edge)) {
                // On cherche les brisures de cette arrete
                path = findBends(ACM, source(edge), target(edge));
                if (path != null && path.size() > 2) {
                    // On retire le sommet source et le sommet destination de la listes des brisures
                    path.remove(source(edge));
                    path.remove(target(edge));
                    // On les met dans bends
                    ArrayList<Coord> newBends = new ArrayList<>();
                    for (Node node : path) {
                        newBends.add(this.getNodePosition(node));
                    }
                    //On donne les coordonnées des brisures à chaque arrete du graphe simple 
                    simpleGraph.setEdgePosition(edge, newBends);

                }

            }
        }
        //On remplace du graphe par les arretes du graphe simple 
        this.setEdges(simpleGraph.getEdges());
    }

    /**
     * Cette méthode permet de récuperer la listes des sommets parcouru pour aller d'un sommet source à un sommet destination de l'ACM
     * @param ACM l'arbre couvrant de cout minimal du graphe
     * @param src le sommet source 
     * @param tgt le sommet destination 
     * @return la listes des sommets parcouru pour aller d'un sommet source à un sommet destination de l'ACM
     */
    private ArrayList<Node> findBends(Graph ACM, Node src, Node tgt) {
        // On créer une liste des sommets qui ont été visités
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for (Node n : ACM.getNodes()) {
            visited.put(n.getNum(), false);
        }
        // On créer une liste des sommets par lequelles ont passe pour aller du sommet
        // source au sommet destination
        ArrayList<Node> path = new ArrayList<>();

        // si on a atteint la destination
        if (DFS(ACM, src, tgt, path, visited)) {
            // on retourne les sommets du chemin parcouru
            return path;
        }
        return null;
    }

    /**
     * Cette méthode permet de déterminer le plus court chemin entre deux sommets grace à l'algorithme de parcours en profondeur 
     * @param ACM l'arbre couvrant de cout minimum
     * @param src le sommet source
     * @param tgt le sommet destination
     * @param path la liste des sommets parcouru
     * @param visited les sommets qui ont été visités
     * @return true si le sommet destination à été atteint false sinon 
     */
    private boolean DFS(Graph ACM, Node src, Node tgt, ArrayList<Node> path,
            HashMap<Integer, Boolean> visited) {
        // On ajoute le sommet dans le chemin
        path.add(src);
        // On ajoute le sommet dans la liste des sommets visités
        visited.put(src.getNum(), true);
        // Si le sommet que l'on est entrain de visiter est la destination alors on
        // retourne true
        if (src.equals(tgt)) {
            return true;
        }

        // Pour chaque sommet voisin du sommet passé en parametre
        for (Node n : ACM.getNeighbors(src)) {
            // Si il a pas déja été visité
            if (!visited.get(n.getNum())) {
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
     *
     * @return un graphe simplifié
     */
    private Graph simpleGraph() {
        ArrayList<Edge> edgesSimple = new ArrayList<Edge>();
        for (Edge e : this.getEdges()) {
            if (!edgesSimple.contains(e)) {
                edgesSimple.add(e);
            }
        }
        return new Graph(this.getNodes(), edgesSimple);
    }

}
