import java.util.Random;


public class Main {
	
	// from http://stackoverflow.com/questions/2546078/java-random-long-number-in-0-x-n-range
	private static long nextLong(Random rng, long n) {
		long bits, val;
		do {
			bits = (rng.nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits-val+(n-1) < 0L);
		return val;
	}
	
	private static long[] generateProblem(int n, long max) {
		Random rand = new Random();
		long[] problem = new long[n];
		for (int i = 0; i < n; i++) {
			problem[i] = nextLong(rand, max) + 1;
		}
		return problem;
	}

	private static void runAll(long[] A, int max) {
		long start = System.nanoTime();
		long kkResidue = LocalSearch.kk(A);
		long elapsed = System.nanoTime() - start;
		double seconds = (double)elapsed / 1000000000.0;
		System.out.println("\nKK: " + kkResidue + "\tTime: " + seconds);
		
		Solution standard = new StandardSolution(A.length);		
		System.out.println("\nStandard Representation:");
		start = System.nanoTime();
		long rrResidue = LocalSearch.repeatedRandom(A, standard, max);
		elapsed = System.nanoTime() - start;
		seconds = (double)elapsed / 1000000000.0;
		System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
		start = System.nanoTime();
		long hcResidue = LocalSearch.hillClimbing(A, standard, max);
		elapsed = System.nanoTime() - start;
		seconds = (double)elapsed / 1000000000.0;
		System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
		start = System.nanoTime();
		long saResidue = LocalSearch.simulatedAnnealing(A, standard, max);
		elapsed = System.nanoTime() - start;
		seconds = (double)elapsed / 1000000000.0;
		System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);
		
		Solution prepartition = new PrepartitionSolution(A.length);		
		System.out.println("\nPrepartition Representation:");
		start = System.nanoTime();
		rrResidue = LocalSearch.repeatedRandom(A, prepartition, max);
		elapsed = System.nanoTime() - start;
		seconds = (double)elapsed / 1000000000.0;
		System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
		start = System.nanoTime();
		hcResidue = LocalSearch.hillClimbing(A, prepartition, max);
		elapsed = System.nanoTime() - start;
		seconds = (double)elapsed / 1000000000.0;
		System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
		start = System.nanoTime();
		saResidue = LocalSearch.simulatedAnnealing(A, prepartition, max);
		elapsed = System.nanoTime() - start;
		seconds = (double)elapsed / 1000000000.0;
		System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// testing solution generation
		/*
		Solution s1 = new StandardSolution(10);
		Solution s2 = new PrepartitionSolution(10);
		s1.print();
		s2.print();
		*/
				
		long[] p1 = generateProblem(50, 1000);
		System.out.print("Problem:\n[");
		for (int i = 0; i < 50; i++) {
			System.out.print(" " + p1[i] + " ");
		}
		System.out.print("]\n");
		
		runAll(p1, 500);
		
		// test local search functions
		/*
		int max = 100;
		long result1 = LocalSearch.repeatedRandom(p1, s1, max);
		long result2 = LocalSearch.repeatedRandom(p1, s2, max);
		long result3 = LocalSearch.hillClimbing(p1, s1, max);
		long result4 = LocalSearch.hillClimbing(p1, s2, max);
		long result5 = LocalSearch.simulatedAnnealing(p1, s1, max);
		long result6 = LocalSearch.simulatedAnnealing(p1, s2, max);
		
		System.out.println("1: " + result1 + "\n2: " + result2 + "\n3: " + result3
				+ "\n4: " + result4 + "\n5: " + result5 + "\n6: " + result6);
		
		// test neighbor functions
		Solution n1 = s1.getNeighbor(false);
		n1.print();
		Solution n2 = s2.getNeighbor(false);
		n2.print();
		
		// test residue functions
		long r1 = s1.residue(p1);
		long r2 = s2.residue(p1);
		System.out.println("r1: " + r1 + "\nr2: " + r2);
		
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
		*/

	}

}
