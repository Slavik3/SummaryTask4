package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;

/**
 * List patients.
 * @author Vyacheslav Kozlov
 *
 */

public class ListPatientsCommand extends Command {
	
	private static final long serialVersionUID = 4430294340301966764L;
	
	private static final Logger LOG = Logger.getLogger(ListPatientsCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ListPatientsCommand starts");
		List<Patient> patientList = DBManager.getInstance().getAllPatients();
		LOG.trace("Found in DB: patientList --> " + patientList);
		
		request.getSession().setAttribute("patientsBeanList", patientList);
		LOG.trace("Set the request attribute: patientList --> " + patientList);
		
		String comeFromUrl = request.getHeader("referer");
		if(comeFromUrl.contains("command=viewAssignThePatientToTheDoctor")) {
			String message = "Patient assigned";
			request.setAttribute("message", message);
		}
		
		
		LOG.debug("ListPatientsCommand finished");
		return "/WEB-INF/jsp/admin/listPatients.jsp";
	}

}
