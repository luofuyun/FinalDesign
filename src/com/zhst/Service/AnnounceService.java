package com.zhst.Service;

import java.util.List;

import com.zhst.Bean.Announce;
import com.zhst.Bean.Info;
import com.zhst.Bean.User;

public interface AnnounceService extends BaseService{

	/**
	 * 获得公告信息
	 * @param start 分页起始页
	 * @param pagesize 页大小
	 * @param status 公告状态，用于筛选
	 * @param username 根据内容筛选（暂不用）
	 * @param creator 创建者（当前登录用户）
	 * @return
	 */
	List<Info> getInfoItem(int start, int pagesize, String status, String username,User creator);

	/**
	 * 获得公告数目
	 * @param creator 创建者（当前登录用户）
	 * @return
	 */
	int getInfoCount(User creator);

	/**
	 * 根据公告id删除公告
	 * @param announceId 公告id
	 * @return
	 */
	boolean delInfo(String infoId);

	/**
	 * 根据公告id获得公告
	 * @param announceId 公告id
	 * @return
	 */
	public Info getInfoByInfoId(String infoId);
}
