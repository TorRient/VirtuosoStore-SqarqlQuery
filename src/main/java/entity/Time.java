package entity;

public class Time extends Entity{
	private static int noTime = 0;
	
	public Time(String label, String description, String extractedLink, String extractedDate) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Time" + noTime);
		noTime++;
	}
	
	public static int getNoTime() {
		return noTime;
	}
}
