package ua.nure.kozlov.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.entity.HospitalCard;
import ua.nure.kozlov.SummaryTask4.db.entity.Patient;
import ua.nure.kozlov.SummaryTask4.db.entity.Specialization;
import ua.nure.kozlov.SummaryTask4.db.entity.TypeOfTreatment;
import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * DB manager. Works with MySQL DB.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class DBManager {


	private static final Logger LOG = Logger.getLogger(DBManager.class);

	// //////////////////////////////////////////////////////////
	// SQL queries
	// //////////////////////////////////////////////////////////
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";	
	private static final String SQL_FIND_USER_BY_ID="select * from users where id=?";	
	private static final String SQL_FIND_PATIENT_BY_ID="select * from patients where id=?";
	private static final String SQL_SELECT_DOCTORS_FROM_USERS = "SELECT * FROM users JOIN specializations ON users.specialization_id =specializations.id";
	private static final String SQL_SELECT_ALL_FROM_SPECIALIZATION = "SELECT * FROM specializations";
	private static final String SQL_SELECT_ALL_FROM_TYPE_OF_TREATMENT = "SELECT * FROM type_of_treatment";
	private static final String SQL_SELECT_PEDIATRICIAN_FROM_USERS = "SELECT * FROM users JOIN specializations ON users.specialization_id =specializations.id WHERE users.specialization_id=1";
	private static final String SQL_SELECT_TRAUMATOLOGIST_FROM_USERS = "SELECT * FROM users JOIN specializations ON users.specialization_id =specializations.id WHERE users.specialization_id=2";
	private static final String SQL_SELECT_SURGEON_FROM_USERS = "SELECT * FROM users JOIN specializations ON users.specialization_id =specializations.id WHERE users.specialization_id=3";
	private static final String SQL_ADD_DOCTOR = "INSERT INTO users(first_name, last_name, login, password, role_id, specialization_id) VALUES (?,?,?,?,1,?)";
	private static final String GET_SPECIALIZATION_ID_BY_NAME = "SELECT id FROM specializations WHERE title = ?";
	private static final String SQL_ADD_PATIENT = "INSERT INTO patients(first_name, last_name, birthday) VALUES (?,?,?)";
	private static final String SQL_SELECT_ALL_FROM_PATIENTS = "SELECT * FROM  patients ORDER BY doctor_id";		
	private static final String SQL_ADD_DOCTOR_TO_PATIENT = "UPDATE patients SET doctor_id=? WHERE id=?";
	private static final String SQL_UPDATE_DOCTOR_COUNT_OF_PATIENTS = "UPDATE users SET count_of_patients=? WHERE id=?";	
	private static final String SQL_SELECT_ALL_FROM_PATIENTS_BY_DOCTOR = "SELECT * FROM  patients WHERE doctor_id=?";	
	private static final String SQL_SELECT_ALL_FROM_DISCHARGED_PATIENTS_BY_DOCTOR = "SELECT * FROM  discharged_patients WHERE doctor_id=?";	
	private static final String GET_TYPE_OF_TREATMENT_ID_BY_NAME = "SELECT id FROM type_of_treatment WHERE title = ?";		
	private static final String SQL_ADD_HOSPITAL_CARD = "INSERT INTO hospital_card(patient_id, type_of_treatment_id, name_of_medication, done) VALUES (?,?,?, 0)";
	private static final String SQL_UPDATE_PATIENT_DIAGNOS = "UPDATE patients SET diagnosis=? WHERE id=?";
	private static final String SQL_SELECT_ALL_FROM_HOSPITAL_CARD_BY_PATIENT_ID = "SELECT * FROM hospital_card JOIN type_of_treatment ON type_of_treatment.id = hospital_card.type_of_treatment_id JOIN patients ON patients.id = hospital_card.patient_id WHERE patient_id=?";
	private static final String SQL_SELECT_DONE_FROM_HOSPITAL_CARD_BY_PATIENT_ID = "SELECT id, done FROM hospital_card WHERE id=?";	
	private static final String SQL_UPDATE_PERFORMING_DESTINATION = "UPDATE hospital_card SET done=? WHERE id=?";	
	private static final String SQL_ADD_NURSE = "INSERT INTO users(first_name, last_name, login, password, role_id) VALUES (?,?,?,?,2)";
	private static final String SQL_GET_COUNT_OF_DOCTORS = "SELECT COUNT(*) FROM users WHERE role_id=1";	
	private static final String SQL_GET_COUNT_OF_NURSES = "SELECT COUNT(*) FROM users WHERE role_id=2";
	private static final String SQL_GET_COUNT_OF_PATIENTS = "SELECT COUNT(*) FROM patients";
	private static final String SQL_SELECT_ALL_FROM_PATIENTS_NURSE = "SELECT * FROM  patients";	
	private static final String SQL_SELECT_ALL_FROM_HOSPITAL_CARD_BY_PATIENT_ID_NURSE = "SELECT * FROM hospital_card JOIN type_of_treatment ON type_of_treatment.id = hospital_card.type_of_treatment_id JOIN patients ON patients.id = hospital_card.patient_id WHERE patient_id=? and type_of_treatment_id<>3";

	private static final String SQL_ADD_TO_COMPLETE_THE_COURSE_OF_TREATMENE = "INSERT INTO discharged_patients(first_name, last_name, birthday, doctor_id, diagnosis) VALUES (?,?,?,?,?)";
	private static final String SQL_REMOVE_PATIENT = "DELETE FROM patients WHERE id=?";
	
	private static final String SQL_UPDATE_COUNT_OF_PATIENTS = "UPDATE users SET count_of_patients=? WHERE id=?";
	
	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private static Connection con = null;


	/**
	* Returns a DB connection from the Pool Connections. Before using this
	* method you must configure the Date Source and the Connections Pool in
	* your WEB_APP_ROOT/META-INF/context.xml file.
	* 
	* @return A DB connection.
	*/
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			// ST4DB - the name of data source
			DataSource ds = (DataSource) envContext.lookup("jdbc/ST4DB");
			con = ds.getConnection();
		} catch (NamingException ex) {
			LOG.error("Cannot obtain a connection from the pool", ex);
		}
		return con;
	}
	

	/**
	 * Returns a user with the given login.
	 * 
	 * @param login
	 *            User login.
	 * @return User entity.
	 */
	public User findUserByLogin(String login) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain a user by its login", ex);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return user;
	}
	
	/**
	 * Get count of doctors.
	 *
	 * @return count Doctors
	 */
	public int getCountOfDoctors(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int countDoctors = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_COUNT_OF_DOCTORS);
			rs = pstmt.executeQuery();
			rs.next();
			countDoctors = rs.getInt(1);
		} catch (Exception e) {
			rollback(con);
			LOG.error(e);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return countDoctors;		
	}
	
	/**
	 * Get count of nurses.
	 * 
	 * @return count Nurses
	 */
	public int getCountOfNurse(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int countNurses = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_COUNT_OF_NURSES);
			rs = pstmt.executeQuery();
			rs.next();
			countNurses = rs.getInt(1);
		} catch (Exception e) {
			rollback(con);
			LOG.error(e);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return countNurses;		
	}
	
	/**
	 * Get count of patients
	 * 
	 * @return count Patients
	 */
	public int getCountOfPatient(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int countPatients = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_COUNT_OF_PATIENTS);
			rs = pstmt.executeQuery();
			rs.next();
			countPatients = rs.getInt(1);
		} catch (Exception e) {
			rollback(con);
			LOG.error(e);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return countPatients;		
	}
	
	
	/**
	 * Add doctor to database.
	 * 
	 * @param user
	 */
	public void addDoctor(User doctor) {
		Connection con = null;
		PreparedStatement ps = null;
		int specializationId = 0;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(GET_SPECIALIZATION_ID_BY_NAME);
			ps.setString(1, doctor.getSpecialization());
			rs = ps.executeQuery();
			if (rs.next()) {
				specializationId = rs.getInt("id");
				System.out.println("specializationId "+specializationId);
			}				
			ps = con.prepareStatement(SQL_ADD_DOCTOR);
			ps.setString(1, doctor.getFirstName());
			ps.setString(2, doctor.getLastName());
			ps.setString(3, doctor.getLogin());
			ps.setString(4, doctor.getPassword());
			ps.setInt(5, specializationId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be insert User", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Add nurse to database.
	 * 
	 * @param user
	 */
	public void addNurse(User nurse) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();						
			ps = con.prepareStatement(SQL_ADD_NURSE);
			ps.setString(1, nurse.getFirstName());
			ps.setString(2, nurse.getLastName());
			ps.setString(3, nurse.getLogin());
			ps.setString(4, nurse.getPassword());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be insert nurse", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Add patient to database
	 * 
	 * @param user
	 */
	public void addPatient(Patient patient) {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = getConnection();					
			ps = con.prepareStatement(SQL_ADD_PATIENT);						
			ps.setString(1, patient.getFirstName());
			ps.setString(2, patient.getLastName());		
			ps.setDate(3, patient.getBirthday());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be insert Patient", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Doctor add First name, Last name, Diagnosis, Type of treatment, Title treatment to hospital card
	 * 
	 * @param hospitalCard
	 */
	public void addHospitalCard(HospitalCard hospitalCard) {
		Connection con = null;
		PreparedStatement ps = null;
		int typeOfTreatment = 0;
		ResultSet rs = null;
		try {
			con = getConnection();			
			ps = con.prepareStatement(GET_TYPE_OF_TREATMENT_ID_BY_NAME);
			ps.setString(1, hospitalCard.getTypeOfTreatment());	
			rs = ps.executeQuery();
			if (rs.next()) {
				typeOfTreatment = rs.getInt("id");
			}				
			ps = con.prepareStatement(SQL_ADD_HOSPITAL_CARD);	
			ps.setInt(1, hospitalCard.getPatientId());	
			ps.setInt(2, typeOfTreatment);	
			ps.setString(3, hospitalCard.getNameOfMedication());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be insert HospitalCard", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Edit patient diagnos.
	 * 
	 * @param patient
	 */
	public void editPatientDiagnos(Patient patient) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(SQL_UPDATE_PATIENT_DIAGNOS);
			ps.setString(1, patient.getDiagnos());
			ps.setLong(2, patient.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be edit patient diagnos", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}


	/**
	 * Find user by id.
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(int id) {
		PreparedStatement pStmt = null;
		Connection conn = null;
		User user = new User();
		try {
			conn = getConnection();			
			pStmt = conn.prepareStatement(SQL_FIND_USER_BY_ID);
			pStmt.setLong(1, id);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				user.setId(rSet.getInt("id"));
				user.setLogin(rSet.getString("login"));
				user.setFirstName(rSet.getString("first_name"));
				user.setLastName(rSet.getString("last_name"));				
				user.setCountOfPatients(rSet.getInt("count_of_patients"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(pStmt);
			commitAndClose(con);
		}
		return user;
	}
	
	/**
	 * Find patient by id
	 * 
	 * @param id
	 * @return patient
	 */
	public Patient getPatientById(int id) {
		PreparedStatement pStmt = null;
		Connection conn = null;
		Patient patient = new Patient();
		try {
			conn = getConnection();			
			pStmt = conn.prepareStatement(SQL_FIND_PATIENT_BY_ID);
			pStmt.setLong(1, id);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				patient.setId(rSet.getInt("id"));
				patient.setFirstName(rSet.getString("first_name"));
				patient.setLastName(rSet.getString("last_name"));
				patient.setDiagnos(rSet.getString("diagnosis"));
				patient.setBirthday(rSet.getDate("birthday"));
				patient.setDoctorId(rSet.getInt("doctor_id"));
				patient.setDiagnos(rSet.getString("diagnosis"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(pStmt);
			commitAndClose(con);
		}
		return patient;
	}
	
	/**
	 * Get hospital card to perform disinfection for in next code update done to 1 - true
	 * 
	 * @param id
	 * @return hospitalCard object
	 */
	public HospitalCard getHospitalCardById(int id) {
		PreparedStatement pStmt = null;
		Connection conn = null;
		HospitalCard hospitalCard = new HospitalCard();
		try {
			conn = getConnection();			
			pStmt = conn.prepareStatement(SQL_SELECT_DONE_FROM_HOSPITAL_CARD_BY_PATIENT_ID);
			pStmt.setInt(1, id);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				hospitalCard.setId(rSet.getInt("id"));
				hospitalCard.setDone(rSet.getBoolean("done"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(pStmt);
			commitAndClose(con);
		}
		return hospitalCard;
	}
	
	
	/**
	 * Get all doctors.
	 * 
	 * @return list of doctors
	 */
	public List<User> getAllDoctors() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<User> users = new ArrayList<User>();
		try {
			con = getConnection();			
			pstmt = con.prepareStatement(SQL_SELECT_DOCTORS_FROM_USERS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setSpecialization(rs.getString("title"));
				user.setCountOfPatients(rs.getInt("count_of_patients"));
				users.add(user);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain get doctors");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return users;
	}
	
	/**
	 * Get all doctors specializations.
	 * 
	 * @return list of doctors specialization
	 */
	public List<Specialization> getAllDoctorsSpecializations() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Specialization> users = new ArrayList<Specialization>();
		try {
			con = getConnection();			
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_SPECIALIZATION);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Specialization specialization = new Specialization();			
				specialization.setTitle(rs.getString("title"));
				users.add(specialization);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain get doctors specializations");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return users;
	}
	
	/**
	 * Get all type of treatment.
	 * 
	 * @return list of type of treatment
	 */
	public List<TypeOfTreatment> getAllTypeOfTreatment() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<TypeOfTreatment> typeOfTreatments = new ArrayList<TypeOfTreatment>();
		try {
			con = getConnection();			
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_TYPE_OF_TREATMENT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TypeOfTreatment typeOfTreatment = new TypeOfTreatment();			
				typeOfTreatment.setTitle(rs.getString("title"));
				typeOfTreatments.add(typeOfTreatment);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain get types of treatment");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return typeOfTreatments;
	}
	
	/**
	 * Get pediatrician doctors.
	 * 
	 * @return list of doctors
	 */
	public List<User> getPediatricianDoctors() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<User> users = new ArrayList<User>();
		try {
			con = getConnection();			
			pstmt = con.prepareStatement(SQL_SELECT_PEDIATRICIAN_FROM_USERS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setSpecialization(rs.getString("title"));
				user.setCountOfPatients(rs.getInt("count_of_patients"));
				users.add(user);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return users;
	}
	
	/**
	 * Get traumatologist doctors.
	 * 
	 * @return list of doctors
	 */
	public List<User> getTraumatologistDoctors() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<User> users = new ArrayList<User>();
		try {
			con = getConnection();			
			pstmt = con.prepareStatement(SQL_SELECT_TRAUMATOLOGIST_FROM_USERS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setSpecialization(rs.getString("title"));
				user.setCountOfPatients(rs.getInt("count_of_patients"));
				users.add(user);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return users;
	}
	
	/**
	 * Get surgeon doctors.
	 * 
	 * @return list of doctors
	 */
	public List<User> getSurgeonDoctors() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<User> users = new ArrayList<User>();
		try {
			con = getConnection();			
			pstmt = con.prepareStatement(SQL_SELECT_SURGEON_FROM_USERS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setSpecialization(rs.getString("title"));
				user.setCountOfPatients(rs.getInt("count_of_patients"));
				users.add(user);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return users;
	}
	
	/**
	 * 
	 * @return list of patients
	 */
	public List<Patient> getAllPatients() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Patient> patients = new ArrayList<Patient>();
		try {
			con = getConnection();			
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_PATIENTS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));	
				patient.setBirthday(rs.getDate("birthday"));	
				patient.setDoctorId(rs.getInt("doctor_id"));	
				patients.add(patient);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return patients;
	}
	
	/**
	 * After login doctor get all his patients.
	 * 
	 * @return list of patients
	 */
	public List<Patient> getAllPatientsByDoctor(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Patient> patients = new ArrayList<Patient>();
		try {
			con = getConnection();						
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_PATIENTS_BY_DOCTOR);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));	
				patient.setBirthday(rs.getDate("birthday"));
				patient.setDiagnos(rs.getString("diagnosis"));
				patient.setDoctorId(rs.getInt("doctor_id"));	
				patients.add(patient);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return patients;
	}
	
	/**
	 * Get all cured patients by doctor id.
	 * 
	 * @param id
	 * @return patients
	 */
	public List<Patient> getAllCuredPatientsByDoctor(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Patient> patients = new ArrayList<Patient>();
		try {
			con = getConnection();						
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_DISCHARGED_PATIENTS_BY_DOCTOR);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));	
				patient.setBirthday(rs.getDate("birthday"));	
				patient.setDoctorId(rs.getInt("doctor_id"));
				patient.setDiagnos(rs.getString("diagnosis"));
				patients.add(patient);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return patients;
	}
	
	/**
	 * After login doctor get all patients.
	 * 
	 * @return list of patients
	 */
	public List<Patient> getAllPatientsNurse() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Patient> patients = new ArrayList<Patient>();
		try {
			con = getConnection();						
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_PATIENTS_NURSE);			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt("id"));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));	
				patient.setBirthday(rs.getDate("birthday"));
				patient.setDiagnos(rs.getString("diagnosis"));
				patient.setDoctorId(rs.getInt("doctor_id"));	
				patients.add(patient);							
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return patients;
	}
	
	
	/**
	 * Getting all appointments for the selected patient and their subsequent treatment.
	 * 
	 * @param patientId
	 * @return hospitalCards
	 */
	public List<HospitalCard> getAllHospitalCardByPatientId(int patientId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<HospitalCard> hospitalCards = new ArrayList<HospitalCard>();
		try {
			con = getConnection();	
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_HOSPITAL_CARD_BY_PATIENT_ID);
			pstmt.setInt(1, patientId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HospitalCard hospitalCard = new HospitalCard();
				hospitalCard.setId(rs.getInt("id"));
				hospitalCard.setLastName(rs.getString("last_name"));//name of patient
				hospitalCard.setFirstNamePatient(rs.getString("first_name"));
				hospitalCard.setTypeOfTreatment(rs.getString("title"));
				hospitalCard.setNameOfMedication(rs.getString("name_of_medication"));
				hospitalCard.setDone(rs.getBoolean("done"));
				System.out.println("hospitalCard "+ hospitalCard);
				hospitalCards.add(hospitalCard);
				
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return hospitalCards;
	}
	
	/**
	 * Getting procedures, drugs appointments for the selected patient and their subsequent treatment.
	 * 
	 * @param patientId
	 * @return hospitalCards
	 */
	public List<HospitalCard> getAllHospitalCardByPatientIdNurse(int patientId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<HospitalCard> hospitalCards = new ArrayList<HospitalCard>();
		try {
			con = getConnection();	
			pstmt = con.prepareStatement(SQL_SELECT_ALL_FROM_HOSPITAL_CARD_BY_PATIENT_ID_NURSE);
			pstmt.setInt(1, patientId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HospitalCard hospitalCard = new HospitalCard();
				hospitalCard.setId(rs.getInt("id"));
				hospitalCard.setLastName(rs.getString("last_name"));//name of patient
				hospitalCard.setTypeOfTreatment(rs.getString("title"));
				hospitalCard.setNameOfMedication(rs.getString("name_of_medication"));
				hospitalCard.setDone(rs.getBoolean("done"));
				hospitalCards.add(hospitalCard);
				
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error("Cannot obtain ");
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return hospitalCards;
	}
	
	
	/**
	 * Add doctor to patient by id.
	 * 
	 * @param station
	 */
	public void addDoctorToPatient(Patient patient) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(SQL_ADD_DOCTOR_TO_PATIENT);
			ps.setInt(1, patient.getDoctorId());
			ps.setLong(2, patient.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be edit Patient", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Update doctor count of patient.
	 * 
	 * @param admin
	 */
	public void updateDoctorCountOfPatient(User admin) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(SQL_UPDATE_DOCTOR_COUNT_OF_PATIENTS);
			ps.setInt(1, admin.getCountOfPatients());
			ps.setLong(2, admin.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be edit doctor count of patient", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Update done in hospital card.
	 * 
	 * @param hospitalCard
	 */
	public void updateIsDoneProcedure(HospitalCard hospitalCard) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(SQL_UPDATE_PERFORMING_DESTINATION);
			ps.setBoolean(1, hospitalCard.isDone());
			ps.setInt(2, hospitalCard.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be edit doctor count of patient", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Complete the course of treatment.
	 * Adding a patient to the table "discharged_patients" and remove the patient from the table "patients".
	 * 
	 * @param patient
	 */
	public void completeTheCourseOfTreatment(Patient patient){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(SQL_ADD_TO_COMPLETE_THE_COURSE_OF_TREATMENE);
			ps.setString(1, patient.getFirstName());
			ps.setString(2, patient.getLastName());		
			ps.setDate(3, patient.getBirthday());			
			ps.setInt(4, patient.getDoctorId());
			ps.setString(5, patient.getDiagnos());
			ps.executeUpdate();
			
			int id = patient.getId();
			ps = con.prepareStatement(SQL_REMOVE_PATIENT);
			ps.setInt(1,id);
			ps.executeUpdate();	
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be complete the course of treatment ", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	/**
	 * Edit count of patients after discharge.
	 * 
	 * @param doctor
	 */
	public void editCountOfPatients(User doctor) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(SQL_UPDATE_COUNT_OF_PATIENTS);
			ps.setInt(1, doctor.getCountOfPatients()-1);
			ps.setInt(2, doctor.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot be edit count of patients", ex);
		} finally {
			close(ps);
			commitAndClose(con);
		}
	}
	
	

	// //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Commits and close the given connection.
	 * 
	 * @param con
	 *            Connection to be committed and closed.
	 */
	private void commitAndClose(Connection con) {
		if (con != null) {
			try {
				con.commit();
				con.close();
			} catch (SQLException ex) {
				LOG.error("Cannot commit transaction and close connection", ex);
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a result set", ex);
			}
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a statement", ex);
			}
		}
	}

	/**
	 * Rollbacks and close the given connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked and closed.
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	/**
	 * Extracts a user entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return User entity
	 */
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		user.setLastName(rs.getString(Fields.USER_LAST_NAME));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID)); 
		return user;
	}

}
