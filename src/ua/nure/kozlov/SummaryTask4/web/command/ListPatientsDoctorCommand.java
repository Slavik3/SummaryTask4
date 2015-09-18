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
 * After entering the doctor in, it is available a list of patients who are in his care.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ListPatientsDoctorCommand extends Command {
	
	private static final long serialVersionUID = -7022896349691585602L;
	
	private static final Logger LOG = Logger.getLogger(ListPatientsDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {		
		LOG.debug("ListPatientsDoctorCommand starts");		
		
		int doctorId = LoginCommand.doctorId;
		System.out.println("doctorId " + doctorId);		
		
		List<Patient> patientList = DBManager.getInstance().getAllPatientsByDoctor(doctorId);
		LOG.trace("Found in DB: patientList --> " + patientList);
		
		request.getSession().setAttribute("patientsBeanList", patientList);
		LOG.trace("Set the request attribute: patientList --> " + patientList);			
	
		LOG.debug("ListPatientsDoctorCommand finished");		
		return "/WEB-INF/jsp/doctor/listPatients.jsp";
	}

}
