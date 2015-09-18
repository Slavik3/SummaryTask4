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
 * Sort list patients by name.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class SortListPatientsByNameCommand extends Command {
	
	private static final long serialVersionUID = 5268442682145869315L;
	
	private static final Logger log = Logger
			.getLogger(SortListPatientsByNameCommand.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("SortListPatientsByNameCommand starts");
		
		// get list of patients
				HttpSession session = request.getSession();
				List<Patient> patients = (List<Patient>) session.getAttribute("patientsBeanList");
				log.trace("List of patients from request --> " + patients);

				// sort patients by name
				if (session.getAttribute("reversePatientsByName") == null) {
					Collections.sort(patients, new Comparator<Patient>() {
						public int compare(Patient o1, Patient o2) {
							return o1.getFirstName().compareTo(o2.getFirstName());
						}
					});
					session.setAttribute("reversePatientsByName", "reverse");
				} else {
					Collections.sort(patients, new Comparator<Patient>() {
						public int compare(Patient o1, Patient o2) {
							return o2.getFirstName().compareTo(o1.getFirstName());
						}
					});
					session.removeAttribute("reversePatientsByName");
				}
				
				// put sorted list of patients to session
				session.setAttribute("patientsBeanList", patients);

				log.trace("Set the session attribute: patients --> "
						+ patients);
		
		log.debug("SortListPatientsByNameCommand finished");
		return "/WEB-INF/jsp/admin/listPatients.jsp";
	}

}
