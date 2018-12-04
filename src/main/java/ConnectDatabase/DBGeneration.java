package ConnectDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;
//import org.eclipse.rdf4j.model.Statement;

import entity.Entity;
import entitygeneration.RandomEntityGen;
import relationship.RelationshipGen;

public class DBGeneration {
	private static final Random RANDOM = new Random();
	
	private static int noEntity = 0;
	private static int noRelationship = 0;
	
	private static int noLink = 10000;
	private static int noDate = 1000;
	private static String startDate = "1998-01-31";
	
	private static String personLabelFileName = "personLabel.txt";
	private static String personDescriptionFileName = "personDescription.txt";
	
	private static String organizationLabelFileName = "organizationLabel.txt";
	private static String organizationDescriptionFileName = "organizationDescription.txt";
	
	private static String countryLabelFileName = "countryLabel.txt";
	private static String countryDescriptionFileName = "countryDescription.txt";
	
	private static String locationLabelFileName = "locationLabel.txt";
	private static String locationDescriptionFileName = "locationDescription.txt";
	
	private static String timeLabelFileName = "timeLabel.txt";
	private static String timeDescriptionFileName = "timeDescription.txt";
	
	private static String eventLabelFileName = "eventLabel.txt";
	private static String eventDescriptionFileName = "eventDescription.txt";
	
	private InsertData insertData;
	private RandomEntityGen randomEntityGen;
	private RelationshipGen relationshipGen;
	
	private List<IRI> entityIRIList;
	
	public DBGeneration() {
		insertData = new InsertData();
		randomEntityGen = new RandomEntityGen();
		relationshipGen = new RelationshipGen();
		
		randomEntityGen.setEntityGeneration(noLink, noDate, startDate);
		randomEntityGen.setPersonGeneration(personLabelFileName, personDescriptionFileName);
		randomEntityGen.setOrganizationGeneration(organizationLabelFileName, organizationDescriptionFileName);
		randomEntityGen.setCountryGeneration(countryLabelFileName, countryDescriptionFileName);
		randomEntityGen.setLocationGeneration(locationLabelFileName, locationDescriptionFileName);
		randomEntityGen.setTimeGeneration(timeLabelFileName, timeDescriptionFileName);
		randomEntityGen.setEventGeneration(eventLabelFileName, eventDescriptionFileName);
		
		relationshipGen.setRelationshipDescriptionList("relationshipDescription.txt");
		
		entityIRIList = new ArrayList<IRI>();
	}
	
	private void genEntity(int numberOfEntity) {
		if(numberOfEntity > noEntity) {
			Entity entity = null;
			for(int i = 0; i < numberOfEntity - noEntity; i++) {
				entity = randomEntityGen.generateRandomEntity();
				entityIRIList.add(insertData.insertEntity(entity));
			}
		}
		noEntity = numberOfEntity;
	}
	
	private void genRelationship(int numberOfRelationship) {
		if(numberOfRelationship > noRelationship) {
			IRI entity1 = null;
			IRI entity2 = null;
			IRI relationship = null;
			
			for(int i = 0; i < numberOfRelationship - noRelationship; i++) {
				entity1 = entityIRIList.get(RANDOM.nextInt(noEntity));
				entity2 = entityIRIList.get(RANDOM.nextInt(noEntity));
				relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription());
				insertData.insertStatement(entity1, relationship, entity2);
			}
		}
		noRelationship = numberOfRelationship;
	}
	
	public void genDatabase(int numberOfEntity, int numberOfRelationship) {
		genEntity(numberOfEntity);
		genRelationship(numberOfRelationship);
	}
	
	public InsertData getInsertData() {
		return insertData;
	}
	
	public void clearStatements() {
		insertData.clear();
	}
}
