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
 * View assign the patient to the doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ViewAssignThePatientToTheDoctorCommand extends Command {
	
	private static final long serialVersionUID = -8681175903062351380L;
	
	private static final Logger LOG = Logger.getLogger(ViewAssignThePatientToTheDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ViewAssignThePatientToTheDoctorCommand starts");
		
		List<User> doctorsList = DBManager.getInstance().getAllDoctors();
		LOG.trace("Found in DB: doctorList --> " + doctorsList);
		
		// put user order beans list to request
		request.getSession().setAttribute("doctorsBeanList", doctorsList);		
		LOG.trace("Set the request attribute: doctorList --> " + doctorsList);
		
		LOG.debug("ViewAssignThePatientToTheDoctorCommand finished");
		return "/WEB-INF/jsp/admin/listDoctorsForAssignment.jsp";
	}

}
