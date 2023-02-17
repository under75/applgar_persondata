package ru.tfoms.applgar.interceptor;

import static ru.tfoms.applgar.util.Constants.HSMO_ROLE;
import static ru.tfoms.applgar.util.Constants.SMO_ROLE;
import static ru.tfoms.applgar.util.Constants.TFOMS_ROLE;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizedUserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String exit = request.getParameter("exit");
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if ((session != null && exit != null && exit.equals("1"))) {
			session.invalidate();
			session = null;
		}
		if (session == null || session.getAttribute("user") == null) {
			((HttpServletResponse) response).sendRedirect("/");
		} else {
			if (!hasRoleToAccess((HttpServletRequest) request, (String) session.getAttribute("roles"))) {
				session.invalidate();
				session = null;
				((HttpServletResponse) response).sendRedirect("/");
			} else
				chain.doFilter(request, response);
		}
	}

	private boolean hasRoleToAccess(HttpServletRequest request, String roles) {
		boolean permitted = false;
		if (roles.contains(SMO_ROLE) || roles.contains(HSMO_ROLE)) {
			if (request.getRequestURI().contains("/appl") || request.getRequestURI().contains("/smo")) {
				permitted = true;
			}
		} else if (roles.contains(TFOMS_ROLE)) {
			if (request.getRequestURI().contains("/pers")) {
				permitted = true;
			}
		}

		return permitted;
	}

}
