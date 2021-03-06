package insertdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.TreeModel;

import databasegeneration.DBGeneration;
// Dùng để so sánh
import entity.Country;
import entity.Entity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;
import entitygeneration.RandomEntityGen;
import relationship.RelationshipGen;

public class InsertDB {
	private final Random RANDOM = new Random();

	private static int noEntity = 0;
	private static int noRelationship = 0;

	private DBGeneration DBgeneration;
	private RandomEntityGen randomEntityGen;
	private RelationshipGen relationshipGen;

	private static List<IRI> personIRIList;
	private static List<IRI> organizationIRIList;
	private static List<IRI> locationIRIList;
	private static List<IRI> countryIRIList;
	private static List<IRI> timeIRIList;
	private static List<IRI> eventIRIList;

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

	public InsertDB() {
		DBgeneration = new DBGeneration();
		randomEntityGen = new RandomEntityGen();
		relationshipGen = new RelationshipGen();
		// Read file entity
		String extractLinkFileName = "entityData/extractLink.txt";
		String extractTimeFileName = "entityData/extractTime.txt";
		String personLabelFileName = "entityData/personLabel.txt";
		String personDescriptionFileName = "entityData/personDescription.txt";
		String personJobFileName = "entityData/personJob.txt";
		String organizationLabelFileName = "entityData/organizationLabel.txt";
		String organizationDescriptionFileName = "entityData/organizationDescription.txt";
		String headquarterFileName = "entityData/headquarter.txt";
		String countryLabelFileName = "entityData/countryLabel.txt";
		String countryDescriptionFileName = "entityData/countryDescription.txt";
		String locationLabelFileName = "entityData/locationLabel.txt";
		String locationDescriptionFileName = "entityData/locationDescription.txt";
		String timeLabelFileName = "entityData/timeLabel.txt";
		String timeDescriptionFileName = "entityData/timeDescription.txt";
		String eventLabelFileName = "entityData/eventLabel.txt";
		String eventDescriptionFileName = "entityData/eventDescription.txt";
		randomEntityGen.setEntityGeneration(extractLinkFileName, extractTimeFileName);
		randomEntityGen.setPersonGeneration(personLabelFileName, personDescriptionFileName, personJobFileName);
		randomEntityGen.setOrganizationGeneration(organizationLabelFileName, organizationDescriptionFileName,headquarterFileName);
		randomEntityGen.setCountryGeneration(countryLabelFileName, countryDescriptionFileName);
		randomEntityGen.setLocationGeneration(locationLabelFileName, locationDescriptionFileName);
		randomEntityGen.setTimeGeneration(timeLabelFileName, timeDescriptionFileName);
		randomEntityGen.setEventGeneration(eventLabelFileName, eventDescriptionFileName);
		// Lưu IRI phục vụ cho việc random 2 entity tạo relationship
		personIRIList = new ArrayList<IRI>();
		organizationIRIList = new ArrayList<IRI>();
		locationIRIList = new ArrayList<IRI>();
		countryIRIList = new ArrayList<IRI>();
		timeIRIList = new ArrayList<IRI>();
		eventIRIList = new ArrayList<IRI>();
		// read file relationship
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
		reOrganization_Time = relationshipGen.setRelationshipDescriptionList("relationship/reOrganization_Time.txt"); // id
																														// 10

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
	}
	// add entity lên server
	private void insertEntity(int numberOfEntity) {
		if (numberOfEntity > noEntity) {
			Entity entity = null;
			if (numberOfEntity >= 1000000) {
				for (int num = 1000000; num <= numberOfEntity; num += 1000000) {
					Model model = new TreeModel();
					for (int i = 0; i < num - noEntity; i++) {
						entity = randomEntityGen.generateRandomEntity();
						if (entity instanceof Person) {
							personIRIList.add(DBgeneration.addEntity(entity, model));
						}
						if (entity instanceof Organization) {
							organizationIRIList.add(DBgeneration.addEntity(entity, model));
						}
						if (entity instanceof Location) {
							locationIRIList.add(DBgeneration.addEntity(entity, model));
						}
						if (entity instanceof Country) {
							countryIRIList.add(DBgeneration.addEntity(entity, model));
						}
						if (entity instanceof Time) {
							timeIRIList.add(DBgeneration.addEntity(entity, model));
						}
						if (entity instanceof Event) {
							eventIRIList.add(DBgeneration.addEntity(entity, model));
						}
					}
					System.out.println("Xong" + num);
					noEntity = num;
					DBgeneration.getConnection().add(model);
					System.gc();
				}
			} else {
				Model model = new TreeModel();
				for (int i = 0; i < numberOfEntity - noEntity; i++) {
					entity = randomEntityGen.generateRandomEntity();
					if (entity instanceof Person) {
						personIRIList.add(DBgeneration.addEntity(entity, model));
					}
					if (entity instanceof Organization) {
						organizationIRIList.add(DBgeneration.addEntity(entity, model));
					}
					if (entity instanceof Location) {
						locationIRIList.add(DBgeneration.addEntity(entity, model));
					}
					if (entity instanceof Country) {
						countryIRIList.add(DBgeneration.addEntity(entity, model));
					}
					if (entity instanceof Time) {
						timeIRIList.add(DBgeneration.addEntity(entity, model));
					}
					if (entity instanceof Event) {
						eventIRIList.add(DBgeneration.addEntity(entity, model));
					}
				}
				noEntity = numberOfEntity;
				DBgeneration.getConnection().add(model);
				System.gc();
			}
		}
		System.out.println("Khởi tạo model entity xong");
	}
	// add relationship lên server
	private void insertRelationship(int numberOfRelationship) {
		if (numberOfRelationship > noRelationship) {
			IRI entity1 = null;
			IRI entity2 = null;
			IRI relationship = null;
			if (numberOfRelationship >= 5000000) {
				for (int num = 5666666; num <= numberOfRelationship; num += 5666666) {
					Model model = new TreeModel();
					for (int i = 0; i < num - noRelationship; i++) {
						int id = RANDOM.nextInt(27); // 0 -> 26 ứng với id trên
						switch (id) {
						// Person
						case 0:
							entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(rePerson_Person));
							break;
						case 1:
							entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(rePerson_Organization));
							break;
						case 2:
							entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(rePerson_Location));
							break;
						case 3:
							entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(rePerson_Country));
							break;
						case 4:
							entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(rePerson_Event));
							break;
						// Organization
						case 5:
							entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reOrganization_Person));
							break;
						case 6:
							entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reOrganization_Organization));
							break;
						case 7:
							entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reOrganization_Location));
							break;
						case 8:
							entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reOrganization_Country));
							break;
						case 9:
							entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reOrganization_Event));
							break;
						case 10:
							entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							entity2 = timeIRIList.get(RANDOM.nextInt(timeIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reOrganization_Time));
							break;
						// Location
						case 11:
							entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reLocation_Person));
							break;
						case 12:
							entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reLocation_Organization));
							break;
						case 13:
							entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reLocation_Location));
							break;
						case 14:
							entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reLocation_Country));
							break;
						case 15:
							entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reLocation_Event));
							break;
						// Country country
						case 16:
							entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reCountry_Person));
							break;
						case 17:
							entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reCountry_Organization));
							break;
						case 18:
							entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reCountry_Location));
							break;
						case 19:
							entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reCountry_Country));
							break;
						case 20:
							entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reCountry_Event));
							break;
						// Event
						case 21:
							entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reEvent_Person));
							break;
						case 22:
							entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reEvent_Organization));
							break;
						case 23:
							entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reEvent_Location));
							break;
						case 24:
							entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reEvent_Country));
							break;
						case 25:
							entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							relationship = DBgeneration.createRelationship(
									relationshipGen.genRandomRelationshipDescription(reEvent_Event));
							break;
						case 26:
							entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
							entity2 = timeIRIList.get(RANDOM.nextInt(timeIRIList.size()));
							relationship = DBgeneration
									.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Time));
							break;
						default:
							break;
						}
						DBgeneration.insertStatement(entity1, relationship, entity2, model);
					}
					noRelationship = num;
					DBgeneration.getConnection().add(model);
					System.gc();
				}
			} else {
				Model model = new TreeModel();
				for (int i = 0; i < numberOfRelationship - noRelationship; i++) {
					int id = RANDOM.nextInt(27); // 0 -> 26 ứng với id trên
					switch (id) {
					// Person
					case 0:
						entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Person));
						break;
					case 1:
						entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(rePerson_Organization));
						break;
					case 2:
						entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(rePerson_Location));
						break;
					case 3:
						entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Country));
						break;
					case 4:
						entity1 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(rePerson_Event));
						break;
					// Organization
					case 5:
						entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reOrganization_Person));
						break;
					case 6:
						entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reOrganization_Organization));
						break;
					case 7:
						entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reOrganization_Location));
						break;
					case 8:
						entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reOrganization_Country));
						break;
					case 9:
						entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reOrganization_Event));
						break;
					case 10:
						entity1 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						entity2 = timeIRIList.get(RANDOM.nextInt(timeIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reOrganization_Time));
						break;
					// Location
					case 11:
						entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reLocation_Person));
						break;
					case 12:
						entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reLocation_Organization));
						break;
					case 13:
						entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reLocation_Location));
						break;
					case 14:
						entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reLocation_Country));
						break;
					case 15:
						entity1 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reLocation_Event));
						break;
					// Country
					case 16:
						entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reCountry_Person));
						break;
					case 17:
						entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reCountry_Organization));
						break;
					case 18:
						entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reCountry_Location));
						break;
					case 19:
						entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reCountry_Country));
						break;
					case 20:
						entity1 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reCountry_Event));
						break;
					// Event
					case 21:
						entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						entity2 = personIRIList.get(RANDOM.nextInt(personIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Person));
						break;
					case 22:
						entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						entity2 = organizationIRIList.get(RANDOM.nextInt(organizationIRIList.size()));
						relationship = DBgeneration.createRelationship(
								relationshipGen.genRandomRelationshipDescription(reEvent_Organization));
						break;
					case 23:
						entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						entity2 = locationIRIList.get(RANDOM.nextInt(locationIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Location));
						break;
					case 24:
						entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						entity2 = countryIRIList.get(RANDOM.nextInt(countryIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Country));
						break;
					case 25:
						entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						entity2 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Event));
						break;
					case 26:
						entity1 = eventIRIList.get(RANDOM.nextInt(eventIRIList.size()));
						entity2 = timeIRIList.get(RANDOM.nextInt(timeIRIList.size()));
						relationship = DBgeneration
								.createRelationship(relationshipGen.genRandomRelationshipDescription(reEvent_Time));
						break;
					default:
						break;
					}
					DBgeneration.insertStatement(entity1, relationship, entity2, model);
				}
				noRelationship = numberOfRelationship;
				DBgeneration.getConnection().add(model);
				System.gc();
			}
		}
	}
	// thêm số lượng cần khởi tạo
	public void insertDatabase(int numberOfEntity, int numberOfRelationship) {
		insertEntity(numberOfEntity);
		System.out.println("Add entity xong");
		System.out.println("Bắt đầu vào relationship");
		insertRelationship(numberOfRelationship);
		System.out.println("Kết thúc vào relationship");
		System.out.println("Khởi tại relationship xong");
	}

	public DBGeneration getDBGen() {
		return DBgeneration;
	}

	public void clearStatements() {
		DBgeneration.clear();
	}
}
