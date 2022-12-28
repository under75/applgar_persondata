package ru.tfoms.applgar.interceptor;

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
		if (session != null && exit != null && exit.equals("1")) {
			session.invalidate();
			session = null;
		}
		if (session == null || session.getAttribute("user") == null) {
			((HttpServletResponse) response).sendRedirect("/");
		} else {
			chain.doFilter(request, response);
		}
	}

}
