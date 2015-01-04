package com.amaker.dao;

import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
public interface OrderDao {

	public int saveOrder(Order o );

	public void saveOrderDetail(OrderDetail od);

	public void updateTableStatus(int tableId);

	public void updateTableStatus2(int orderId);
}
