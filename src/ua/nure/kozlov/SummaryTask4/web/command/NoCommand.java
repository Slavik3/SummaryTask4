package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.Path;

/**
 * Invoked when no command was found for client request.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class NoCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2115886253801069651L;
	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("NoCommand starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

		LOG.debug("NoCommand finished");
		return Path.PAGE_ERROR_PAGE;
	}
}
