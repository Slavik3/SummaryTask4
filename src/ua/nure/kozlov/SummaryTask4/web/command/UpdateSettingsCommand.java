package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;


/**
 * Update settings items.
 * 
 * @author Vyacheslav Kozlov
 * 
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(UpdateSettingsCommand.class);
	
	public String urlLocale;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		LOG.debug("UpdateSettingsCommand starts");
		
		// UPDATE USER ////////////////////////////////////////////////////////
		
		Object roleOb = request.getSession().getAttribute("userRole");
		String role = null;
		if(roleOb!=null) {
			role = roleOb.toString();
		}
		
		String localeToSet = request.getParameter("localeToSet");
		if (localeToSet != null && !localeToSet.isEmpty()) {
			HttpSession session = request.getSession();
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);			
			request.getSession().setAttribute("defaultLocale", localeToSet);
			//
			//ServletContextEvent event = null;
			//Config.set(("javax.servlet.jsp.jstl.fmt.locale", localeToSet));
			//
			LOG.trace("User locale was set: localeToSet --> " + localeToSet);
		}
				
		String url = request.getHeader("referer");
		LOG.trace("url " + url);
		
		String cutUrl = url.replace("http://localhost:8080/SummaryTask4","");
		LOG.trace("cut Url " + cutUrl);
		
		String ru = "/controller?command=updateSettings&localeToSet=ru";
		String en = "/controller?command=updateSettings&localeToSet=en";
		if ((cutUrl.equals(en)) || (cutUrl.equals(ru) || (!cutUrl.contains("command")) )){		
			System.out.println("urlTmp " + urlLocale);
		}
		else {
			//change locale second or more time
			urlLocale = cutUrl;
			System.out.println("urlTmp " + urlLocale);
		}
		//If you update the language repeatedly
		if (cutUrl.contains("updateSettings")){//???
			System.out.println("urlTmp " + urlLocale);
			System.out.println("role========> "+role);
			if ((urlLocale==null) && (role==null)) {
				System.out.println("urlTmp==null");
				return "login.jsp";
			} 
			
			if (urlLocale==null && role=="ADMIN") {
				return "/controller?command=listDoctors";
			}
			
			if (urlLocale==null && role=="DOCTOR") {
				return "/controller?command=listPatientsDoctor";
			}
			
			if (urlLocale==null && role=="NURSE") {
				return "/controller?command=listPatientsNurse";
			}
			
			if(url.equals("http://localhost:8080/SummaryTask4/controller?command=updateSettings&localeToSet=ru") && urlLocale.equals("/controller?command=logout")) {
				return "login.jsp";
			}
			
			else {
				return urlLocale;//
			}
		}
		//if there are no command - listDoctors - first page
		else if(!cutUrl.contains("command")){
			System.out.println("no command");
			
			//еще не залогинился
			if(roleOb==null){
				System.out.println("roleOb=null");
				return "login.jsp";
			}
			
			if(role=="ADMIN") {
				return "/controller?command=listDoctors";
			}
			
			if(role=="DOCTOR") {
				return "/controller?command=listPatientsDoctor";
			}
			
			else {
				return "/controller?command=listPatientsNurse";
			}
			
		}
		//if come from addDoctor
		else if(url.equals("http://localhost:8080/SummaryTask4/controller?command=addDoctor")) {
			urlLocale="/controller?command=viewAddDoctor";
			return "/WEB-INF/jsp/admin/addDoctor.jsp";
		}
		//if come from addNurse
		else if(url.equals("http://localhost:8080/SummaryTask4/controller?command=addNurse")) {
			urlLocale="/controller?command=viewAddNurse";
			return "/WEB-INF/jsp/admin/addNurse.jsp";
		}
		
		else if(url.equals("http://localhost:8080/SummaryTask4/controller?command=logout")) {
			return "login.jsp";
		}
		
		else {
		LOG.debug("UpdateSettingsCommand finished");
		return cutUrl;
		}
	}

}