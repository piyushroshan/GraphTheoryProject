package gtc;

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
	
	/** The max expected permutations. */
	final int MAX_P = 10000;
	
	/** The max expected numbers to be permuted. */
	final int MAX = 10;
	 
 	/** The count of permutations. */
 	int cP;
	
	/** The particular permutation of I. */
	int[][] P = new int[MAX][MAX];
	
	/** The transpose of P. */
	int[][] PT = new int[MAX][MAX];
	
	/** The unit matrix I. */
	int[][] I = new int[MAX][MAX];
	
	/** The number of vertices. */
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
	 * @param array the array to store all the permutations of 1..n
	 * @param l the index of array till which numbers are permuted
	 * @param h the number of items to be permuted
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
	 * Checks if two graphs are isomorphic.
	 *
	 * @param a the input graph
	 * @param b the graph to be transformed by permutation
	 * @return true, if the graphs are isomorphic
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
