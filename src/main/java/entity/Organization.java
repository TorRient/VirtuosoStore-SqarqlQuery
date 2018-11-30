package entity;

public class Organization extends Entity {
	private static int noOrganization = 0;
	private String headquarter;

	public Organization(String label, String description, String extractedLink, String extractedDate,
			String headquarter) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Organization" + noOrganization);
		this.headquarter = headquarter;
		noOrganization++;
	}

	public String getHeadquarter() {
		return headquarter;
	}

	public void setHeadquarter(String headquarter) {
		this.headquarter = headquarter;
	}

	public static int getNoOrganization() {
		return noOrganization;
	}

}
