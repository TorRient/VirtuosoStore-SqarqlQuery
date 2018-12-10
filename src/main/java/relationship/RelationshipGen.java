package relationship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RelationshipGen {
	private static final Random RANDOM = new Random();
	public List<String> setRelationshipDescriptionList(String fileName) {
		List<String> relationshipDescriptionList = new ArrayList<String>();
		Scanner scanner = null;
		relationshipDescriptionList.clear();
		try {
			scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()) {
				relationshipDescriptionList.add(scanner.nextLine().replace(" ", "_"));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relationshipDescriptionList;
	}
	
	public String genRandomRelationshipDescription(List<String> relationship) {
		return relationship.get(RANDOM.nextInt(relationship.size()));
	}
	
	public Relationship genRelationship(List<String> relationship) {
		return new Relationship(genRandomRelationshipDescription(relationship));
	}
}
