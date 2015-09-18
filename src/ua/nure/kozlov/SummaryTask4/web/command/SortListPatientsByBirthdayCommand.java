package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.entity.Patient;

/**
 * Sort list patients by birthday.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class SortListPatientsByBirthdayCommand extends Command {
	
	private static final long serialVersionUID = -3131908171477059944L;
	
	private static final Logger log = Logger.getLogger(SortListPatientsByBirthdayCommand.class);
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("SortListPatientsByBirthdayCommand starts");

		// get list of patients
		HttpSession session = request.getSession();
		List<Patient> patients = (List<Patient>) session
				.getAttribute("patientsBeanList");
		log.trace("List of patients from request --> " + patients);

		// sort patients by birthday
		if (session.getAttribute("reverseBirthday") == null) {
			Collections.sort(patients, new Comparator<Patient>() {
				public int compare(Patient o1, Patient o2) {
					return o1.getBirthday().compareTo(o2.getBirthday());
				}
			});
			session.setAttribute("reverseBirthday", "reverse");
		} else {
			Collections.sort(patients, new Comparator<Patient>() {
				public int compare(Patient o1, Patient o2) {
					return o2.getBirthday().compareTo(o1.getBirthday());
				}
			});
			session.removeAttribute("reverseBirthday");
		}

		// put sorted list of patients to session
		session.setAttribute("patientsBeanList", patients);

		log.trace("Set the session attribute: patientsWithoutDoctors --> "
				+ patients);

		log.debug("SortListPatientsByBirthdayCommand finished");
		return "/WEB-INF/jsp/admin/listPatients.jsp";		
	}

}
