
public class Driver {


	public static void main(String[] args) {

		// Create an instance of the Dijkstra class
		// The parameter is the name of the file
		Dijkstra dijkstra = new Dijkstra(args[0]);

		// create the GUI window with the panel
		GUIApp app = new GUIApp(dijkstra);
	}


}