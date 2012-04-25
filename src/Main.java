import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	private static long[] generateFromFile(String filename) {
		int numLines = 100;
		long[] A = new long[numLines];
		try {
			FileReader input = new FileReader(filename);
			BufferedReader reader = new BufferedReader(input);
			for (int i = 0; i < numLines; i++) {
				String line = reader.readLine();
				A[i] = Long.parseLong(line);
			}
						
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found! Make the path is either absolute or relative to the bin directory.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return A;
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
	
	private static void test() {
		long[] p1 = generateProblem(100, 1000000000000L);
		System.out.print("Problem:\n[");
		for (int i = 0; i < 50; i++) {
			System.out.print(" " + p1[i] + " ");
		}
		System.out.print("]\n");
		
		runAll(p1, 25000);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * uncomment before submission
		String usage = "Usage: ./kk <inputfile>";
        if (args.length != 1) {
            System.out.println("Wrong number of arguments.");
            System.out.println(usage);
            return;
        }
        
        String filename = args[0];
        long[] problem = generateFromFile(filename);
        LocalSearch.kk(problem);
        */
		
		test();
		

	}

}
