
public class LocalSearch {
	
	public static int kk(int[] A) {
		//TODO
		return 0;
	}
	
	public static int repeatedRandom(int[] A, Solution s, int max) {
		int r = s.residue(A);
		for (int i = 0; i < max; i++) {
			Solution ss = s.getNeighbor(true);
			int rr = ss.residue(A);
			if (rr < r) {
				r = rr;
				s = ss;
			}
		}
		return r;
	}
	
	public static int hillClimbing(int[] A, Solution s, int max) {
		int r = s.residue(A);
		for (int i = 0; i < max; i++) {
			Solution ss = s.getNeighbor(false);
			int rr = ss.residue(A);
			if (rr < r) {
				r = rr;
				s = ss;
			}
		}
		return r;
	}
	
	private static double T(int i) {
		return (Math.pow(10, 10)*Math.pow(0.8, Math.floor(i/300)));
	}
	
	public static int simulatedAnnealing(int[] A, Solution s, int max) {
		// TODO
		return 0;
	}
		

}
