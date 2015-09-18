package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.HospitalCard;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * Complete the course of treatment.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class CompleteTheCourseOfTreatmentCommand extends Command {
	
	private static final long serialVersionUID = 2541630566289784689L;
	
	private static final Logger LOG = Logger.getLogger(CompleteTheCourseOfTreatmentCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("CompleteCourseOfTreatment starts");
		
		DBManager manager = DBManager.getInstance();
		
		Integer patientId = Integer.valueOf(request.getParameter("patientId"));
		
				
		Patient patient = manager.getPatientById(patientId);
		
		
		String lastName = patient.getLastName();
		String firstName = patient.getFirstName();		
		String birthday = patient.getBirthday().toString();
		int doctorId = patient.getDoctorId();
		String diagnos = patient.getDiagnos();
		
		User doctor = manager.getUserById(doctorId);
		
		List<HospitalCard>  hospitalCardList = manager.getAllHospitalCardByPatientId(patientId);
		System.out.println("hospitalCardList "+hospitalCardList);
				
		String message;		
		
		//Write to file
		try {
		String doctorName = doctor.getFirstName();
		String doctorLastName = doctor.getLastName();
		String typeOfTreatment;
		String nameOfMedication;    
		Boolean isDone;
		PrintWriter writer = new PrintWriter("../workspace/SummaryTask4/WebContent/WEB-INF/сompleteTheCourseOfTreatment/"+lastName+firstName+".txt", "UTF-8");
		writer.println("Пациент: " + firstName + " " + lastName + ", " + birthday);
		writer.println("Врач: " + doctorName + " " + doctorLastName);
		writer.println("Диагноз: " + diagnos);
		writer.println("Список назначений: ");
		for(int i=0; i<hospitalCardList.size(); i++) {
			typeOfTreatment = hospitalCardList.get(i).getTypeOfTreatment();
			nameOfMedication = hospitalCardList.get(i).getNameOfMedication();
			isDone = hospitalCardList.get(i).isDone();
			String done;
			if(isDone==true) {
				done="выполнено";
			}
			else {
				done="не выполнено";
			}
			writer.println("Тип лечения: "+typeOfTreatment + "; Название лечения: " + nameOfMedication + "; Статус: " + done);		    	  
		}		    	  
		writer.close();
	  		
		manager.editCountOfPatients(doctor); 
		
		
		manager.completeTheCourseOfTreatment(patient);
		
		message = "The patient is discharged";
		request.setAttribute("message", message);
		} catch (IndexOutOfBoundsException e) {
			message = "You need to make at least one appointment";
			request.setAttribute("message", message);
			LOG.error(e.getMessage());
		}
		
		LOG.debug("CompleteCourseOfTreatment finished");
		
		return "/controller?command=listPatientsDoctor";
	}

}
