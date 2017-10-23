import java.io.IOException;

public class Compare {
	static boolean debug = false; // enable to test with default sample files in project

	public static void main(String[] args) throws IOException {

		String firstFilePath = "abc.txt";
		String secondFilePath = "def.txt";
		String synonymsFilePath = "synonyms.txt";

		int tupleSize = 3;

		// To run sample program
		if (args.length > 0 && args[0].compareTo("-sample") == 0) {
			debug = true;
		}

		if (!debug) {
			if (args.length < 3) {
				System.out.println("Usage : java compare synonymListFile firstFile secondFile tupleSize"
						+ "\n Tuple size is optional, default is 3");
				return;
			}

			firstFilePath = args[1];
			secondFilePath = args[2];
			synonymsFilePath = args[0];
			if (args.length > 3) {
				try {
					tupleSize = Integer.parseInt(args[3]);
				} catch (NumberFormatException e) {
					System.err.println("Tuple size must be a valid number");
					// e.printStackTrace();
					return;
				}
			}
		}
		if (tupleSize < 3) {
			System.out.println("Minimum tuple size is 3");
			return;
		}
		NTupleChecker checker = new NTupleChecker(firstFilePath, secondFilePath, synonymsFilePath, tupleSize);

		System.out.println("Similarity percentage : " + checker.getRatio() * 100 + " %");
	}
}
