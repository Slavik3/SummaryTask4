package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;

/**
 * Invoked when user wants to add patient. Command allowed only for admins.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class AddPatientCommand extends Command {
	
	private static final long serialVersionUID = -1097167909086881611L;
	
	private static final Logger LOG = Logger.getLogger(AddPatientCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("AddPatientCommand starts");
		
		DBManager manager = DBManager.getInstance();
		Patient patient = new Patient();
		
		int countOfPatientsBeforeAdding = manager.getCountOfPatient();
		LOG.trace("countOfPatientsBeforeAdding " + countOfPatientsBeforeAdding);
		try {
			String firstName = String.valueOf(request.getParameter("firstName"));
			patient.setFirstName(firstName);
		
			String lastName = String.valueOf(request.getParameter("lastName"));
			patient.setLastName(lastName);		
		
			Date birthday = Date.valueOf(request.getParameter("birthday"));
			patient.setBirthday((java.sql.Date) birthday);	
			
			manager.addPatient(patient);
		} catch (IllegalArgumentException e) {
			LOG.error("empty field");
		}
		
		int countOfPatientsAfterAdding = manager.getCountOfPatient();
		LOG.trace("countOfPatientsAfterAdding " + countOfPatientsAfterAdding);
		
		if(countOfPatientsAfterAdding>countOfPatientsBeforeAdding) {
			String message = "Patient added";
			request.setAttribute("message", message);
		}
		
		if(countOfPatientsAfterAdding==countOfPatientsBeforeAdding) {
			String message = "Patient no added";
			request.setAttribute("message", message);
		}		
		
		
		LOG.debug("AddPatientCommand finished");
		return "/controller?command=viewAddPatient";
	}

}
