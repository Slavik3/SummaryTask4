package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.Specialization;

/**
 * View add doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class ViewAddDoctorCommand extends Command {
	
	private static final long serialVersionUID = -8671011819156414020L;
	
	private static final Logger LOG = Logger.getLogger(ViewAddDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ViewAddDoctorCommand start");
		
		List<Specialization> specializationList = DBManager.getInstance().getAllDoctorsSpecializations();
		LOG.trace("Found in DB: specializationList --> " + specializationList);
		
		request.setAttribute("specializationDoctorsBeanList", specializationList);		
		LOG.trace("Set the request attribute: specializationDoctorsList --> " + specializationList);
		
		LOG.debug("ViewAddDoctorCommand finished");
		return "/WEB-INF/jsp/admin/addDoctor.jsp";
	}

}
