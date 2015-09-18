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
 * List traumatologist doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ListTraumatologistCommand extends Command {
	
	private static final long serialVersionUID = -5312331181078483554L;
	
	private static final Logger LOG = Logger.getLogger(ListTraumatologistCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ListTraumatologistCommand starts");
		
		List<User> traumatologistDoctorsList = DBManager.getInstance().getTraumatologistDoctors();
		LOG.trace("Found in DB: traumatologistDoctorsList --> " + traumatologistDoctorsList);
		
		request.setAttribute("traumatologistDoctorsBeanList", traumatologistDoctorsList);
		LOG.trace("Set the request attribute: traumatologistDoctorsBeanList --> " + traumatologistDoctorsList);
		LOG.debug("ListTraumatologistCommand finished");
		
		return "/WEB-INF/jsp/admin/listTraumatologistDoctors.jsp";
	}

}
