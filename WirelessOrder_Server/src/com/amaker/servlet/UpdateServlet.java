package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UpdateDao;
import com.amaker.dao.impl.UpdateDaoImpl;
import com.amaker.entity.Menu;

/**
 * @author KeXu
 * Function of refreshing information
 */
public class UpdateServlet extends HttpServlet {
	public UpdateServlet() {
		super();
	}
	public void destroy() {
		super.destroy();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		UpdateDao dao = new UpdateDaoImpl();
		List list = dao.getMenuList();
		
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		out.println("<menulist>");
			for (int i = 0; i <list.size(); i++) {
				Menu m = (Menu)list.get(i);
				out.println("<menu>");
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					out.print("<typeId>");
						out.print(m.getTypeId());
					out.println("</typeId>");
					out.print("<name>");
						out.print(m.getName());
					out.println("</name>");
					out.print("<pic>");
						out.print(m.getPic());
					out.println("</pic>");
					out.print("<price>");
						out.print(m.getPrice());
					out.println("</price>");
					out.print("<remark>");
						out.print(m.getRemark());
					out.println("</remark>");
					
				out.println("</menu>");
			}
		out.println("</menulist>");
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}
}
