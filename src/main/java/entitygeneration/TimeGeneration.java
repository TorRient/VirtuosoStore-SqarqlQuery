package entitygeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Time;

public class TimeGeneration extends EntityGeneration {
	private static List<String> labelList = new ArrayList<String>();
	private static List<String> descriptionList = new ArrayList<String>();
	
	public void setLabelList(String fileName) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()) {
			labelList.add(scanner.nextLine());
		}
	}
	
	public void setDescriptionList(String fileName) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()) {
			descriptionList.add(scanner.nextLine());
		}
	}
	
	public String generateRandomLabel() {
		return labelList.get(this.getRandom().nextInt(labelList.size()));
	}
	
	public String generateRandomDescription() {
		return descriptionList.get(this.getRandom().nextInt(descriptionList.size()));
	}
	
	public Time generateTime() {
		return new Time(this.generateRandomLabel(), this.generateRandomDescription(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate());
	}
}
