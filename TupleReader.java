import java.io.*;
import java.util.*;

// a queue-based reader to read tuples from words
public class TupleReader extends WordReader {
	int tupleSize;
	List<String> wordList;
	List<String> values;

	public TupleReader(Reader reader, int tupleSize) throws FileNotFoundException {
		super(reader);
		wordList = new ArrayList<String>();
		this.tupleSize = tupleSize;
	}

	public Tuple<String> getTuple() throws IOException {
		List<String> newWords = null;
		if (wordList.size() < tupleSize) {
			if ((newWords = super.getWords()) != null) {
				wordList.addAll(newWords);
			}
		}
		if (wordList.size() < tupleSize) {
			return null;
		}

		Tuple<String> tuple = new Tuple<String>(wordList.subList(0, tupleSize));
		wordList.remove(0);

		return tuple;
	}
}
