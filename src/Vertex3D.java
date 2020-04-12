/**
 * This class implements a point in 3D Cartesian space.
 * @author Brandon Lewis
 */


public class Vertex3D extends Vertex2D{
	/**
	 * Internal coordinate storage.
	 */
	private double x;
	private double y;
	private double z;
	
	/**
	 * Default constructor. Sets all coordinates to zero.
	 */
	protected Vertex3D() {
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
	protected Vertex3D(double x, double y, double z) {
		super(x,y);
		setLocation(x,y,z);
	}
	
	/**
	 * Clone constructor. Sets all coordinates to that of a specified vertex.
	 * 
	 * @param v the vertex to copy
	 */
	protected Vertex3D(Vertex3D v) {
		super(v);
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
		if(n == 'z') return z;
		return 0.0;
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
	public void setLocation(Vertex3D v) {
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
	public double distance(Vertex3D v) {
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
		Vertex3D v = (Vertex3D) o;
		return getX() == v.getX() && getY() == v.getY() && getZ() == v.getZ();
	}
}
