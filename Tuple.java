import java.util.*;

// a general tuple class that can use a synonyms map to match objects of its type

public class Tuple<T extends Comparable<? super T>> implements Comparable<Tuple<T>> {
	boolean isMatched;
	int length;
	private List<T> values;

	Tuple(List<T> data) {
		values = new ArrayList<T>();
		values.addAll(data);
		length = data.size();
	}
	
	Tuple(T [] data) {
		values = Arrays.asList(data);
		length = data.length;
	}

	public boolean matchWith(Tuple<T> that, Map<T, Set<T>> synonymsMap) throws TupleFormatException {
		if (this.length != that.length) {
			throw new TupleFormatException(this.length, that.length);
		}
		for (int i = 0; i < length; i++) {
			T first = this.values.get(i);
			T second = that.values.get(i);
			if (first.compareTo(second) != 0) {
				if (!synonymsMap.containsKey(first)) {
					return false;
				} else {
					if (!synonymsMap.get(first).contains(second)) {
						return false;
					}
				}
			}
		}
		//System.out.println("Matched " + this + " with " + that);
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		int i = 0;
		for (; i < values.size() - 1; i++) {
			sb.append(values.get(i).toString() + ", ");
		}
		sb.append(values.get(i).toString());
		sb.append(")");
		return sb.toString();
	}

	// for exact comparison without using synonyms map
	@Override
	public int compareTo(Tuple<T> that) throws TupleFormatException {
		if (this.length != that.length) {
			throw new TupleFormatException(this.length, that.length);
		}
		for (int i = 0; i < length; i++) {
			if (this.values.get(i) != that.values.get(i)) {
				return -1;
			}
		}
		//System.out.println("Matched " + this + " with " + that);
		return 0;
	}
}
