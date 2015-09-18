package ua.nure.kozlov.SummaryTask4.web.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Download file extracts the selected patient.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public class DownloadFileCommand extends Command {
	
	private static final long serialVersionUID = 214246385783210130L;
	
	private static final Logger LOG = Logger.getLogger(DownloadFileCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("DownloadFileCommand starts");
		
		String patientName = new String(request.getParameter("patientName").getBytes("ISO-8859-1"), "UTF-8");
		String patientLastName = new String(request.getParameter("patientLastName").getBytes("ISO-8859-1"), "UTF-8");		
		// to obtain the bytes for unsafe characters
		String fileName = URLEncoder.encode(patientLastName+patientName+".txt", "UTF-8");
		fileName = URLDecoder.decode(fileName, "ISO8859_1");
		String filePath = "../workspace/SummaryTask4/WebContent/WEB-INF/ñompleteTheCourseOfTreatment/"+patientLastName+patientName+".txt";
        
        response.setHeader("Content-disposition","attachment; filename="+fileName);       
        File my_file = new File(filePath);

        // This should send the file to browser
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
           out.write(buffer, 0, length);
        }
        in.close();
        out.flush();		
		
		LOG.debug("DownloadFileCommand finished");
		return null;
	}

}
