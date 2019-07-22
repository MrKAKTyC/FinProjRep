package test.ua.nure.nechaev.summarytask;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.entity.AccessLevel;
import ua.nure.nechaev.summarytask.db.entity.Manager;
import ua.nure.nechaev.summarytask.web.filter.AccesFilter;

public class TestFilter {

	private static String ADMIN = "managerList addNewManager managerEdit "
			+ "deleteManager updateManager workerList workerAddNew workerEdit "
			+ "workerUpdate workerDelete flightAddNew flightEdit flightUpdate "
			+ "flightDelete reportList reportUpdate flightList crewList " + "crewAddNew crewDelete reportAdd";

	private static String DISPATCHER = "flightList crewList flightEdit crewAddNew crewDelete reportAdd";

	@Mock
	private HttpSession session;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain chain;

	@Mock
	private FilterConfig config;

	private AccesFilter filter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(config.getInitParameter("Administrator")).thenReturn(ADMIN);
		when(config.getInitParameter("Dispatcher")).thenReturn(DISPATCHER);
		when(config.getInitParameter("out-of-control")).thenReturn("");
		when(request.getSession(false)).thenReturn(session);
		when(request.getSession()).thenReturn(session);
		filter = new AccesFilter();
		filter.init(config);
	}

	@Test
	public void testDispatcherCannotAccessManagerList() throws Exception {
		Manager manager = new Manager();
		manager.setLevel(AccessLevel.Dispatcher);
		String command = "managerList";

		when(request.getParameter("command")).thenReturn(command);
		when(session.getAttribute("user")).thenReturn(manager);

		filter.doFilter(request, response, chain);

		verify(response).sendRedirect(Path.PAGE_LOGIN);
	}

	@Test
	public void testAdminAllowAccessManagerList() throws Exception {
		Manager manager = new Manager();
		manager.setLevel(AccessLevel.Administrator);
		String command = "managerList";

		when(request.getParameter("command")).thenReturn(command);
		when(session.getAttribute("user")).thenReturn(manager);

		filter.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
//		FrontController servlet = Mockito.spy(new FrontController());
//		servlet.init();
//		CommandContainer commands = new CommandContainer();
//		commands.add(command, new ManagerListCommand());
//		ServletContext context = Mockito.mock(ServletContext.class);
//		when(context.getAttribute("commands")).thenReturn(commands);
//		Mockito.doReturn(context).when(servlet).getServletContext();
//		servlet.doGet(request, response);
//		verify(request).getRequestDispatcher("");
	}

}
