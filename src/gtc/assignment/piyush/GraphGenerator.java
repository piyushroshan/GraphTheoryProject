package gtc.assignment.piyush;

public class GraphGenerator {
	int n;
	final int MAX = 10;
	final int MAXF = 100;
	int[][][] tf = new int[MAXF][MAXF][MAXF];
	int tfcount;

	public GraphGenerator(int x) {
		n = x;
		tfcount = 0;
		call();
		
	}

	void call() {
		int i, j;
		int[][] a = new int[MAX][MAX];
		int[][] b = new int[n][n];
		for (int ii = 0; ii < n; ii++) {
			System.arraycopy(a[ii], 0, b[ii], 0, n);
		}
		b[0][1] = 1;
		b[1][0] = 1;
		int[] s = new int[MAX];
		s[1] = 1;
		s[2] = 1;
		addedge(b, s, 2);
		
		printtf();
	}

	/**
	 * @param args
	 */

	void addedge(int[][] a, int[] s, int c) {
		int i, j, oldi, oldj, flag = 0;
		// System.out.println("C :" + c);

		if (c == n) {
			for (i = 0; i < n; i++) {
				for (j = 0; j < i; j++) {
					if (i != j && a[i][j] == 0 && s[i] == 1 && s[j] == 1) {
						int[][] b = new int[n][n];
						for (int ii = 0; ii < n; ii++) {
							System.arraycopy(a[ii], 0, b[ii], 0, n);
						}
						b[i][j] = 1;
						b[j][i] = 1;
						if (istrainglefree(b, n)) {
							// printGraphtest(b);
							flag = 1;
							int[] sb = new int[MAX];
							System.arraycopy(s, 0, sb, 0, sb.length);
							addedge(b, sb, c);
						} else {

						}

					}
				}
			}
			if (flag == 0) {

				System.out.println("Graph found");
				addGraph(a, n);
				// printGraph(a);
			}

		}

		if (c <= (n - 1)) {
			for (i = 0; i < n; i++)
				for (j = 0; j < i; j++) {
					if (i != j
							&& a[i][j] == 0
							&& ((s[i] == 0 && s[j] == 1) || (s[i] == 1 && s[j] == 0))) {
						int[][] b = new int[n][n];
						for (int ii = 0; ii < n; ii++) {
							System.arraycopy(a[ii], 0, b[ii], 0, n);
						}
						b[i][j] = 1;
						b[j][i] = 1;
						oldi = s[i];
						oldj = s[j];
						s[i] = 1;
						s[j] = 1;

						if (istrainglefree(b, n)) {
							// printGraphtest(b);
							// System.out.println("Added edge type 2");
							int[] sb = new int[MAX];
							System.arraycopy(s, 0, sb, 0, sb.length);
							addedge(b, sb, c + s[i] - oldi + s[j] - oldj);
						}
						s[i] = oldi;
						s[j] = oldj;
					}
				}
		}

	}

	void addGraph(int[][] a, int n) {
		int i, j, c;
		if(tfcount==0)
		{
			for (int ii = 0; ii < n; ii++) {
				System.arraycopy(a[ii], 0, tf[tfcount][ii], 0, n);
			}
			tfcount++;
			return;
		}
		int[] flag= new int[tfcount];
		for (c = 0; c < tfcount; c++) {
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					if (tf[c][i][j] != a[i][j]) {
						flag[c]=1;
						break;
					}
				}
			}
		}
		int sum=0;
		for (int ii = 0; ii < tfcount; ii++) {
			sum+=flag[ii];
		}
		if(sum==tfcount){
		for (int ii = 0; ii < n; ii++) {
			System.arraycopy(a[ii], 0, tf[tfcount][ii], 0, n);
		}
		tfcount++;
		return;
		}
	}

	Boolean istrainglefree(int[][] a, int n) {
		int i, j, k, sum;
		int[][] x = new int[n][n];
		int[][] y = new int[n][n];
		int trace = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				sum = 0;
				for (k = 0; k < n; k++)
					sum = sum + a[i][k] * a[k][j];
				x[i][j] = sum;
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				sum = 0;
				for (k = 0; k < n; k++)
					sum = sum + x[i][k] * a[k][j];
				y[i][j] = sum;
			}
		}

		for (i = 0; i < n; i++) {
			trace += y[i][i];
		}
		if (trace == 0)
			return true;
		else
			return false;
	}

	void printGraph(int[][] a) {
		int i, j;
		System.out.print(" | ");
		for (i = 0; i < n; i++)
			System.out.print(i + 1 + " ");
		System.out.println();

		for (i = 0; i < n; i++)
			System.out.print("---");
		System.out.println();
		for (i = 0; i < n; i++) {
			System.out.print(i + 1 + "| ");
			for (j = 0; j < n; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	void printGraphtest(int[][] a) {
		int i, j;
		System.out.println("testing");
		System.out.print(" | ");
		for (i = 0; i < n; i++)
			System.out.print(i + 1 + " ");
		System.out.println();

		for (i = 0; i < n; i++)
			System.out.print("---");
		System.out.println();
		for (i = 0; i < n; i++) {
			System.out.print(i + 1 + "| ");
			for (j = 0; j < n; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}

	}

	void printtf() {
		int i, j, k;
		
		System.out.print(tfcount+"\n");
		for (k = 0; k < tfcount; k++) {
			System.out.print(" | ");
			for (i = 0; i < n; i++)
				System.out.print(i + 1 + " ");
			System.out.println();

			for (i = 0; i < n; i++)
				System.out.print("---");
			System.out.println();
			for (i = 0; i < n; i++) {
				System.out.print(i + 1 + "| ");
				for (j = 0; j < n; j++) {
					System.out.print(tf[k][i][j] + " ");
				}
				System.out.println("");
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphGenerator g = new GraphGenerator(6);
	}

}
