package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.HospitalCard;

/**
 * List of the patient's doctor, who need treatment (treatments, medications, operations).
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ViewToPerformDisinfectionCommand extends Command {
	
	private static final long serialVersionUID = -7565481659964001101L;
	
	private static final Logger LOG = Logger.getLogger(ViewToPerformDisinfectionCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ViewToPerformDisinfectionCommand starts");
		
		Integer patientId = Integer.valueOf(request.getParameter("patientId"));
		System.out.println("patientId " + patientId);
		List<HospitalCard> hospitalCardList = DBManager.getInstance().getAllHospitalCardByPatientId(patientId);
		LOG.trace("Found in DB: hospitalCardList --> " + hospitalCardList);
		request.setAttribute("hospitalCardList", hospitalCardList);
		
		LOG.debug("ViewToPerformDisinfectionCommand finished");
		return "/WEB-INF/jsp/doctor/toPerformDisinfection.jsp";
	}

}
