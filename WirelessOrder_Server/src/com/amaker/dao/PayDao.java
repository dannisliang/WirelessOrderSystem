package com.amaker.dao;

import java.util.List;

import com.amaker.entity.QueryOrder;

public interface PayDao {

	public QueryOrder getOrderById(int id);

	public List getOrderDetailList(int id);

	public void pay(int id);
}
