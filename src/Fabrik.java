
public class Fabrik {
	public static void main(String args[]) {
		Vertex[] limb = {new Vertex(0,0,0), new Vertex(1,1,0), new Vertex(2,1,0), new Vertex(3,0,0)};
		limb = Fabrik.moveLimb(limb, new Vertex(2,-1,0));
		for(int i=0;i<limb.length;i++) {
			System.out.print("(" + limb[i].getX() + "," + limb[i].getY() + "," + limb[i].getZ() + ") ");
		}
	}
	
	public static final double MARGIN_OF_ERROR = 0.01;
	
	public static Vertex[] moveLimb(Vertex[] limb, Vertex target) {
		if(limb[limb.length-1].distance(target) < MARGIN_OF_ERROR) return limb;
		
		Vertex[] altered = new Vertex[limb.length];
		Vertex currentTarget = new Vertex(target);
		
		for(int i=limb.length-1;i>=0;i--) {
			altered[i] = new Vertex(currentTarget);
			if(i != 0) {
				currentTarget.setLocation(currentTarget.getX() - limb[i].distance(limb[i-1]) / currentTarget.distance(limb[i-1])*(currentTarget.getX()-limb[i-1].getX()),
						currentTarget.getY() - limb[i].distance(limb[i-1]) / currentTarget.distance(limb[i-1])*(currentTarget.getY()-limb[i-1].getY()),
						currentTarget.getZ() - limb[i].distance(limb[i-1]) / currentTarget.distance(limb[i-1])*(currentTarget.getZ())-limb[i-1].getZ());
			}
		}
		
		Vertex[] altered2 = new Vertex[limb.length];
		Vertex currentTarget2 = limb[0];
		
		for(int i=0;i<limb.length;i++) {
			altered2[i] = new Vertex(currentTarget2);
			if(i != limb.length-1) {
				currentTarget2.setLocation(currentTarget2.getX() - limb[i].distance(limb[i+1]) / currentTarget2.distance(altered[i+1])*(currentTarget2.getX()-altered[i+1].getX()),
						currentTarget2.getY() - limb[i].distance(limb[i+1]) / currentTarget2.distance(altered[i+1])*(currentTarget2.getY()-altered[i+1].getY()),
						currentTarget2.getZ() - limb[i].distance(limb[i+1]) / currentTarget2.distance(altered[i+1])*(currentTarget2.getZ()-altered[i+1].getZ()));
			}
		}
		return moveLimb(altered2,target);
	}
}
