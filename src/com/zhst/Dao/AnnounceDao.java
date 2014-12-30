package com.zhst.Dao;

import com.zhst.Bean.Info;

public interface AnnounceDao extends BaseDao{
	
	/**
	 * 获得公告
	 * @param announceId
	 * @return
	 */
	public Info getInfoByInfoId(String announceId);
	
	/**
	 * 删除公告
	 * @param info
	 * @return
	 */
	public boolean delInfo(Info info);
}
