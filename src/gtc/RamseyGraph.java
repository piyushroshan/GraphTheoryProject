package gtc;

/**	
 *	RamseyGraph.java
 * 
* 	@Instititution : National Institute of Technology Calicut
 * 	@Date_Start	: 7th February, 2013
 * 	@Date_End	: 14th March, 2013
 * 	@Number_of_days_worked_on	:	6
 * 	@Author	:	Roshan Piyush
 * 				Pooja Prajod
 *  
 *	Purpose	:	To find Ramsey Graphs with r colorings of 2 set subset containing monochromatic triangle, of n vertices.
 * 
 *	References :	http://www.combinatorics.org/ojs/index.php/eljc/article/view/v19i4p36/pdf
 * 
 */

public class RamseyGraph {
	
	/** The number of vertices. */
	int n;
	
	/** The monochromatic complete graph size. */
	final int l = 3;
	
	/** The maximum edges in the graph. */
	int maxE;
	
	/** The max c. */
	int maxC;
	
	/** The max graph size constants. */
	final int MAX = 10;
	
	/** The max no. of graphs. */
	final int MAX_G = 10000;
	
	/** The filename. */
	final String filename = "ramseyGraph.txt";
	
	/** The tf. */
	int[][][] tf = new int[MAX_G][MAX][MAX];
	
	/** The c g. */
	int cG;
	
	/** The isomorphic object */
	Isomorphism isomrphc;
	
	/** The file out. */
	FileOutput fileOut;
	
	/**
	 * Constructor.
	 *
	 * @param n : No of vertices
	 */
	public RamseyGraph(int n) {
		this.n = n;
		cG = 0;
		isomrphc = new Isomorphism(n);
		fileOut = new FileOutput(filename);
		fileOut.writeTextFile("Ramsey Graphs");
		call();
	}
	
	/**
	 * Initiate the graph creation
	 *
	 */
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
			if (n>=3){
				addGraph(a);
			}
		} else {
			for (int C = 2; C <= maxC; C++) {
				int[][] b = new int[n][n];
				for (int ii = 0; ii < n; ii++) {
					System.arraycopy(a[ii], 0, b[ii], 0, n);
				}
				
				// choose any initial edge and continue
				b[0][1] = C;
				b[1][0] = C;
				addEdge(b, 1);
			}
		}
		fileOut.writeTextFile("No of Ramsey Graphs of "+ n +" vertices = "+ cG);
		System.out.println("No of Ramsey Graphs of "+ n +" vertices = "+ cG);
	}

	/**
	 * Keep adding edges till it does not form a complete graph
	 *
	 * @param a : graph to add edge on
	 * @param cE: the count of edge
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

	/**
	 * Adds only non-isomorphic and non-identical graphs.
	 *
	 * @param a : graph to add
	 */
	void addGraph(int[][] a) {
		
		// if it is first graph found store it in tf[][]
		if (cG == 0) {
			for (int i = 0; i < n; i++) {
				System.arraycopy(a[i], 0, tf[cG][i], 0, n);
			}
			cG++;
			fileOut.writeTextFile(a, n);
			return;
		}
		
		//check for identical graphs
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
		
		//check if the graph found is isomorphic to any previously found graph stored in tf[][]
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

	/**
	 * Checks if is ramsey graph.
	 *
	 * @param a : graph
	 * @return boolean
	 */
	Boolean isRamseyGraph(int[][] a) {
		int sum = 0;
		
		//separate the graphs of different colour
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
			if (!isTraingleFree(b))
				sum++;
		}
		
		//check if it is ramsey graph
		if (sum == 1)
			return true;
		return false;
	}

	/**
	 * Checks if is triangle free.
	 *
	 * @param a the graph
	 * @return boolean
	 */
	Boolean isTraingleFree(int[][] a) {
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
		
		//triangle free if trace = 0
		if (trace == 0)
			return true;
		else
			return false;
	}
}
