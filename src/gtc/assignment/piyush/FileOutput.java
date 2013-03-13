package gtc.assignment.piyush;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileOutput {
	String filename;
	PrintWriter writer = null;

	public FileOutput(String filename) {
		this.filename = filename;
		try {
			writer = new PrintWriter(filename);
			System.out.println("created\t" + filename + "\t sucessfully");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("could not create\t" + filename);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void writeTextFile(String s) {
		writer.println(s);
	}

	public void writeTextFile(int[][] a, int n) {
		writer.print(" | ");
		for (int i = 0; i < n; i++)
			writer.print(i + 1 + " ");
		writer.println();
		for (int i = 0; i < n; i++) {
			writer.print(i + 1 + "| ");
			for (int j = 0; j < n; j++) {
				writer.print(a[i][j] + " ");
			}
			writer.println("");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		FileOutput f = new FileOutput("ramseyGraph.txt");
		f.writeTextFile("hellvhmhjvmhco");
		int a[][] = { { 1, 2, 3 }, { 6, 7, 8 }, { 3, 4, 5 } };
		f.writeTextFile(a, 3);
		f.writeTextFile(a, 3);
		f.writer.close();
	}
}
