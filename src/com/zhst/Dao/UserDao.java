package com.zhst.Dao;

import java.util.List;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;

public interface UserDao extends BaseDao{

	void updateUser(User user);

	User getUserById(Class<User> class1, int accountId);

	List<User> getAllUser();

	User getUserByAccountId(String accountId);

	boolean delUser(User user);

	List<Semester_Course> getCourseByTeacherId(int teacherid);

	List<Class_Course> getClassByCourseId(int teacherid,int courseId);

	List<User_Class> getStudentByClassId(StringBuffer classIdBuff,int start,int pageSize,boolean isGetCount);

	List<User_Class> getClassByClassIdAndName(StringBuffer classIdBuff,String userName,int start,int pageSize,boolean isGetCount);
	
	List<User_Class> getStudentByClassId(int id,int start,int pageSize,boolean isGetCount);
	//富允
	public int getMatchCount(String accountId,String password);

	List<User_Class> getUserClassByTwoId(int classId, int userId);
}
