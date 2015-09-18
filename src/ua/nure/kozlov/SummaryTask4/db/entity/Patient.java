package ua.nure.kozlov.SummaryTask4.db.entity;

import java.sql.Date;

/**
 * Describes the patient entity
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class Patient extends Entity {
	
	private static final long serialVersionUID = 9038163778546217614L;
	
	private String firstName;
	private String lastName;
	private Date birthday;
	private int doctorId;
	private String diagnos;

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

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctor) {
		this.doctorId = doctor;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date date) {
		this.birthday = date;
	}

	public String getDiagnos() {
		return diagnos;
	}

	public void setDiagnos(String diagnos) {
		this.diagnos = diagnos;
	}

	@Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", lastName=" + lastName
				+ ", birthday=" + birthday + ", doctorId=" + doctorId
				+ ", diagnos=" + diagnos + "]";
	}
	
		

}
