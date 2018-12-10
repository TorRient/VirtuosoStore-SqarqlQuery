package entitygeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Person;

public class PersonGen extends EntityGen{
	private List<String> labelList = new ArrayList<String>();
	private List<String> descriptionList = new ArrayList<String>();
	
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
	
	public int generateRandomAge() {
		return this.getRandom().nextInt(100) + 5;
	}
	
	public Person generatePerson() {
		return new Person(generateRandomLabel(), generateRandomDescription(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate(), generateRandomAge());
	}
}
