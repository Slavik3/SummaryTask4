package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.HospitalCard;

/**
 * Perform treatment by doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class DoctorToPerformTreatmentCommand extends Command {
	
	private static final long serialVersionUID = 3548504000999867577L;
	
	private static final Logger LOG = Logger.getLogger(DoctorToPerformTreatmentCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("DoctorToPerformTreatmentCommand starts");
		
		int id = Integer.valueOf(request.getParameter("id"));
		System.out.println("id "+id);
		
		HospitalCard hospitalCard  =  DBManager.getInstance().getHospitalCardById(id);
		boolean doneNew = hospitalCard.isDone();
		System.out.println("doneNew " + doneNew);
		doneNew=true;
		System.out.println("doneNew " + doneNew);
		hospitalCard.setDone(doneNew);
		DBManager.getInstance().updateIsDoneProcedure(hospitalCard);
		
		String comeFromUrl = request.getHeader("referer");		
		if(comeFromUrl.contains("command=viewToPerformDisinfection")) {
			String message = "Treatments performed";
			request.setAttribute("message", message);
		}
		
		LOG.debug("DoctorToPerformTreatmentCommand finished");
		return "/controller?command=listPatientsDoctor";
	}

}
