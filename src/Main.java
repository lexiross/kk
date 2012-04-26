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

	// The monstrous testing method
	private static void runAll(int max, int n) {
		long[] kkResidues = new long[n];
		long[] standardrrResidues = new long[n];
		long[] standardhcResidues = new long[n];
		long[] standardsaResidues = new long[n];
		long[] pprrResidues = new long[n];
		long[] pphcResidues = new long[n];
		long[] ppsaResidues = new long[n];
		double[] kkTimes = new double[n];
		double[] standardrrTimes = new double[n];
		double[] standardhcTimes = new double[n];
		double[] standardsaTimes = new double[n];
		double[] pprrTimes = new double[n];
		double[] pphcTimes = new double[n];
		double[] ppsaTimes = new double[n];
		for (int i = 0; i < n; i++) {
			long[] A = generateProblem(100, 1000000000000L);
			long start = System.nanoTime();
			long kkResidue = LocalSearch.kk(A);
			kkResidues[i] = kkResidue;
			long elapsed = System.nanoTime() - start;
			double seconds = (double)elapsed / 1000000000.0;
			kkTimes[i] = seconds;
			System.out.println("\nKK: " + kkResidue + "\tTime: " + seconds);

			Solution standard = new StandardSolution(A.length);		
			System.out.println("\nStandard Representation:");
			start = System.nanoTime();
			long rrResidue = LocalSearch.repeatedRandom(A, standard, max);
			standardrrResidues[i] = rrResidue;
			elapsed = System.nanoTime() - start;
			seconds = (double)elapsed / 1000000000.0;
			standardrrTimes[i] = seconds;
			System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
			start = System.nanoTime();
			long hcResidue = LocalSearch.hillClimbing(A, standard, max);
			standardhcResidues[i] = hcResidue;
			elapsed = System.nanoTime() - start;
			seconds = (double)elapsed / 1000000000.0;
			standardhcTimes[i] = seconds;
			System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
			start = System.nanoTime();
			long saResidue = LocalSearch.simulatedAnnealing(A, standard, max);
			standardsaResidues[i] = saResidue;
			elapsed = System.nanoTime() - start;
			seconds = (double)elapsed / 1000000000.0;
			standardsaTimes[i] = seconds;
			System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);

			Solution prepartition = new PrepartitionSolution(A.length);		
			System.out.println("\nPrepartition Representation:");
			start = System.nanoTime();
			rrResidue = LocalSearch.repeatedRandom(A, prepartition, max);
			pprrResidues[i] = rrResidue;
			elapsed = System.nanoTime() - start;
			seconds = (double)elapsed / 1000000000.0;
			pprrTimes[i] = seconds;
			System.out.println("Repeated Random: " + rrResidue + "\tTime: " + seconds);
			start = System.nanoTime();
			hcResidue = LocalSearch.hillClimbing(A, prepartition, max);
			pphcResidues[i] = hcResidue;
			elapsed = System.nanoTime() - start;
			seconds = (double)elapsed / 1000000000.0;
			pphcTimes[i] = seconds;
			System.out.println("Hill Climbing: " + hcResidue + "\tTime: " + seconds);
			start = System.nanoTime();
			saResidue = LocalSearch.simulatedAnnealing(A, prepartition, max);
			ppsaResidues[i] = saResidue;
			elapsed = System.nanoTime() - start;
			seconds = (double)elapsed / 1000000000.0;
			ppsaTimes[i] = seconds;
			System.out.println("Simulated Annealing: " + saResidue + "\tTime: " + seconds);
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + kkResidues[i] + "}},");
			else
				System.out.print("{" + i + "," + kkResidues[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + standardrrResidues[i] + "}},");
			else
				System.out.print("{" + i + "," + standardrrResidues[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + standardhcResidues[i] + "}},");
			else
				System.out.print("{" + i + "," + standardhcResidues[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + standardsaResidues[i] + "}},");
			else
				System.out.print("{" + i + "," + standardsaResidues[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + pprrResidues[i] + "}},");
			else
				System.out.print("{" + i + "," + pprrResidues[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + pphcResidues[i] + "}},");
			else
				System.out.print("{" + i + "," + pphcResidues[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + ppsaResidues[i] + "}}");
			else
				System.out.print("{" + i + "," + ppsaResidues[i] + "},");
		}
		
		System.out.println("Time:");
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + kkTimes[i] + "}},");
			else
				System.out.print("{" + i + "," + kkTimes[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + standardrrTimes[i] + "}},");
			else
				System.out.print("{" + i + "," + standardrrTimes[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + standardhcTimes[i] + "}},");
			else
				System.out.print("{" + i + "," + standardhcTimes[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + standardsaTimes[i] + "}},");
			else
				System.out.print("{" + i + "," + standardsaTimes[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + pprrTimes[i] + "}},");
			else
				System.out.print("{" + i + "," + pprrTimes[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + pphcTimes[i] + "}},");
			else
				System.out.print("{" + i + "," + pphcTimes[i] + "},");
		}
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			if (i == n-1)
				System.out.print("{" + i + "," + ppsaTimes[i] + "}}");
			else
				System.out.print("{" + i + "," + ppsaTimes[i] + "},");
		}
		
	}
	
	private static void test() {

	
		runAll(25000, 50);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {


		String usage = "Usage: ./kk <inputfile>";
        if (args.length != 1) {
            System.out.println("Wrong number of arguments.");
            System.out.println(usage);
            return;
        }
        
        String filename = args[0];
        long[] problem = generateFromFile(filename);
        long residue = LocalSearch.kk(problem);
        System.out.println(residue);
  
		// Uncomment below to print Mathematica-ready results
		//test();
		

	}

}
