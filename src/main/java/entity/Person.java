package entity;

public class Person extends Entity {
	private static int noPerson = 0;
	int age;

	public Person(String label, String description, String extractedLink, String extractedDate, int age) {
		super(label, description, extractedLink, extractedDate);
		this.setId("Person" + noPerson);
		this.age = age;
		noPerson++;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static int getNoPerson() {
		return noPerson;
	}

}
