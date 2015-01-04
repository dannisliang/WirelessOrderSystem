package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.PayDao;
import com.amaker.dao.impl.PayDaoImpl;
import com.amaker.entity.QueryOrder;
import com.amaker.entity.QueryOrderDetail;

/**
 * @author KeXu
 * Request order information by order No
 */
public class PayServlet extends HttpServlet {
	public PayServlet() {
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
		QueryOrder qo = dao.getOrderById(Integer.parseInt(id));
		List list = dao.getOrderDetailList(Integer.parseInt(id));
		
		// HTML Display
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD></HEAD>");
		out.println("  <BODY>");
		out.print("<table>");
			out.print("<tr>");
			
				out.print("<th>");
					out.print("Order No");
				out.print("</th>");
				
				out.print("<th>");
					out.print("Order Time");
				out.print("</th>");
				
				out.print("<th>");
					out.print("Waiter");
				out.print("</th>");
				
				out.print("<th>");
					out.print("Number of Customers");
				out.print("</th>");
				
				out.print("<th>");
					out.print("Table No");
				out.print("</th>");
				
			out.print("</tr>");
			
			out.print("<tr>");
				
				out.print("<td>");
					out.print(id);
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getOrderTime());
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getName());
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getPersonNum());
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getTableId());
				out.print("</td>");
				
			out.print("</tr>");
			
			
			out.print("<tr>");
			
			out.print("<th>");
				out.print("Dish Name");
			out.print("</th>");
			
			out.print("<th>");
				out.print("Price");
			out.print("</th>");
			
			out.print("<th>");
				out.print("Quantity");
			out.print("</th>");
			
			out.print("<th>");
				out.print("Total");
			out.print("</th>");
			
			out.print("<th>");
				out.print("Remark");
			out.print("</th>");
			
		out.print("</tr>");
			
			for (int i = 0; i < list.size(); i++) {
				QueryOrderDetail qod = (QueryOrderDetail) list.get(i);
				String name = qod.getName();
				int price = qod.getPrice();
				int num = qod.getNum();
				int total = qod.getTotal();
				String remark = qod.getRemark();
				
				out.print("<tr>");
				
					out.print("<td>");
						out.print(name);
					out.print("</td>");
					
					out.print("<td>");
						out.print(price==0? "" :price+"");
					out.print("</td>");
					
					out.print("<td>");
						out.print(num==0? "" :num+"");
					out.print("</td>");
					
					out.print("<td>");
						out.print(total);
					out.print("</td>");
					
					out.print("<td>");
						out.print(remark);
					out.print("</td>");
					
				out.print("</tr>");
			}
		out.print("</table>");
		out.println("  </BODY>");
		out.println("</HTML>");
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
