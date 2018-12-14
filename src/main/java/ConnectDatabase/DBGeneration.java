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
	private final Random RANDOM = new Random();

	private static int noEntity = 0;
	private static int noRelationship = 0;

	private String extractLinkFileName = "entityData/extractLink.txt";
	private String extractTimeFileName = "entityData/extractTime.txt";

	private String personLabelFileName = "entityData/personLabel.txt";
	private String personDescriptionFileName = "entityData/personDescription.txt";
	private String personJobFileName = "entityData/personJob.txt";

	private String organizationLabelFileName = "entityData/organizationLabel.txt";
	private String organizationDescriptionFileName = "entityData/organizationDescription.txt";
	private String headquarterFileName = "entityData/headquarter.txt";

	private String countryLabelFileName = "entityData/countryLabel.txt";
	private String countryDescriptionFileName = "entityData/countryDescription.txt";

	private String locationLabelFileName = "entityData/locationLabel.txt";
	private String locationDescriptionFileName = "entityData/locationDescription.txt";

	private String timeLabelFileName = "entityData/timeLabel.txt";
	private String timeDescriptionFileName = "entityData/timeDescription.txt";

	private String eventLabelFileName = "entityData/eventLabel.txt";
	private String eventDescriptionFileName = "entityData/eventDescription.txt";

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
	
	private List<String> reOrganization_Person;
	private List<String> reOrganization_Organization;
	private List<String> reOrganization_Location;
	private List<String> reOrganization_Country;
	private List<String> reOrganization_Event;
	private List<String> reOrganization_Time;
	
	private List<String> reLocation_Person;
	private List<String> reLocation_Organization;
	private List<String> reLocation_Location;
	private List<String> reLocation_Country;
	private List<String> reLocation_Event;
	
	private List<String> reCountry_Person;
	private List<String> reCountry_Organization;
	private List<String> reCountry_Location;
	private List<String> reCountry_Country;
	private List<String> reCountry_Event;
	
	private List<String> reEvent_Person;
	private List<String> reEvent_Organization;
	private List<String> reEvent_Location;
	private List<String> reEvent_Country;
	private List<String> reEvent_Event;
	private List<String> reEvent_Time;

	public DBGeneration() {
		insertData = new InsertData();
		randomEntityGen = new RandomEntityGen();
		relationshipGen = new RelationshipGen();

		randomEntityGen.setEntityGeneration(extractLinkFileName, extractTimeFileName);
		randomEntityGen.setPersonGeneration(personLabelFileName, personDescriptionFileName, personJobFileName);
		randomEntityGen.setOrganizationGeneration(organizationLabelFileName, organizationDescriptionFileName,headquarterFileName);
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
		
		rePerson_Person = new ArrayList<String>();
		rePerson_Organization = new ArrayList<String>();
		rePerson_Location = new ArrayList<String>();
		reOrganization_Country = new ArrayList<String>();
		rePerson_Event = new ArrayList<String>();
		
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
		System.out.println("Khởi tạo model entity xong");
		noEntity = numberOfEntity;
	}

	private void genRelationship(int numberOfRelationship) {
		if (numberOfRelationship > noRelationship) {
			IRI entity1 = null;
			IRI entity2 = null;
			IRI relationship = null;
			rePerson_Person = relationshipGen.setRelationshipDescriptionList("relationship/rePerson_Person.txt"); // id 0
			rePerson_Organization = relationshipGen.setRelationshipDescriptionList("relationship/rePerson_Organization.txt"); // id 1
			rePerson_Location = relationshipGen.setRelationshipDescriptionList("relationship/rePerson_Location.txt"); // id 2
			rePerson_Country = relationshipGen.setRelationshipDescriptionList("relationship/rePerson_Country.txt"); // id 3
			rePerson_Event = relationshipGen.setRelationshipDescriptionList("relationship/rePerson_Event.txt"); // id 4
			
			reOrganization_Person = relationshipGen.setRelationshipDescriptionList("relationship/reOrganization_Person.txt"); // id 5
			reOrganization_Organization = relationshipGen.setRelationshipDescriptionList("relationship/reOrganization_Organization.txt"); // id 6
			reOrganization_Location = relationshipGen.setRelationshipDescriptionList("relationship/reOrganization_Location.txt"); // id 7
			reOrganization_Country = relationshipGen.setRelationshipDescriptionList("relationship/reOrganization_Country.txt"); // id 8
			reOrganization_Event = relationshipGen.setRelationshipDescriptionList("relationship/reOrganization_Event.txt"); // id 9
			reOrganization_Time = relationshipGen.setRelationshipDescriptionList("relationship/reOrganization_Time.txt"); // id 10
			
			reLocation_Person = relationshipGen.setRelationshipDescriptionList("relationship/reLocation_Person.txt"); // id 11
			reLocation_Organization = relationshipGen.setRelationshipDescriptionList("relationship/reLocation_Organization.txt"); // id 12
			reLocation_Location = relationshipGen.setRelationshipDescriptionList("relationship/reLocation_Location.txt"); // id 13
			reLocation_Country = relationshipGen.setRelationshipDescriptionList("relationship/reLocation_Country.txt"); // id 14
			reLocation_Event = relationshipGen.setRelationshipDescriptionList("relationship/reLocation_Event.txt"); // id 15
			
			reCountry_Person = relationshipGen.setRelationshipDescriptionList("relationship/reCountry_Person.txt"); // id 16
			reCountry_Organization = relationshipGen.setRelationshipDescriptionList("relationship/reCountry_Organization.txt"); // id 17
			reCountry_Location = relationshipGen.setRelationshipDescriptionList("relationship/reCountry_Location.txt"); // id 18
			reCountry_Country = relationshipGen.setRelationshipDescriptionList("relationship/reCountry_Country.txt"); // id 19
			reCountry_Event = relationshipGen.setRelationshipDescriptionList("relationship/reCountry_Event.txt"); // id 20
			
			reEvent_Person = relationshipGen.setRelationshipDescriptionList("relationship/reEvent_Person.txt"); // id 21
			reEvent_Organization = relationshipGen.setRelationshipDescriptionList("relationship/reEvent_Organization.txt"); // id 22
			reEvent_Location = relationshipGen.setRelationshipDescriptionList("relationship/reEvent_Location.txt"); // id 23
			reEvent_Country = relationshipGen.setRelationshipDescriptionList("relationship/reEvent_Country.txt"); // id 24
			reEvent_Event = relationshipGen.setRelationshipDescriptionList("relationship/reEvent_Event.txt"); // id 25
			reEvent_Time = relationshipGen.setRelationshipDescriptionList("relationship/reEvent_Time.txt"); // id 26

			System.out.println(rePerson_Person.size());
			for (int i = 0; i < numberOfRelationship - noRelationship; i++) {
				int id = RANDOM.nextInt(27); // 0 -> 26 ứng với id trên
				switch (id) {
				// Person
				case 0:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Person));
					break;
				case 1:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(rePerson_Organization));
					break;
				case 2:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Location));
					break;
				case 3:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Country));
					break;
				case 4:
					entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Event));
					break;
				//Organization
				case 5:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reOrganization_Person));
					break;
				case 6:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reOrganization_Organization));
					break;
				case 7:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reOrganization_Location));
					break;
				case 8:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reOrganization_Country));
					break;
				case 9:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reOrganization_Event));
					break;
				case 10:
					entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					entity2 = timeIRIList.get(RANDOM.nextInt(timeIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reOrganization_Time));
					break;
				// Location
				case 11:
					entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reLocation_Person));
					break;
				case 12:
					entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reLocation_Organization));
					break;
				case 13:
					entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reLocation_Location));
					break;
				case 14:
					entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reLocation_Country));
					break;
				case 15:
					entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reLocation_Event));
					break;
				// Country country
				case 16:
					entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reCountry_Person));
					break;
				case 17:
					entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reCountry_Organization));
					break;
				case 18:
					entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reCountry_Location));
					break;
				case 19:
					entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reCountry_Country));
					break;
				case 20:
					entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reCountry_Event));
					break;
				//Event
				case 21:
					entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reEvent_Person));
					break;
				case 22:
					entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reEvent_Organization));
					break;
				case 23:
					entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
					relationship = insertData.createRelationship(
							relationshipGen.genRandomRelationshipDescription(reEvent_Location));
					break;
				case 24:
					entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Country));
					break;
				case 25:
					entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Event));
					break;
				case 26:
					entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
					entity2 = timeIRIList.get(RANDOM.nextInt(timeIRIList.size()));
					relationship = insertData
							.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Time));
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
		insertData.getConnection().add(insertData.getModel());
		System.out.println("Add entity xong");
		insertData.getModel().clear();
		System.out.println("Bắt đầu vào relationship");
		genRelationship(numberOfRelationship);
		System.out.println("Kết thúc vào relationship");
		insertData.getConnection().add(insertData.getModel());
		System.out.println("Khởi tại relationship xong");
		insertData.getModel().clear();
	}

	public InsertData getInsertData() {
		return insertData;
	}

	public void clearStatements() {
		insertData.clear();
	}
}
