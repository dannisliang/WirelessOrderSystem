package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.entity.Order;

/**
 * @author KeXu
 * Start a new table on Servlet£¬and save information in table OrderTbl
 */
public class StartTableServlet extends HttpServlet {
	public StartTableServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String orderTime = request.getParameter("orderTime");
		String userId = request.getParameter("userId");
		String tableId = request.getParameter("tableId");
		String personNum = request.getParameter("personNum");
		OrderDao dao = new OrderDaoImpl();
		Order o = new Order();
		o.setOrderTime(orderTime);
		o.setPersonNum(Integer.parseInt(personNum));
		o.setUserId(Integer.parseInt(userId));
		o.setTableId(Integer.parseInt(tableId));
		int id = dao.saveOrder(o);
		
		dao.updateTableStatus(Integer.parseInt(tableId));
		out.print(id);
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
