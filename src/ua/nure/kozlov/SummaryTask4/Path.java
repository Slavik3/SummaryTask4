package ua.nure.kozlov.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author Vyacheslav Kozlov
 * 
 */
public class Path {

	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";	

	// commands
	public static final String COMMAND_LIST_DOCTOR = "/controller?command=listDoctors";
	public static final String COMMAND_LIST_PATIENT = "/controller?command=listPatientsDoctor";
	public static final String COMMAND_LIST_PATIENT_NURSE = "/controller?command=listPatientsNurse";

}
