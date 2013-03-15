package gtc.assignment.piyush;

/*	
 *	FileOutput.java
 * 
 * 	@Author	:	Roshan Piyush
 * 				Pooja Prajod
 *  
 *	Purpose:	To print content to a file
 * 
 * 
 * 
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileOutput {
	String filename;
	private PrintWriter writer = null;

	public FileOutput(String filename) {
		this.filename = filename;
		try {
			writer = new PrintWriter(filename);
			System.out.println("Created\t\"" + filename + "\" sucessfully");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not create\t" + filename);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void writeTextFile(String s) {
		writer.println(s);
	}

	public void writeTextFile(int[][] a, int n) {
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
	}

	public void closeFile(){
		writer.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		FileOutput f = new FileOutput("ramseyGraph.txt");
		f.writeTextFile("hellvhmhjvmhco");
		int a[][] = { { 1, 2, 3 }, { 6, 7, 8 }, { 3, 4, 5 } };
		f.writeTextFile(a, 3);
		f.writeTextFile(a, 3);
		f.closeFile();
		f.writeTextFile("skkjsakc");
	}
}
