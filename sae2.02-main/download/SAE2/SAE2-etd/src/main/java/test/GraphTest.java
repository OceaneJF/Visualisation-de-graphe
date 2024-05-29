package test;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import info.iut.sae2.graphs.Coord;
import info.iut.sae2.graphs.Edge;
import info.iut.sae2.graphs.Node;
import info.iut.sae2.graphs.Graph;
public class GraphTest {


    @Test
    public void testAddEdge() {

    }

    @Test
    public void testAddEdge2() {

    }

    @Test
    public void testAddNode() {

    }

    @Test
    public void testAddNode2() {

    }

    @Test
    public void testBundle() {

    }

    @Test
    public void testDegree() {

    }

    @Test
    public void testDelEdge() {

    }

    @Test
    public void testDelNode() {

    }

    @Test
    public void testExistEdge() {

    }

    @Test
    public void testGetBoundingBox() {

    }

    @Test
    public void testGetEdge() {

    }

    @Test
    public void testGetEdgePosition() {

    }

    @Test
    public void testGetEdges() {

    }

    @Test
    public void testGetInEdges() {

    }

    @Test
    public void testGetInOutEdges() {

    }

    @Test
    public void testGetMinimumSpanningTree() {

    }

    @Test
    public void testGetNeighbors() {
        Coord cNode1= new Coord(6, 8);
        Coord cNode2= new Coord(8, 12);
        Coord cNode3= new Coord(8, 8);
        Coord cNode4= new Coord(6, 12);

        Node node1=new Node(cNode1, 1);
        Node node2=new Node(cNode2, 2);
        Node node3=new Node(cNode3, 3);
        Node node4=new Node(cNode4, 4);

        Edge edge13 =new Edge(node1, node3);
        Edge edge21 =new Edge(node2, node1);
        Edge edge34 = new Edge(node3,node4);

        HashSet<Edge> n1Edges= new HashSet<>();
        n1Edges.add(edge13);
        n1Edges.add(edge21);
        HashSet<Edge> n2Edges= new HashSet<>();
        n2Edges.add(edge21);
        HashSet<Edge> n3Edges= new HashSet<>();
        n3Edges.add(edge34);
        n3Edges.add(edge13);
        HashSet<Edge> n4Edges= new HashSet<>();
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

        Graph graph1=new Graph(nodes, edges);

        ArrayList<Node> actual = graph1.getNeighbors(node1);

        ArrayList<Node> expected= new ArrayList<>();
        expected.add(node2);
        expected.add(node3);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void testGetNodePosition() {

    }

    @Test
    public void testGetNodes() {

    }

    @Test
    public void testGetOutEdges() {

    }

    @Test
    public void testGetPredecessors() {

    }

    @Test
    public void testGetSuccesors() {

    }

    @Test
    public void testInDegree() {

    }

    @Test
    public void testNumberOfEdges() {

    }

    @Test
    public void testNumberOfNodes() {

    }

    @Test
    public void testOutDegree() {

    }

    @Test
    public void testSetAllEdgesPositions() {

    }

    @Test
    public void testSetAllNodesPositions() {

    }

    @Test
    public void testSetEdgePosition() {

    }

    @Test
    public void testSetEdges() {

    }

    @Test
    public void testSetNodePosition() {

    }

    @Test
    public void testSource() {

    }

    @Test
    public void testTarget() {

    }
}
