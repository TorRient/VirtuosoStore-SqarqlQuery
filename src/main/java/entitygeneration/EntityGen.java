package entitygeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class EntityGen {
	private static final Random RANDOM = new Random();
	private static List<String> extractedLinkList = new ArrayList<String>();
	private static List<String> extractedDateList = new ArrayList<String>();

	public void setExtractedDateList(String fileName) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (scanner.hasNextLine()) {
			extractedDateList.add(scanner.nextLine());
		}
	}

	public void setExtractedLinkList(String fileName) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (scanner.hasNextLine()) {
			extractedLinkList.add(scanner.nextLine());
		}
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
