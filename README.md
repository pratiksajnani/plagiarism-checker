The following text details code structure, my design decisions and theoretical as well as practical assumptions around the problem

----------
HOW TO RUN
----------

Compile : javac *.java
Run : java Compare synonymsListFile firstFile secondFile tupleSize
Run Sample : java Compare -sample
Clean : rm *.class

NOTE1 : Tuple size is optional and defaults to 3

NOTE2 : "C" in compare is capital

-----------
TERMINOLOGY
-----------

Base File - The file we are checking against. It is the "secondFile" in command-line arguments

Candidate File - The file we're checking for plagiarism. It is the "firstFile" in command-line arguments

Tuple - A set of word values stored as string. Example - ("a", "tuple", "is", "useful")

-----------
ASSUMPTIONS
-----------

1. Files are not extremely large(~10 mb takes a while)
2. Special characters are not to be compared(only alphanumeric characters are compared)

--------------
FILE STRUCTURE
--------------

Compare.java - main file to run the program, does not contain much code because I like it that way

NTupleChecker.java - the class where the matching is done and match ratio is computed

InputReader.java - A wrapper class that loads synonyms and the other two files as tuple sets for NTupleChecker to use. Also validates input files based on file access and length

TupleReader.java - A reader that reads contiguous tuples from a file using an intermediate queue and a WordReader class(mentioned below)

WordReader.java - A reader that reads words that only contain alphanumeric characters using BufferedReader

Tuple.java - A generic comparable tuple class that can use a synonyms map and has a string representation

TupleFormatException.java - An exception thrown when two tuples of different sizes are compared

----------
EFFICIENCY
----------

1. If there are M words in first file and N words in second file the algorithm runs in O(MN) time

2. A better way would be to store hashes of tuples in-memory and compare them

3. The base tuples file could be pre-loaded

---------------
IN-MEMORY SPACE
---------------
1. All candidate tuples are stored till the end, they could be dropped after comparison as we only care about the number of matches, not matches themselves

2. There doesn't seem to be any such workaround for base tuples


---------------------
EXTENSIBILITY & REUSE
---------------------

1. The checker itself can be extended and implemented in any other way
2. The Tuple, WordReader and TupleReader classes are generic utilities that could be reused
