package gtc.assignment.piyush;

public class TraingleFree {
	
	public TraingleFree() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	
	Boolean istrainglefree(int[][] a, int n)
	{
		int i, j, k, sum;
		int[][] x = new int[n][n];
		int[][] y = new int[n][n];
		int trace = 0;
	    for(i=0;i<n;i++){ //row of first matrix
			for(j=0;j<n;j++){  //column of second matrix
				sum=0;
				for(k=0;k<n;k++)
					sum=sum+a[i][k]*a[k][j];
				x[i][j]=sum;
			}
		}
		for(i=0;i<n;i++){ //row of first matrix
			for(j=0;j<n;j++){  //column of second matrix
				sum=0;
				for(k=0;k<n;k++)
					sum=sum+x[i][k]*a[k][j];
				y[i][j]=sum;
			}
		}
		for(i=0;i<n;i++){
			trace+=y[i][i];
		}
		if(trace == 0)
			return true;
		else
			return false;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TraingleFree t =new TraingleFree();
		int[][] a  = {{0, 1, 1, 1}, 
		{ 1, 0, 1, 0 },
		{ 1, 1, 0, 0 },
		{ 1, 0, 0, 0}} ;
		
		System.out.println(t.istrainglefree(a, 4));
	}
	
}
