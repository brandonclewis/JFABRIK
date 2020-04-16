
public class Fabrik {
	public static void main(String args[]) {
		Vertex3D[] limb = {new Vertex3D(1,1,1), new Vertex3D(2,2,2), new Vertex3D(3,3,3)};
		
		limb = optimizedCoplanarSolve(limb,new Vertex3D(3,0,3));
		/*for(int i=0;i<limb.length;i++) {
			System.out.println("(" + limb[i].getX() + "," + limb[i].getY() + "," + limb[i].getZ() + ")");
		}*/
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
	public static Vertex3D[] solveLimb(Vertex3D[] limb, Vertex3D target) {
		if(limb[limb.length-1].distance(target) < MARGIN_OF_ERROR) return limb;
		if(lengthOfLimb(limb) < limb[0].distance(target)) return solveLimb(limb,pointAlongVector(limb[0],target,lengthOfLimb(limb)));
		
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
		return solveLimb(backward,target);
	}
	
	/**
	 * solveLimb modified to work with {@link Vertex2D#}.
	 * 
	 * @param limb the initial array of vertices
	 * @param target the location to move the end of the limb to
	 * @return the array of vertices describing the new location of each joint
	 */
	public static Vertex2D[] solveLimb(Vertex2D[] limb, Vertex2D target) {
		if(limb[limb.length-1].distance(target) < MARGIN_OF_ERROR) return limb;
		if(lengthOfLimb(limb) < limb[0].distance(target)) return solveLimb(limb,pointAlongVector(limb[0],target,lengthOfLimb(limb)));
		
		Vertex2D[] forward = new Vertex2D[limb.length];
		Vertex2D currentTarget = new Vertex2D(target);
		
		for(int i=limb.length-1;i>=0;i--) {
			forward[i] = new Vertex2D(currentTarget);
			if(i != 0)
				currentTarget = pointAlongVector(currentTarget,limb[i-1],limb[i].distance(limb[i-1]));
		}
		
		Vertex2D[] backward = new Vertex2D[limb.length];
		currentTarget = limb[0];
		
		for(int i=0;i<limb.length;i++) {
			backward[i] = new Vertex2D(currentTarget);
			if(i != limb.length-1)
				currentTarget = pointAlongVector(currentTarget,forward[i+1],limb[i].distance(limb[i+1]));
		}
		return solveLimb(backward,target);
	}
	
	/**
	 * solveLimb optimized for vertically coplanar points.
	 * 
	 * @param limb the initial array of vertices
	 * @param target the location to move the end of the limb to
	 * @return the array of vertices describing the new location of each joint
	 */
	public static Vertex3D[] optimizedCoplanarSolve(Vertex3D[] limb, Vertex3D target) {
		Vertex2D[] simplified = new Vertex2D[limb.length];
		simplified[0] = new Vertex2D(0,limb[0].getY());
		
		for(int i=1;i<limb.length;i++)
			simplified[i] = new Vertex2D(simplified[i-1].getX() + Math.sqrt(Math.pow(limb[i].getX()-limb[i-1].getX(), 2) + Math.pow(limb[i].getZ()-limb[i-1].getZ(), 2)),limb[i].getY());
		
		simplified = solveLimb(simplified,new Vertex2D(Math.sqrt(Math.pow(target.getX() - limb[0].getX(),2) + Math.pow(target.getZ() - limb[0].getZ(), 2)),target.getY()));

		Vertex3D[] converted = new Vertex3D[limb.length];
		converted[0] = limb[0];
		double angle = Math.atan((limb[limb.length-1].getZ()-limb[0].getZ())/(limb[limb.length-1].getX()-limb[0].getX()));
		
		for(int i=1;i<limb.length;i++)
			converted[i] = new Vertex3D(converted[0].getX() + simplified[i].getX() * Math.cos(angle),simplified[i].getY(),converted[0].getZ() + simplified[i].getX() * Math.sin(angle));
		
		return converted;
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
	
	/**
	 * pointAlongVector modified to work with {@link=Vertex2D#}.
	 * 
	 * @param start the vertex to start with
	 * @param end the vertex to end the vector
	 * @param distance the distance along the vector to place the point
	 * @return the point a set distance on the vector from start to end
	 */
	public static Vertex2D pointAlongVector(Vertex2D start, Vertex2D end, double distance) {
		double[] coordinates = new double[2];
		for(int i='x';i<='y';i++)
			coordinates[i-'x'] = start.getN((char)i) - (distance)/(start.distance(end)) * (start.getN((char)i)-end.getN((char)i));
		return new Vertex2D(coordinates[0],coordinates[1]);
	}
	
	/**
	 * Returns the sum of every distance between vertices.
	 * 
	 * @param limb the array of vertices
	 * @return the length of the limb
	 */
	public static double lengthOfLimb(Vertex3D[] limb) {
		double sum = 0;
		for(int i=0;i<limb.length-1;i++)
			sum += limb[i].distance(limb[i+1]);
		return sum;
	}
	
	/**
	 * lengthOfLimb modified to work with {@link=Vertex2D#}.
	 * 
	 * @param limb the array of vertices
	 * @return the length of the limb
	 */
	public static double lengthOfLimb(Vertex2D[] limb) {
		double sum = 0;
		for(int i=0;i<limb.length-1;i++)
			sum += limb[i].distance(limb[i+1]);
		return sum;
	}
}
