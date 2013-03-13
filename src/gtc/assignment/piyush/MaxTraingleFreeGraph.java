package gtc.assignment.piyush;


public class MaxTraingleFreeGraph {
	int n;
	final int MAX = 10;
	final int MAXF = 10000;
	int[][][] tf = new int[MAXF][MAX][MAX];
	int cTF;
	Isomorphism isomrphc;
	public MaxTraingleFreeGraph(int x) {
		n = x;
		cTF = 0;
		isomrphc = new Isomorphism(n);
		call();	
	}

	void call() {
		int[][] a = new int[n][n];
		int[] vSet = new int[n];
		a[0][1] = 1;
		a[1][0] = 1;
		vSet[1] = 1;
		vSet[2] = 1;
		addedge(a, vSet, 2);
		printTF();
	}

	/**
	 * @param args
	 */

	void addedge(int[][] a, int[] vSet, int cN) {
		boolean flag = true;;
		if (cN == n) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (i != j && a[i][j] == 0 && vSet[i] == 1 && vSet[j] == 1) {
						int[][] aCopy = new int[n][n];
						for (int ii = 0; ii < n; ii++) {
							System.arraycopy(a[ii], 0, aCopy[ii], 0, n);
						}
						aCopy[i][j] = 1;
						aCopy[j][i] = 1;
						if (istrainglefree(aCopy, n)) {
							flag = false;
							int[] vSetCopy = new int[n];
							System.arraycopy(vSet, 0, vSetCopy, 0, vSetCopy.length);
							addedge(aCopy, vSetCopy, cN);
						} 
					}
				}
			}
			if (flag) {
				addGraph(a, n);
			}
		}
	
		if (cN < n) {
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

						if (istrainglefree(aCopy, n)) {
							int[] vSetCopy = new int[n];
							System.arraycopy(vSet, 0, vSetCopy, 0, vSetCopy.length);
							addedge(aCopy, vSetCopy, cN + vSet[i] - oldi + vSet[j] - oldj);
						}
						
						vSet[i] = oldi;
						vSet[j] = oldj;
					}
				}
		}
	}

	void addGraph(int[][] a, int n) {
		int c;
		if(cTF == 0)	{
			for (int i = 0; i < n; i++) {
				System.arraycopy(a[i], 0, tf[cTF][i], 0, n);
			}
			cTF++;
			return;
		}
		
		for (c = 0; c < cTF; c++) {
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
		
		for (c = 0; c < cTF; c++) {
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
		return;
	}

	Boolean istrainglefree(int[][] a, int n) {
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

	void printTF() {
		for (int c = 0; c < cTF; c++) {
			System.out.print(" | ");
			for (int i = 0; i < n; i++)
				System.out.print(i + 1 + " ");
			System.out.println();
			for (int i = 0; i < n; i++) {
				System.out.print(i + 1 + "| ");
				for (int j = 0; j < n; j++) {
					System.out.print(tf[c][i][j] + " ");
				}
				System.out.println("");
			}
		}
		System.out.print("No of Maximal-Traingle-Free graphs: " + cTF + "\n");
	}

	public static void main(String[] args) {
		MaxTraingleFreeGraph g = new MaxTraingleFreeGraph(5);
	}
}
