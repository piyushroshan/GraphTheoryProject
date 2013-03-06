

class trainglefree{
int a[3][3] = {{0, 1, 0},
			  {1, 0, 1},
			  {0, 1, 0}};
int istrainglefree(int a[3][3], int n)
{
	int i, j, k, sum, x[n][n], y[n][n];
	int trace = 0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
           x[i][j]=0;
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
		return 1;
	else
		return 0;
}

public static void main(){
	System.out.println(istrainglefree(a,3)+"\n");
}
}