package info.iut.sae2.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import info.iut.sae2.graphs.*;

public class Algos {

    private Graph g;

    public Algos(Graph gra){
        g = gra;
    }

    /**
    * Cette méthode permet de récuperer les sommets de chaque composantes connexes du graph
    * @return la liste des sommets de chaque composantes connexes du graph
    */
    public ArrayList<ArrayList<Node>> findConnectedComponents() {
        //On crée un liste qui va contenir les sommets de chaques composantes connexes
        ArrayList<ArrayList<Node>> connectedComponents = new ArrayList<>();
        //On crée la liste des sommets visités
        HashSet<Node> visited = new HashSet<>();

        //On détermine la listes des sommets d'une composante connexe du graph et on l'ajoute à la liste de toutes les composantes connexes
        for (Node node : g.getNodes()) {
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
            if (g.target(edge).equals(node)) {
                neighbor = g.source(edge);
            } else {
                neighbor = g.target(edge);
            }
            if (!visited.contains(neighbor)) {
                dfsForPrim(neighbor, visited, component);
            }
        }
    }


    public void bundle() {
        // La liste des sommets du chemin
        ArrayList<Node> path;
        // On crée le graphe simplifié
        Graph simpleGraph = simpleGraph();

        // L'arbre couvrant de cout minimum du graph
        Graph acm = g.getMinimumSpanningTree();

        //On enlève les arretes voisines des sommets de l'acm qui sont présentes dans le graph simple mais pas l'acm
        for (Edge edge : simpleGraph.getEdges()) {
            for (Node node : acm.getNodes()) {
                if (node.getEdges().contains(edge) && !acm.getEdges().contains(edge)) {
                    node.getEdges().remove(edge);
                }
            }
        }

        // Pour chaque arrete du graphe
        for (Edge edge : simpleGraph.getEdges()) {
            // Si cette arrete n'est pas dans l'arbre couvrant minimum
            if (!acm.getEdges().contains(edge)) {
                // On cherche les brisures de cette arrete
                path = findBends(acm, g.source(edge), g.target(edge));
                if (path != null && path.size() > 2) {
                    // On retire le sommet source et le sommet destination de la listes des brisures
                    path.remove(g.source(edge));
                    path.remove(g.target(edge));
                    // On les met dans bends
                    ArrayList<Coord> newBends = new ArrayList<>();
                    for (Node node : path) {
                        newBends.add(g.getNodePosition(node));
                    }
                    //On donne les coordonnées des brisures à chaque arrete du graphe simple 
                    simpleGraph.setEdgePosition(edge, newBends);

                }

            }
        }
        //On remplace du graphe par les arretes du graphe simple 
        g.setEdges(simpleGraph.getEdges());
    }

    /**
     * Cette méthode permet de récuperer la listes des sommets parcouru pour aller d'un sommet source à un sommet destination de l'acm
     * @param acm l'arbre couvrant de cout minimal du graphe
     * @param src le sommet source 
     * @param tgt le sommet destination 
     * @return la listes des sommets parcouru pour aller d'un sommet source à un sommet destination de l'acm
     */
    private ArrayList<Node> findBends(Graph acm, Node src, Node tgt) {
        // On créer une liste des sommets qui ont été visités
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for (Node n : acm.getNodes()) {
            visited.put(n.getNum(), false);
        }
        // On créer une liste des sommets par lequelles ont passe pour aller du sommet
        // source au sommet destination
        ArrayList<Node> path = new ArrayList<>();

        // si on a atteint la destination
        if (dfs(acm, src, tgt, path, visited)) {
            // on retourne les sommets du chemin parcouru
            return path;
        }
        return null;
    }

    /**
     * Cette méthode permet de déterminer le plus court chemin entre deux sommets grace à l'algorithme de parcours en profondeur 
     * @param acm l'arbre couvrant de cout minimum
     * @param src le sommet source
     * @param tgt le sommet destination
     * @param path la liste des sommets parcouru
     * @param visited les sommets qui ont été visités
     * @return true si le sommet destination à été atteint false sinon 
     */
    private boolean dfs(Graph acm, Node src, Node tgt, ArrayList<Node> path,
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
        for (Node n : acm.getNeighbors(src)) {
            // Si il a pas déja été visité
            if (!visited.get(n.getNum())) {
                // et qu'il s'agit de la destination, on retourne true
                if (dfs(acm, n, tgt, path, visited)) {
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
        ArrayList<Edge> edgesSimple = new ArrayList<>();
        for (Edge e : g.getEdges()) {
            if (!edgesSimple.contains(e)) {
                edgesSimple.add(e);
            }
        }
        return new Graph(g.getNodes(), edgesSimple);
    }

    /**
     * // * Cette méthode retourne un arbre couvrant de cout minimum en utilisant
     * // * l'algorithm de Prim
     * //
     */
    public Graph getMinimumSpanningTree() {
        // Les arretes de l'acm
        ArrayList<Edge> acm = new ArrayList<>();
        // Les sommets de l'acm
        ArrayList<Node> s = new ArrayList<>();
        // Toutes les arretes voisines des sommets visités
        ArrayList<Edge> neighbors = new ArrayList<>();

        // On cherche toutes les composantes connexes et on les met dans un tableau de
        // tableau
        Algos a = new Algos(g);
        ArrayList<ArrayList<Node>> connectedComponents = a.findConnectedComponents();
        // On crée une liste de Graphs qui correspondent à chacunes des composantes
        // connexes
        ArrayList<Graph> graphs = new ArrayList<>();

        // On fait l'algorithm de Prim pour chacune des composantes connexes
        for (ArrayList<Node> component : connectedComponents) {
            // On efface le contenu de toutes les listes
            s.clear();
            acm.clear();

            // Initialisation
            Node u = component.get(0);
            s.add(u);

            // Tant qu'on a pas parcouru tous les sommets de la composante connexe
            while (s.size() < component.size()) {
                // On récupère tous les voisins pas deja visités de chaque sommets visité
                for (Node node : s) {
                    for (Edge e : node.getEdges()) {
                        if (!(s.contains(g.source(e)) && s.contains(g.target(e)))) {
                            neighbors.add(e);
                        }
                    }
                }

                // Parmis ces arretes voisines on choisit celles dont la distance entre sa
                // source et sa destination est la plus petite
                Edge edgeMin = chooseEdge(neighbors);
                // On ajoute cette arrete dans l'acm
                acm.add(edgeMin);
                // On éfface le contenu de la liste des arretes voisines
                neighbors.clear();

                // On récupère le sommet suivant à parcourir qui n'a pas déja été visité
                Node nextNode;
                if (!s.contains(g.source(edgeMin))) {
                    nextNode = g.source(edgeMin);
                } else {
                    nextNode = g.target(edgeMin);
                }
                // On ajoute ce sommet dans la liste des sommets visité
                s.add(nextNode);
            }
            // On créer un graph pour chaque composante connexe sur lequel on a appliqué
            // l'algorithm de Prim
            Graph mstGraph = new Graph(g.getNodes(), acm);
            // On l'ajoute à la liste des graphs qui correspond aux composantes connexe d'un
            // graph
            graphs.add(mstGraph);
        }

        // Un créer un nouveau graph qui correspond au regroupement des composantes
        // connexes sur lequels on a appliqué l'algorithm de Prim
        Graph mergeGraph = new Graph();

        for (Graph gr : graphs) {
            for (Node nodeG : gr.getNodes()) {
                mergeGraph.addNode(nodeG);
            }

            for (Edge edgeG : gr.getEdges()) {
                mergeGraph.addEdge(edgeG);
            }
        }

        return mergeGraph;
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
        Coord srcCoord = g.getNodePosition(g.source(edgeMin));
        Coord tgtCoord = g.getNodePosition(g.target(edgeMin));
        double min = srcCoord.dist(tgtCoord);
        double dist;
        // Pour chaque arretes si la distance entre ses deux extrémités est plus petite
        // que la distance minimale alors la distance minimale prend cette distance
        for (int i = 1; i < edges.size(); i++) {
            srcCoord = g.getNodePosition(g.source(edges.get(i)));
            tgtCoord = g.getNodePosition(g.target(edges.get(i)));
            dist = srcCoord.dist(tgtCoord);
            if (dist < min) {
                min = dist;
                edgeMin = edges.get(i);
            }
        }
        return edgeMin;

    }

}
