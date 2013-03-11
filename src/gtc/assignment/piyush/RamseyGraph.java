package gtc.assignment.piyush;


public class RamseyGraph {
	int n;
	int MAXE;
	int MAXC;
	final int MAX = 10;
	final int MAXF = 10000;
	int[][][] tf = new int[MAXF][MAX][MAX];
	int ram_c;
	permatrix isomrphc;
	public RamseyGraph(int x) {
		n = x;
		ram_c = 0;
		isomrphc = new permatrix(n);
		call();	
	}

	void call() {
		int[][] a = new int[MAX][MAX];
		int[][] b = new int[n][n];
		MAXC = (int) Math.floor((n-1)/2);
		System.out.println(MAXC);
		MAXE = (n*n-n)/2;
		for(int C=1;C<=MAXC;C++){
			
			for (int ii = 0; ii < n; ii++) {
				System.arraycopy(a[ii], 0, b[ii], 0, n);
			}
			b[0][1] = C;
			b[1][0] = C;
		
			addedge(b, 1);
		}
		printtf();
	}

	/**
	 * @param args
	 */

	void addedge(int[][] a, int e) {
		int i, j, flag = 0;
		if (e == MAXE) {
			
			if(isRamseyGraph(a))
				addGraph(a);
			
		}
		
		if (e < MAXE) {
			for (i = 0; i < n; i++)
				for (j = 0; j < i; j++) {
					if (i != j && a[i][j] == 0) {
						for (int C = 1; C <= MAXC; C++){
							int[][] b = new int[n][n];
							for (int ii = 0; ii < n; ii++) {
								System.arraycopy(a[ii], 0, b[ii], 0, n);
							}
							b[i][j] = C;
							b[j][i] = C;
							addedge(b,e+1);
						}
					}
				}
		}
	}

	void addGraph(int[][] a) {
		int c;
		if(ram_c==0)	{
				for (int ii = 0; ii < n; ii++) {
					System.arraycopy(a[ii], 0, tf[ram_c][ii], 0, n);
				}
				ram_c++;
				return;
		}
		
		for (c = 0; c < ram_c; c++) {
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
		
		for (c = 0; c < ram_c; c++) {
			int tf_c=0, a_c=0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if(tf[c][i][j]==1)
						tf_c++;
					if(a[i][j]==1)
						a_c++;
				}	
			}
			if(tf_c!=a_c){
				continue;
			}
			if(isomrphc.isomorphic(a, tf[c])==0)
				return;
		}
		for (int ii = 0; ii < n; ii++) {
			System.arraycopy(a[ii], 0, tf[ram_c][ii], 0, n);
		}
		ram_c++;
		return;
	}
	
	Boolean isRamseyGraph(int[][] a){
		int sum=0;
		for(int C = 1;C <= MAXC; C++){
			int[][] b = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(a[i][j] == C){
						b[i][j]=C;
					}
				}
			}
			if(!istrainglefree(b))
				sum++;
		}
		if(sum==1)
			return true;
		return false;
	}
	
	Boolean istrainglefree(int[][] a) {
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

	void printgraph(int[][] a, String s){
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
	void printtf() {
		int i, j, k;
		for (k = 0; k < ram_c; k++) {
			System.out.print(" | ");
			for (i = 0; i < n; i++)
				System.out.print(i + 1 + " ");
			System.out.println();
			for (i = 0; i < n; i++) {
				System.out.print(i + 1 + "| ");
				for (j = 0; j < n; j++) {
					System.out.print(tf[k][i][j] + " ");
				}
				System.out.println("");
			}
		}
		System.out.print("No of Ramsey graphs: " + ram_c + "\n");
	}

	public static void main(String[] args) {
		RamseyGraph g = new RamseyGraph(5);
	}
}
