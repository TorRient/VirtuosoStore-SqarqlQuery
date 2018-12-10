package ConnectDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;
//import org.eclipse.rdf4j.model.Statement;

import entity.Country;
import entity.Entity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;
import entitygeneration.RandomEntityGen;
import relationship.RelationshipGen;

public class DBGeneration {
	private static final Random RANDOM = new Random();

	private static int noEntity = 0;
	private static int noRelationship = 0;

	private int noLink = 10000;
	private int noDate = 1000;
	private String startDate = "1998-01-31";

	private String personLabelFileName = "personLabel.txt";
	private String personDescriptionFileName = "personDescription.txt";

	private String organizationLabelFileName = "organizationLabel.txt";
	private String organizationDescriptionFileName = "organizationDescription.txt";

	private String countryLabelFileName = "countryLabel.txt";
	private String countryDescriptionFileName = "countryDescription.txt";

	private String locationLabelFileName = "locationLabel.txt";
	private String locationDescriptionFileName = "locationDescription.txt";

	private String timeLabelFileName = "timeLabel.txt";
	private String timeDescriptionFileName = "timeDescription.txt";

	private String eventLabelFileName = "eventLabel.txt";
	private String eventDescriptionFileName = "eventDescription.txt";

	private InsertData insertData;
	private RandomEntityGen randomEntityGen;
	private RelationshipGen relationshipGen;

	private List<IRI> personIRIList;
	private List<IRI> organizationIRIList;
	private List<IRI> locationIRIList;
	private List<IRI> countryIRIList;
	private List<IRI> timeIRIList;
	private List<IRI> eventIRIList;

	private List<String> rePerson_Person;
	private List<String> rePerson_Organization;
	private List<String> rePerson_Location;
	private List<String> rePerson_Country;
	private List<String> rePerson_Event;
	private List<String> reOrganization_Event;
	private List<String> reOrganization_Location;
	private List<String> reOrganization_Time;
	private List<String> reLocation_Country;
	private List<String> reCountry_Country;
	private List<String> reEvent_Location;

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

		personIRIList = new ArrayList<IRI>();
		organizationIRIList = new ArrayList<IRI>();
		locationIRIList = new ArrayList<IRI>();
		countryIRIList = new ArrayList<IRI>();
		timeIRIList = new ArrayList<IRI>();
		eventIRIList = new ArrayList<IRI>();

		rePerson_Person = new ArrayList<String>();
		rePerson_Organization = new ArrayList<String>();
		rePerson_Location = new ArrayList<String>();
		rePerson_Country = new ArrayList<String>();
		rePerson_Event = new ArrayList<String>();
		reOrganization_Event = new ArrayList<String>();
		reOrganization_Location = new ArrayList<String>();
		reOrganization_Time = new ArrayList<String>();
		reLocation_Country = new ArrayList<String>();
		reCountry_Country = new ArrayList<String>();
		reEvent_Location = new ArrayList<String>();
	}

	private void genEntity(int numberOfEntity) {
		if (numberOfEntity > noEntity) {
			Entity entity = null;
			for (int i = 0; i < numberOfEntity - noEntity; i++) {
				entity = randomEntityGen.generateRandomEntity();
				if (entity instanceof Person) {
					personIRIList.add(insertData.insertEntity(entity));
				}
				if (entity instanceof Organization) {
					organizationIRIList.add(insertData.insertEntity(entity));
				}
				if (entity instanceof Location) {
					locationIRIList.add(insertData.insertEntity(entity));
				}
				if (entity instanceof Country) {
					countryIRIList.add(insertData.insertEntity(entity));
				}
				if (entity instanceof Time) {
					timeIRIList.add(insertData.insertEntity(entity));
				}
				if (entity instanceof Event) {
					eventIRIList.add(insertData.insertEntity(entity));
				}
			}
		}
		noEntity = numberOfEntity;
	}

	private void genRelationship(int numberOfRelationship) {
		if(numberOfRelationship > noRelationship) {
			IRI entity1 = null;
			IRI entity2 = null;
			IRI relationship = null;
			rePerson_Person = relationshipGen.setRelationshipDescriptionList("rePerson_Person.txt"); // id 0
			rePerson_Organization=relationshipGen.setRelationshipDescriptionList("rePerson_Organization.txt"); // id 1
			rePerson_Location= relationshipGen.setRelationshipDescriptionList("rePerson_Location.txt"); // id 2
			rePerson_Country= relationshipGen.setRelationshipDescriptionList("rePerson_Country.txt"); // id 3
			rePerson_Event= relationshipGen.setRelationshipDescriptionList("rePerson_Event.txt"); // id 4
			reOrganization_Event= relationshipGen.setRelationshipDescriptionList("reOrganization_Event.txt"); // id 5
			reOrganization_Location= relationshipGen.setRelationshipDescriptionList("reOrganization_Location.txt"); // id 6
			reOrganization_Time= relationshipGen.setRelationshipDescriptionList("reOrganization_Time.txt"); // id 7
			reLocation_Country= relationshipGen.setRelationshipDescriptionList("reLocation_Country.txt"); // id 8
			reCountry_Country= relationshipGen.setRelationshipDescriptionList("reCountry_Country.txt"); // id 9
			reEvent_Location= relationshipGen.setRelationshipDescriptionList("reEvent_Location.txt"); // id 10

			for(int i = 0; i < numberOfRelationship - noRelationship; i++) {
				int id = RANDOM.nextInt(11); // 0 -> 10 ứng với id trên
				switch (id) {
				case 0:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Person));
					break;
				case 1:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Organization));
					break;
				case 2:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Location));
					break;
				case 3:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Country));
					break;
				case 4:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Event));
					break;
				case 5:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(reOrganization_Event));
					break;
				case 6:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(reOrganization_Location));
					break;
				case 7:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = timeIRIList.get(RANDOM.nextInt(timeIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(reOrganization_Time));
					break;
				case 8:
					entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(reLocation_Country));
					break;
				case 9:
					entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(reCountry_Country));
					break;
				case 10:
					entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Location));
					break;
				default:
					break;
				}
				insertData.insertStatement(entity1, relationship, entity2);
			}
		}
		noRelationship = numberOfRelationship;
	}

	public void genDatabase(int numberOfEntity, int numberOfRelationship) {
		genEntity(numberOfEntity);
		genRelationship(numberOfRelationship);
		insertData.getConnection().add(insertData.getModel());
		
	}

	public InsertData getInsertData() {
		return insertData;
	}

	public void clearStatements() {
		insertData.clear();
	}
}
