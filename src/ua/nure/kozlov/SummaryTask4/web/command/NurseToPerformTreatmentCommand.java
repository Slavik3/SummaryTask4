package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.HospitalCard;

/**
 * Perform treatment by nurse.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class NurseToPerformTreatmentCommand extends Command {
	
	private static final long serialVersionUID = -155563655917047225L;
	
	private static final Logger LOG = Logger.getLogger(NurseToPerformTreatmentCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("NurseToPerformTreatmentCommand starts");
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		HospitalCard hospitalCard  =  DBManager.getInstance().getHospitalCardById(id);
		boolean doneNew = hospitalCard.isDone();
		System.out.println("doneNew " + doneNew);
		doneNew=true;
		System.out.println("doneNew " + doneNew);
		hospitalCard.setDone(doneNew);
		DBManager.getInstance().updateIsDoneProcedure(hospitalCard);
		
		String comeFromUrl = request.getHeader("referer");		
		if(comeFromUrl.contains("command=viewToPerformTreatmentByNurse")) {
			String message = "Treatments performed";
			request.setAttribute("message", message);
		}
		
		LOG.debug("NurseToPerformTreatmentCommand finished");
		return "/controller?command=listPatientsNurse";
	}

}
