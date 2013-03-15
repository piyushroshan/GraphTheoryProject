package gtc.assignment.piyush;

/*	
 *	Isomorphism.java
 * 
 * 	@Author	:	Roshan Piyush
 * 				Pooja Prajod
 *  
 *	Purpose:	To check if two graphs are isomorphic
 * 
 * 
 * 
 */

public class Isomorphism {
	final static int MAX_P = 10000;
	final static int MAX = 10;
	static int cP;
	int[][] P = new int[MAX][MAX];
	int[][] PT = new int[MAX][MAX];
	static int[][] I = new int[MAX][MAX];
	static int n;
	static int[][] permutations = new int[MAX_P][MAX];

	Isomorphism(int n) {
		Isomorphism.n = n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					I[i][j] = 1;
				else
					I[i][j] = 0;
			}
		}
		
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = i + 1;
		permuteVertex(array, 0, n);
	}

	public static void permuteVertex(int[] array, int l, int h) {
		int temp;
		boolean skip;
		if (l == h) {
			System.arraycopy(array, 0, permutations[cP++], 0, l);
		} else {
			for (int i = l; i < h; i++) {
				skip = false;
				for (int j = l; j < i; j++) {
					if (array[j] == array[i]) {
						skip = true;
						break;
					}
				}
				
				if (!skip) {
					temp = array[l];
					array[l] = array[i];
					array[i] = temp;
					permuteVertex(array, l + 1, h);
					temp = array[l];
					array[l] = array[i];
					array[i] = temp;
				}
			}
		}
	}

	boolean isIsomorphic(int[][] a, int[][] b) {
		for (int w = 0; w < cP; w++) {
			int cE = 0;
			for (int i = 0; i < n; i++) {
				System.arraycopy(I[permutations[w][i] - 1], 0, P[i], 0, n);
			}

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					PT[j][i] = P[i][j];

			int[][] temp = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					temp[i][j] = 0;
					for (int k = 0; k < n; k++) {
						temp[i][j] += P[i][k] * b[k][j];
					}
				}
			}
			
			int[][] tempIsomorphic = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					tempIsomorphic[i][j] = 0;
					for (int k = 0; k < n; k++) {
						tempIsomorphic[i][j] += temp[i][k] * PT[k][j];
					}
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (a[i][j] == tempIsomorphic[i][j])
						cE++;
				}
			}
			
			if (cE == n * n) {
				return true;
			}
		}
		return false;
	}
}
