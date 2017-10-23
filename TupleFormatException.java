
@SuppressWarnings("serial")
public class TupleFormatException extends ClassCastException {
	int size1;
	int size2;
	
	TupleFormatException(int size1, int size2) {
		this.size1 = size1;
		this.size2 = size2;
	}
	String getSizes() {
		return String.format("{0} and {1}", size1, size2);
	}
	
}
