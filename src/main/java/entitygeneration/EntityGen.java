package entitygeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import readfile.ReadFile;

public class EntityGen {
	ReadFile readFile = new ReadFile();
	private static final Random RANDOM = new Random();
	private static List<String> extractedLinkList = new ArrayList<String>();
	private static List<String> extractedDateList = new ArrayList<String>();

	public void setExtractedDateList(String fileName) {
		extractedDateList = readFile.readerFile(fileName);
	}

	public void setExtractedLinkList(String fileName) {
		extractedLinkList = readFile.readerFile(fileName);
	}

	public String generateRandomExtractedLink() {
		return extractedLinkList.get(RANDOM.nextInt(extractedLinkList.size()));
	}

	public String generateRandomExtractedDate() {
		return extractedDateList.get(RANDOM.nextInt(extractedDateList.size()));
	}

	public Random getRandom() {
		return RANDOM;
	}
}
