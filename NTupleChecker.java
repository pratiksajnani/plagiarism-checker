import java.io.*;
import java.util.*;

public class NTupleChecker {

	InputReader reader = null;

	public NTupleChecker(String candidateFilePath, String baseFilePath, String synonymsFilePath, int tupleSize) {
		reader = InputReader.create(candidateFilePath, baseFilePath, synonymsFilePath, tupleSize);
	}

	public double getRatio() throws IOException {

		if (reader == null) {
			return -1;
		}

		List<Tuple<String>> baseTuples = reader.getBaseTuples();
		List<Tuple<String>> candidateTuples = reader.getCandidateTuples();

		long matches = compare(candidateTuples, baseTuples);

		long totalTuples = candidateTuples.size();

		System.out.println("Matches : " + matches);
		System.out.println("Total tuples : " + totalTuples);

		return (double) matches / totalTuples;
	}

	// read file line by line and compare
	private long compare(List<Tuple<String>> candidateTuples, List<Tuple<String>> baseTuples) throws IOException {
		Map<String, Set<String>> synonymsMap = reader.generateSynonymsMap();
		long matches = 0;
		Set<Integer> matchedCandidates = new HashSet<Integer>(); // to prevent matching many times
		for (int i = 0; i < candidateTuples.size(); i++) {
			for (Tuple<String> base : baseTuples) {
				if(!matchedCandidates.contains(i)) {
					if (candidateTuples.get(i).compareTo(base) == 0) {
						matchedCandidates.add(i);
						matches++;
					} else if (synonymsMap != null && candidateTuples.get(i).matchWith(base, synonymsMap)) {
						matchedCandidates.add(i);
						matches++;
					}
				}
			}
		}
		return matches;
	}
}
