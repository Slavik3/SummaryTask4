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
 * Sort list doctors by surname.
 * 
 * @author Vyacheslav Kozlov
 * 
 */
public class SortListDoctorsBySurnameCommand extends Command {
		
	private static final long serialVersionUID = 753688252009914587L;
	
	private static final Logger log = Logger
			.getLogger(SortListDoctorsBySurnameCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		log.debug("SortListDoctorsBySurnameCommand starts");
        HttpSession session =  request.getSession();
		List<User> doctors = (ArrayList<User>)session.getAttribute("doctorsBeanList");//TODO
		log.trace("Found in session: doctors --> " + doctors);
		// sort doctors by surname
		if (session.getAttribute("reverseDoctorsBySurname") == null) {
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o1.getLastName().compareTo(o2.getLastName());
				}
			});
			session.setAttribute("reverseDoctorsBySurname", "reverse");
		} else {
			Collections.sort(doctors, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o2.getLastName().compareTo(o1.getLastName());
				}
			});
			session.removeAttribute("reverseDoctorsBySurname");
		}
		
		session.setAttribute("doctorsBeanList", doctors);
		log.trace("Set the session attribute: doctors --> " + doctors);

		log.debug("SortListDoctorsBySurnameCommand finished");
		return "/WEB-INF/jsp/admin/listDoctors.jsp";
	}

}