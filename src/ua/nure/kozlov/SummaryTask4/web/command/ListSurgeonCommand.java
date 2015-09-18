package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * List surgeon doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ListSurgeonCommand extends Command {
	
	private static final long serialVersionUID = 2546690151450309858L;
	
	private static final Logger LOG = Logger.getLogger(ListSurgeonCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ListSurgeonCommand starts");
		
		List<User> surgeonDoctorsList = DBManager.getInstance().getSurgeonDoctors();
		LOG.trace("Found in DB: surgeonDoctorsList --> " + surgeonDoctorsList);
		
		request.setAttribute("surgeonDoctorsBeanList", surgeonDoctorsList);
		LOG.trace("Set the request attribute: surgeonDoctorsList --> " + surgeonDoctorsList);
		LOG.debug("ListSurgeonCommand finished");
		return "/WEB-INF/jsp/admin/listSurgeonDoctors.jsp";
	}

}
