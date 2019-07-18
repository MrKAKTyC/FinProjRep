package ua.nure.nechaev.summarytask;

public final class Path {
	//pages
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_ADMIN_MENU = "/WEB-INF/jsp/admin/options.jsp";
	public static final String PAGE_LIST_FLIGHTS = "/WEB-INF/jsp/admin/flights.jsp";
	public static final String PAGE_LIST_WORKERS = "/WEB-INF/jsp/admin/workers.jsp";
	public static final String PAGE_LIST_MANAGERS = "/WEB-INF/jsp/admin/managers.jsp";
	public static final String PAGE_LIST_REPORTS = "/WEB-INF/jsp/admin/reports.jsp";
	public static final String PAGE_EDIT_MANAGER = "/WEB-INF/jsp/admin/manager.jsp";
	public static final String PAGE_EDIT_WORKER = "/WEB-INF/jsp/admin/worker.jsp";
	public static final String Page_EDIT_FLIGHT = "/WEB-INF/jsp/admin/flight.jsp";
	public static final String PAGE_EDIT_CREW = "/WEB-INF/jsp/admin/crew.jsp";
	public static final String INDEX_PAGE = "index.jsp";
	
	//commands
	public static final String SHOW_ADMIN_MENU = "./Controller?command=adminMenu";
	public static final String SHOW_FLIGHTS_LIST = "./Controller?command=flightList";
}
