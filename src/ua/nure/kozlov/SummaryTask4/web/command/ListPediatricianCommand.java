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
 * List pediatrician doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ListPediatricianCommand extends Command {
	
	private static final long serialVersionUID = -2134592888538145255L;
	
	private static final Logger LOG = Logger.getLogger(ListPediatricianCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ListPediatricianCommand starts");
		
		List<User> pediatricianDoctorsList = DBManager.getInstance().getPediatricianDoctors();
		LOG.trace("Found in DB: pediatricianDoctorsList --> " + pediatricianDoctorsList);
		
		request.setAttribute("pediatricianDoctorsBeanList", pediatricianDoctorsList);		
		LOG.trace("Set the request attribute: pediatricianDoctorsList --> " + pediatricianDoctorsList);	
		
		LOG.debug("ListPediatricianCommand finished");
		return "/WEB-INF/jsp/admin/listPediatricianDoctors.jsp";
	}

}
