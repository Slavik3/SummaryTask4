package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public abstract class Command implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -86261102290940278L;

	/**
	 * Execution method for command. Returns path to go to based on the client
	 * request. If Command is specific to some user role, then subclasses in
	 * this method should perform validation and grant or not permissions to
	 * proceed. 
	 * 
	 * @param request - client request
	 * @param response server response
	 * @param actionType - client HTTP method
	 * 
	 * @throws IOException
	 * @throws ServletException
	 * 
	 * @return Address to go once the command is executed.
	 */
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
