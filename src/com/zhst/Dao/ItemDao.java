package com.zhst.Dao;

import java.util.ArrayList;
import java.util.List;

import com.zhst.Bean.Item;


public interface ItemDao extends BaseDao{
	public void save(Item file);
	
	public ArrayList<Item> getAllHomework();
	
	public int getCountItemByUser(int userId, boolean isTeacher, int courseid);
	
	public List<Item> getItemByUser(int start, int pagesize, int userId,
			boolean isTeacher, int courseid);
	
	
}
