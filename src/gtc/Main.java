package gtc;

/**
 * Main.java
 * 
 * @Instititution : National Institute of Technology Calicut
 * @Date_Start : 12th March, 2013
 * @Date_End : 13th March, 2013
 * @Number_of_days_worked_on : 1
 * @Author : Roshan Piyush 
 * 			 Pooja Prajod
 * 
 * Purpose : To help interact with user
 * 
 * References : http://docs.oracle.com/javase/tutorial/
 * 
 */

public class Main {

	/**
	 * @param args
	 *            the commandline input
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int n;
		String usage = "Invalid arguments\nUsage instructions :\n"
				+ "\tfilename.jar <options> <n>\n"
				+ "\t<options>\n"
				+ "\t\t-a : To compute both Ramsey Graphs and Maximal-Triangle-Free Graphs\n"
				+ "\t\t-r : To compute Ramsey Graphs\n"
				+ "\t\t-t : to compute Maximal-Triangle-Free Graphs\n" + "\n"
				+ "\t<n>\t   : No. of vertices";
		try {
			if (args.length > 0 && Integer.parseInt(args[1]) > 0) {
				n = Integer.parseInt(args[1]);
				if (args[0].equals("-a")) {

					MaxTriangleFreeGraph g = new MaxTriangleFreeGraph(n);
					RamseyGraph r = new RamseyGraph(n);
				} else if (args[0].equals("-r")) {
					RamseyGraph r = new RamseyGraph(n);
				} else if (args[0].equals("-t")) {
					MaxTriangleFreeGraph g = new MaxTriangleFreeGraph(n);
				}
			} else {
				System.out.println(usage);

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
