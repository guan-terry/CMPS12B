import java.io.*;
import java.util.*;

public class FileReverse {
	public static String stringReverse (String s) {
		String newString = "";
		for(int i = s.length() - 1; i >= 0; i--) {
			newString += s.charAt(i);
		}
		return newString;
	}
	public static void main(String[] args) throws IOException {
		int LineNumber = 0;
		
		//Checks if there are more than 2 command line arguments
		if (args.length < 2) {
			System.out.println("Usage: Java -jar FileReverse <Input File>" +
			" <output File>");
			System.exit(1);
		}
		
		//open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		//read lines from in, extracts and print tokens backwards from each line
		while(in.hasNextLine()) {
			LineNumber++;
			
			String line = in.nextLine().trim() + " ";
			String[]token = line.split("\\s+");
			
			int n = token.length;
			for(int i = 0;i < n ; i++) {
				out.println(" " + stringReverse(token[i]));
			}
		}
		in.close();
		out.close();
	}
}