package entity;

public class Location extends Entity {
	private static int noLocation = 0;

	public Location(String label, String description, String extractedLink, String extractedDate) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Location" + noLocation);
		noLocation++;
	}

	public static int getNoLocation() {
		return noLocation;
	}

}
