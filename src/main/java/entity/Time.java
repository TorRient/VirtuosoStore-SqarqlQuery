package entity;

public class Time extends Entity{
	public static int noTime = 0;
	
	public Time(String label, String description, String extractedLink, String extractedDate) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Time" + noTime);
		noTime++;
	}
	
//	public int getNoTime() {
//		return noTime;
//	}
}
