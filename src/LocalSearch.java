import java.util.Random;


public class LocalSearch {
	
	public static long kk(long[] A) {
		MaxHeap h = new MaxHeap();
		for (long i : A) {
			h.insert(i);
		}
		while (h.size() > 1) {
			long n1 = h.deleteMax();
			long n2 = h.deleteMax();
			long diff = Math.abs(n1 - n2);
			h.insert(diff);
		}
		return h.deleteMax();
	}
	
	public static long repeatedRandom(long[] A, Solution s, int max) {
		long r = s.residue(A);
		for (int i = 0; i < max; i++) {
			Solution ss = s.getNeighbor(true);
			long rr = ss.residue(A);
			if (rr < r) {
				r = rr;
				s = ss;
			}
		}
		return r;
	}
	
	public static long hillClimbing(long[] A, Solution s, int max) {
		long r = s.residue(A);
		for (int i = 0; i < max; i++) {
			Solution ss = s.getNeighbor(false);
			long rr = ss.residue(A);
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
	
	public static long simulatedAnnealing(long[] A, Solution s, int max) {
		long r = s.residue(A);
		Solution sBest = s;
		long rBest = r;
		for (int i = 0; i < max; i++) {
			Solution ss = s.getNeighbor(false);
			long rr = ss.residue(A);
			if (rr < r) {
				r = rr;
				s = ss;
			} else {
				Random rand = new Random();
				double p = rand.nextDouble();
				double prob = Math.exp(-(rr-r)/T(i));
				if (p < prob) {
					r = rr;
					s = ss;
				}
			}
			if (r < rBest) {
				rBest = r;
				sBest = s;
			}
		}
		return rBest;
	}
		

}
