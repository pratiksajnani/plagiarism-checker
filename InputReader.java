import java.io.*;
import java.util.*;

// wrapper class for handling input files
public class InputReader {

	static File synonymsFile;
	static File candidateFile;
	static File baseFile;
	static int tupleSize;

	static InputReader instance = null;

	private InputReader(String candidateFilePath, String baseFilePath, String synonymsFilePath, int size) {
		candidateFile = new File(candidateFilePath);
		baseFile = new File(baseFilePath);
		synonymsFile = new File(synonymsFilePath);
		tupleSize = size;
	}

	public static InputReader create(String candidateFilePath, String baseFilePath, String synonymsFilePath,
			int tupleSize) {
		instance = new InputReader(candidateFilePath, baseFilePath, synonymsFilePath, tupleSize);
		if(instance.validateFiles()) {
			return instance;
		}
		return null;
	}

	
	public Map<String, Set<String>> generateSynonymsMap() throws IOException {
		WordReader reader = new WordReader(new FileReader(synonymsFile));
		Map<String, Set<String>> synonymsMap = new HashMap<String, Set<String>>();
		List<String> newWords = null;
		while ((newWords = reader.getWords()) != null) {
			for (String word : newWords) {
				synonymsMap.put(word, new HashSet<String>(newWords));
			}
		}
		reader.close();
		return synonymsMap;
	}

	public List<Tuple<String>> getCandidateTuples() throws IOException {
		return getAllTuples(candidateFile);
	}

	public List<Tuple<String>> getBaseTuples() throws IOException {
		return getAllTuples(baseFile);
	}

	public List<Tuple<String>> getAllTuples(File file) throws IOException {
		TupleReader reader = new TupleReader(new FileReader(file), tupleSize);
		List<Tuple<String>> tuples = new ArrayList<Tuple<String>>();
		Tuple<String> tuple = null;
		while ((tuple = reader.getTuple()) != null) {
			tuples.add(tuple);
		}
		reader.close();
		return tuples;
	}
	// TODO - can throw custom exceptions
	protected boolean validateFiles() {
		if (!candidateFile.canRead() || candidateFile.length() == 0) {
			System.err.println("First File(Candidate file) corrupted or empty");
			return false;
		} else if (!baseFile.canRead() || baseFile.length() == 0) {
			System.err.println("Second File(Base file) corrupted or empty");
			return false;
		} else if (!synonymsFile.canRead() || synonymsFile.length() == 0) {
			System.err.println("Synonyms File corrupted or empty");
			return false;
		} else if (tupleSize > candidateFile.length()) {
			System.err.println("Tuple size too big");
			return false;
		} else if (tupleSize < 2) {
			System.err.println("Tuple size too small");
		}
		return true;
	}

}
