package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * View add nurse.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ViewAddNurseCommand extends Command {
	
	private static final long serialVersionUID = -7392092392986347024L;
	
	private static final Logger LOG = Logger.getLogger(ViewAddNurseCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ViewAddDoctorCommand start");
		LOG.debug("ViewAddDoctorCommand finished.");
		return "/WEB-INF/jsp/admin/addNurse.jsp";
	}

}
