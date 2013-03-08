package gtc.assignment.piyush;

public class permatrix {
	final static int MAX = 1000;
	static int cc;
	int[][] A = new int[MAX][MAX];
	int[][] B = new int[MAX][MAX];
	int[][] P = new int[MAX][MAX];
	int[][] PT = new int[MAX][MAX];
	static int[][] permutations = new int[MAX][20];

	public static void printPermutations(int[] array, int l, int h) {
		int i, temp, j;
		boolean skip;
		if (l == h) {
			System.arraycopy(array, 0, permutations[cc++], 0, l);
		} else {
			for (i = l; i < h; i++) {
				skip = false;
				for (j = l; j < i; j++) {
					if (array[j] == array[i]) {
						skip = true;
						break;
					}
				}
				if (!skip) {
					temp = array[l];
					array[l] = array[i];
					array[i] = temp;

					printPermutations(array, l + 1, h);

					temp = array[l];
					array[l] = array[i];
					array[i] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		printPermutations(a, 0, 5);
		for (int i = 0; i < cc; i++) {
			for (int k = 0; k < 5; k++)
				System.out.print(permutations[i][k] + "\t");
			System.out.println();
		}
	}

}
