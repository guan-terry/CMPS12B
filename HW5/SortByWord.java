import java.util.*;

public class SortByWord implements Comparator<Word> {
	public int compare(Word w1, Word w2) {
		return w1.str.compareTo(w2.str);
	}
}
