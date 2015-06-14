/** A class that represents a node of the graph. Contains the name of the city and
 * the location on the "map".
 */
import java.awt.Color;
import java.awt.Point;

public class CityNode {
	private final String city;
	private Point location;
	private Color color;
	private Vertex v;

	/**
	 * Call this method when you are reading the graph file to create a node
	 * 
	 * @param String
	 *            cityName
	 * @param double x
	 * @param double y
	 */
	public CityNode(String cityName, double x, double y) {
		this.city = cityName;
		int xint = (int) (507 * x / 7.0);
		int yint = (int) (289 - 289 * y / 4.0);
		this.location = new Point(xint, yint);
		this.v = new Vertex(cityName);
	}

	public Point getLocation() {
		return location;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return getCity();
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Vertex getVertex () {
		return this.v;
	}




}
