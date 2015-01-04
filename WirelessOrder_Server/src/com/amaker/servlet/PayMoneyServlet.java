package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.PayDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.dao.impl.PayDaoImpl;
/**
 * @author KeXu
 * Function of checkin
 */
public class PayMoneyServlet extends HttpServlet {
	public PayMoneyServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		PayDao dao = new PayDaoImpl();
		String id = request.getParameter("id");
		dao.pay(Integer.parseInt(id));
		OrderDao dao2 = new OrderDaoImpl();
		dao2.updateTableStatus2(Integer.parseInt(id));
		out.print("Bill paid£¡");
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
