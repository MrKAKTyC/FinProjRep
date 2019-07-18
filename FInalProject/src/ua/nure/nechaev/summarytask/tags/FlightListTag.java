package ua.nure.nechaev.summarytask.tags;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.nechaev.summarytask.db.bean.FlightBean;
import ua.nure.nechaev.summarytask.db.entity.AccessLevel;
import ua.nure.nechaev.summarytask.db.entity.Manager;

public class FlightListTag extends SimpleTagSupport {

	private List<FlightBean> flights = new LinkedList<FlightBean>();

	private Manager user;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Manager user = null;
		try {
			user = (Manager) getJspContext().getAttribute("user", PageContext.SESSION_SCOPE);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		out.println(user);
		out.println("Here will be flightList for user: WIP ");
		out.println(getHeader());
		try {
			for (FlightBean fligt : flights) {
				out.println(getRow(fligt));
			}
			out.println("</table>");
		} catch (NullPointerException e) {
			out.print("Sorry no flights");
			e.printStackTrace();
		}
		super.doTag();
	}

	private String getHeader() {
		StringBuilder sb = new StringBuilder();
		if (user == null) {
			sb.append(
					"<table><tr><th><a href=\"./Controller?command=flights&action=sort&field=flightNumb\">FlightNumber</a></th><th><a href=\"./Controller?command=flights&action=sort&field=flightName\">FlightName</a></th><th>Departure Point</th><th>Arrival Point</th><th>Date</th><th>Time</th><th>Flight Status</th></tr>");
		} else {
			sb.append(
					"<table><tr><th>FlightNumber</th><th>FlightName</th><th>Departure Point</th><th>Arrival Point</th><th>Date</th><th>Time</th><th>Flight Status</th><th>Action</th></tr>");
		}
		return sb.toString();
	}

	private String getRow(FlightBean flight) {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr><td>" + flight.getId() + "</td><td>" + flight.getName() + "</td><td>" + flight.getFrom()
				+ "</td><td>" + flight.getTo() + "</td><td>" + flight.getDate() + "</td><td>" + flight.getTime() + "</td><td" + flight.getTime()
				+ "</td><td>" + flight.getStatus() + "</td>");
		if (user != null && user.getLevel() == AccessLevel.Administrator) {
			sb.append("<td><button onclick='document.location.href=\"./Controller?command=flightEdit&id="
					+ flight.getId()
					+ "'>Mod</button><button onclick='document.location.href=\"./Controller?command=crewList&id="
					+ flight.getId() + "'>Crew</button></td>");
		} else if (user != null && user.getLevel() == AccessLevel.Dispatcher) {
			sb.append("<td><button onclick='document.location.href=\"./Controller?command=crewList&id=" + flight.getId()
					+ "'>Crew</button></td>");
		}
		sb.append("</tr>");
		return sb.toString();
	}

	public void setFlights(List<FlightBean> flights) {
		this.flights = flights;
	}
	// @Override
//	public int doStartTag() throws JspException {
//		// TODO Auto-generated method stub
//		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
//		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
//		HttpSession session = request.getSession();
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
////        Menu menu = (Menu)session.getAttribute("app.menu");
//		out.println("Here will be flightList for user: WIP "+session.getAttribute("user"));
//		return super.doStartTag();
//	}

}
