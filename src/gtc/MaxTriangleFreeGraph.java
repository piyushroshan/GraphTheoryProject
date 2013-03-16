package gtc;

// TODO: Auto-generated Javadoc
/**	
 *	MaxTriangleFreeGraph.java
 *
 * 	@Instititution : National Institute of Technology Calicut
 * 	@Date_Start	: 22nd February, 2013
 * 	@Date_End	: 9th March, 2013
 * 	@Number_of_days_worked_on	:	8
 * 	@Author	:	Roshan Piyush
 * 				Pooja Prajod
 *  
 *	Purpose	:	To find the maximal triangle free connected graphs containing n vertices
 * 
 *	References	:	http://en.wikipedia.org/wiki/Triangle-free_graph
 *					http://math.stackexchange.com/questions/117024/complexity-of-counting-the-number-of-triangles-of-a-graph
 *					http://hog.grinvin.org/MTF
 *					http://www.combinatorics.org/ojs/index.php/eljc/article/view/v19i4p36/pdf
 * 
 */

public class MaxTriangleFreeGraph {
	
	/** The n. */
	int n;
	
	/** The max. */
	final int MAX = 10;
	
	/** The maxf. */
	final int MAXF = 10000;
	
	/** The tf. */
	int[][][] tf = new int[MAXF][MAX][MAX];
	
	/** The c tf. */
	int cTF;
	
	/** The filename. */
	final String filename = "maxTriangleFreeGraph.txt";
	
	/** The isomrphc. */
	Isomorphism isomrphc;
	
	/** The file out. */
	FileOutput fileOut;
	
	
	/**
	 * Instantiates a new max triangle free graph.
	 *
	 * @param n the n
	 */
	public MaxTriangleFreeGraph(int n) {
		this.n = n;
		cTF = 0;
		isomrphc = new Isomorphism(n);
		fileOut = new FileOutput(filename);
		fileOut.writeTextFile("Maximal-Triangle-Free Graphs");
		call();	
	}

	/**
	 * Call.
	 */
	void call() {
		int[][] a = new int[n][n];
		int[] vSet = new int[n];
		a[0][1] = 1;
		a[1][0] = 1;
		vSet[1] = 1;
		vSet[2] = 1;
		addEdge(a, vSet, 2);
		fileOut.writeTextFile("No of Maximal-Triangle-Free Graphs of "+ n +" vertices = "+ cTF);
		System.out.println("No of Maximal-Triangle-Free Graphs of "+ n +" vertices = "+ cTF);
	}

	/**
	 * Adds the edge.
	 *
	 * @param a the a
	 * @param vSet the v set
	 * @param cV the c v
	 */

	void addEdge(int[][] a, int[] vSet, int cV) {
		boolean flag = true;;
		if (cV == n) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (i != j && a[i][j] == 0 && vSet[i] == 1 && vSet[j] == 1) {
						int[][] aCopy = new int[n][n];
						for (int ii = 0; ii < n; ii++) {
							System.arraycopy(a[ii], 0, aCopy[ii], 0, n);
						}
						aCopy[i][j] = 1;
						aCopy[j][i] = 1;
						if (isTriangleFree(aCopy, n)) {
							flag = false;
							int[] vSetCopy = new int[n];
							System.arraycopy(vSet, 0, vSetCopy, 0, vSetCopy.length);
							addEdge(aCopy, vSetCopy, cV);
						} 
					}
				}
			}
			if (flag) {
				addGraph(a, n);
			}
		}
	
		if (cV < n) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < i; j++) {
					if (i != j
							&& a[i][j] == 0
							&& ((vSet[i] == 0 && vSet[j] == 1) || (vSet[i] == 1 && vSet[j] == 0))) {
						int[][] aCopy = new int[n][n];
						for (int ii = 0; ii < n; ii++) {
							System.arraycopy(a[ii], 0, aCopy[ii], 0, n);
						}
						aCopy[i][j] = 1;
						aCopy[j][i] = 1;
						int oldi=vSet[i];
						int oldj=vSet[j];
						vSet[i] = 1;
						vSet[j] = 1;

						if (isTriangleFree(aCopy, n)) {
							int[] vSetCopy = new int[n];
							System.arraycopy(vSet, 0, vSetCopy, 0, vSetCopy.length);
							addEdge(aCopy, vSetCopy, cV + vSet[i] - oldi + vSet[j] - oldj);
						}
						
						vSet[i] = oldi;
						vSet[j] = oldj;
					}
				}
		}
	}

	/**
	 * Adds the graph.
	 *
	 * @param a the a
	 * @param n the n
	 */
	void addGraph(int[][] a, int n) {
		if(cTF == 0)	{
			for (int i = 0; i < n; i++) {
				System.arraycopy(a[i], 0, tf[cTF][i], 0, n);
			}
			cTF++;
			fileOut.writeTextFile(a, n);
			return;
		}
		
		for (int c = 0; c < cTF; c++) {
			int sum=0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if(tf[c][i][j]==a[i][j])
						sum++;
				}
			}
			if(sum==(n*n-n)/2){
					return;
			}
		}
		
		for (int c = 0; c < cTF; c++) {
			int cTf=0, cA=0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if(tf[c][i][j]==1)
						cTf++;
					if(a[i][j]==1)
						cA++;
				}	
			}
			if(cTf!=cA){
				continue;
			}
			if(isomrphc.isIsomorphic(a, tf[c]))
				return;
		}
		for (int i = 0; i < n; i++) {
			System.arraycopy(a[i], 0, tf[cTF][i], 0, n);
		}
		cTF++;
		fileOut.writeTextFile(a, n);
		return;
	}

	/**
	 * Checks if is triangle free.
	 *
	 * @param a the a
	 * @param n the n
	 * @return the boolean
	 */
	Boolean isTriangleFree(int[][] a, int n) {
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

}
