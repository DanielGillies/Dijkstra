/** A class representing a graph. Stores an array of nodes and adjacency list.
 *
 */
import java.awt.Point;


public class MapGraph {

	public CityNode[] nodes;

	// for each vertex store a linked list of edges;
	private final Edge[] adjacencyList;

	private int numNodes = 0;
	private final int numEdges = 0;

	public final int EPS_DIST = 5;

	MapGraph(int numNodes) {
		this.numNodes = numNodes;
		nodes = new CityNode[numNodes];
		adjacencyList = new Edge[numNodes];
	}

	/**
	 * Returns a node with index i
	 */
	CityNode getNode(int i) {
		return nodes[i];
	}

	/**
	 * Returns the head of the linked list of edges for a vertex with id = i
	 */
	Edge getEdge(int i) {
		return adjacencyList[i];
	}

	/**
	 * Adds a node to the graph
	 *
	 * @param node
	 */
	public void addNode(CityNode node) {
		for (int i = 0; i < numNodes; i++) {
			if (nodes[i] == null) {
				nodes[i] = node;
				break;
			}
		}
	}

	public int numNodes() {
		return numNodes;
	}

	/**
	 * Adds the edge to the linked list for this vertexId
	 *
	 * @param vertexId
	 * @param edge
	 */
	public void addEdge(int nodeId, Edge edge) {
		if (nodes[nodeId].getVertex().adjacencies==(null)) {
			nodes[nodeId].getVertex().adjacencies = edge;
		} else {
			edge.next = nodes[nodeId].getVertex().adjacencies;
			nodes[nodeId].getVertex().adjacencies = edge;
		}

	}

	/**
	 * Given the location of the click, return the node of the graph at this
	 * location.
	 */
	public CityNode getMapNode(Point loc) {
		for (CityNode n : nodes) {
			Point p = n.getLocation();
			if ((Math.abs(loc.x - p.x) < EPS_DIST)
					&& (Math.abs(loc.y - p.y) < EPS_DIST)) {
				return n;
			}
		}
		return null;
	}

	/**
	 * Returns the array of all edges for drawing: each element in the array
	 * corresponds to one edge and is the array of two Point objects
	 * (corresponding to the locations of the two nodes connected by this edge).
	 */
	public Point[][] getEdges() {
		System.out.println("YAYAYAYAYAYAYAYAYAY");
		Point[][] edges = new Point[numEdges][2];
		return edges;

	}

	/**
	 * Returns the array of nodes as points. Used by MapGraph to draw little
	 * circles at the location of the nodes
	 */
	public Point[] getNodeLocations() {
		Point[] locations = new Point[numNodes];
		for (int i = 0; i < numNodes; i++) {
			locations[i] = nodes[i].getLocation();
		}
		return locations;
	}

	/**
	 * Returns the array of cities corresponding to the vertices of this graph
	 * in the array
	 *
	 * @return
	 */
	public String[] getCities() {
		String[] labels = new String[numNodes];
		for (int i = 0; i < numNodes; i++) {
			labels[i] = nodes[i].getCity();
		}

		return labels;
	}

} // class MapGraph