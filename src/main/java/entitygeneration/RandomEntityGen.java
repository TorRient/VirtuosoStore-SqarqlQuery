package entitygeneration;

import java.util.Random;

import entity.Entity;

public class RandomEntityGen {
	private static final Random RANDOM = new Random();
	private static final EntityGen Entity = new EntityGen();
	private static final PersonGen Person = new PersonGen();
	private static final OrganizationGen Organization = new OrganizationGen();
	private static final LocationGen Location = new LocationGen();
	private static final TimeGen Time = new TimeGen();
	private static final EventGen Event = new EventGen();
	private static final CountryGen Country = new CountryGen();

	public RandomEntityGen() {
	}

	public void setEntityGeneration(int noLink, int noDate, String startDate) {
		Entity.setExtractedDateList(noDate, startDate);
		Entity.setExtractedLinkList(noLink);
	}

	public void setPersonGeneration(String personLabelFileName, String personDescriptionFileName) {
		Person.setDescriptionList(personDescriptionFileName);
		Person.setLabelList(personLabelFileName);
	}

	public void setOrganizationGeneration(String organizationLabelFileName, String organizationDescriptionFileName) {
		Organization.setLabelList(organizationLabelFileName);
		Organization.setDescriptionList(organizationDescriptionFileName);
	}

	public void setLocationGeneration(String locationLabelFileName, String locationDescriptionFileName) {
		Location.setDescriptionList(locationDescriptionFileName);
		Location.setLabelList(locationLabelFileName);
	}

	public void setTimeGeneration(String timeLabelFileName, String timeDescriptionFileName) {
		Time.setDescriptionList(timeDescriptionFileName);
		Time.setLabelList(timeLabelFileName);
	}

	public void setEventGeneration(String eventLabelFileName, String eventDescriptionFileName) {
		Event.setDescriptionList(eventDescriptionFileName);
		Event.setLabelList(eventLabelFileName);
	}

	public void setCountryGeneration(String countryLabelFileName, String countryDescriptionFileName) {
		Country.setLabelList(countryLabelFileName);
		Country.setDescriptionList(countryDescriptionFileName);
	}
	public Entity generateRandomEntity() {
		int random = RANDOM.nextInt(6);
		switch (random) {
		case 0:
			return Person.generatePerson();
		case 1:
			return Organization.generateOrganization();
		case 2:
			return Location.generateLocation();
		case 3:
			return Time.generateTime();
		case 4:
			return Event.generateEvent();
		case 5:
			return Country.generateCountry();
		default:
			break;
		}
		return null;
	}
}
