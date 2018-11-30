package entity;

public class Entity {
	private String id;
	private String label;
	private String description;
	private String extractedLink;
	private String extractedDate;

	public Entity() {

	}

	public Entity(String label, String description, String extractedLink, String extractedDate) {
		this.label = label;
		this.description = description;
		this.extractedLink = extractedLink;
		this.extractedDate = extractedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtractedLink() {
		return extractedLink;
	}

	public void setExtractedLink(String extractedLink) {
		this.extractedLink = extractedLink;
	}

	public String getExtractedDate() {
		return extractedDate;
	}

	public void setExtractedDate(String extractedDate) {
		this.extractedDate = extractedDate;
	}

}
