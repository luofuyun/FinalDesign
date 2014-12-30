package com.zhst.Service;

import java.util.ArrayList;
import java.util.List;

import com.zhst.Bean.Arrange;
import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Item;
import com.zhst.Bean.Submit;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;


@SuppressWarnings("rawtypes")
public interface ItemService extends BaseService{
	public void upHomework();
	public void downloadHomework();
	public List<Item> getAllItem();
	public List<Arrange> getUserArranges(int userId);
	public List<Arrange> getCourseArranges(int courseId);
	public List<Arrange> getCourseArranges(int courseId,int userId);
	
//	public List<Item> getTeacherItems(int teacherId);
	
	public List<Arrange> getTeacherArranges(int teacherid);
	public void addPatchArranges(String[] itemId,String[] classCourseId,ArrayList date);
	public List<Submit> getArrangeSubmits(int id); 
	public List<User_Class> getArrangeClassUser(int classCourseId);
	public List<User> getUnsubmitUser(List<User_Class> u_c, List<Submit> submits);
	public List<Class_Course> getUserCourse(int teacherId);
	public void giveScoreRandom(int minindex, int maxindex, int minScore,
			int maxScore, int arrangeId, String[] accountId);
	
	public int hasSubmitCount(List<Arrange> arrange);
	public List<Arrange> getUserSubmitArranges(List<Arrange> arrange,int option);
	public Submit getUserSubmit(int arrangeid,int userid);
	
	public int getCountItemByUser(int userId, boolean isTeacher, int courseid);
	
	public List<Item> getItemByUser(int start, int pagesize, int userId,
			boolean isTeacher, int courseid);
	
	public List<Item> getItemByUser(int userId);
	
	public boolean delItem(Item item);
	
	public int getCountDefaultItems();

	public List<Item> getDefaultItems(int start,int pageSize);
	
	public List<Item> getDefaultItems();
}
