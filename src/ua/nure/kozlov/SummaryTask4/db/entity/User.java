package ua.nure.kozlov.SummaryTask4.db.entity;

/**
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class User extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7496662197339049584L;

	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String localeName;
	private int roleId;	
	private String specialization;
	private int countOfPatients;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLocaleName() {
		return localeName;
	}

	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getCountOfPatients() {
		return countOfPatients;
	}

	public void setCountOfPatients(int countOfPatients) {
		this.countOfPatients = countOfPatients;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", localeName=" + localeName + ", roleId=" + roleId
				+ ", specialization=" + specialization + ", countOfPatients="
				+ countOfPatients + "]";
	}
	
	
}
