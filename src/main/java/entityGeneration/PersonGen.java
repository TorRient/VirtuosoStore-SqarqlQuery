package entityGeneration;

import java.util.ArrayList;
import java.util.List;

import ReadFile.readFile;
import entity.Person;

public class PersonGen extends EntityGen{
	readFile readfile = new readFile();
	private static List<String> labelList = new ArrayList<String>();
	private static List<String> descriptionList = new ArrayList<String>();
	private static List<String> jobList = new ArrayList<String>();
	
	public void setLabelList(String fileName) {
		labelList = readfile.readerFile(fileName);
	}
	
	public void setDescriptionList(String fileName) {
		descriptionList = readfile.readerFile(fileName);
	}
	public void setJobList(String fileName) {
		jobList = readfile.readerFile(fileName);
	}
	
	public String generateRandomLabel() {
		return labelList.get(this.getRandom().nextInt(labelList.size()));
	}
	
	public String generateRandomDescription() {
		return descriptionList.get(this.getRandom().nextInt(descriptionList.size()));
	}
	public String generateRandomJob() {
		return jobList.get(this.getRandom().nextInt(jobList.size()));
	}
	
	public int generateRandomAge() {
		return this.getRandom().nextInt(70) + 5;
	}
	
	public Person generatePerson() {
		return new Person(generateRandomLabel(), generateRandomDescription(), generateRandomJob(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate(), generateRandomAge());
	}
}
