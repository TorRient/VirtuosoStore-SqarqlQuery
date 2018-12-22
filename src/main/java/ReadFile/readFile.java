package ReadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readFile {
	public ArrayList<String> readerFile(String fileName) {
		ArrayList<String> listText = new ArrayList<String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			listText.add(scanner.nextLine());
		}
		return listText;
	}
}
