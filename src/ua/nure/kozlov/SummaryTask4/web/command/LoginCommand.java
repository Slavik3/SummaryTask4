package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;//TODO зачем это?

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.Path;
import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.Role;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * Servlet provides user's authorization in system.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = 354967949273826461L;
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	
	public static int doctorId;
	
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		LOG.debug("LoginCommand starts");
		
		HttpSession session = request.getSession();
		
		// obtain login and password from the request
		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
		System.out.println("login "+login);
		LOG.trace("Request parameter: loging --> " + login);
		
		String password = request.getParameter("password");
		System.out.println("password "+password);
		// error handler
		String userNotFoundErrorMessage = null;		
		String forward = Path.PAGE_ERROR_PAGE;
		
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			userNotFoundErrorMessage = "Login/password cannot be empty_";
			request.setAttribute("errorMessage", userNotFoundErrorMessage);
			LOG.error("errorMessage --> " + userNotFoundErrorMessage);
			return "login.jsp";
		}
		
		User user = manager.findUserByLogin(login);
		LOG.trace("Found in DB: user --> " + user);
			
		if (user == null || !password.equals(user.getPassword())) {
			userNotFoundErrorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", userNotFoundErrorMessage);
			LOG.error("errorMessage --> " + userNotFoundErrorMessage);
			return "login.jsp";//TODO forward
		} else {
			Role userRole = Role.getRole(user);
			LOG.trace("userRole --> " + userRole);
				
			if (userRole == Role.ADMIN) {
				forward = Path.COMMAND_LIST_DOCTOR;
			}
		
			if (userRole == Role.DOCTOR) {
				forward = Path.COMMAND_LIST_PATIENT;
			}
			
			if (userRole == Role.NURSE) {
				forward = Path.COMMAND_LIST_PATIENT_NURSE;
			}
			
			session.setAttribute("user", user);
			LOG.trace("Set the session attribute: user --> " + user);
				
			session.setAttribute("userRole", userRole);				
			LOG.trace("Set the session attribute: userRole --> " + userRole);
				
			LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
						
			doctorId = user.getId();			
			
			
			// work with i18n
			String userLocaleName = user.getLocaleName();
			LOG.trace("userLocalName --> " + userLocaleName);
			
			if (userLocaleName != null && !userLocaleName.isEmpty()) {
				Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
				
				session.setAttribute("defaultLocale", userLocaleName);
				LOG.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
				
				LOG.info("Locale for user: defaultLocale --> " + userLocaleName);
			}
		}
		
		LOG.debug("LoginCommand finished");
		return forward;
	}
}
