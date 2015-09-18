package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.HospitalCard;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;

/**
 * Add treatment to hospital card
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class AddTreatmentToHospitalCardCommand extends Command {
	
	private static final long serialVersionUID = 7437805456977282763L;
	
	private static final Logger LOG = Logger.getLogger(AddTreatmentToHospitalCardCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("AddTreatmentToHospitalCardCommand starts");
		
		DBManager manager = DBManager.getInstance();
		HospitalCard hospitalCcard = new HospitalCard();
				
		int patientId = PrescribeTreatmentCommand.id;
		hospitalCcard.setPatientId(patientId);				
		
		String typeOfTreatment = String.valueOf(request.getParameter("typeOfTreatment"));
		hospitalCcard.setTypeOfTreatment(typeOfTreatment);
		
		String nameOfMedication = String.valueOf(request.getParameter("nameOfMedication"));
		hospitalCcard.setNameOfMedication(nameOfMedication);
		
		String diagnosis = String.valueOf(request.getParameter("diagnosis"));	
		
		manager.addHospitalCard(hospitalCcard);
		
		Patient patient = new Patient();
					
		patient.setDiagnos(diagnosis);
		patient.setId(patientId);
		manager.editPatientDiagnos(patient);
		
		
		String comeFromUrl = request.getHeader("referer");
		System.out.println("Польозователь пришел с "+comeFromUrl);
		if(comeFromUrl.contains("command=prescribeTreatment")) {
			String message = "Treatment assignment";
			request.setAttribute("message", message);
		}
		
		
		
		LOG.debug("AddTreatmentToHospitalCardCommand finished");
		return "/controller?command=listPatientsDoctor";
	}

}
