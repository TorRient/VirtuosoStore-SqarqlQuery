package entity;

public class Person extends Entity {
	private static int noPerson = 0;
	private int age;
	private String job;

	public Person(String label, String description, String job, String extractedLink, String extractedDate, int age) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Person" + noPerson);
		this.age = age;
		this.job = job;
		noPerson++;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getNoPerson() {
		return noPerson;
	}

}
