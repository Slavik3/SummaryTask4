package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * Invoked when user wants to add doctor. Command allowed only for admins.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class AddDoctorCommand extends Command {
	
	private static final long serialVersionUID = 2172252954475579819L;
	
	private static final Logger LOG = Logger.getLogger(AddDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("AddDoctorCommand starts");
		
		DBManager manager = DBManager.getInstance();
		User user = new User();
		
		int countOfDoctorsBeforeAdding = manager.getCountOfDoctors();
		LOG.trace("countOfDoctorsBeforeAdding " + countOfDoctorsBeforeAdding);					
		
		String firstName = String.valueOf(request.getParameter("firstName"));
		user.setFirstName(firstName);
		
		String lastName = String.valueOf(request.getParameter("lastName"));
		user.setLastName(lastName);
		
		String login = String.valueOf(request.getParameter("login"));
		user.setLogin(login);
		
		String password = String.valueOf(request.getParameter("password"));
		user.setPassword(password);
		
		String specialization = String.valueOf(request.getParameter("specialization"));		
		user.setSpecialization(specialization);
			
		if(firstName.equals("") || lastName.equals("") || login.equals("") || password.equals("")) {
			LOG.error("empty field");
		}
		else {
			manager.addDoctor(user);
		}
		
		int countOfDoctorsAfterAdding = manager.getCountOfDoctors();
		LOG.trace("countOfDoctorsAfterAdding " + countOfDoctorsAfterAdding);
		
		if(countOfDoctorsAfterAdding>countOfDoctorsBeforeAdding) {
			String message = "Doctor added";
			request.setAttribute("message", message);
		}
		
		if(countOfDoctorsAfterAdding==countOfDoctorsBeforeAdding) {
			String message = "Doctor no added";
			request.setAttribute("message", message);
		}
		
		LOG.debug("AddDoctorCommand finished");
		return "/controller?command=viewAddDoctor";
	}

}
