package gtc;

// TODO: Auto-generated Javadoc
/**
 *	Isomorphism.java
 * 
 * 	@Instititution : National Institute of Technology Calicut
 * 	@Date_Start	: 4th March, 2013
 * 	@Date_End	: 8th March, 2013
 * 	@Number_of_days_worked_on	:	3
 * 	@Author	:	Roshan Piyush
 * 				Pooja Prajod
 *  
 *	Purpose:	To check if two graphs are isomorphic.
 *
 *	References	:	http://www.usafa.edu/df/dfe/dfer/centers/accr/docs/augeri2007b.pdf
 * 
 */

public class Isomorphism {
	
	/** The max p. */
	final int MAX_P = 10000;
	
	/** The max. */
	final int MAX = 10;
	 
 	/** The count of Permutations. */
 	int cP;
	
	/** The p. */
	int[][] P = new int[MAX][MAX];
	
	/** The pt. */
	int[][] PT = new int[MAX][MAX];
	
	/** The i. */
	int[][] I = new int[MAX][MAX];
	
	/** The n. */
	int n;
	
	/** The permutations. */
	int[][] permutations = new int[MAX_P][MAX];

	/**
	 * Instantiates a new isomorphism.
	 *
	 * @param n the n
	 */
	Isomorphism(int n) {
		this.n = n;
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

	/**
	 * Permute vertex.
	 *
	 * @param array the array
	 * @param l the l
	 * @param h the h
	 */
	public void permuteVertex(int[] array, int l, int h) {
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

	/**
	 * Checks if is isomorphic.
	 *
	 * @param a the a
	 * @param b the b
	 * @return true, if is isomorphic
	 */
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
