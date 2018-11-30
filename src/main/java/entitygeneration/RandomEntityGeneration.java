package entitygeneration;

import java.util.Random;

import entity.Entity;

public class RandomEntityGeneration {
	private static final Random RANDOM = new Random();
	private static EntityGeneration ENTITY_GENERATION = new EntityGeneration();
	private static final PersonGeneration PERSON_GENERATION = new PersonGeneration();
	private static final OrganizationGeneration ORGANIZATION_GENERATION = new OrganizationGeneration();
	private static final LocationGeneration LOCATION_GENERATION = new LocationGeneration();
	private static final TimeGeneration TIME_GENERATION = new TimeGeneration();
	private static final EventGeneration EVENT_GENERATION = new EventGeneration();
	private static final CountryGeneration COUNTRY_GENERATION = new CountryGeneration();

	public RandomEntityGeneration() {

	}

	public void setEntityGeneration(int noLink, int noDate, String startDate) {
		ENTITY_GENERATION.setExtractedDateList(noDate, startDate);
		ENTITY_GENERATION.setExtractedLinkList(noLink);
	}

	public void setPersonGeneration(String personLabelFileName, String personDescriptionFileName) {
		PERSON_GENERATION.setDescriptionList(personDescriptionFileName);
		PERSON_GENERATION.setLabelList(personLabelFileName);
	}

	public void setOrganizationGeneration(String organizationLabelFileName, String organizationDescriptionFileName,
			String headquarterFileName) {
		ORGANIZATION_GENERATION.setLabelList(organizationLabelFileName);
		ORGANIZATION_GENERATION.setDescriptionList(organizationDescriptionFileName);
		ORGANIZATION_GENERATION.setHeadquarterList(headquarterFileName);
	}

	public void setLocationGeneration(String locationLabelFileName, String locationDescriptionFileName) {
		LOCATION_GENERATION.setDescriptionList(locationDescriptionFileName);
		LOCATION_GENERATION.setLabelList(locationLabelFileName);
	}

	public void setTimeGeneration(String timeLabelFileName, String timeDescriptionFileName) {
		TIME_GENERATION.setDescriptionList(timeDescriptionFileName);
		TIME_GENERATION.setLabelList(timeLabelFileName);
	}

	public void setEventGeneration(String eventLabelFileName, String eventDescriptionFileName) {
		EVENT_GENERATION.setDescriptionList(eventDescriptionFileName);
		EVENT_GENERATION.setLabelList(eventLabelFileName);
	}

	public void setCountryGeneration(String countryLabelFileName, String countryDescriptionFileName) {
		COUNTRY_GENERATION.setLabelList(countryLabelFileName);
		COUNTRY_GENERATION.setDescriptionList(countryDescriptionFileName);
	}
	public Entity generateRandomEntity() {
		int random = RANDOM.nextInt(6);
		switch (random) {
		case 0:
			return PERSON_GENERATION.generatePerson();
		case 1:
			return ORGANIZATION_GENERATION.generateOrganization();
		case 2:
			return LOCATION_GENERATION.generateLocation();
		case 3:
			return TIME_GENERATION.generateTime();
		case 4:
			return EVENT_GENERATION.generateEvent();
		case 5:
			return COUNTRY_GENERATION.generateCountry();
		default:
			break;
		}
		return null;
	}
}
