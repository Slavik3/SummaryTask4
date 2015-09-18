package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.Path;

/**
 * Invoked when user wants to logout from the system.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class LogoutCommand extends Command {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3175820677473830999L;
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("LogoutCommand starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		LOG.debug("LogoutCommand finished");
		return Path.PAGE_LOGIN;
	}
}
