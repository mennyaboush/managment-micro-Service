package management.service;

public class Reader {
	private String id;

	private String email;

	private String firstName;

	private String lastName;

	public Reader() {
	}

	public Reader(String id, String email,
			String firstName, String lastName) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
