package com.zhst.Service.Impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhst.Bean.Arrange;
import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Item;
import com.zhst.Bean.Submit;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;
import com.zhst.Dao.ItemDao;
import com.zhst.Service.ItemService;


@Service("itemService")
@SuppressWarnings("rawtypes")
public class ItemServiceImpl extends BaseServiceImpl implements ItemService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    @Qualifier("ItemDao")
	private ItemDao itemDao;
	

	public ItemDao getItemdao() {
		return itemDao;
	}

	public void setItemdao(ItemDao itemdao) {
		this.itemDao = itemdao;
	}

	public void save(Item file) {
		this.getItemdao().save(file);
	}
	
	@Override
	public void upHomework() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadHomework() {
	}

	@Override
	public List<Item> getAllItem() {
		return itemDao.find("from Item where valid=1");
	}

	@SuppressWarnings({ "unchecked" })
	public List<Arrange> getUserArranges(int userId) {
		String hql="from User_Class where user = "+userId/*+" and valid = 1"*/;
		List<User_Class> userClass=itemDao.find(hql);		
		Iterator itUserClass=userClass.iterator();
		List<Class_Course> classCourse=new ArrayList(); 
		while(itUserClass.hasNext()){
			User_Class u_c=(User_Class)itUserClass.next();
			String hql1="from Class_Course where courseClass = "+u_c.getClas().getClassId()/*+" and valid = 1"*/;
			List<Class_Course> c_c=itemDao.find(hql1);
			classCourse.addAll(c_c);
		}							
		Iterator itClassCourse=classCourse.iterator();		
		List<Arrange> arranges = new ArrayList();
		while(itClassCourse.hasNext()){
			Class_Course c_c=(Class_Course)itClassCourse.next();
			String hql1="from Arrange where classCourse.course.courseId="+c_c.getCourse().getCourseId()+" and classCourse.courseClass.classId = "+c_c.getCourseClass().getClassId()/*+" and valid = 1"*/;
			List<Arrange> arrange=itemDao.find(hql1);
			arranges.addAll(arrange);
		}
		/*
		Iterator itarranges=arranges.iterator();
		while(itarranges.hasNext()){
			Arrange arr=(Arrange)itarranges.next();
			String hql1="from Submit where student = "+userId+" and arrange = "+arr.getArrangeId();
			Integer size=itemdao.find(hql1).size();
			if(size > 0){
				arr.setHasSubmit(1);
				System.out.println(arr.getArrangeId()+"  size");
			}
		}*/
		return arranges;
	}

	@Override
	public List<Arrange> getCourseArranges(int courseId) {
		String hql="from Arrange where courseId = "+courseId+" and valid = 1 ";
		List<Arrange> courseArranges=itemDao.find(hql);
		return courseArranges;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Arrange> getCourseArranges(int courseId,int userId) {
		String hql="from Arrange where course = "+courseId;
		List<Arrange> courseArranges=itemDao.find(hql);
		Iterator itarranges=courseArranges.iterator();
		while(itarranges.hasNext()){
			Arrange arr=(Arrange)itarranges.next();
			String hql1="from Submit where student = "+userId+" and arrange = "+arr.getArrangeId()+" order by submitId desc ";
			List<Submit> submitlist=itemDao.find(hql1);
			Integer size=submitlist.size();
			if(size > 0){
				arr.setHasSubmit(1);
				if(submitlist.get(0).getScore() != -1)
					arr.setScore(String.valueOf(submitlist.get(0).getScore()));
				else
					arr.setScore("--");
				SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
				arr.setSubmitTime(sim.format(submitlist.get(0).getSubmitTime()));
				System.out.println(arr.getArrangeId()+"  size");
			}else{
				arr.setScore("--");
				arr.setSubmitTime("--");
			}	
		}
		return courseArranges;
	}

	/**
	 * 返回所有管理员添加的所有默认作业
	 */
	public List<Item> getDefaultItems(int start,int pageSize) {
		String hql="from Item where creator.role=1 and isDefault=1 order by course.courseName";
		List<Item> defaultItems=itemDao.find(hql,start,pageSize);
		return defaultItems;
	}

	/**
	 * 返回所有管理员添加的所有默认作业(不分页)
	 */
	public List<Item> getDefaultItems() {
		String hql="from Item where creator.role=1 and isDefault=1 order by course.courseName";
		List<Item> defaultItems=itemDao.find(hql);
		return defaultItems;
	}
	
	/**
	 * 返回所有管理员添加的所有默认作业的记录数
	 */
	@Override
	public int getCountDefaultItems() {
		String queryStr = "from Item t where t.creator.role=1 and t.isDefault=1 order by t.course.courseName";
		return itemDao.getTotalCount(queryStr);
	}
	
	@Override
	public void addPatchArranges(String[] itemId, String[] classCourseId,ArrayList date) {
		Iterator itdate=date.iterator();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<itemId.length;i++){ 
			String s=itdate.next().toString();
			for(int j=0;j<classCourseId.length;j++){
				Arrange arrange = new Arrange();
				int id=Integer.parseInt(itemId[i]);
				Item item=(Item)itemDao.get(Item.class, id);
				int classCourse=Integer.parseInt(classCourseId[j]);
				Class_Course c_c=itemDao.get(Class_Course.class,classCourse);
				arrange.setItem(item);
				arrange.setClassCourse(c_c);
				arrange.setStartTime(new Date());
				try {
					arrange.setDeadlineDate(formatDate.parse(s));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				itemDao.save(arrange);
			}
		}
		
	}

	@Override
	public List<Arrange> getTeacherArranges(int teacherId) {
		String hql="from Arrange where creater="+teacherId+" and valid=1";
		return itemDao.find(hql);
	}

	@Override
	public List<Submit> getArrangeSubmits(int id) {
		String hql="from Submit where arrange="+id+" and valid=1 order by score,student.userName";
		return itemDao.find(hql);
	}

	@Override
	public List<User_Class> getArrangeClassUser(int classCourseId) { 
		Class_Course c_c=itemDao.get(Class_Course.class, classCourseId);
		System.out.println("test:"+c_c.getClassCourseId()+"   "+c_c.getCourseClass().getClassId());
		String hql="from User_Class where clas="+c_c.getCourseClass().getClassId()+" and valid=1";
		return itemDao.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUnsubmitUser(List<User_Class> u_c, List<Submit> submits) {
		List<User> user = new ArrayList();
		Iterator itsubmit=submits.iterator();
		while(itsubmit.hasNext()){
			Submit submit=(Submit) itsubmit.next();
			Iterator ituc=u_c.iterator();
			while(ituc.hasNext()){
				User_Class uc=(User_Class) ituc.next();
				if(submit.getStudent().getUserId()==uc.getUser().getUserId()){
					ituc.remove();
					break;
				}
			}
		}
		Iterator ituc2=u_c.iterator();
		while(ituc2.hasNext()){
			User_Class uc=(User_Class)ituc2.next();
			user.add(uc.getUser());
			System.out.println("username:"+uc.getUser().getUserName());
		}
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Class_Course> getUserCourse(int teacherId) {
		String hql="from User_Class where user="+teacherId+" and valid =1";
		List<User_Class> u_c=itemDao.find(hql);
		Iterator itu_c=u_c.iterator();
		List<Class_Course> c_c=new ArrayList();
		while(itu_c.hasNext()){
			User_Class uc=(User_Class) itu_c.next();
			String hql1="from Class_Course where courseClass="+uc.getClas().getClassId()+" and valid=1";
			List<Class_Course> cc=itemDao.find(hql1);
			c_c.addAll(cc);
		}
		return c_c;
	}

	@Override
	public void giveScoreRandom(int minindex, int maxindex, int minScore,
			int maxScore, int arrangeId,String[] accountId) {
		for(int i=minindex-1;i<maxindex;i++){
			String hql="from Submit where student.accountId="+accountId[i]+" and arrange.arrangeId="+arrangeId+" and valid=1";
			List<Submit> submits=itemDao.find(hql);
			Iterator itsubmits=submits.iterator();
			Submit submit = (Submit) itsubmits.next();
			Random random=new Random();
			int s = random.nextInt(maxScore)%(maxScore-minScore) + minScore;
			System.out.println("score:"+s);
			submit.setScore(s);
			submit.setScoreTime(new Date());
			itemDao.update(submit);
		}
	}
	
	@Override
	public int hasSubmitCount(List<Arrange> arrange) {
		int hassubmitcout=0;
		for(int i=0;i<arrange.size();i++)
		{
			if(arrange.get(i).getHasSubmit()==1)
				hassubmitcout++;
		}
		return hassubmitcout;
	}

	@Override
	public List<Arrange> getUserSubmitArranges(List<Arrange> arrange, int option) {
		for(int i=0;i<arrange.size();i++)
		{
			System.out.println("第"+i+"个======"+arrange.get(i).getHasSubmit());
			if(option==1){
				if(arrange.get(i).getHasSubmit()==1)
					{
						arrange.remove(i);
						i--;
					}
			}else if(option==2){
				if(arrange.get(i).getHasSubmit()==0)
					{
						arrange.remove(i);
						i--;
					}
			}else
				return arrange;
		}
		return arrange;
	}

	@Override
	public Submit getUserSubmit(int arrangeid, int userid) {
	String hql1="from Submit where student = "+userid+" and arrange = "+arrangeid+" order by submitId desc ";
		List<Submit> submitlist=itemDao.find(hql1);
		Integer size=submitlist.size();
		if(size > 0){
			return submitlist.get(0);
		}else{
			Submit submit=new Submit();
			submit.setAnswer("");
			submit.setFileName("");
			return submit;
		}	
	}

	/**
	 * 根据用户（管理员，教师）获得个人题库的数目
	 * 教师的个人题库加上课程条件
	 */
	@Override
	public int getCountItemByUser(int userId, boolean isTeacher, int courseid) {
		return itemDao.getCountItemByUser(userId,isTeacher,courseid);
	}

	/**
	 * 根据用户（管理员，教师）获得个人题库的记录
	 * 教师的个人题库加上课程条件
	 */
	@Override
	public List<Item> getItemByUser(int start, int pagesize, int userId,
			boolean isTeacher, int courseid) {
		return itemDao.getItemByUser(start,pagesize,userId,isTeacher,courseid);
	}

	/**
	 * 根据用户（管理员，教师）获得个人题库的记录（不分页）
	 */
	@Override
	public List<Item> getItemByUser(int userId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Item where creator.userId=? and isDefault=0 group by createDate order by course.courseName");
		params.add(userId);
//		String hql="from Item where creator = "+teacherId+" and isDefault=0 group by createDate";
		List<Item> teacherItems = itemDao.find(queryString.toString(),params);
		return teacherItems;
	}
	
	@Override
	public boolean delItem(Item item) {
		itemDao.delete(item);
		return true;
	}

}
