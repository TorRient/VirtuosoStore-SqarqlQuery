package entity;

public class Country extends Entity {
	private static int noCountry = 0;

	public Country(String label, String description, String extractedLink, String extractedDate) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Country"  + noCountry);
		noCountry++;
	}

	public static int getNoCountry() {
		return noCountry;
	}

}
