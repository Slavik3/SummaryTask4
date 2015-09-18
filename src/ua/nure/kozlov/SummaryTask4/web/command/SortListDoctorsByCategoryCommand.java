package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * Sort list doctors by category.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class SortListDoctorsByCategoryCommand extends Command {
	
	private static final long serialVersionUID = 951801030474531600L;
	
	private static final Logger log = Logger.getLogger(SortListDoctorsByCategoryCommand.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("SortListDoctorsByCategoryCommand starts");
		
		HttpSession session = request.getSession();
		List<User> doctors = (ArrayList<User>) session.getAttribute("doctorsBeanList");
		log.trace("Found in session: doctors --> " + doctors);

		if (session.getAttribute("reverseDoctorsByCategory") == null) {
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o1.getSpecialization().compareTo(o2.getSpecialization());
				}
			});
			session.setAttribute("reverseDoctorsByCategory", "reverse");
		} else {
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o2.getSpecialization().compareTo(o1.getSpecialization());
				}
			});
			session.removeAttribute("reverseDoctorsByCategory");
		}

		session.setAttribute("doctorsBeanList", doctors);
		log.trace("Set the session attribute: doctors --> " + doctors);
		log.debug("SortListDoctorsByCategoryCommand finished");
		
		return "/WEB-INF/jsp/admin/listDoctors.jsp";
	}

}
