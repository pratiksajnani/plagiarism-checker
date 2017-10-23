import java.io.*;
import java.util.*;
import java.util.regex.*;

// a buffered reader for reading words
public class WordReader extends BufferedReader {
	public WordReader(Reader reader) throws FileNotFoundException {
		super(reader);
	}

	public List<String> getWords() throws IOException {
		String line;
		List<String> words;
		boolean areValidWords = false;
		List<String> output = new LinkedList<String>();
		while (!areValidWords && (line = super.readLine()) != null ) {
			words = parseLine(line);
			if (words.size() > 1) {
				output.addAll(words);
				areValidWords = true;
			} else {
				areValidWords = false;
			}
		}
		return output.size() > 0 ? output : null;
	}

	static List<String> parseLine(String line) {
		Pattern pattern = Pattern.compile("[\\w]+");
		Matcher matcher = pattern.matcher(line);
		List<String> words = new ArrayList<String>();
		while (matcher.find()) {
			words.add(line.substring(matcher.start(), matcher.end()));
		}
		return words;
	}
}
