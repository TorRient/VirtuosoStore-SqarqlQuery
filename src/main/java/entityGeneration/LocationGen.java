package entityGeneration;

import java.util.ArrayList;
import java.util.List;

import ReadFile.readFile;
import entity.Location;

public class LocationGen extends EntityGen{
	readFile readfile = new readFile();
	private static List<String> labelList = new ArrayList<String>();
	private static List<String> descriptionList = new ArrayList<String>();
	
	public void setLabelList(String fileName) {
		labelList = readfile.readerFile(fileName);
	}
	
	public void setDescriptionList(String fileName) {
		descriptionList = readfile.readerFile(fileName);
	}
	
	public String generateRandomLabel() {
		return labelList.get(this.getRandom().nextInt(labelList.size()));
	}
	
	public String generateRandomDescription() {
		return descriptionList.get(this.getRandom().nextInt(descriptionList.size()));
	}
	
	public Location generateLocation() {
		return new Location(this.generateRandomLabel(), this.generateRandomDescription(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate());
	}
}
