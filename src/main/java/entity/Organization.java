package entity;

public class Organization extends Entity {
	private static int noOrganization = 0;
	private String headquarter;

	public Organization(String label, String description,String headquarter, String extractedLink, String extractedDate) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Organization" + noOrganization);
		this.headquarter = headquarter;
		noOrganization++;
	}	
	public String getHeadquater() {
		return headquarter;
	}

	public void setHeadquater(String headquater) {
		this.headquarter = headquater;
	}

//	public int getNoOrganization() {
//		return noOrganization;
//	}

}
