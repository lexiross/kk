import java.util.Random;


public class Main {
	
	// from http://stackoverflow.com/questions/2546078/java-random-long-number-in-0-x-n-range
	long nextLong(Random rng, long n) {
		long bits, val;
		do {
			bits = (rng.nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits-val+(n-1) < 0L);
		return val;
	}

	public static void runAll(int[] A, int max) {
		long start = System.nanoTime();
		int kkResidue = LocalSearch.kk(A);
		long elapsed = System.nanoTime() - start;
		double seconds = (double)elapsed / 1000000000.0;
		System.out.println("KK: " + kkResidue + "\tTime: " + seconds);
		
		Solution standard = new StandardSolution(A.length);		
		System.out.println("Standard Representation:");
		start = System.nanoTime();
		System.out.println("Repeated Random: " + LocalSearch.repeatedRandom(A, standard, max));
		System.out.println("Hill Climbing: " + LocalSearch.repeatedRandom(A, standard, max));

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s1 = new StandardSolution(10);
		Solution s2 = new PrepartitionSolution(10);
		s1.print();
		s2.print();
		
		// test heap
		int[] arr = s2.getArray();
		MaxHeap h = new MaxHeap();
		for (int i : arr) {
			h.insert(i);
		}
		while (!h.isEmpty()) {
			long l = h.deleteMax();
			System.out.println(l);
		}

	}

}
