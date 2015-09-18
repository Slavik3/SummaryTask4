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
 * Sort list doctors by number patients.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class SortListDoctorsByNumberPatientsCommand extends Command {
	
	private static final long serialVersionUID = 9018569007534348484L;
	
	private static final Logger log = Logger.getLogger(SortListDoctorsByNumberPatientsCommand.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("SortListDoctorsByNumberPatientsCommand starts");
        HttpSession session =  request.getSession();
		List<User> doctors = (ArrayList<User>)session.getAttribute("doctorsBeanList");
		log.trace("Found in session: doctors --> " + doctors);
		// sort doctors by name
		if (session.getAttribute("reverseDoctorsByNumberPatients") == null) {
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o1.getCountOfPatients() - o2.getCountOfPatients();
				}
			});
			session.setAttribute("reverseDoctorsByNumberPatients", "reverse");
		} else {
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o2.getCountOfPatients() - o1.getCountOfPatients();
				}
			});
			session.removeAttribute("reverseDoctorsByNumberPatients");
		}
		
		session.setAttribute("doctorsBeanList", doctors);
		log.trace("Set the session attribute: doctors --> " + doctors);

		log.debug("SortListDoctorsByNumberPatientsCommand finished");
		
		return "/WEB-INF/jsp/admin/listDoctors.jsp";
	}

}
