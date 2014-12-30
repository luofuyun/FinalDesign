package com.zhst.Service;

import java.util.List;
import java.util.Map;

import com.zhst.Bean.Course;
import com.zhst.Bean.User;

public interface UserService extends BaseService{

	void updateUser(User user);

	User getUserById(int accountId);

	List<User> getAllUser();

	User getUserByAccountId(String accountId);

	boolean delUser(String accountId);

	int getUserCount();

	 List<User>  getUserItem(int start, int pagesize,String type,String username);

	int getStudentCountByTeacher(int teacherid,int courseid);
	
    int getStudentCountByTeacherAndName(int teacherid,StringBuffer courseidBuff,String userName);

	List<Map<String,Object>> getStudentItem(int start, int pagesize, int teacherid,int courseId);

	public List<Map<String,Object>> getStudentItem(int start, int pagesize, int teacherid,StringBuffer courseidBuff,String userName);
	
	Course getCourseByStudentClass(int studentId);
	//富允
	public boolean hasMatchUser(String accountId, String password);

	boolean delUserInClass(String userId, String classId, int teacherId);

	boolean resetPassword(String userId);
	
}
