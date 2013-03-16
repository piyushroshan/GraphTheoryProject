/**
 * 
 */
package gtc;

/**
 * @author R0$H4N
 *
 */
public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		String usage = "Invalid arguments\nUsage instructions :\n" +
				"\tfilename.jar <options> <n>\n" +
				"\t<options>\n" +
				"\t\t-a : To compute both Ramsey Graphs and Maximal-Traingle-Free Graphs\n" +
				"\t\t-r : To compute Ramsey Graphs\n" +
				"\t\t-t : to compute Maximal-Traingle-Free Graphs\n" +
				"\n" +
				"\t<n>\t   : No. of vertices";
		if( args.length > 0){
			if ( args[0].equals("-a")){
				n=Integer.parseInt(args[1]);
				MaxTraingleFreeGraph g = new MaxTraingleFreeGraph(n);
				RamseyGraph r = new RamseyGraph(n);
			}
			else if ( args[0].equals("-r")){
				n=Integer.parseInt(args[1]);
				RamseyGraph r = new RamseyGraph(n);
			}
			else if ( args[0].equals("-t")){
				n=Integer.parseInt(args[1]);
				MaxTraingleFreeGraph g = new MaxTraingleFreeGraph(n);
			}
		} else {
			System.out.println(usage);
							
		}
	}

}
