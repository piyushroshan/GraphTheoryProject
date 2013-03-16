package gtc.assignment.piyush;

/*	
 *	RamseyGraph.java
 * 
* 	@Instititution : National Institute of Technology Calicut
 * 	@Date_Start	: 7th February, 2013
 * 	@Date_End	: 14th March, 2013
 * 	@Number of days worked on	:	6
 * 	@Author	:	Roshan Piyush
 * 				Pooja Prajod
 *  
 *	Purpose	:	To find Ramsey Graphs with r colorings of 2 set subset containg monochromatic traingle, of n vertices.
 * 
 *	References :	http://www.combinatorics.org/ojs/index.php/eljc/article/view/v19i4p36/pdf
 * 
 */

public class RamseyGraph {
	static int n;
	final static int l = 3;
	static int maxE;
	static int maxC;
	final static int MAX = 10;
	final static int MAX_G = 10000;
	final String filename = "ramseyGraph.txt";
	int[][][] tf = new int[MAX_G][MAX][MAX];
	int cG;
	Isomorphism isomrphc;
	FileOutput fileOut;
	
	
	public RamseyGraph(int n) {
		RamseyGraph.n = n;
		cG = 0;
		isomrphc = new Isomorphism(n);
		fileOut = new FileOutput(filename);
		fileOut.writeTextFile("Ramsey Graphs");
		call();
	}

	void call() {
		int[][] a = new int[MAX][MAX];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < i; j++) {
				a[i][j] = 1;
				a[j][i] = 1;
			}
		maxC = (int) (n - 1) / (l - 1);
		System.out.println("No. of colours = " + maxC);
		maxE = (n * n - n) / 2;
		if (maxC == 1) {
			if (isRamseyGraph(a)){
				addGraph(a);
				fileOut.writeTextFile(a, n);
			}
		} else {
			for (int C = 2; C <= maxC; C++) {
				int[][] b = new int[n][n];
				for (int ii = 0; ii < n; ii++) {
					System.arraycopy(a[ii], 0, b[ii], 0, n);
				}
				b[0][1] = C;
				b[1][0] = C;
				addEdge(b, 1);
			}
		}
		printRamseyGraphs();
		fileOut.writeTextFile("No of Ramsey Graphs of "+ n +" vertices = "+ cG);
		fileOut.closeFile();
	}

	/**
	 * @param args
	 */

	void addEdge(int[][] a, int cE) {
		int i, j;
		if (isRamseyGraph(a))
			addGraph(a);

		if (cE < maxE) {
			for (i = 0; i < n; i++)
				for (j = 0; j < i; j++) {
					if (i != j && a[i][j] == 1) {
						for (int C = 2; C <= maxC; C++) {
							int[][] b = new int[n][n];
							for (int ii = 0; ii < n; ii++) {
								System.arraycopy(a[ii], 0, b[ii], 0, n);
							}
							b[i][j] = C;
							b[j][i] = C;
							addEdge(b, cE + 1);
						}
					}
				}
		}
	}

	void addGraph(int[][] a) {
		if (cG == 0) {
			for (int i = 0; i < n; i++) {
				System.arraycopy(a[i], 0, tf[cG][i], 0, n);
			}
			cG++;
			fileOut.writeTextFile(a, n);
			return;
		}

		for (int c = 0; c < cG; c++) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (tf[c][i][j] == a[i][j])
						sum++;
				}
			}
			if (sum == (n * n - n) / 2) {
				return;
			}
		}

		for (int c = 0; c < cG; c++) {
			int cTf = 0, cA = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (tf[c][i][j] == 1)
						cTf++;
					if (a[i][j] == 1)
						cA++;
				}
			}
			if (cTf != cA) {
				continue;
			}
			if (isomrphc.isIsomorphic(a, tf[c]))
				return;
		}
		
		for (int i = 0; i < n; i++) {
			System.arraycopy(a[i], 0, tf[cG][i], 0, n);
		}
		cG++;
		fileOut.writeTextFile(a, n);
		return;
	}

	Boolean isRamseyGraph(int[][] a) {
		int sum = 0;
		for (int C = 1; C <= maxC; C++) {
			int[][] b = new int[n][n];
			int cE=0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (a[i][j] == C) {
						b[i][j] = 1;
						b[j][i] = 1;
						cE=cE+1;
					}
				}
			}
			if(cE==maxE){
				return false;
			}
			if (!istrainglefree(b))
				sum++;
		}
		if (sum == 1)
			return true;
		return false;
	}

	Boolean istrainglefree(int[][] a) {
		int[][] x = new int[n][n];
		int[][] y = new int[n][n];
		int trace = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = 0;
				for (int k = 0; k < n; k++)
					sum = sum + a[i][k] * a[k][j];
				x[i][j] = sum;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = 0;
				for (int k = 0; k < n; k++)
					sum = sum + x[i][k] * a[k][j];
				y[i][j] = sum;
			}
		}

		for (int i = 0; i < n; i++) {
			trace += y[i][i];
		}

		if (trace == 0)
			return true;
		else
			return false;
	}

	void printGraph(int[][] a, String s) {
		System.out.println(s);
		System.out.print(" | ");
		for (int i = 0; i < n; i++)
			System.out.print(i + 1 + " ");
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i + 1 + "| ");
			for (int j = 0; j < n; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}
	}

	void printRamseyGraphs() {
		for (int c = 0; c < cG; c++) {
			System.out.print("_|_");
			for (int i = 0; i < n; i++)
				System.out.print(i + 1 + "_");
			System.out.println();
			for (int i = 0; i < n; i++) {
				System.out.print(i + 1 + "| ");
				for (int j = 0; j < n; j++) {
					System.out.print(tf[c][i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.print("No of Ramsey graphs of "+ n +" vertices = " + cG + "\n");
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		RamseyGraph g = new RamseyGraph(5);
	}
}
