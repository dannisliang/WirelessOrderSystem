package com.amaker.dao;

import java.util.List;

public interface UnionTableDao {

	public  List getTableList();

	public void updateStatus(int tableId1,int tableId2);
}
