/**
 * This class implements a point in 3D Cartesian space.
 * @author Brandon Lewis
 */


public class Vertex {
	/**
	 * Internal coordinate storage.
	 */
	private double x;
	private double y;
	private double z;
	
	/**
	 * Default constructor. Sets all coordinates to zero.
	 */
	protected Vertex() {
		x=0;
		y=0;
		z=0;
	}
	
	/**
	 * Coordinate constructor. Sets all coordinates to the specified values.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	protected Vertex(double x, double y, double z) {
		setLocation(x,y,z);
	}
	
	/**
	 * Clone constructor. Sets all coordinates to that of a specified vertex.
	 * 
	 * @param v the vertex to copy
	 */
	protected Vertex(Vertex v) {
		setLocation(v);
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
	 * Get the Z coordinate.
	 * 
	 * @return the z coordinate
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Sets the location of this vertex to the specified coordinates.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 * @param z the new z coordinate
	 */
	public void setLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Sets the location of this vertex to the location of another vertex.
	 * 
	 * @param v the vertex to copy
	 */
	public void setLocation(Vertex v) {
		setLocation(v.getX(),v.getY(),v.getZ());
	}
	
	/**
	 * Return the distance between this vertex and the specified coordinates
	 * 
	 * @param x the x coordinate of the other point
	 * @param y the y coordinate of the other point
	 * @param z the z coordinate of the other point
	 * @return the distance from this vertex to (x,y,z)
	 */
	public double distance(double x, double y, double z) {
		return Math.sqrt(Math.pow(getX()-x,2) + Math.pow(getY()-y, 2) + Math.pow(getZ()-z, 2));
	}
	
	/**
	 * Return the distance between this vertex and the specified vertex
	 * 
	 * @param v the vertex to find the distance to
	 * @return the distance from this vertex to v
	 */
	public double distance(Vertex v) {
		return distance(v.getX(),v.getY(),v.getZ());
	}
	
	/**
	 * Compares two points for equality and returns true if the coordinates are the same.
	 * 
	 * @param o the point to compare
	 * @return true if locations are equal
	 */
	public boolean equals(Object o) {
		if(o == null || getClass() != o.getClass()) return false;
		Vertex v = (Vertex) o;
		return getX() == v.getX() && getY() == v.getY() && getZ() == v.getZ();
	}
}
