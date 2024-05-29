package info.iut.sae2.Algorithms;

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
        Graph ACM = g.getMinimumSpanningTree();

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
                path = findBends(ACM, g.source(edge), g.target(edge));
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
        for (Edge e : g.getEdges()) {
            if (!edgesSimple.contains(e)) {
                edgesSimple.add(e);
            }
        }
        return new Graph(g.getNodes(), edgesSimple);
    }

}
