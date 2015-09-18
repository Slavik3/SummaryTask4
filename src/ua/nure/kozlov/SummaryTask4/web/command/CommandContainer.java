package ua.nure.kozlov.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;


/**
 * Class that manages all commands.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class CommandContainer {
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());		
		commands.put("noCommand", new NoCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());				
		
		//admin commands
		commands.put("addDoctor", new AddDoctorCommand());		
		commands.put("viewAddDoctor", new ViewAddDoctorCommand());
		commands.put("viewAddPatient", new ViewAddPatientCommand());
		commands.put("addPatient", new AddPatientCommand());
		commands.put("listDoctors", new ListDoctorCommand());
		commands.put("listPediatricians", new ListPediatricianCommand());		
		commands.put("listTraumatologist", new ListTraumatologistCommand());
		commands.put("listSurgeon", new ListSurgeonCommand());		
		commands.put("listPatients", new ListPatientsCommand());
		commands.put("sortDoctorsBySurname", new SortListDoctorsBySurnameCommand());
		commands.put("sortDoctorsByName", new SortListDoctorsByNameCommand());
		commands.put("sortPatientsByBirthday", new SortListPatientsByBirthdayCommand());
		commands.put("sortPatientsByName", new SortListPatientsByNameCommand());
		commands.put("sortDoctorsByNumberPatients", new SortListDoctorsByNumberPatientsCommand());		
		commands.put("viewAssignThePatientToTheDoctor", new ViewAssignThePatientToTheDoctorCommand());
		commands.put("assignThePatientToTheDoctor", new AssignThePatientToTheDoctorCommand());			
		commands.put("sortDoctorsByCategory", new SortListDoctorsByCategoryCommand());		
		commands.put("viewAddNurse", new ViewAddNurseCommand());
		commands.put("addNurse", new AddNurseCommand());
		
		//doctor commands
		commands.put("listPatientsDoctor", new ListPatientsDoctorCommand());
		commands.put("listCuredPatientsDoctor", new ListCuredPatientsDoctorCommand());
		commands.put("prescribeTreatment", new PrescribeTreatmentCommand());
		commands.put("addTreatmentToHospitalCard", new AddTreatmentToHospitalCardCommand());
		commands.put("viewToPerformDisinfection", new ViewToPerformDisinfectionCommand());
		commands.put("doctorToPerformTreatment", new DoctorToPerformTreatmentCommand());
		commands.put("completeTheCourseOfTreatment", new CompleteTheCourseOfTreatmentCommand());		
		commands.put("downloadFile", new DownloadFileCommand());
		
		//nurse commands
		commands.put("listPatientsNurse", new ListPatientsNurseCommand());
		commands.put("viewToPerformTreatmentByNurse", new ViewToPerformTreatmentByNurseCommand());
		commands.put("nurseToPerformTreatment", new NurseToPerformTreatmentCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object if container contains such command, otherwise
	 *         specific <code>noCommand</code object will be returned.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
}
