package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * Invoked when user wants to add nurse. Command allowed only for admins.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class AddNurseCommand extends Command {
	
	private static final long serialVersionUID = 5103874631737565626L;
	
	private static final Logger LOG = Logger.getLogger(AddNurseCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("AddNurseCommand starts");
		DBManager manager = DBManager.getInstance();
		User nurse = new User();
		
		int countOfNursesBeforeAdding = manager.getCountOfNurse();
		LOG.trace("countOfNurseBeforeAdding " + countOfNursesBeforeAdding);
		
		String firstName = String.valueOf(request.getParameter("firstName"));
		nurse.setFirstName(firstName);
		
		String lastName = String.valueOf(request.getParameter("lastName"));
		nurse.setLastName(lastName);
		
		String login = String.valueOf(request.getParameter("login"));
		nurse.setLogin(login);
		
		String password = String.valueOf(request.getParameter("password"));
		nurse.setPassword(password);	
		
		if(firstName.equals("") || lastName.equals("") || login.equals("") || password.equals("")) {
			LOG.error("empty field");
		}
		else {
			manager.addNurse(nurse);
		}
		
		int countOfNursesAfterAdding = manager.getCountOfNurse();
		LOG.trace("countOfNursesAfterAdding " + countOfNursesAfterAdding);
		
		if(countOfNursesAfterAdding>countOfNursesBeforeAdding) {
			String message = "Nurse added";
			request.setAttribute("message", message);
		}
		
		if(countOfNursesAfterAdding==countOfNursesBeforeAdding) {
			String message = "Nurse no added";
			request.setAttribute("message", message);
		}
		
		LOG.debug("AddNurseCommand finished");
		
		return "/controller?command=viewAddNurse";
	}

}
