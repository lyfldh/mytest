package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.TUser;
import service.LoginService;

public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginService ls=new LoginService();
			TUser tuser=ls.find(username,password);
			if(tuser!=null){
				String autoLogin = request.getParameter("autoLogin");
				if(autoLogin.equals("1")){
					Cookie cookie=new Cookie("mes", username+":"+password);
					cookie.setPath("/");
					cookie.setMaxAge(60*60);
					response.addCookie(cookie);
				}
				request.getSession().setAttribute("tuser", tuser);
				response.sendRedirect(request.getContextPath()+"/success.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
