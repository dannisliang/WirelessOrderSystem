package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.CheckTableDao;
import com.amaker.dao.impl.CheckTableDaoImpl;
import com.amaker.entity.CheckTable;
/**
 * @author KeXu
 * Function of checking the status of a table 
 */
public class CheckTableServlet extends HttpServlet {
	public CheckTableServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		CheckTableDao dao = new CheckTableDaoImpl();
		List list = dao.getTableList();
		String msg = build(list);
		out.print(msg);
		out.flush();
		out.close();
	}
	private String build(List list){
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			CheckTable ct = (CheckTable) list.get(i);
			int num = ct.getNum();
			int flag = ct.getFlag();
			msg+=num+","+flag;
			if(i<(list.size()-1))msg+=";";
		}
		return msg;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}

}
