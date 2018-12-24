package entitygeneration;

import java.util.ArrayList;
import java.util.List;

import entity.Event;
import readfile.ReadFile;

public class EventGen extends EntityGen {
	ReadFile readFile = new ReadFile();
	private static List<String> labelList = new ArrayList<String>();
	private static List<String> descriptionList = new ArrayList<String>();
	
	public void setLabelList(String fileName) {
		labelList = readFile.readerFile(fileName);
	}
	
	public void setDescriptionList(String fileName) {
		descriptionList = readFile.readerFile(fileName);
	}
	
	public String generateRandomLabel() {
		return labelList.get(this.getRandom().nextInt(labelList.size()));
	}
	
	public String generateRandomDescription() {
		return descriptionList.get(this.getRandom().nextInt(descriptionList.size()));
	}
	
	public Event generateEvent() {
		return new Event(this.generateRandomLabel(), this.generateRandomDescription(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate());
	}
}
