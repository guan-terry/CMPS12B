import java.io.*;
import java.util.*;

public class Bard {
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage: Java - jar Nqueens.jar <input file> <output file>");
			System.exit(1);
		}
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));
		Scanner text = new Scanner(new File("shakespeare.txt"));
		Hashtable<String, Integer> wordFreq = new Hashtable<String, Integer>();
		while (text.hasNextLine()) {
			String line = text.nextLine().trim();
			line = replaceToWhiteSpace(line).trim();
			while (!line.isEmpty()) {
				putWordsIn(wordFreq, line);
				if (line.indexOf(' ') == -1) {
					line = "";
				} else {
					line = line.substring(line.indexOf(' ') + 1).trim();
				}
			}
		}

		while (in.hasNextLine()) {
			String newLine = in.nextLine().trim();
			int firstSpace = newLine.indexOf(' ');
			int lengthOfWord = Integer.parseInt(newLine.substring(0, firstSpace).trim());
			int frequency = Integer.parseInt(newLine.substring(firstSpace + 1).trim());
			ArrayList<Word> wordLength = putIntoArrayList(wordFreq, lengthOfWord);
			Collections.sort(wordLength, new SortByWord());
			Collections.sort(wordLength, new SortByLength());
			if (frequency >= wordLength.size() || wordLength.size() == 0) {
				out.println("-");
				continue;
			}
			out.println(wordLength.get(frequency).str);
			// System.out.println(wordLength.get(frequency).str.length() == lengthOfWord);
			// System.out.println(wordLength.get(frequency).str);
			// System.out.println(lengthOfWord);
		}

		in.close();
		out.close();
		text.close();
	}

	// REMEBER TO PUT THIS IN THE README
	public static Hashtable<String, Integer> putWordsIn(Hashtable<String, Integer> h, String str) {
		int Space = str.indexOf(' ');
		String word;
		if (Space == -1) {
			word = str.substring(0).toLowerCase();
		} else {
			word = str.substring(0, Space).toLowerCase();
		}
		if (h.containsKey(word)) {
			h.put(word, h.get(word) + 1);
		} else {
			h.put(word, 1);
		}

		return h;
	}

	public static ArrayList<Word> putIntoArrayList(Hashtable<String, Integer> wordFrequency, int size) {
		Set<String> keys = wordFrequency.keySet();
		Iterator<String> itr = keys.iterator();
		ArrayList<Word> wordLength = new ArrayList<Word>();
		while (itr.hasNext()) {
			String str = itr.next();
			if (str.length() == size)
				wordLength.add(new Word(str, wordFrequency.get(str)));
		}
		return wordLength;
	}

	public static String replaceToWhiteSpace(String s) {
		s = s.replace('?', ' ');
		s = s.replace('.', ' ');
		s = s.replace(',', ' ');
		s = s.replace('!', ' ');
		s = s.replace(':', ' ');
		s = s.replace(';', ' ');
		s = s.replace('[', ' ');
		s = s.replace(']', ' ');

		return s;
	}
}
