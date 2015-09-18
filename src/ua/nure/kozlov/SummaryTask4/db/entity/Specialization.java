package ua.nure.kozlov.SummaryTask4.db.entity;

/**
 * Describes the doctor specialization entity
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class Specialization extends Entity{
	
	private static final long serialVersionUID = -204096488377806258L;
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Specialization [title=" + title + "]";
	}	
	

}
