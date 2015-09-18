package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;
import ua.nure.kozlov.SummaryTask4.db.entity.TypeOfTreatment;

/**
 * Prescribe treatment.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class PrescribeTreatmentCommand extends Command {
	
	private static final long serialVersionUID = 2197001173413817520L;
	
	private static final Logger LOG = Logger.getLogger(PrescribeTreatmentCommand.class);
	public static Integer id;
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("PrescribeTreatmentCommand starts");		
		id = Integer.valueOf(request.getParameter("patientId"));
		System.out.println("id "+id);
		Patient patient = DBManager.getInstance().getPatientById(id);
		
		request.setAttribute("patient", patient);
		LOG.trace("Set the request attribute: patient --> " + patient);		
		
		List<TypeOfTreatment> typeOfTreatmentList = DBManager.getInstance().getAllTypeOfTreatment();
		LOG.trace("Found in DB: typeOfTreatmentList --> " + typeOfTreatmentList);
		
		request.setAttribute("typeOfTreatmentBeanList", typeOfTreatmentList);		
		LOG.trace("Set the request attribute: typeOfTreatmentList --> " + typeOfTreatmentList);
		
		LOG.debug("PrescribeTreatmentCommand finished");
		return "/WEB-INF/jsp/doctor/prescribe_treatment.jsp";
	}

}
