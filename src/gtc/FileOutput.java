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

public class FileOutput {
	String filename;
	private PrintWriter writer = null;

	public FileOutput(String filename) {
		this.filename = filename;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(filename,false)));
			System.out.println("Created\t\"" + filename + "\" sucessfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not create\t" + filename);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			writer.close();
		}
		
	}

	public void writeTextFile(String s) {
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
			writer.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not write to\t\"" + filename + "\"");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}

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
			// TODO Auto-generated catch block
			System.out.println("Could not write to\t\"" + filename + "\"");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			writer.close();
		}
		
	}

	public void closeFile(){
		writer.close();
	}
}
