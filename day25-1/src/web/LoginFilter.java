package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.TUser;
import service.LoginService;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			TUser tuser = (TUser) req.getSession().getAttribute("tuser");
			if (tuser != null) {
				chain.doFilter(req, res);
				return;
			}
			Cookie[] cookies = req.getCookies();
			String str = null;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					if (name.equals("mes")) {
						str = cookie.getValue();
					}
				}
			}
			if (str == null) {
				chain.doFilter(req, res);
				return;
			}
			String[] strs = str.split(":");
			String username = strs[0];
			String password = strs[1];
			LoginService ls = new LoginService();
			tuser = ls.find(username, password);

			if (tuser == null) {
				Cookie cookie = new Cookie("mes", username + ":" + password);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				res.addCookie(cookie);
				chain.doFilter(req, res);
				return;
			}
			req.getSession().setAttribute("tuser", tuser);
			chain.doFilter(req, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
