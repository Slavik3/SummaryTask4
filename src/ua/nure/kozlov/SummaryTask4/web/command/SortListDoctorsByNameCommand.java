package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.entity.User;


/**
 * Sort list doctors by name.
 * 
 * @author Vyacheslav Kozlov
 * 
 */
public class SortListDoctorsByNameCommand extends Command {

	
	private static final long serialVersionUID = -2201053652073106414L;
	
	private static final Logger log = Logger
			.getLogger(SortListDoctorsByNameCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("SortListDoctorsByNameCommand starts");
        HttpSession session =  request.getSession();
		List<User> doctors = (ArrayList<User>)session.getAttribute("doctorsBeanList");
		log.trace("Found in session: doctors --> " + doctors);
		if (session.getAttribute("reverseDoctorsByName") == null) {				
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o1.getFirstName().compareTo(o2.getFirstName());
				}
			});
			session.setAttribute("reverseDoctorsByName", "reverse");								
		} else {									
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o2.getFirstName().compareTo(o1.getFirstName());
				}
			});
			session.removeAttribute("reverseDoctorsByName");				
		}

		session.setAttribute("doctorsBeanList", doctors);
		log.trace("Set the session attribute: doctors --> " + doctors);

		log.debug("SortListDoctorsByNameCommand finished");
		return "/WEB-INF/jsp/admin/listDoctors.jsp";
	}

}