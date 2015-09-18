package ua.nure.kozlov.SummaryTask4.db.entity;

/**
 * Describes the Hospital Card entity
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class HospitalCard extends Entity {
	
	private static final long serialVersionUID = 59386495361517993L;
	
	private int patientId;
	private String lastNamePatient;
	private String firstNamePatient;
	private String typeOfTreatment;
	private String nameOfMedication;
	private boolean done;
	
	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getLastName() {
		return lastNamePatient;
	}

	public void setLastName(String lastName) {
		this.lastNamePatient = lastName;
	}
	
	public String getFirstNamePatient() {
		return firstNamePatient;
	}

	public void setFirstNamePatient(String firstNamePatient) {
		this.firstNamePatient = firstNamePatient;
	}	
	
	public String getTypeOfTreatment() {
		return typeOfTreatment;
	}

	public void setTypeOfTreatment(String typeOfTreatment) {
		this.typeOfTreatment = typeOfTreatment;
	}

	public String getNameOfMedication() {
		return nameOfMedication;
	}
	
	public void setNameOfMedication(String nameOfMedication) {
		this.nameOfMedication = nameOfMedication;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "HospitalCard [patientId=" + patientId + ", lastNamePatient="
				+ lastNamePatient + ", firstNamePatient=" + firstNamePatient
				+ ", typeOfTreatment=" + typeOfTreatment
				+ ", nameOfMedication=" + nameOfMedication + ", done=" + done
				+ "]";
	}
	
	
	
}
