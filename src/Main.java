
public class Main {

	public static void runAll(int[] A) {
		System.out.println("KK: " + LocalSearch.kk(A));
		Solution standard = new StandardSolution(A.length);
		System.out.println("Repeated Random: " + LocalSearch.repeatedRandom(A, standard));
		System.out.println("Hill Climbing: " + LocalSearch.repeatedRandom(A, standard));

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
