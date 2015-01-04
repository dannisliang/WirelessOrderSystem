package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.ChangeTableDao;
import com.amaker.dao.impl.ChangeTableDaoImpl;
/**
 * @author KeXu
 * Function to transfer one table to another
 */
public class ChangeTableServlet extends HttpServlet {
	public ChangeTableServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	// Response to request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Get response
		String orderId = request.getParameter("orderId");
		String tableId = request.getParameter("tableId");
		// Initialization of ChangeTableDao
		ChangeTableDao dao = new ChangeTableDaoImpl();
		// changeTable
		dao.changeTable(Integer.parseInt(orderId), Integer.parseInt(tableId));
		// Return infor
		out.println("You have changed the table£¡");
		out.flush();
		out.close();
	}
	// Resonse to post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// Initialize the method 
	public void init() throws ServletException {
	}

}
