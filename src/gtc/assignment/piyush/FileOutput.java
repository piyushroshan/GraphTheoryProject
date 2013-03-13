package gtc.assignment.piyush;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOutput {
	final static String filename = "ramseyGraph.txt";

	public void writeTextFile(String fileName, String s) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename);

			writer.println(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public void writeTextFile(String fileName, int[][] a, int n) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename);
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		FileOutput f = new FileOutput();
		f.writeTextFile(filename, "hellvhmhjvmhco");
		int a[][] = { { 1, 2, 3 }, { 6, 7, 8 }, { 3, 4, 5 } };
		f.writeTextFile(filename, a, 3);
		f.writeTextFile(filename, a, 3);
	}
}
