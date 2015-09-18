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
 * After entering the nurse in, it is available a list of patients.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ListPatientsNurseCommand extends Command {
	
	private static final long serialVersionUID = 7861561908278109668L;
	
	private static final Logger LOG = Logger.getLogger(ListPatientsDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ListPatientsNurseCommand starts");
		
		int doctorId = LoginCommand.doctorId;
		System.out.println("doctorId " + doctorId);	
		
		List<Patient> patientList = DBManager.getInstance().getAllPatientsNurse();
		LOG.trace("Found in DB: patientList --> " + patientList);
		
		request.getSession().setAttribute("patientsBeanList", patientList);
		LOG.trace("Set the request attribute: patientList --> " + patientList);		
		
		LOG.debug("ListPatientsNurseCommand finished");		
		return "/WEB-INF/jsp/nurse/listPatients.jsp";
	}

}
