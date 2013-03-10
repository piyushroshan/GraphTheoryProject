package gtc.assignment.piyush;

public class permatrix {
	final static int MAX = 10000;
	static int cc;
	int[][] P = new int[MAX][MAX];
	int[][] PT = new int[MAX][MAX];
	static int[][] I = new int[20][20];
	static int v;
	static int[][] permutations = new int[MAX][20];

	permatrix(int v) {
		permatrix.v = v;
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				if (i == j)
					I[i][j] = 1;
				else
					I[i][j] = 0;
			}
		}
		int[] arr = new int[v];
		for (int y = 0; y < v; y++)
			arr[y] = y + 1;
		permuteVertex(arr, 0, v);
	}

	public static void permuteVertex(int[] array, int l, int h) {
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

					permuteVertex(array, l + 1, h);

					temp = array[l];
					array[l] = array[i];
					array[i] = temp;
				}
			}
		}
	}

	int isomorphic(int[][] a, int[][] b) {
		for (int w = 0; w < cc; w++) {
			int ele = 0;
			for (int r = 0; r < v; r++) {
				System.arraycopy(I[permutations[w][r] - 1], 0, P[r], 0, v);
			}

			for (int i = 0; i < v; i++)
				for (int j = 0; j < v; j++)
					PT[j][i] = P[i][j];

			int[][] temp = new int[v][v];
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					temp[i][j] = 0;
					for (int k = 0; k < v; k++) {
						temp[i][j] += P[i][k] * b[k][j];
					}
				}
			}
			int[][] temp1 = new int[v][v];
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					temp1[i][j] = 0;
					for (int k = 0; k < v; k++) {
						temp1[i][j] += temp[i][k] * PT[k][j];
					}
				}
			}
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					if (a[i][j] == temp1[i][j])
						ele++;
				}
			}
			if (ele == v * v) {
				return 0;
			}

		}
		return 1;
	}
}
