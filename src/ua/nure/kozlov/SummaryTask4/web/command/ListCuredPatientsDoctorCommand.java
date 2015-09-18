package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * List of cured patients of the doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ListCuredPatientsDoctorCommand extends Command {
	
	private static final long serialVersionUID = -9069882637379260399L;
	
	private static final Logger LOG = Logger.getLogger(ListCuredPatientsDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ListCuredPatientsDoctorCommand starts");
		User doctor = (User) request.getSession().getAttribute("user");
		int doctorId = doctor.getId();
		
		List<Patient> dischargedPatientList = DBManager.getInstance().getAllCuredPatientsByDoctor(doctorId);
		LOG.trace("Found in DB discharged patients: patientList --> " + dischargedPatientList);
		
		request.getSession().setAttribute("dischargedPatientsBeanList", dischargedPatientList);
		LOG.trace("Set the request attribute: dischargedPatientsBeanList --> " + dischargedPatientList);		
		
		LOG.debug("ListCuredPatientsDoctorCommand finished");
		return "/WEB-INF/jsp/doctor/listDischargedPatients.jsp";
	}

}
