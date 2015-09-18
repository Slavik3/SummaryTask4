package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kozlov.SummaryTask4.db.DBManager;
import ua.nure.kozlov.SummaryTask4.db.entity.User;


/**
 * List doctor.
 * 
 * @author Vyacheslav Kozlov
 *
 */

public class ListDoctorCommand extends Command {
	
	private static final long serialVersionUID = 3857701804323625580L;
	
	private static final Logger LOG = Logger.getLogger(ListDoctorCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("ListDoctorCommand starts");
		
		String url = request.getHeader("referer");//получаем УРЛ, с которого проишол пользователь
		System.out.println("url" + url);
		if(url.equals("http://localhost:8080/SummaryTask4/controller?command=viewAddDoctor")){//количество записей прибавилось на еденицу
			System.out.println("Пользователь добавлен");
		}
				
		List<User> doctorsList = DBManager.getInstance().getAllDoctors();
		LOG.trace("Found in DB: doctorList --> " + doctorsList);
		
		// put user order beans list to request
		request.getSession().setAttribute("doctorsBeanList", doctorsList);		
		LOG.trace("Set the request attribute: doctorList --> " + doctorsList);
		
		LOG.debug("ListDoctorCommand finished");
		return "/WEB-INF/jsp/admin/listDoctors.jsp";
	}

}
