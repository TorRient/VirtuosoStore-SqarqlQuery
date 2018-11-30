package relationship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RelationshipGeneration {
	private static final Random RANDOM = new Random();
	private static List<String> relationshipDescriptionList = new ArrayList<String>();
	
	public void setRelationshipDescriptionList(String fileName) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()) {
				relationshipDescriptionList.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String generateRandomRelationshipDescription() {
		return relationshipDescriptionList.get(RANDOM.nextInt(relationshipDescriptionList.size()));
	}
	
	public Relationship generateRelationship() {
		return new Relationship(generateRandomRelationshipDescription());
	}
}
