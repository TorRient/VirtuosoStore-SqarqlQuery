package ConnectDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;
//import org.eclipse.rdf4j.model.Statement;

import entity.Entity;
import entitygeneration.RandomEntityGeneration;
import relationship.RelationshipGeneration;

public class DatabaseGeneration {
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
	private static String headquarterFileName = "headquarter.txt";
	
	private static String countryLabelFileName = "countryLabel.txt";
	private static String countryDescriptionFileName = "countryDescription.txt";
	
	private static String locationLabelFileName = "locationLabel.txt";
	private static String locationDescriptionFileName = "locationDescription.txt";
	
	private static String timeLabelFileName = "timeLabel.txt";
	private static String timeDescriptionFileName = "timeDescription.txt";
	
	private static String eventLabelFileName = "eventLabel.txt";
	private static String eventDescriptionFileName = "eventDescription.txt";
	
	private DatabaseAccess dataAcessObject;
	private RandomEntityGeneration randomEntityGeneration;
	private RelationshipGeneration relationshipGeneration;
	
	private List<IRI> entityIRIList;
	
	public DatabaseGeneration() {
		dataAcessObject = new DatabaseAccess();
		randomEntityGeneration = new RandomEntityGeneration();
		relationshipGeneration = new RelationshipGeneration();
		
		randomEntityGeneration.setEntityGeneration(noLink, noDate, startDate);
		randomEntityGeneration.setPersonGeneration(personLabelFileName, personDescriptionFileName);
		randomEntityGeneration.setOrganizationGeneration(organizationLabelFileName, organizationDescriptionFileName, headquarterFileName);
		randomEntityGeneration.setCountryGeneration(countryLabelFileName, countryDescriptionFileName);
		randomEntityGeneration.setLocationGeneration(locationLabelFileName, locationDescriptionFileName);
		randomEntityGeneration.setTimeGeneration(timeLabelFileName, timeDescriptionFileName);
		randomEntityGeneration.setEventGeneration(eventLabelFileName, eventDescriptionFileName);
		
		relationshipGeneration.setRelationshipDescriptionList("relationshipDescription.txt");
		
		entityIRIList = new ArrayList<IRI>();
	}
	
	private void generateEntity(int numberOfEntity) {
		if(numberOfEntity > noEntity) {
			Entity entity = null;
			for(int i = 0; i < numberOfEntity - noEntity; i++) {
				entity = randomEntityGeneration.generateRandomEntity();
				entityIRIList.add(dataAcessObject.insertEntity(entity));
			}
		}
		noEntity = numberOfEntity;
	}
	
	private void generateRelationship(int numberOfRelationship) {
		if(numberOfRelationship > noRelationship) {
			IRI entity1 = null;
			IRI entity2 = null;
			IRI relationship = null;
			
			for(int i = 0; i < numberOfRelationship - noRelationship; i++) {
				entity1 = entityIRIList.get(RANDOM.nextInt(noEntity));
				entity2 = entityIRIList.get(RANDOM.nextInt(noEntity));
				relationship = dataAcessObject.createRelationship(relationshipGeneration.generateRandomRelationshipDescription());
				dataAcessObject.insertStatement(entity1, relationship, entity2);
			}
		}
		noRelationship = numberOfRelationship;
	}
	
	public void generateDatabase(int numberOfEntity, int numberOfRelationship) {
		generateEntity(numberOfEntity);
		generateRelationship(numberOfRelationship);
	}
	
	public DatabaseAccess getDataAccessObject() {
		return dataAcessObject;
	}
	
//	public void clearStatements() {
//		dataAcessObject.clear();
//	}
}
