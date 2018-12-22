package entityGeneration;

import java.util.ArrayList;
import java.util.List;

import ReadFile.readFile;
import entity.Organization;

public class OrganizationGen extends EntityGen{
	readFile readfile = new readFile();
	private static List<String> labelList = new ArrayList<String>();
	private static List<String> descriptionList = new ArrayList<String>();
	private static List<String> headquarterList = new ArrayList<String>();
	
	public void setLabelList(String fileName) {
		labelList = readfile.readerFile(fileName);
	}
	
	public void setDescriptionList(String fileName) {
		descriptionList = readfile.readerFile(fileName);
	}
	
	public void setHeadquarterList(String fileName) {
		headquarterList = readfile.readerFile(fileName);
	}
	
	public String generateRandomLabel() {
		return labelList.get(this.getRandom().nextInt(labelList.size()));
	}
	
	public String generateRandomDescription() {
		return descriptionList.get(this.getRandom().nextInt(descriptionList.size()));
	}
	
	public String generateRandomHeadquarter() {
		return headquarterList.get(this.getRandom().nextInt(headquarterList.size()));
	}
	
	public Organization generateOrganization() {
		return new Organization(generateRandomLabel(), generateRandomDescription(),generateRandomHeadquarter(), generateRandomExtractedLink(), generateRandomExtractedDate());
	}
}
