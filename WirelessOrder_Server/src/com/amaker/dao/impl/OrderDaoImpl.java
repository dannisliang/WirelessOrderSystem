package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.OrderDao;
import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.util.DBUtil;
/**
 * @author KeXu
 * Ordering by DAO
 */
public class OrderDaoImpl implements OrderDao {


	public int saveOrder(Order o) {

		String sql = " insert into ordertbl(orderTime,userId,tableId,personNum)values(?,?,?,?) ";

		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, o.getOrderTime());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getTableId());
			pstmt.setInt(4, o.getPersonNum());

			pstmt.executeUpdate();

			String sql2 = " select max(id) as id  from orderTbl ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			if(rs.next()){
				int id = rs.getInt(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return 0;
	}

	public void saveOrderDetail(OrderDetail od) {

		String sql = " insert into orderdetailtbl(orderId,menuId,num,remark)values(?,?,?,?) ";

		DBUtil util = new DBUtil();

		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, od.getOrderId());
			pstmt.setInt(2, od.getMenuId());
			pstmt.setInt(3, od.getNum());
			pstmt.setString(4, od.getRemark());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	

	public void updateTableStatus(int tableId) {

		String sql = " update tableTbl set flag=1 where id = ? ";

		DBUtil util = new DBUtil();

		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tableId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	public void updateTableStatus2(int orderId) {

		String sql = " update TableTbl set flag = 0 where id = "+
							" ( select tableId from OrderTbl where id = ?) ";

		DBUtil util = new DBUtil();

		Connection conn = util.openConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
}
