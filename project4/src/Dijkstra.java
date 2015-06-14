import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {

	private int sourceVertex = -1; // store the id of the source vertex
	private MapGraph graph;
	OpenHash nodeTable;
	private int numCities = 0;

	Dijkstra(String filename) {
		loadGraph(filename);

	}

	public MapGraph getGraph() {
		return graph;
	}

	/**
	 * Compute all the shortest paths from the source vertex to all the other
	 * vertices in the graph; This function is called from GUIApp, when the user
	 * clicks on the city.
	 */
	public void computePaths(CityNode vSource) {
		this.sourceVertex = nodeTable.getID(vSource.getCity(), numCities);

		for (CityNode n : graph.nodes) {
			n.getVertex().minDistance = Double.POSITIVE_INFINITY;
			n.getVertex().previous = null;
		}
		vSource.getVertex().minDistance = 0.0;
		PriorityQueue<Vertex> vertexQueue1 = new PriorityQueue<Vertex>();

		PriorityHeap vertexQueue = new PriorityHeap(numCities);
		Vertex targetVertex = vSource.getVertex();

		vertexQueue.insert(nodeTable.getID(targetVertex.name, numCities),
				(int) targetVertex.minDistance);
		while (vertexQueue.size > 0) {
			Vertex vertex1 = graph.nodes[vertexQueue.removeMin()].getVertex();
			Edge edge = vertex1.adjacencies;
			while (edge != null) {

				Vertex vertex2 = edge.dest;
				double distance = edge.distance;
				double vertex1Distance = vertex1.minDistance + distance;
				if (vertex1Distance < vertex2.minDistance) {
					vertex2.minDistance = vertex1Distance;
					if (vertexQueue
							.contains(nodeTable.getID(vertex2.name, numCities))) {
						vertexQueue.reduceKey(
								nodeTable.getID(vertex2.name, numCities),
								(int) vertex1Distance);
					} else {
						vertexQueue.insert(nodeTable.getID(vertex2.name, numCities),
								(int) vertex1Distance);
					}
					vertex2.previous = vertex1;
				}
				if (edge.next == null) {
					break;
				}
				edge = edge.next;
			}
		}
	}

	/**
	 * Returns the shortest path between the source vertex and this vertex.
	 * Returns the array of node id-s on the path
	 */
	public ArrayList<Integer> shortestPath(CityNode vTarget) {
		ArrayList<Integer> path = new ArrayList<>();

		for (Vertex vertex = vTarget.getVertex(); vertex != null; vertex = vertex.previous) {
			path.add(nodeTable.getID(vertex.name, numCities));
		}
		Collections.reverse(path);
		return path;
	}

	/**
	 * Loads graph info from the text file into MapGraph graph
	 *
	 * @param filename
	 */
	public void loadGraph(String filename) {

		CityNode[] nodes = null;
		String[] cities = null;

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while (br.ready()) {
				if (br.readLine().equals("NODES")) {
					numCities = Integer.parseInt(br.readLine());
					nodes = new CityNode[numCities];
					cities = new String[numCities];
					for (int i = 0; i < numCities; i++) {
						String[] CT_ra = br.readLine().split(" ");
						nodes[i] = new CityNode(CT_ra[0],
								Double.parseDouble(CT_ra[1]),
								Double.parseDouble(CT_ra[2]));
						cities[i] = CT_ra[0];
					}
				}

				nodeTable = new OpenHash(numCities);
				nodeTable.insert(cities);
				graph = new MapGraph(numCities);
				for (CityNode n : nodes) {
					graph.addNode(n);
				}
				if (br.readLine().contains("ARC")) {
					while (br.ready()) {

						String[] ARC_Line = br.readLine().split("\\s");
						String source = ARC_Line[0];
						String dest = ARC_Line[1];
						int weight = Integer.parseInt(ARC_Line[2]);
						int id = nodeTable.getID(source, numCities);

						graph.addEdge(
								id,
								new Edge(graph.nodes[nodeTable.getID(
										dest, numCities)].getVertex(),
										weight));

						graph.addEdge(
								nodeTable.getID(dest, numCities),
								new Edge(graph.nodes[id].getVertex(), weight));

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
