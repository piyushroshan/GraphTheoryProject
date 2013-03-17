package gtc;

/**	
 *	FileOutput.java
 * 
* 	@Instititution : National Institute of Technology Calicut
 * 	@Date_Start	: 7th March, 2013
 * 	@Date_End	: 8th March, 2013
 * 	@Number_of_days_worked_on	:	1
 * 	@Author	:	Roshan Piyush
 * 				Pooja Prajod
 * 
 *	Purpose	:	To print content to a file.
 * 	
 * 	References : http://docs.oracle.com/javase/tutorial/
 * 
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Class FileOutput.
 */
public class FileOutput {
	
	/** The output filename. */
	String filename;
	
	private PrintWriter writer = null;

	/**
	 * Instantiates a new file output.
	 *
	 * @param filename the output file name
	 */
	public FileOutput(String filename) {
		this.filename = filename;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(filename,false)));
			System.out.println("Created\t\"" + filename + "\" sucessfully");
		} catch (IOException e) {
			System.out.println("Could not create\t" + filename);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			writer.close();
		}
		
	}

	/**
	 * Printing string to file.
	 *
	 * @param s the output string to print
	 */
	public void writeTextFile(String s) {
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
			writer.println(s);
		} catch (IOException e) {
			System.out.println("Could not write to\t\"" + filename + "\"");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}

	/**
	 * Printing graph to file.
	 *
	 * @param a the graph to print
	 * @param n the number of vertices in graph
	 */
	public void writeTextFile(int[][] a, int n) {
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
			writer.print("_|_");
			for (int i = 0; i < n; i++)
				writer.print(i + 1 + "_");
			writer.println();
			for (int i = 0; i < n; i++) {
				writer.print(i + 1 + "| ");
				for (int j = 0; j < n; j++) {
					writer.print(a[i][j] + " ");
				}
				writer.println();
			}
			writer.println();
		} catch (IOException e) {
			System.out.println("Could not write to\t\"" + filename + "\"");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			writer.close();
		}
		
	}

	/**
	 * Close file.
	 */
	public void closeFile(){
		writer.close();
	}
}
