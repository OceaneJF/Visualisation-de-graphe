package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import info.iut.sae2.graphs.Coord;
import info.iut.sae2.graphs.Edge;
import info.iut.sae2.graphs.Node;
import info.iut.sae2.graphs.Graph;

/**
 *
 * @author cbardot et ojfrancois
 */
public class GraphTest {

    private Node node1;
    private Node node2;
    private Node node3;
    private Node node4;
    private Edge edge13;
    private Edge edge21;
    private Edge edge34;
    private Graph graph1;

    @Before
    public void setUp() {
        Coord cNode1 = new Coord(6, 8);
        Coord cNode2 = new Coord(8, 12);
        Coord cNode3 = new Coord(8, 8);
        Coord cNode4 = new Coord(6, 12);

        node1 = new Node(cNode1, 1);
        node2 = new Node(cNode2, 2);
        node3 = new Node(cNode3, 3);
        node4 = new Node(cNode4, 4);

        edge13 = new Edge(node1, node3);
        edge21 = new Edge(node2, node1);
        edge34 = new Edge(node3, node4);

        HashSet<Edge> n1Edges = new HashSet<>();
        n1Edges.add(edge13);
        n1Edges.add(edge21);
        HashSet<Edge> n2Edges = new HashSet<>();
        n2Edges.add(edge21);
        HashSet<Edge> n3Edges = new HashSet<>();
        n3Edges.add(edge34);
        n3Edges.add(edge13);
        HashSet<Edge> n4Edges = new HashSet<>();
        n4Edges.add(edge34);

        node1.setEdges(n1Edges);
        node2.setEdges(n2Edges);
        node3.setEdges(n3Edges);
        node4.setEdges(n4Edges);

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(edge13);
        edges.add(edge34);
        edges.add(edge21);

        graph1 = new Graph(nodes, edges);
    }


    @Test
    public void testBundle() {
        Edge edge23 = new Edge(node2, node3);
        node2.addEdge(edge23);
        node3.addEdge(edge23);
        graph1.bundle();
        ArrayList<Coord> bends = edge21.getBends();
        Coord co1 = node3.getPosition();
        assertTrue(bends.size()==1);
        assertTrue(bends.contains(co1));
        assertTrue(edge23.getBends().size()==0);
        assertTrue(edge13.getBends().size()==0);
        assertTrue(edge34.getBends().size()==0);
    }

    @Test
    public void testGetBoundingBox() {
        ArrayList<Coord> actual = graph1.getBoundingBox();
        Coord co1 = new Coord(6, 8);
        Coord co2 = new Coord(8, 12);
        assertTrue(actual.size() == 2);
        assertTrue(actual.contains(co1));
        assertTrue(actual.contains(co2));
    }

    @Test
    public void testGetMinimumSpanningTree() {
        Edge edge23 = new Edge(node2, node3);
        node2.addEdge(edge23);
        node3.addEdge(edge23);
        Graph actual = graph1.getMinimumSpanningTree();
        ArrayList<Edge> edges = actual.getEdges();
        ArrayList<Node> nodes = actual.getNodes();
        assertTrue(edges.size()==3);
        assertTrue(nodes.size()==4);
        assertTrue(edges.contains(edge23));
        assertTrue(edges.contains(edge13));
        assertTrue(edges.contains(edge34));
        assertFalse(edges.contains(edge21));
    }

    @Test
    public void testGetNeighbors() {
        ArrayList<Node> actual = graph1.getNeighbors(node1);

        assertTrue(actual.size() == 2);
        assertFalse(actual.contains(node1));
        assertTrue(actual.contains(node2));
        assertTrue(actual.contains(node3));
        assertFalse(actual.contains(node4));
    }
}
