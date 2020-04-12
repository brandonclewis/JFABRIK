
public class Fabrik {
	public static void main(String args[]) {
		Vertex3D[] limb = {new Vertex3D(0,0,0), new Vertex3D(1,1,0), new Vertex3D(2,1,0), new Vertex3D(3,0,0)};
		limb = moveLimb(limb, new Vertex3D(2,-1,0));
		for(int i=0;i<limb.length;i++) {
			System.out.print("(" + limb[i].getX() + "," + limb[i].getY() + "," + limb[i].getZ() + ") ");
		}
	}
	
	/**
	 * Constant for the margin of error for acceptable closeness of the end point in the limb to the target.
	 */
	public static final double MARGIN_OF_ERROR = 0.01;
	
	/**
	 * Simple FABRIK algorithm. Supports no constraints. Returns an array of vertices ordered from a rigid base to end point.
	 * 
	 * @param limb the initial array of vertices
	 * @param target the location to move the end of the limb to
	 * @return the array of vertices describing the new location of each joint
	 */
	public static Vertex3D[] moveLimb(Vertex3D[] limb, Vertex3D target) {
		if(limb[limb.length-1].distance(target) < MARGIN_OF_ERROR) return limb;
		
		Vertex3D[] forward = new Vertex3D[limb.length];
		Vertex3D currentTarget = new Vertex3D(target);
		
		for(int i=limb.length-1;i>=0;i--) {
			forward[i] = new Vertex3D(currentTarget);
			if(i != 0)
				currentTarget = pointAlongVector(currentTarget,limb[i-1],limb[i].distance(limb[i-1]));
		}
		
		Vertex3D[] backward = new Vertex3D[limb.length];
		currentTarget = limb[0];
		
		for(int i=0;i<limb.length;i++) {
			backward[i] = new Vertex3D(currentTarget);
			if(i != limb.length-1)
				currentTarget = pointAlongVector(currentTarget,forward[i+1],limb[i].distance(limb[i+1]));
		}
		return moveLimb(backward,target);
	}
	
	/**
	 * Returns the point a set distance from the starting point in the direction of the end point.
	 * 
	 * @param start the vertex to start with
	 * @param end the vertex to end the vector
	 * @param distance the distance along the vector to place the point
	 * @return the point a set distance on the vector from start to end
	 */
	public static Vertex3D pointAlongVector(Vertex3D start, Vertex3D end, double distance) {
		double[] coordinates = new double[3];
		for(int i='x';i<='z';i++)
			coordinates[i-'x'] = start.getN((char)i) - (distance)/(start.distance(end)) * (start.getN((char)i)-end.getN((char)i));
		return new Vertex3D(coordinates[0],coordinates[1],coordinates[2]);
	}
}
