import java.util.Comparator;

public class SortByLength implements Comparator<Word> {
	
	public int compare(Word w1, Word w2) {
		return w2.frequency - w1.frequency;
	}
	
}
