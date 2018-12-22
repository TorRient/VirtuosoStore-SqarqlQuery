package relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ReadFile.readFile;

public class RelationshipGen {
	private Random RANDOM = new Random();
	readFile readfile = new readFile();
	public List<String> setRelationshipDescriptionList(String fileName) {
		List<String> relationship = new ArrayList<String>();
		List<String> relationshipList = new ArrayList<String>();
		relationship = readfile.readerFile(fileName);
		for(int i = 0 ; i<relationship.size(); i++) {
			relationshipList.add(relationship.get(i).replace(" ", "_"));
		}
		return relationshipList;
	}
	
	public String genRandomRelationshipDescription(List<String> relationship) {
		return relationship.get(RANDOM.nextInt(relationship.size()));
	}
	
	public Relationship genRelationship(List<String> relationship) {
		return new Relationship(genRandomRelationshipDescription(relationship));
	}
}
