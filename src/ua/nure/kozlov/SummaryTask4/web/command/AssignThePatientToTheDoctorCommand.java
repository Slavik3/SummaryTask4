package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * Add doctor_id to patient and add count of patient on the doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class AssignThePatientToTheDoctorCommand extends Command {
	
	private static final long serialVersionUID = -3544441317561807551L;
	
	private static final Logger LOG = Logger.getLogger(AssignThePatientToTheDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("AssignThePatientToTheDoctorCommand starts");
		
		Patient patient = new Patient();
		int id = Integer.valueOf(request.getParameter("id"));
		int doctor_id = Integer.valueOf(request.getParameter("doctor_id"));
		
		System.out.println("id "+id);
		System.out.println("doctor_id "+doctor_id);
		
		//station.setName(name);
		patient.setId(id);
		patient.setDoctorId(doctor_id);
		
		DBManager.getInstance().addDoctorToPatient(patient);		
		
		User doctor =  DBManager.getInstance().getUserById(doctor_id);		
		doctor.setId(doctor_id);
		
		int countOfPatients = doctor.getCountOfPatients();		
		countOfPatients++;		
		doctor.setCountOfPatients(countOfPatients);
		DBManager.getInstance().updateDoctorCountOfPatient(doctor);
		
		LOG.debug("AssignThePatientToTheDoctorCommand finished");
		
		return "/controller?command=listPatients";
	}

}
