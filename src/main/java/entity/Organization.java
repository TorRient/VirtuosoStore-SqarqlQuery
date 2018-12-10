package entity;

public class Organization extends Entity {
	private static int noOrganization = 0;

	public Organization(String label, String description, String extractedLink, String extractedDate) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Organization" + noOrganization);
		noOrganization++;
	}

	public int getNoOrganization() {
		return noOrganization;
	}

}
