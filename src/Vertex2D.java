/**
 * This class implements a point in 2D Cartesian space.
 * @author Brandon Lewis
 */


public class Vertex2D {
	/**
	 * Internal coordinate storage.
	 */
	private double x;
	private double y;
	
	/**
	 * Default constructor. Sets all coordinates to zero.
	 */
	protected Vertex2D() {
		x=0;
		y=0;
	}
	
	/**
	 * Coordinate constructor. Sets all coordinates to the specified values.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	protected Vertex2D(double x, double y) {
		setLocation(x,y);
	}
	
	/**
	 * Clone constructor. Sets all coordinates to that of a specified vertex.
	 * 
	 * @param v the vertex to copy
	 */
	protected Vertex2D(Vertex2D v) {
		setLocation(v);
	}
	
	/**
	 * Gets a specified coordinate.
	 * @param the dimension n of the coordinate
	 * @return the n coordinate
	 */
	public double getN(char n) {
		if(n == 'x') return x;
		if(n == 'y') return y;
		return 0.0;
	}
	
	/**
	 * Get the X coordinate.
	 * 
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Get the Y coordinate.
	 * 
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Sets the location of this vertex to the specified coordinates.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the location of this vertex to the location of another vertex.
	 * 
	 * @param v the vertex to copy
	 */
	public void setLocation(Vertex2D v) {
		setLocation(v.getX(),v.getY());
	}
	
	/**
	 * Return the distance between this vertex and the specified coordinates
	 * 
	 * @param x the x coordinate of the other point
	 * @param y the y coordinate of the other point
	 * @return the distance from this vertex to (x,y)
	 */
	public double distance(double x, double y) {
		return Math.sqrt(Math.pow(getX()-x,2) + Math.pow(getY()-y, 2));
	}
	
	/**
	 * Return the distance between this vertex and the specified vertex
	 * 
	 * @param v the vertex to find the distance to
	 * @return the distance from this vertex to v
	 */
	public double distance(Vertex2D v) {
		return distance(v.getX(),v.getY());
	}
	
	/**
	 * Compares two points for equality and returns true if the coordinates are the same.
	 * 
	 * @param o the point to compare
	 * @return true if locations are equal
	 */
	public boolean equals(Object o) {
		if(o == null || getClass() != o.getClass()) return false;
		Vertex2D v = (Vertex2D) o;
		return getX() == v.getX() && getY() == v.getY();
	}
}
