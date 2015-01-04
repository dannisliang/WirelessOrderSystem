package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.UpdateDao;
import com.amaker.entity.Menu;
import com.amaker.util.DBUtil;

/**
 * @author KeXu
 *
 */
public class UpdateDaoImpl implements UpdateDao {
	public List getMenuList() {
		String sql =" select id,typeId,price,name,pic,remark from MenuTbl ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			List list = new ArrayList();
			while (rs.next()) {
				
				int id = rs.getInt(1);
				int typeId = rs.getInt(2);
				int price = rs.getInt(3);
				String name = rs.getString(4);
				String pic = rs.getString(5);
				String remark = rs.getString(6);
				
				Menu m = new Menu();
				m.setId(id);
				m.setName(name);
				m.setPic(pic);
				m.setPrice(price);
				m.setRemark(remark);
				m.setTypeId(typeId);
				
				list.add(m);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	public List getTableList() {
		return null;
	}

}
