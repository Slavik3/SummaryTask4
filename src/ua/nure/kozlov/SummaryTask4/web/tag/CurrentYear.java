package ua.nure.kozlov.SummaryTask4.web.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CurrentYear extends SimpleTagSupport {
	 public void doTag() throws JspException, IOException {
		    JspWriter out = getJspContext().getOut();
		    Calendar cal = new GregorianCalendar();
		    out.println(new SimpleDateFormat("yyyy").format(cal.getTime()));
		  }
}
