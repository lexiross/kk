import java.util.Random;


public class StandardSolution implements Solution {
	
	private int[] S;
	private int n;
	
	public StandardSolution(int n) {
		int[] S = new int[n];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			double r = rand.nextDouble();
			if (r < 0.5) {
				S[i] = -1;
			} else {
				S[i] = 1;
			}
		}
		this.S = S;
		this.n = n;
	}
	
	public StandardSolution(int[] S) {
		this.S = S;
		this.n = S.length;
	}

	public Solution getNeighbor(boolean random) {
		Random rand = new Random();
		int[] neighbor = new int[this.n];
		if (random) {
			for (int i = 0; i < n; i++) {
				double r = rand.nextDouble();
				if (r < 0.5) {
					neighbor[i] = -1;
				} else {
					neighbor[i] = 1;
				}
			}
		} else {
			int i = rand.nextInt(n);
			int j;
			do {
				j = rand.nextInt(n);
			} while (i != j);
			neighbor[i] *= -1;
			double p = rand.nextDouble();
			if (p < 0.5) {
				neighbor[j] *= -1;
			}
		}
		return new StandardSolution(neighbor);
	}

	public int residue(int[] A) {
		int residue = 0;
		for (int i = 0; i < this.n; i++) {
			residue += (this.S[i]*A[i]);
		}
		return residue;
	}

}
