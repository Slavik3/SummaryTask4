package ua.nure.kozlov.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.web.command.Command;
import ua.nure.kozlov.SummaryTask4.web.command.CommandContainer;

/**
 * Main servlet controller. Handles requests for all users, causing the desired command and mailing on the necessary page.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1054866369592544166L;
	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		process(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = command.execute(request, response);
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);

		// if the forward address is not null go to the address
		if (forward != null) {
			RequestDispatcher disp = request.getRequestDispatcher(forward);
			disp.forward(request, response);
		}
	}
}
