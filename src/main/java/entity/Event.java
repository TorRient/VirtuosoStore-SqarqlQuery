package entity;

public class Event extends Entity{
	public static int noEvent = 0;
	
	public Event(String label, String description, String extractedLink, String extractedDate) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Event" + noEvent);
		noEvent++;
	}
	
//	public int getNoEvent() {
//		return noEvent;
//	}
}
